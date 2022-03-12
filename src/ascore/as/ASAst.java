package ascore.as;

import ascore.as.erreurs.ASErreur;
import ascore.as.lang.ASType;
import ascore.as.lang.datatype.ASEntier;
import ascore.ast.Ast;
import ascore.ast.buildingBlocs.Expression;
import ascore.ast.buildingBlocs.Programme;
import ascore.ast.buildingBlocs.exemple.expressions.ExprEntier;
import ascore.ast.buildingBlocs.expressions.AppelFonc;
import ascore.ast.buildingBlocs.expressions.Argument;
import ascore.ast.buildingBlocs.expressions.CreerListe;
import ascore.ast.buildingBlocs.expressions.Var;
import ascore.ast.buildingBlocs.programmes.CreerFonction;
import ascore.ast.buildingBlocs.programmes.Declarer;
import ascore.ast.buildingBlocs.programmes.Echo;
import ascore.ast.buildingBlocs.programmes.FinFonction;
import ascore.executeur.Executeur;
import ascore.generateurs.ast.AstGenerator;
import ascore.tokens.Token;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * Classe o\u00F9 sont d\u00E9finis les programmes et les expressions support\u00E9s par le
 * langage
 *
 * @author Mathis Laroche
 */
public class ASAst extends AstGenerator {
    private final Executeur executeurInstance;

    public ASAst(Executeur executeurInstance) {
        reset();
        this.stmtSeparator = "SEMI_COLON";
        ajouterProgrammes();
        ajouterExpressions();
        this.executeurInstance = executeurInstance;
    }

    protected void ajouterProgrammes() {
        // ajouter vos programmes ici
        ajouterProgramme("expression", (p, __) -> Programme.evalExpression((Expression<?>) p.get(0), "expr"));
        ajouterProgramme("ECHO expression", (p, __) -> new Echo((Expression<?>) p.get(1), executeurInstance));
        ajouterProgramme("VARIABLE ASSIGN expression", (p, __) ->
                new Declarer(new Var(((Token) p.get(0)).getValeur()), (Expression<?>) p.get(2), null, false)
        );
        ajouterProgramme("FUNCTION FUNCTION_NAME PARENT_OPEN expression PARENT_CLOSE COLON",
                new Ast<CreerFonction>(
                        Map.entry("VARIABLE COLON expression ASSIGN expression~"
                                  + "VARIABLE ASSIGN expression~"
                                  + "VARIABLE COLON expression",
                                new Ast<Argument>(19) {
                                    @Override
                                    public Argument apply(List<Object> p, Integer variante) {
                                        ASType type = new ASType("tout");
                                        Expression<?> valParDefaut = null;
                                        p.set(0, new Var(((Token) p.get(0)).getValeur()));
                                        if (!(p.get(0) instanceof Var var)) {
                                            throw new ASErreur.ErreurSyntaxe("Une d\u00E9claration de fonction doit commencer par une variable, pas par " + p.get(0));
                                        }

                                        Token deuxPointsToken = (Token) p.stream()
                                                .filter(t -> t instanceof Token token && token.getNom().equals("COLON"))
                                                .findFirst()
                                                .orElse(null);
                                        if (deuxPointsToken != null) {
                                            Expression<?> typeObj = (Expression<?>) p.get(p.indexOf(deuxPointsToken) + 1);
                                            if (!(typeObj instanceof ASType)) {
                                                String nom;
                                                if (p.get(0) instanceof Var) {
                                                    nom = ((Var) typeObj).getNom();
                                                } else {
                                                    nom = typeObj.eval().toString();
                                                }
                                                throw new ASErreur.ErreurType("Le symbole ':' doit \u00EAtre suivi d'un type valide ('" + nom + "' n'est pas un type valide)");
                                            }
                                            type = (ASType) typeObj;
                                        }
                                        Token assignementToken = (Token) p.stream()
                                                .filter(t -> t instanceof Token token && token.getNom().equals("ASSIGN"))
                                                .findFirst()
                                                .orElse(null);
                                        if (assignementToken != null) {
                                            valParDefaut = (Expression<?>) p.get(p.indexOf(assignementToken) + 1);
                                        }

                                        return new Argument(var, valParDefaut, type);
                                    }
                                })
                ) {
                    @Override
                    public CreerFonction apply(List<Object> p, Integer variante) {
                        Argument[] params = new Argument[]{};

                        ASType typeRetour = p.get(p.size() - 1) instanceof ASType type ? type : new ASType("tout");

                        if (p.get(p.size() - 1) == null && p.get(3) instanceof ASType type) {
                            typeRetour = type;
                            return new CreerFonction((Var) p.get(1), params, typeRetour, executeurInstance);
                        }

                        if (p.get(3) != null && !(p.get(3) instanceof Token)) {
                            if (p.get(3) instanceof CreerListe.Enumeration enumeration) {
                                params = enumeration.getExprs()
                                        .stream()
                                        .map(expr -> expr instanceof Argument arg
                                                ? arg
                                                : new Argument((Var) expr, null, null))
                                        .toArray(Argument[]::new);
                            } else if (p.get(3) instanceof Argument arg) {
                                params = new Argument[]{arg};
                            } else {
                                params = new Argument[]{
                                        new Argument((Var) p.get(3), null, null)
                                };
                            }
                        }

                        return new CreerFonction(new Var(((Token) p.get(1)).getValeur()), params, typeRetour, executeurInstance);
                    }
                });

        ajouterProgramme("GREEK_SEMI_COLON", (p, __) -> new FinFonction(executeurInstance));
    }

    protected void ajouterExpressions() {
        // ajouter vos expressions ici
        ajouterExpression("VARIABLE~FUNCTION_NAME", (p, __) -> new Var(((Token) p.get(0)).getValeur()));
        ajouterExpression("INT", (p, __) -> new ExprEntier(new ASEntier((Token) p.get(0))));
        ajouterExpression("expression PARENT_OPEN #expression PARENT_CLOSE~"
                          + "expression PARENT_OPEN PARENT_CLOSE",
                (p, __) -> {
                    if (p.size() == 3) {
                        return new AppelFonc((Expression<?>) p.get(0), new CreerListe());
                    }
                    Hashtable<String, Ast<?>> astParams = new Hashtable<>();

                    Expression<?> contenu = evalOneExpr(new ArrayList<>(p.subList(2, p.size() - 1)), astParams);

                    CreerListe args = contenu instanceof CreerListe.Enumeration enumeration ?
                            enumeration.buildCreerListe() :
                            new CreerListe(contenu);

                    return new AppelFonc((Expression<?>) p.get(0), args);
                });
    }
}


























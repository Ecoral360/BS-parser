package ascore.as;

import ascore.as.lang.datatype.ASEntier;
import ascore.ast.buildingBlocs.Expression;
import ascore.ast.buildingBlocs.exemple.expressions.ExprEntier;
import ascore.ast.buildingBlocs.expressions.Var;
import ascore.ast.buildingBlocs.programmes.Declarer;
import ascore.ast.buildingBlocs.programmes.Echo;
import ascore.executeur.Executeur;
import ascore.generateurs.ast.AstGenerator;
import ascore.tokens.Token;


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
        ajouterProgrammes();
        ajouterExpressions();
        this.executeurInstance = executeurInstance;
    }

    protected void ajouterProgrammes() {
        // ajouter vos programmes ici
        ajouterProgramme("ECHO expression", (p, __) -> new Echo((Expression<?>) p.get(1), executeurInstance));
        ajouterProgramme("expression ASSIGN expression", (p, __) ->
                new Declarer((Expression<?>) p.get(0), (Expression<?>) p.get(2), null, false)
        );
    }

    protected void ajouterExpressions() {
        // ajouter vos expressions ici
        ajouterExpression("VARIABLE", (p, __) -> new Var(((Token) p.get(0)).getValeur()));
        ajouterExpression("INT", (p, __) -> new ExprEntier(new ASEntier((Token) p.get(0))));
    }
}


























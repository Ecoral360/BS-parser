package ascore.ast.buildingBlocs.programmes;

import ascore.as.lang.*;
import ascore.as.lang.datatype.ASFonction;
import ascore.as.lang.managers.ASFonctionManager;
import ascore.ast.buildingBlocs.Programme;
import ascore.ast.buildingBlocs.expressions.Argument;
import ascore.ast.buildingBlocs.expressions.Var;
import ascore.executeur.Coordonnee;
import ascore.executeur.Executeur;
import ascore.tokens.Token;

import javax.lang.model.type.NullType;
import java.util.Arrays;
import java.util.List;


public class CreerFonction extends Programme {
    private final ASScope scope;
    private final Var var;
    private final List<Argument> args;
    private final ASType typeRetour;

    public CreerFonction(Var var, Argument[] args, ASType typeRetour, Executeur executeurInstance) {
        super(executeurInstance);
        this.var = var;
        this.args = Arrays.asList(args);
        this.typeRetour = typeRetour;
        // declare fonction
        ASScope.getCurrentScope().declarerVariable(new ASVariable(var.getNom(), null, new ASType("fonctionType")));
        this.scope = ASScope.makeNewCurrentScope();
    }

    @Override
    public NullType execute() {
        ASScope scope = new ASScope(this.scope);
        ASFonction fonction = new ASFonction(var.getNom(), this.args.stream().map(Argument::eval).toArray(ASParametre[]::new), this.typeRetour, executeurInstance);

        ASScope.getCurrentScopeInstance().getVariable(fonction.getNom()).changerValeur(fonction);
        // declare fonction
        // Scope.getCurrentScope().declarerVariable(new ASObjet.Variable(fonction.getNom(), fonction, new Type(fonction.obtenirNomType())));

        // declare params
        for (Argument arg : this.args) {
            ASParametre param = arg.eval();
            scope.declarerVariable(new ASVariable(param.getNom(), param.getValeurParDefaut(), param.getType()));
            //ASObjet.VariableManager.ajouterVariable(new ASObjet.Variable(param.getNom(), param.getValeurParDefaut(), param.getType()), scopeName);
        }

        fonction.setScope(scope);
        scope.setParent(ASScope.getCurrentScopeInstance());

        return null;
    }

    @Override
    public Coordonnee prochaineCoord(Coordonnee coord, List<Token> ligne) {
        return new Coordonnee(executeurInstance.nouveauScope("fonc_" + ASFonctionManager.ajouterDansStructure(this.var.getNom())));
    }

    @Override
    public String toString() {
        return "CreerFonction{" +
                "nom=" + var +
                ", args=" + args +
                ", typeRetour?=" + typeRetour +
                '}';
    }
}

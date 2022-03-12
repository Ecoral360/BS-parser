package ascore.ast.buildingBlocs.programmes;

import ascore.as.erreurs.ASErreur;
import ascore.as.lang.ASScope;
import ascore.as.lang.datatype.ASNul;
import ascore.ast.buildingBlocs.Programme;
import ascore.executeur.Coordonnee;
import ascore.executeur.Executeur;
import ascore.tokens.Token;

import java.util.List;

public class FinFonction extends Programme {

    public FinFonction(Executeur executeurInstance) {
        super(executeurInstance);
        ASScope.popCurrentScope();
    }

    @Override
    public ASNul execute() {
        return new ASNul();
    }

    @Override
    public Coordonnee prochaineCoord(Coordonnee coord, List<Token> ligne) {
        if (!coord.getScope().startsWith("fonc_"))
            throw new ASErreur.ErreurFermeture(coord.getScope(), "fin fonction");
        return new Coordonnee(executeurInstance.finScope());
    }

    @Override
    public String toString() {
        return "FinFonction";
    }
}

package ascore.ast.buildingBlocs.expressions;

import ascore.as.lang.datatype.ASNul;
import ascore.as.lang.datatype.ASObjet;
import ascore.ast.buildingBlocs.Expression;

/**
 * Squelette de l'impl\u00E9mentation d'une expression.<br>
 * Pour trouver un exemple d'une impl\u00E9mentation compl\u00E8te, voir {@link Var}
 *
 * @author Mathis Laroche
 * @see Var
 */
public class ExprExemple implements Expression<ASObjet<?>> {

    /**
     * Appel\u00E9 durant le Runtime, cette m\u00E9thode retourne un objet de type ASObjet
     *
     * @return le r\u00E9sultat de l'expression
     */
    @Override
    public ASObjet<?> eval() {
        return new ASNul();
    }
}

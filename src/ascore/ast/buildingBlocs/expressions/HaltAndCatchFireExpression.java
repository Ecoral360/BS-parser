package ascore.ast.buildingBlocs.expressions;

import ascore.as.erreurs.HaltAndCatchFire;
import ascore.as.lang.datatype.ASObjet;
import ascore.ast.buildingBlocs.Expression;

public record HaltAndCatchFireExpression() implements Expression<ASObjet<Object>> {

    @Override
    public ASObjet<Object> eval() {
        throw new HaltAndCatchFire();
    }
}

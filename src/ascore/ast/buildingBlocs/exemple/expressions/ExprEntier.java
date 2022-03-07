package ascore.ast.buildingBlocs.exemple.expressions;

import ascore.as.lang.datatype.ASEntier;
import ascore.ast.buildingBlocs.Expression;


public record ExprEntier(ASEntier val) implements Expression<ASEntier> {

    @Override
    public ASEntier eval() {
        return val;
    }

    @Override
    public String toString() {
        return "ExprEntier{" +
                "val=" + val +
                '}';
    }
}

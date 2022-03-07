package ascore.ast.buildingBlocs.exemple.expressions;

import ascore.as.lang.datatype.ASEntier;
import ascore.ast.buildingBlocs.Expression;

public record Addition(ExprEntier nb1, ExprEntier nb2) implements Expression<ASEntier> {
    @Override
    public ASEntier eval() {
        return new ASEntier((nb1.eval()).getValue() + (nb2.eval()).getValue());
    }
}

package ascore.ast.buildingBlocs.exemple.expressions;

import ascore.as.lang.datatype.ASTexte;
import ascore.ast.buildingBlocs.Expression;

public record ExprTexte(ASTexte val) implements Expression<ASTexte> {

    @Override
    public ASTexte eval() {
        return val;
    }

    @Override
    public String toString() {
        return "ExprTexte{" +
                "val=" + val +
                '}';
    }
}

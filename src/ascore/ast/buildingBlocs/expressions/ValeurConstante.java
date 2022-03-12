package ascore.ast.buildingBlocs.expressions;

import ascore.as.lang.datatype.ASObjet;
import ascore.as.lang.datatype.ASTexte;
import ascore.ast.buildingBlocs.Expression;

public record ValeurConstante(ASObjet<?> val) implements Expression<ASObjet<?>> {

    @Override
    public ASObjet<?> eval() {
        return val;
    }

    @Override
    public String toString() {
        return "ValeurConstante{" +
               "val=" + (val instanceof ASTexte ? "'" + val.toString().replaceAll("'", "\\\\'") + "'" : val) +
               '}';
    }
}



























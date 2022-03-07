package ascore.ast.buildingBlocs;

import ascore.as.lang.datatype.ASObjet;

import java.io.Serializable;

/**
 * Une expression est un objet cr\u00E9e au <i>Compile time</i> et \u00E9valu\u00E9 au <i>Runtime</i> par un
 * {@link Programme}
 *
 * @param <T> le type de l'objet retourn\u00E9 par l'expression lorsqu'\u00E9valuer
 * @author Mathis Laroche
 */
public interface Expression<T extends ASObjet<?>> extends Serializable {

    /**
     * Appel\u00E9 durant le <i>Runtime</i>, cette m\u00E9thode retourne un objet de type {@link ASObjet}
     *
     * @return le r\u00E9sultat de l'expression
     */
    T eval();

    class ExpressionVide implements Expression<ASObjet<?>> {

        @Override
        public ASObjet<?> eval() {
            return null;
        }
    }
}

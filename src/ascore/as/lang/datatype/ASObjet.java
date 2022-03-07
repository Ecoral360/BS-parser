package ascore.as.lang.datatype;


/**
 * Interface commune \u00e0 <b><i>tous</i></b> les objets du langage
 *
 * @param <T>
 * @author Mathis Laroche
 */
public interface ASObjet<T> {

    T getValue();

    /**
     * Repr\u00E9sentation de l'objet en tant que bool\u00E9en
     *
     * @return si l'objet consid\u00E8re qu'il vaut <code>true</code> ou <code>false</code>
     */
    boolean boolValue();

    /**
     * Va \u00EAtre chang\u00E9 pour retourner un objet ASType
     *
     * @return un string repr\u00E9sentant le type de l'objet
     */
    String obtenirNomType();

}




















package ascore.as.lang.datatype;

import ascore.as.erreurs.ASErreur;

import java.util.Iterator;
import java.util.List;


/**
 * Interface commune \u00e0 tous les iterables du langage.
 *
 * @param <T>
 * @author Mathis Laroche
 */
public interface ASIterable<T> extends ASObjet<T> {
    boolean contient(ASObjet<?> element);

    ASIterable<T> sousSection(int debut, int fin);

    ASObjet<?> get(int index);

    int taille();

    Iterator<ASObjet<?>> iter();

    default int idxRelatif(List<?> valeur, int idx) {
        if (Math.abs(idx) > valeur.size()) {
            throw new ASErreur.ErreurIndex("l'index est trop grand");
        }
        idx = (idx < 0) ? valeur.size() + idx : idx;
        return idx;
    }

    @Override
    default String obtenirNomType() {
        return "iterable";
    }
}

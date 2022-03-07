package ascore.as.modules.builtins;

import ascore.as.lang.ASConstante;
import ascore.as.lang.ASFonctionModule;
import ascore.as.lang.ASVariable;
import ascore.as.modules.core.ASModule;
import ascore.executeur.Executeur;

/**
 * Classe o\u00F9 sont d\u00E9finis les {@link ASFonctionModule fonctions} et les
 * {@link ASVariable variables}/{@link ASConstante constantes} builtin
 *
 * @author Mathis Laroche
 */
public class ModuleBuiltin {

    public static ASModule charger(Executeur executeurInstance) {
        var fonctionsBuiltin = new ASFonctionModule[]{
                // ajouter vos fonctions builtin ici
        };
        var variablesBuiltin = new ASVariable[]{
                // ajouter vos variables et vos constantes builtin ici
        };

        return new ASModule(fonctionsBuiltin, variablesBuiltin);
    }
}

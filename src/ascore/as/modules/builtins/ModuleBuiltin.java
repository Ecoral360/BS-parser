package ascore.as.modules.builtins;

import ascore.as.lang.*;
import ascore.as.lang.datatype.ASEntier;
import ascore.as.lang.datatype.ASListe;
import ascore.as.lang.datatype.ASObjet;
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
                new ASFonctionModule("len", ASTypeBuiltin.rien, new ASParametre[]{
                        new ASParametre(ASTypeBuiltin.liste, "obj", null)
                }) {
                    @Override
                    public ASObjet<?> executer() {
                        return new ASEntier(((ASListe) this.getValeurParam("obj")).taille());
                    }
                }
        };
        var variablesBuiltin = new ASVariable[]{
                // ajouter vos variables et vos constantes builtin ici
        };

        return new ASModule(fonctionsBuiltin, variablesBuiltin);
    }
}

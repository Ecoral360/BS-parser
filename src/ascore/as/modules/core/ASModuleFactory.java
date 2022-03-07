package ascore.as.modules.core;

import ascore.executeur.Executeur;

@FunctionalInterface
public interface ASModuleFactory {

    ASModule charger(Executeur executeurInstance);

}

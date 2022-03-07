package ascore.as.modules;

import ascore.as.modules.builtins.ModuleBuiltin;
import ascore.as.modules.core.ASModuleManager;
import ascore.as.modules.core.ASModuleFactory;

public enum EnumModule {
    builtins(ModuleBuiltin::charger),
    ;

    EnumModule(ASModuleFactory moduleFactory) {
        ASModuleManager.enregistrerModule(this, moduleFactory);
    }
}

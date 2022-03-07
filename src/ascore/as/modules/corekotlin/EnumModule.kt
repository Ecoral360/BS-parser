package ascore.`as`.modules.corekotlin

import ascore.`as`.modules.EnumModule
import ascore.`as`.modules.core.ASModuleFactory
import ascore.`as`.modules.core.ASModuleManager

enum class EnumModule(moduleFactory: ASModuleFactory?) {
    ;

    init {
        ASModuleManager.enregistrerModule(EnumModule.valueOf(this.toString()), moduleFactory)
    }
}
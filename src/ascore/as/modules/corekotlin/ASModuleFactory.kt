package ascore.`as`.modules.corekotlin

import ascore.`as`.lang.ASFonctionModule
import ascore.`as`.lang.ASParametre
import ascore.`as`.lang.ASTypeBuiltin
import ascore.`as`.lang.datatype.ASObjet
import ascore.executeur.Executeur

interface ASModuleFactory {
    fun charger(executeurInstance: Executeur): ASModule

    fun fonction(
        nom: String,
        typeRetour: ASTypeBuiltin,
        parametres: Array<ASParametre>,
        effet: (ASFonctionModule) -> ASObjet<*>
    ): ASFonctionModule {
        return object : ASFonctionModule(nom, typeRetour, parametres) {
            override fun executer(): ASObjet<*> {
                return effet(this)
            }
        }
    }

    fun fonction(
        nom: String,
        typeRetour: ASTypeBuiltin,
        effet: (ASFonctionModule) -> ASObjet<*>
    ): ASFonctionModule {
        return object : ASFonctionModule(nom, typeRetour, arrayOf()) {
            override fun executer(): ASObjet<*> {
                return effet(this)
            }
        }
    }
}
package ascore.`as`.modules.corekotlin

import ascore.`as`.erreurs.ASErreur
import ascore.`as`.lang.ASParametre
import ascore.`as`.lang.ASScope
import ascore.`as`.lang.ASTypeBuiltin
import ascore.`as`.lang.ASVariable
import ascore.`as`.lang.datatype.ASNul
import ascore.`as`.lang.datatype.ASTexte
import ascore.data_manager.Data
import ascore.executeur.Executeur

object ModuleBuiltin : ASModuleFactory {

    override fun charger(executeurInstance: Executeur): ASModule {
        val fonctions = arrayOf(
            fonction(
                "afficher", ASTypeBuiltin.nulType, arrayOf(
                    ASParametre(ASTypeBuiltin.texte, "txt", ASTexte(""))
                )
            ) {
                val txt = it.getValeurParam("txt")
                executeurInstance.addData(Data(Data.Id.AVERTISSEMENT).addParam(txt))
                ASNul()
            },
            fonction(
                "getVar", ASTypeBuiltin.tout, arrayOf(
                    ASParametre(ASTypeBuiltin.texte, "nomVariable", null)
                )
            ) {
                val nomVar = it.getValeurParam("nomVariable").value as String
                val variable = ASScope.getCurrentScopeInstance().getVariable(nomVar)
                    ?: throw ASErreur.ErreurVariableInconnue("La variable '$nomVar' n'est pas d\u00E9clar\u00E9e dans ce scope.")
                variable.valeurApresGetter
            }
        )
        val variables = arrayOf<ASVariable>()

        return ASModule(fonctions)
    }
}











package ascore.as.modules.core;

import ascore.as.lang.*;
import ascore.as.lang.managers.ASFonctionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe repr\u00E9sentant un module.<br>
 * Un module est un ensemble de {@link ASFonctionModule fonctions} et de
 * {@link ASVariable variables}/{@link ASConstante constantes}
 * qui, lorsqu'{@link #utiliser(String) utiliser}, sont d\u00E9clar\u00E9es dans le scope
 * pour \u00EAtre utilis\u00E9 plus loin dans le code
 *
 * @author Mathis Laroche
 */
public final record ASModule(ASFonctionModule[] fonctionModules,
                             ASVariable[] variables) {

    public ASModule(ASFonctionModule[] fonctionModules) {
        this(fonctionModules, new ASVariable[]{});
    }

    public ASModule(ASVariable[] variables) {
        this(new ASFonctionModule[]{}, variables);
    }

    public void utiliser(String prefix) {
        ASFonctionManager.ajouterStructure(prefix);
        for (ASFonctionModule fonctionModule : fonctionModules) {
            ASScope.getCurrentScope().declarerVariable(new ASVariable(fonctionModule.getNom(), fonctionModule, new ASType(fonctionModule.obtenirNomType())));
        }
        for (ASVariable variable : variables) {
            ASScope.getCurrentScope().declarerVariable(variable.clone());
        }
        ASFonctionManager.retirerStructure();
    }

    public void utiliser(List<String> nomMethodes) {
        for (ASFonctionModule fonctionModule : fonctionModules) {
            if (nomMethodes.contains(fonctionModule.getNom()))
                ASFonctionManager.ajouterFonction(fonctionModule);
        }
        for (ASVariable variable : variables) {
            if (nomMethodes.contains(variable.obtenirNom())) {
                ASScope.getCurrentScope().declarerVariable(variable);
            }
        }
    }

    /**
     * @return un array contenant toutes les fonctions du module
     */
    public ASFonctionModule[] getFonctions() {
        return fonctionModules;
    }

    /**
     * @return un array contenant toutes les variables du module
     */
    public ASVariable[] getVariables() {
        return variables;
    }

    /**
     * @return la liste des noms des fonctions du module
     */
    public List<String> getNomsFonctions() {
        if (fonctionModules.length == 0) return new ArrayList<>();
        return Stream.of(fonctionModules).map(ASFonctionModule::getNom).collect(Collectors.toList());
    }

    /**
     * @return la liste des noms des constantes du module
     */
    public List<String> getNomsVariables() {
        if (variables.length == 0) return new ArrayList<>();
        return Stream.of(variables).map(ASVariable::obtenirNom).collect(Collectors.toList());
    }

    /**
     * @return la liste des noms des constantes du module
     */
    public List<String> getNomsConstantesEtFonctions() {
        List<String> noms = getNomsFonctions();
        noms.addAll(getNomsVariables());
        return noms;
    }

    @Override
    public String toString() {
        return "Module{\n" +
                "fonctions=" + Arrays.toString(fonctionModules) + "\n" +
                ", variables=" + Arrays.toString(variables) + "\n" +
                '}';
    }
}















package ascore.as.lang;

import ascore.as.lang.datatype.ASObjet;

/**
 * Classe responsable de definir les proprietes des parametres des fonctions
 */
public record ASParametre(ASType type, String nom,
                          ASObjet<?> valeurParDefaut) implements ASObjet<Object> {
    /**
     * @param type            <li>
     *                        Nom du type du parametre (ex: <i>entier</i>, <i>texte</i>, <i>liste</i>, ect.)
     *                        </li>
     *                        <li>
     *                        le parametre peut avoir plusieurs types
     *                        -> separer chaque type par un <b>|</b> (les espaces sont ignores)
     *                        <br> (ex: <i>texte | liste</i>, <i>entier | decimal</i>)
     *                        </li>
     *                        <li>
     *                        Mettre <b>null</b> si le parametre n'a pas de type forcee
     *                        </li>
     * @param nom             <li>
     *                        Nom du parametre
     *                        </li>
     * @param valeurParDefaut <li>
     *                        Valeur de type ASObjet qui sera assigne au parametre s'il ne recoit aucune valeur lors de l'appel de la fonction
     *                        </li>
     *                        <li>
     *                        Mettre <b>null</b> pour rendre ce parametre obligatoire lors de l'appel de la fonction
     *                        </li>
     */
    public ASParametre(ASType type, String nom, ASObjet<?> valeurParDefaut) {
        this.nom = nom;
        this.type = type == null ? ASTypeBuiltin.tout.asType() : type;
        this.valeurParDefaut = valeurParDefaut;
    }

    public ASParametre(ASTypeBuiltin type, String nom, ASObjet<?> valeurParDefaut) {
        this(type.asType(), nom, valeurParDefaut);
    }

    public String getNom() {
        return nom;
    }

    public ASType getType() {
        return type;
    }

    public ASObjet<?> getValeurParDefaut() {
        return valeurParDefaut;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean boolValue() {
        return false;
    }

    @Override
    public String obtenirNomType() {
        return this.type.nom();
    }

    @Override
    public String toString() {
        return "Parametre{" +
                "nom='" + nom + '\'' +
                ", type=" + type +
                ", valeurParDefaut=" + valeurParDefaut +
                '}';
    }

}

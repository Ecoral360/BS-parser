package ascore.as.lang;

import ascore.as.erreurs.ASErreur;
import ascore.as.lang.datatype.ASObjet;
import ascore.executeur.Coordonnee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public abstract class ASFonctionModule implements ASObjet<Object> {
    private final ASType typeRetour;
    private final ASParametre[] parametres; //String[] de forme {nomDuParam�tre, typeDuParam�tre (ou null s'il n'en poss�de pas)}
    private final String nom;
    private final Coordonnee coordReprise = null;
    private Hashtable<String, ASObjet<?>> parametres_appel = new Hashtable<>();  // Object[][] de forme {{nom_param, valeur}, {nom_param2, valeur2}}
    private String scopeName;


    /**
     * @param nom        <li>
     *                   Nom de la fonction
     *                   </li>
     * @param typeRetour <li>
     *                   Nom du type de retour de la fonction (ex: <i>entier</i>, <i>texte</i>, <i>liste</i>, ect.)
     *                   </li>
     *                   <li>
     *                   le type du retour peut avoir plusieurs types
     *                   -> separer chaque type par un <b>|</b> (les espaces sont ignores)
     *                   <br> (ex: <i>texte | liste</i>, <i>entier | decimal</i>)
     *                   </li>
     *                   <li>
     *                   Mettre <b>null</b> si le type du retour n'a pas de type forcee
     * @param parametres
     */
    public ASFonctionModule(String nom, ASType typeRetour, ASParametre[] parametres) {
        this.nom = nom;
        this.scopeName = "fonc_";
        this.parametres = parametres;
        this.typeRetour = typeRetour;
    }

    /**
     * @param nom        <li>
     *                   Nom de la fonction
     *                   </li>
     * @param typeRetour <li>
     *                   Nom du type de retour de la fonction (ex: <i>entier</i>, <i>texte</i>, <i>liste</i>, ect.)
     *                   </li>
     *                   <li>
     *                   le type du retour peut avoir plusieurs types
     *                   -> separer chaque type par un <b>|</b> (les espaces sont ignores)
     *                   <br> (ex: <i>texte | liste</i>, <i>entier | decimal</i>)
     *                   </li>
     *                   <li>
     *                   Mettre <b>null</b> si le type du retour n'a pas de type forcee
     *                   </li>
     */
    public ASFonctionModule(String nom, ASType typeRetour) {
        this(nom, typeRetour, new ASParametre[0]);
    }

    public ASFonctionModule(String nom, ASTypeBuiltin typeRetour, ASParametre[] parametres) {
        this(nom, typeRetour.asType(), parametres);
    }

    public ASFonctionModule(String nom, ASTypeBuiltin typeRetour) {
        this(nom, typeRetour.asType());
    }

    public String getNom() {
        return nom;
    }

    public ASType getTypeRetour() {
        return this.typeRetour;
    }

    public ASParametre[] getParams() {
        return this.parametres;
    }

    public Hashtable<String, ASObjet<?>> getParamsValeursDict() {
        return this.parametres_appel;
    }

    public ASObjet<?> getValeurParam(String nomParametre) {
        return this.parametres_appel.get(nomParametre);
    }

    /**
     * @return true -> si les parametres sont initialisees <br> false -> s'il n'y a pas de parametres
     * @throws Error une erreur si un des tests n'est pas passe
     */
    public boolean testParams(ArrayList<?> paramsValeurs) {
        if (this.parametres.length == 0 && paramsValeurs.size() == 0) return false;

        int nonDefaultParams = (int) Arrays.stream(parametres).filter(param -> param.getValeurParDefaut() == null).count();

        if (paramsValeurs.size() < nonDefaultParams || paramsValeurs.size() > this.parametres.length) {
            if (nonDefaultParams == this.parametres.length) {
                throw new ASErreur.ErreurAppelFonction(this.nom, "Le nombre de param\u00E8tres donn\u00E9s est '" + paramsValeurs.size() +
                        "' alors que la fonction en prend '" + this.parametres.length + "'");
            } else {
                throw new ASErreur.ErreurAppelFonction(this.nom, "Le nombre de param\u00E8tres donn\u00E9s est '" + paramsValeurs.size() +
                        "' alors que la fonction en prend entre '" + nonDefaultParams + "' et '" + this.parametres.length + "'");
            }

        }
        for (int i = 0; i < paramsValeurs.size(); i++) {
            ASParametre parametre = this.parametres[i];
            if (parametre.getType().noMatch(((ASObjet<?>) paramsValeurs.get(i)).obtenirNomType())) {
                throw new ASErreur.ErreurType("Le param\u00E8tres '" + parametre.getNom() + "' est de type '" + parametre.getType().nom() +
                        "', mais l'argument pass\u00E9 est de type '" + ((ASObjet<?>) paramsValeurs.get(i)).obtenirNomType() + "'.");
            }
        }
        return true;

        /*
         * grandement am�liorer les tests ici
         * test � ajouter:
         * 	1. tous les param = valeur sont apr�s les params sans val_defaut
         *
         */
    }

    //		public void afficherParams() {
//			System.out.println("Fonction: " + this.nom);
//			for (Object[] param: this.parametres) {
//				String message = "Param�tre " + param[0] + ": {";
//				for (String m : param) {
//					message += ((m == null || m.equals("null")) ? "tout" : m) + ", ";
//				}
//				System.out.println(message.substring(0, message.length()-2) + "}");
//			};
//		}

    public ASObjet<?> setParamPuisExecute(ArrayList<ASObjet<?>> paramsValeurs) {
        this.parametres_appel = new Hashtable<>();

        if (testParams(paramsValeurs)) {
            /*
             * Défini la valeur de chaque argument passé explicitement par nom dans l'appel de la fonction
             * ex: foo(param1=vrai)
             */
            for (ASObjet<?> param : paramsValeurs) {
                if (param instanceof ASParametre parametre) {
                    if (Arrays.stream(parametres).noneMatch(p -> p.getNom().equals(parametre.getNom()))) {
                        throw new ASErreur.ErreurAppelFonction("l'argument: " + parametre.getNom() + " pass\u00E9 en param\u00E8tre" +
                                " ne correspond \u00E0 aucun param\u00E8tre d\u00E9fini dans la fonction '" + this.nom + "'");
                    }
                    this.parametres_appel.put(parametre.getNom(), parametre.getValeurParDefaut());
                }
            }

            for (int i = 0; i < this.parametres.length; i++) {
                ASParametre param = this.parametres[i];
                if (i < paramsValeurs.size()) {
                    this.parametres_appel.putIfAbsent(param.getNom(), paramsValeurs.get(i));

                } else {
                    if (param.getValeurParDefaut() == null) {
                        throw new ASErreur.ErreurAppelFonction(this.nom, "l'argument: " + param.getNom() + " n'a pas reçu de valeur" +
                                "et ne poss\u00E8de aucune valeur par d\u00E9faut");
                    }
                    this.parametres_appel.putIfAbsent(param.getNom(), param.getValeurParDefaut());
                }
            }

            for (ASParametre param : this.parametres) {
                this.parametres_appel.computeIfAbsent(param.getNom(), (val) -> {
                    if (param.getValeurParDefaut() == null) {
                        throw new ASErreur.ErreurAppelFonction(this.nom, "l'argument: " + param.getNom() + " n'a pas reçu de valeur" +
                                "et ne poss\u00E8de aucune valeur par d\u00E9faut");
                    }
                    return param.getValeurParDefaut();
                });
            }
        }
        return this.executer();
    }

    abstract public ASObjet<?> executer();

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    @Override
    public String toString() {
        return this.nom + "(" +
                String.join(", ", Arrays.stream(this.parametres).map(p -> p.getNom() + ": " + p.obtenirNomType())
                        .toArray(String[]::new)) +
                ") " +
                "\u2192 " + this.typeRetour.nom();
    }

    @Override
    public Object getValue() {
        return this;
    }

    @Override
    public boolean boolValue() {
        return true;
    }

    @Override
    public String obtenirNomType() {
        return ASTypeBuiltin.fonctionType.toString();
    }

}

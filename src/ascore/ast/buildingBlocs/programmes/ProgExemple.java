package ascore.ast.buildingBlocs.programmes;

import ascore.ast.buildingBlocs.Programme;
import ascore.executeur.Coordonnee;
import ascore.executeur.Executeur;
import ascore.tokens.Token;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Squelette de l'impl\u00E9mentation d'un programme.<br>
 * Pour trouver un exemple d'une impl\u00E9mentation compl\u00E8te, voir {@link Declarer}
 *
 * @author Mathis Laroche
 * @see Declarer
 */
public class ProgExemple extends Programme {

    /**
     * Si le programme n'a pas besoin d'avoir acc\u00E8s \u00e0 l'ex\u00E9cuteur lorsque la m\u00E9thode {@link #execute()}
     * est appel\u00E9e
     */
    public ProgExemple() {
        super();
    }

    /**
     * Si le programme doit avoir acc\u00E8s \u00e0 l'ex\u00E9cuteur lorsque la m\u00E9thode {@link #execute()}
     * est appel\u00E9e
     *
     * @param executeurInstance l'ex\u00E9cuteur actuel
     */
    public ProgExemple(@NotNull Executeur executeurInstance) {
        super(executeurInstance);
    }

    /**
     * M\u00E9thode d\u00E9crivant l'effet de la ligne de code
     *
     * @return Peut retourner plusieurs choses, ce qui provoque plusieurs effets diff\u00E9rents:
     * <ul>
     *     <li>
     *         <code>null</code> -> continue normalement l'ex\u00E9cution
     *         (la plupart des programmes retournent <code>null</code>)
     *     </li>
     *     <li>
     *         <code>instance of Data</code> -> data ajout\u00E9e \u00e0 la liste de data tenue par l'ex\u00E9cuteur
     *     </li>
     *     <li>
     *         autre -> si le programme est appel\u00E9 \u00e0 l'int\u00E9rieur d'une fonction,
     *         cette valeur est celle retourn\u00E9e par la fonction. Sinon, la valeur est ignor\u00E9e
     *     </li>
     * </ul>
     */
    @Override
    public Object execute() {
        return null;
    }


    /**
     * L'override de cette m\u00E9thode n'est pas obligatoire, mais est n\u00E9cessaire
     * si le but est de changer la coordonn\u00E9e de la prochaine ligne \u00e0 compiler par
     * {@link Executeur l'executeur}
     *
     * @param coord coordonn\u00E9e actuelle
     * @param ligne ligne actuelle tokenized
     * @return la coordonn\u00E9e de la prochaine ligne \u00e0 compiler
     */
    @Override
    public Coordonnee prochaineCoord(Coordonnee coord, List<Token> ligne) {
        return super.prochaineCoord(coord, ligne);
    }
}

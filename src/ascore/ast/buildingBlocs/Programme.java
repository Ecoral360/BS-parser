package ascore.ast.buildingBlocs;

import ascore.executeur.Coordonnee;
import ascore.executeur.Executeur;
import ascore.tokens.Token;
import org.jetbrains.annotations.NotNull;

import javax.lang.model.type.NullType;
import java.io.Serializable;
import java.util.List;

/**
 * Un programme est cr\u00E9e au <i>Compile time</i> et ex\u00E9cut\u00E9 au <i>Runtime</i>.
 * <br>
 * Chaque programme est responsable d'une instruction particuli\u00E8re dans le langage et
 * de contr\u00F4ler le flow du code
 *
 * @author Mathis Laroche
 */
public abstract class Programme implements Serializable {
    protected final Executeur executeurInstance;
    private int numLigne = -1;

    protected Programme() {
        this.executeurInstance = null;
    }

    protected Programme(@NotNull Executeur executeurInstance) {
        this.executeurInstance = executeurInstance;
    }

    public static Programme evalExpression(Expression<?> expression, String toString) {
        return new Programme() {
            @Override
            public Object execute() {
                expression.eval();
                return null;
            }

            @Override
            public String toString() {
                return toString;
            }
        };
    }

    /**
     * appelé au runtime
     */
    public abstract Object execute();

    /**
     * L'override de cette m\u00E9thode n'est pas obligatoire, mais est n\u00E9cessaire
     * si le but est de changer la coordonn\u00E9e de la prochaine ligne \u00e0 compiler par
     * {@link Executeur l'executeur}
     *
     * @param coord coordonn\u00E9e actuelle
     * @param ligne ligne actuelle tokenized
     * @return la coordonn\u00E9e de la prochaine ligne \u00e0 compiler
     */
    public Coordonnee prochaineCoord(Coordonnee coord, List<Token> ligne) {
        return coord;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    @Override
    public String toString() {
        return "vide";
    }

    /**
     * INDIQUE LA FIN DU PROGRAMME
     */
    public static class ProgrammeFin extends Programme {
        @Override
        public NullType execute() {
            return null;
        }

        @Override
        public String toString() {
            return "'FIN'";
        }
    }
}

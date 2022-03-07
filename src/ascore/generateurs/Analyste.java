package ascore.generateurs;

import ascore.as.ASLexer;
import ascore.tokens.Token;

import java.util.List;
import java.util.Scanner;

import static java.lang.Math.max;

public record Analyste(String[] lignes, ASLexer lexer) {
    public static final String NOM = "Alice l'Analyste";

    public Analyste(String[] lignes) {
        this(lignes, new ASLexer());
    }

    public void afficherProgramme() {
        System.out.println("Programme: ");
        System.out.println(String.join("\n", lignes));
        delimiter();
    }

    private void delimiter() {
        System.out.println("~".repeat(30));
    }

    /**
     * Toutes les lignes, une \u00e0 la fois
     */
    public void analyserLexing(Precision precision, boolean inclureLigneLexed) {
        for (int i = 0; i < lignes.length; i++) {
            analyserLexing(i, inclureLigneLexed);
            delimiter();
            if (precision == Precision.LIGNE_PAR_LIGNE && i != lignes.length - 1) {
                demanderProchaineEtape();
            } else System.out.println();
        }
    }

    /**
     * Juste la ligne pr\u00E9cis\u00E9e
     */
    public void analyserLexing(int numLigne, boolean inclureLigneLexed) {
        String ligne = lignes[numLigne];
        List<Token> ligneLexed = lexer.lex(ligne);

        System.out.printf("(%d)\n", numLigne);
        afficherLigneEtTokenAlignes(ligneLexed, inclureLigneLexed);
    }

    private void afficherLigneEtTokenAlignes(List<Token> ligneLexed, boolean inclureLigneLexed) {
        var firstLine = new StringBuilder();
        var secondLine = new StringBuilder();
        var thirdLine = new StringBuilder();

        ligneLexed.forEach(token -> {
            String val = token.getValeur();
            String nom = token.getNom();
            String regle = token.getRegleParent().getPattern();

            String format = "%1$-" + (max(max(nom.length(), regle.length()), val.length()) + 3) + "s";

            firstLine.append(String.format(format, val));
            secondLine.append(String.format(format, regle));
            thirdLine.append(String.format(format, nom));
        });

        System.out.println("Ligne:  " + firstLine);
        System.out.println("Regle:  " + secondLine);
        System.out.println("Token:  " + thirdLine);
        if (inclureLigneLexed)
            System.out.println("\nLigne lexed: [\n\t" +
                    String.join("\n\t", ligneLexed.stream().map(Token::toString).toList()) +
                    "\n]");
    }

    private void demanderProchaineEtape() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public enum Precision {
        LIGNE_PAR_LIGNE,
        TOUT_EN_MEME_TEMPS,
    }
}











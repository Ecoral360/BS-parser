package ascore.as;


import ascore.generateurs.lexer.LexerGenerator;
import ascore.generateurs.lexer.LexerLoader;

/**
 * 
 * Les explications vont être rajouté quand j'aurai la motivation de les écrire XD
 * @author Mathis Laroche
 */ 
public class ASLexer extends LexerGenerator {
	public ASLexer() {
        super();
        LexerLoader loader = new LexerLoader("ascore/regle_et_grammaire/Grammaire.yaml");
        loader.load();
        sortRegle();
    }
}

import ascore.executeur.Executeur;
import org.json.JSONArray;

public class Main {
    static String[] YOUR_BS_PROGRAM = """
            function printMessage(€message):
                echo €message;
            end function

            €msg = "hello world!";
            printMessage(€msg);

            """.split("\n");


    public static void main(String[] args) {
        Executeur executeur = new Executeur();
        JSONArray compilerResult = executeur.compiler(YOUR_BS_PROGRAM, true);
        executeur.debug = true;

        if (!compilerResult.toString().equals("[]")) {
            System.out.println("Error when compiling: " + compilerResult);
            return;
        }

        Object executionResult = executeur.executerMain(false);
        System.out.println(executionResult);
    }
}

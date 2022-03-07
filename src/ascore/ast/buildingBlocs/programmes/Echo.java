package ascore.ast.buildingBlocs.programmes;

import ascore.ast.buildingBlocs.Expression;
import ascore.ast.buildingBlocs.Programme;
import ascore.executeur.Executeur;
import org.jetbrains.annotations.NotNull;

public class Echo extends Programme {
    private final Expression<?> exprAfficher;

    public Echo(Expression<?> expr, @NotNull Executeur executeur) {
        super(executeur);
        this.exprAfficher = expr;
    }

    @Override
    public Object execute() {
        if (executeurInstance != null)
            executeurInstance.ecrire(exprAfficher.eval().toString());
        return null;
    }
}

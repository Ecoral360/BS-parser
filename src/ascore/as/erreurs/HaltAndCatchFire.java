package ascore.as.erreurs;

public class HaltAndCatchFire extends ASErreur.ErreurAliveScript {
    public HaltAndCatchFire() {
        super("", "HALT_AND_CATCH_FIRE");
    }
    public HaltAndCatchFire(String message) {
        super(message, "HALT_AND_CATCH_FIRE");
    }
}

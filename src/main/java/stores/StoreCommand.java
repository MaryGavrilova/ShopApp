package stores;

public interface StoreCommand {
    boolean doCommand();

    boolean undoCommand();
}

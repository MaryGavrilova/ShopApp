public interface StoreCommand {
    boolean doCommand();
    boolean undoCommand();
}

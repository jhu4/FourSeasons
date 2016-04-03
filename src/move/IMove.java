package move;

public interface IMove {
	boolean execute();
	boolean undo();
	boolean isvalid();
}

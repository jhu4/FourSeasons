package move;
import ks.common.games.*;

public interface IMove {
	boolean execute(Solitaire s);
	boolean undo(Solitaire s);
	boolean isvalid(Solitaire s);
}

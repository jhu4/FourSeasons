package move;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Pile;

public class StockMoveWastepile implements IMove{
	Deck stock;
	Pile wastepile;
	
	public StockMoveWastepile(Deck stock, Pile wastepile){
		this.stock=stock;
		this.wastepile=wastepile;
	}

	@Override
	public boolean execute(Solitaire s) {
		// VALIDATE:
		if (isvalid(s) == false)
			return false;
		
		wastepile.add(stock.get());
		return true;
	}

	public boolean undo(Solitaire s) {
		// Can't undo
		return false;
	}

	@Override
	public boolean isvalid(Solitaire s) {
		// TODO Auto-generated method stub
		return false;
	}
}

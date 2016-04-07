package move;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Pile;

public class StockMoveWastepile extends ks.common.model.Move{
	Deck stock;
	Pile wastepile;
	
	public StockMoveWastepile(Deck stock, Pile wastepile){
		this.stock=stock;
		this.wastepile=wastepile;
	}

	public boolean undo(Solitaire s) {
		// Can't undo
		return false;
	}

	public boolean doMove(Solitaire s) {
		// VALIDATE:
		if (valid(s) == false)
			return false;
		
		wastepile.add(stock.get());
		return true;
	}

	public boolean valid(Solitaire s) {
		
		if(stock.count()>=1) {return true;}
		
		return false;
	}
}

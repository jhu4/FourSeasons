package Controller;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Pile;

public class MoveToCross extends ks.common.model.Move{
	Pile frompile;
	Card draggingcard;
	Pile topile;
	
	public MoveToCross(Pile frompile, Card draggingcard, Pile topile){
		this.draggingcard=draggingcard;
		this.frompile=frompile;
		this.topile=topile;
	}

	
	public boolean undo(Solitaire s) {
		//cant't undo
		return false;
	}

	public boolean doMove(Solitaire s) {
		if(valid(s)){
			topile.add(draggingcard);
			return true;
		}
		return false;
	}

	public boolean valid(Solitaire s) {
		
		if(draggingcard==null) {return false;}
		
		else if(topile.empty()) {return true;}
		
		//case where dragging card is K and the top of the cross is A
		else if(draggingcard.getRank()==13&&topile.peek().isAce()) {return true;}
		
		//dragging card is always smaller than the top of the cross
		else if(topile.peek().getRank()-draggingcard.getRank()==1) {return true;}
			
		return false;
	}
}

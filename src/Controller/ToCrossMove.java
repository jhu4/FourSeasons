package Controller;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Pile;

public class ToCrossMove extends ks.common.model.Move{
	Card draggingcard;
	Pile topile;
	Pile frompile;
	
	public ToCrossMove(Pile frompile, Card draggingcard, Pile topile){
		this.draggingcard=draggingcard;
		this.topile=topile;
		this.frompile=frompile;
	}

	@Override
	public boolean undo(Solitaire s) {
		if(topile.empty()){
			return false;
		}
		frompile.add(topile.get());
		return true;
	}
	
	@Override
	public boolean doMove(Solitaire s) {
		if(valid(s)){
			topile.add(draggingcard);
			return true;
		}
		return false;
	}

	@Override
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

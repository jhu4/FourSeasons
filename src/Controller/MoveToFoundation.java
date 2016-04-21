package Controller;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Pile;

public class MoveToFoundation extends ks.common.model.Move{
	Pile topile;
	Pile frompile;
	Card draggingcard;
	int basenumber;
	
	public MoveToFoundation(Pile frompile, Card draggingcard, Pile topile, int basenumber){
		this.draggingcard=draggingcard;
		this.frompile=frompile;
		this.topile=topile;
		this.basenumber=basenumber;
	}

	
	public boolean undo(Solitaire s) {
		//can't undo
		return false;
	}

	public boolean doMove(Solitaire s) {
		if(valid(s)){
			topile.add(draggingcard);
			s.updateScore(1);
			s.updateNumberCardsLeft(-1);
			return true;
		}
		return false;
	}

	public boolean valid(Solitaire s) {
		
		if(draggingcard==null) {return false;}
		
		else if(topile.empty()&& draggingcard.getRank()==basenumber) {return true;}
		
		else if(draggingcard.sameSuit(topile.peek())){
			
			if(draggingcard.isAce()&&topile.peek().getRank()==13) {return true;}
			
			else if(draggingcard.getRank()-topile.peek().getRank()==1) {return true;}
		}
				
		return false;
	}

}

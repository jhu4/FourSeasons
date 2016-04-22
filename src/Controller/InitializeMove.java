package Controller;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Pile;

public class InitializeMove extends ks.common.model.Move{
	Deck stock;
	Pile foundation;
	Pile[] cross;
	
	public InitializeMove(Deck stock, Pile f1, Pile[] cross){
		this.stock=stock;
		this.foundation=f1;
		this.cross=cross;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if(valid(game)){
			foundation.add(stock.get()); //add base card
			for(int i=1;i<=5;i++){
				cross[i].add(stock.get());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean undo(Solitaire game) {
		//can't undo this one
		return false;
	}

	@Override
	public boolean valid(Solitaire game) {
		if(foundation.count()==0 && cross[1].count()==0 && cross[2].count()==0 && cross[3].count()==0 && cross[4].count()==0
				&& cross[5].count()==0 && stock.count()!=0){
			return true;
		}
		return false;
	}

}

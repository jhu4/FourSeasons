package JinanHu;

import java.awt.Dimension;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;


public class FourSeasons extends Solitaire{
	Deck stock;
	Pile[] foundation = new Pile[4];
	Pile[] cross = new Pile[5];
	Pile wastepile;
	int score,cardleft;
	
	DeckView stockview;
	PileView[] crossview = new PileView[5]; 
	PileView[] foundationview = new PileView[4];
	PileView wastepileview;
	IntegerView scoreview,cardleftview;
	
	public FourSeasons(){
		super();
	};
	
	
	
	@Override
	public String getName() {
		return "FourSeasons";
	}

	@Override
	public boolean hasWon() {
		return getScoreValue() == 46;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}

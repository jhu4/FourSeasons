package JinanHu;

import java.awt.Dimension;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;


public class FourSeasons extends Solitaire{
	Deck stock;
	Pile upperleft,upperright,lowerleft,lowerright;
	Pile crossup,crossdown,crossmiddle,crossleft,crossright;
	Pile wastepile;
	int score,cardleft;
	
	DeckView stockview;
	PileView[] = PileView[5]; 
	PileView crossupview,crossdownview,crossleftview,crossrightview;
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

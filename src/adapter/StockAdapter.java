package adapter;

import ks.common.controller.*;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import JinanHu.FourSeasons;

public class StockAdapter extends SolitaireReleasedAdapter {
	DeckView src;
	
	public StockAdapter(FourSeasons theGame, DeckView src) {
		super(theGame);
		this.src=src;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent me){
		
	}
	
}

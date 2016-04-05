package adapter;

import ks.common.controller.*;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import JinanHu.FourSeasons;
import JinanHu.*;

public class StockController extends SolitaireReleasedAdapter {
	FourSeasons game;
	Deck stock;
	Pile wastepile;
	
	public StockController(FourSeasons theGame, Deck stock, Pile wastepile) {
		super(theGame);
		this.game=theGame;
		this.wastepile=wastepile;
		this.stock=stock;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent me){
		
	}
	
}

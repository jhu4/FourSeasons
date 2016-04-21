package Controller;

import ks.common.controller.*;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import JinanHu.FourSeasons;

import java.awt.event.MouseEvent;

import JinanHu.*;

public class StockController extends SolitaireReleasedAdapter {
	FourSeasons game;
	DeckView stockview;
	
	public StockController(FourSeasons theGame, DeckView stockview) {
		super(theGame);
		this.game=theGame;
		this.stockview=stockview;
	}
	
	public void mousePressed(MouseEvent me){
		
		Deck stock = (Deck) stockview.getModelElement();
		if(stock.count()==0){
			System.err.println ("StockController::mouseClicked(). Won game error");
			return;
		}
		
		Pile wastepile = (Pile) game.getModelElement("wastepile");
		Move m = new StockMoveWastepile(stock, wastepile);
		if(m.doMove(game)){
			game.pushMove(m);
			game.refreshWidgets();
		}
	}	
}

package Controller;

import ks.common.games.Solitaire;
import ks.common.model.Deck;
import ks.common.model.Pile;

public class StockToWastepileMove extends ks.common.model.Move{
	Deck stock;
	Pile wastepile;
	
	
	public StockToWastepileMove(Deck stock, Pile wastepile){
		this.stock=stock;
		this.wastepile=wastepile;
	}
	
	@Override
	public boolean undo(Solitaire s) {
		if(wastepile.empty()){
			return false;
		}
		
		stock.add(wastepile.get());
		return true;
	}
	@Override
	public boolean doMove(Solitaire s) {
		if(valid(s)){
			wastepile.add(stock.get());
			return true;
		} 
		return false;
	}
	@Override
	
	public boolean valid(Solitaire s) {
		
		if(stock.count()>=1) {return true;}
		
		return false;
	}
}

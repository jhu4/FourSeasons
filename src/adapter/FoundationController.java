package adapter;

import java.awt.event.MouseEvent;

import JinanHu.FourSeasons;
import ks.common.view.PileView;

public class FoundationController extends java.awt.event.MouseAdapter{
	PileView foundationview;
	FourSeasons thegame;
	
	
	public FoundationController(PileView foundationview, FourSeasons thegame){
		this.foundationview = foundationview;
		this.thegame = thegame;
	}
	
	public void mouseReleased(MouseEvent me){
		
	}
	
}

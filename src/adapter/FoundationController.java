package adapter;

import JinanHu.FourSeasons;
import ks.common.view.PileView;

public class FoundationController extends java.awt.event.MouseAdapter{
	PileView foundationview;
	FourSeasons thegame;
	
	
	public FoundationController(PileView fv, FourSeasons FS){
		this.foundationview = fv;
		this.thegame = FS;
	}
}

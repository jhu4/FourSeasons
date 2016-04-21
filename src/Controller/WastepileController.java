package Controller;



import java.awt.event.MouseEvent;

import JinanHu.FourSeasons;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Card;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class WastepileController extends SolitaireReleasedAdapter{
	FourSeasons thegame;
	PileView wastepileview;
	
	public WastepileController(FourSeasons thegame, PileView wastepileview) {
		super(thegame);
		this.thegame=thegame;
		this.wastepileview=wastepileview;
	}

	public void mousePressed(MouseEvent me){
		Container c = thegame.getContainer();
		
		Pile wastepile = (Pile) wastepileview.getModelElement();
		if(wastepile.empty()) return;
		
		CardView cardview = wastepileview.getCardViewForTopCard(me);
		if (cardview == null) {
			c.releaseDraggingObject();
			return;
		}
		Widget w =  c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println ("WastepileController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}
		c.setActiveDraggingObject(cardview, me);
		c.setDragSource(wastepileview);
		wastepileview.redraw();
	}

}

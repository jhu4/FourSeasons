package Controller;

import java.awt.event.MouseEvent;

import JinanHu.FourSeasons;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;

public class FoundationController extends java.awt.event.MouseAdapter{
	PileView foundationview;
	FourSeasons thegame;
	
	
	public FoundationController(FourSeasons thegame, PileView foundationview){
		this.foundationview = foundationview;
		this.thegame = thegame;
	}
	
	public void mouseReleased(MouseEvent me){
		Container c = thegame.getContainer();
		
		
		/** Return if there is no card being dragged chosen. */
		Widget w = c.getActiveDraggingObject();
		if (w == Container.getNothingBeingDragged()) {
			c.releaseDraggingObject();		
			return; 
		} 
		
		/** Recover */ 
		Widget fromWidget = c.getDragSource();
		if (fromWidget == null) {
			System.err.println ("oundationController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}
		
		CardView cardview = (CardView) w;
		Card card = (Card) cardview.getModelElement();
		if (card == null) {
			System.err.println ("FoundationController::mouseReleased(): somehow CardView model element is null.");
			return;
		}

		int basenumber = thegame.getbasenumber();
		Pile frompile = (Pile) fromWidget.getModelElement();
		Pile topile = (Pile) foundationview.getModelElement();
		if (topile == null){
			System.err.println("FountaionController::MouseReleased():to pile is null");
			return;
		}
		Move m = new ToFoundationMove(frompile, card, topile,basenumber);
		if(m.doMove(thegame)){
			thegame.pushMove(m);
		
		}
		else{
			frompile.add(card);
		}
		
		thegame.refreshWidgets();
		// release the dragging object, (container will reset dragSource)
		c.releaseDraggingObject();
		c.repaint();			
	}
	
}

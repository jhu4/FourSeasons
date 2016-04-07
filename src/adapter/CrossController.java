package adapter;

import java.awt.event.MouseEvent;

import JinanHu.FourSeasons;
import ks.common.model.Card;
import ks.common.model.Move;
import ks.common.model.Pile;
import ks.common.view.CardView;
import ks.common.view.Container;
import ks.common.view.PileView;
import ks.common.view.Widget;
import move.MoveToCross;

public class CrossController extends java.awt.event.MouseAdapter{
	PileView crossview;
	FourSeasons thegame;
	
	public CrossController(FourSeasons thegame, PileView cross){
		this.crossview=cross;
		this.thegame=thegame;
	}
	
	public void mousePressed(MouseEvent me){
		Container c = thegame.getContainer();
				
				Pile cross = (Pile) crossview.getModelElement();
		
				Card card = cross.get();
				
				
				if(card==null){
					return;
				}
				else{
					Widget w =  c.getActiveDraggingObject();
					if (w != Container.getNothingBeingDragged()) {
						System.err.println ("CrossController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
						return;
					}
					CardView cardview = new CardView(card);
					c.setActiveDraggingObject(cardview, me);
					c.setDragSource(crossview);
					crossview.redraw();
				}
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
			System.err.println ("CrossController::mouseReleased(): somehow no dragSource in container.");
			c.releaseDraggingObject();
			return;
		}
		
		Pile topile = (Pile) crossview.getModelElement();
		
		CardView cardview = (CardView) w;
		Card card = (Card) cardview.getModelElement();
		if (card == null) {
			System.err.println ("CrossController::mouseReleased(): somehow CardView model element is null.");
			return;
		}
		
		if (fromWidget==this.crossview){
			Pile cross = (Pile) this.crossview.getModelElement();
			cross.add(card);
		}
		
		Pile frompile = (Pile) fromWidget.getModelElement();
		Move m = new MoveToCross(frompile, card, topile);
		if(m.doMove(thegame)){
			thegame.pushMove(m);
		}
		else{
			frompile.add(card);
		}
		
		
		// release the dragging object, (container will reset dragSource)
				c.releaseDraggingObject();
				
				c.repaint();
	}
	
}

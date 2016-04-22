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
		if(cross.empty()) return;

		CardView cardview = crossview.getCardViewForTopCard (me);

		// an invalid selection of some sort.
		if (cardview == null) {
			return;
		}
		Widget w =  c.getActiveDraggingObject();
		if (w != Container.getNothingBeingDragged()) {
			System.err.println ("CrossController::mousePressed(): Unexpectedly encountered a Dragging Object during a Mouse press.");
			return;
		}
		c.setActiveDraggingObject (cardview, me);
		c.setDragSource(crossview);
		crossview.redraw();

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
		if(topile==null){
			System.err.println ("CrossController::mouseReleased(): to pile is null.");
			c.releaseDraggingObject();
			return;
		}
		
		CardView cardview = (CardView) w;
		Card card = (Card) cardview.getModelElement(); 
		if (card == null) {
			System.err.println ("CrossController::mouseReleased(): somehow CardView model element is null.");
			return;
		}

		
		if (fromWidget==this.crossview){
			Pile cross = (Pile) this.crossview.getModelElement();
			cross.add(card);
		} else {

			Pile frompile = (Pile) fromWidget.getModelElement();
			Move m = new ToCrossMove(frompile,card, topile);
			if(m.doMove(thegame)){
				thegame.pushMove(m);
				
			}
			else{
//				frompile.add(card);
				fromWidget.returnWidget(cardview);
			}
		}

//		thegame.refreshWidgets();
		// release the dragging object, (container will reset dragSource)
		c.releaseDraggingObject();
		c.repaint();
	}

}

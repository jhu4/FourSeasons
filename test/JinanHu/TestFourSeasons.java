package JinanHu;

import org.junit.Test;
import JinanHu.FourSeasons;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Pile;
import ks.launcher.Main;
import ks.tests.KSTestCase;

public class TestFourSeasons extends KSTestCase{
	FourSeasons game;
	GameWindow gw;
	
	protected void setUp(){
		game=new FourSeasons();
		gw = Main.generateWindow(game, Deck.OrderBySuit); 
		assertEquals(new Card(Card.KING,Card.SPADES),game.foundation[1].peek());
		assertEquals(new Card(Card.QUEEN,Card.SPADES),game.cross[1].peek());
		assertEquals(new Card(Card.JACK,Card.SPADES),game.cross[2].peek());
		assertEquals(new Card(Card.TEN,Card.SPADES),game.cross[3].peek());
		assertEquals(new Card(Card.NINE,Card.SPADES),game.cross[4].peek());
		assertEquals(new Card(Card.EIGHT,Card.SPADES),game.cross[5].peek());
	}
	
	protected void tearDown() {
		gw.setVisible(false);
		gw.dispose();
	}
	
	public void testInit(){
		
	}
}

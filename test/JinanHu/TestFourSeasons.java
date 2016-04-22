package JinanHu;

import java.awt.event.MouseEvent;

import org.junit.Test;

import Controller.*;
import JinanHu.FourSeasons;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
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
		assertEquals(null,game.wastepile.peek());
	}
	
	protected void tearDown() {
		gw.setVisible(false);
		gw.dispose();
	}
	
	public void testInit(){
		assertEquals(game.stock.count(),46);
		for(int i=1;i<6;i++){
			assertEquals(game.cross[i].count(),1);
		}
		assertEquals(game.foundation[1].count(),1);
		assertEquals(game.foundation[2].count(),0);
		assertEquals(game.foundation[3].count(),0);
		assertEquals(game.foundation[4].count(),0);
		assertEquals(game.basenumber,13);
	}
	
	public void testToCrossMove(){
		game.cross[3].add(new Card(Card.ACE,Card.CLUBS));
		game.wastepile.add(new Card(Card.KING,Card.HEARTS));
		Move move1 = new ToCrossMove(game.wastepile,game.wastepile.peek(),game.cross[3]);
		
		assertTrue(move1.valid(game));
		assertTrue(move1.doMove(game));
		
		game.wastepile.add(new Card(Card.NINE,Card.HEARTS));
		Move move2 = new ToCrossMove(game.wastepile,game.wastepile.peek(),game.cross[4]);
		assertFalse(move2.valid(game));
		assertFalse(move2.doMove(game));
		
		game.undoMove();
		assertEquals(new Card(Card.NINE,Card.HEARTS),game.wastepile.peek());
		assertEquals(new Card(Card.NINE,Card.SPADES),game.cross[4].peek());
		
	}
	
	public void testToFoundationMove(){
		for(int i=0;i<7;i++){
			game.stock.get();
		}
		
		game.wastepile.add(game.stock.get());
		
		Move move2 = new ToFoundationMove(game.wastepile,game.wastepile.get(),game.foundation[2],game.getbasenumber());
		assertTrue(move2.valid(game));
		assertTrue(move2.doMove(game));
		
		game.wastepile.add(game.stock.get());
		
		Move move1 = new ToFoundationMove(game.wastepile,game.wastepile.get(),game.foundation[1],game.getbasenumber());
		assertFalse(move1.valid(game));
		assertFalse(move1.doMove(game));
		
		for(int i=0;i<10;i++){
			game.stock.get();
		}
		game.wastepile.add(game.stock.get());
		
		Card meow = game.wastepile.peek();
		Move move3 = new ToFoundationMove(game.wastepile,game.wastepile.get(),game.foundation[2],game.getbasenumber());
		assertTrue(move3.valid(game));
		assertTrue(move3.doMove(game));
		
		game.undoMove();
//		assertEquals(meow, game.wastepile.peek());
	}
	
	public void testStockController(){
		assertEquals(game.stock.peek(),new Card(Card.SEVEN,Card.SPADES));
		
		MouseEvent me = this.createPressed(game, game.stockview, 0, 0);
		game.stockview.getMouseManager().handleMouseEvent(me);
		
		assertEquals(game.stock.peek(),new Card(Card.SIX,Card.SPADES));
		assertEquals(game.wastepile.peek(),new Card(Card.SEVEN,Card.SPADES));
	}
	
	public void testCrossController(){
		MouseEvent me = this.createPressed(game, game.crossview[2], 0, 0);
		game.crossview[2].getMouseManager().handleMouseEvent(me);
		assertEquals(null,game.cross[2].peek());
		
		MouseEvent me2 = this.createReleased(game, game.crossview[1], 0, 0);
		game.crossview[1].getMouseManager().handleMouseEvent(me2);
		assertEquals(new Card(Card.JACK,Card.SPADES),game.cross[1].peek());
	}
	
	public void testFoundationController(){
		game.wastepile.add(new Card(Card.KING,Card.CLUBS));
		MouseEvent me = this.createPressed(game, game.wastepileview, 0, 0);
		game.wastepileview.getMouseManager().handleMouseEvent(me);
		
		MouseEvent me2 = this.createReleased(game, game.foundationview[3], 0, 0);
		game.foundationview[3].getMouseManager().handleMouseEvent(me2);
		assertEquals(game.foundation[3].peek(),new Card(Card.KING,Card.CLUBS));
		
		
	}
	
	public void testWin(){
		game.stock.add(game.cross[5].get());
		game.stock.add(game.cross[4].get());
		game.stock.add(game.cross[3].get());
		game.stock.add(game.cross[2].get());
		game.stock.add(game.cross[1].get());
		game.stock.add(game.foundation[1].get());
		game.updateScore(-1);
		
		for(int j=1;j<5;j++){
			for(int i=0;i<13;i++){
				if(i!=0){
					Move m1 = new StockToWastepileMove(game.stock,game.wastepile);
					m1.doMove(game);
					Move m2 = new ToCrossMove(game.wastepile,game.wastepile.get(),game.cross[j]);
					m2.doMove(game);
				}
				else{
					Move m1 = new StockToWastepileMove(game.stock,game.wastepile);
					m1.doMove(game);
					Move m2 = new ToFoundationMove(game.wastepile,game.wastepile.get(),game.foundation[j],13);
					m2.doMove(game);
				}
			}
			for(int k=0;k<12;k++){
				Move m3 = new ToFoundationMove(game.cross[j],game.cross[j].get(),game.foundation[j],13);
				assertTrue(m3.doMove(game));
			}
		}
		
		assertTrue(game.hasWon());
	}
}

package JinanHu;

import java.awt.Dimension;

import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.*;
import ks.common.view.*;
import ks.launcher.Main;


public class FourSeasons extends Solitaire{
	Deck stock;
	Pile[] foundation = new Pile[5];
	Pile[] cross = new Pile[6];
	Pile wastepile;
	int basenumber;
	
	DeckView stockview;
	PileView[] crossview = new PileView[6]; 
	PileView[] foundationview = new PileView[5];
	PileView wastepileview;
	IntegerView scoreview,numleftview;
	
	public FourSeasons(){
		super();
	};
	
	public int getBaseNumber(){
		return basenumber;
	}
	

	public String getName() {
		return "FourSeasons";
	}
 
	public boolean hasWon() {
		return getScoreValue() == 46;
	}

	@Override
	public void initialize() {
		initializeModel(52);
		initializeView();
		
	}
	
	private void initializeModel(int seed){
		stock = new Deck("deck");
		stock.create(seed);
		model.addElement (stock);   // add to our model (as defined within our superclass).
		
		wastepile = new Pile("wastepile");
		model.addElement(wastepile);
		
				
		//adding 4 foundations
		for(int i=1;i<=4;i++){
			foundation[i]=new Pile("foundation"+i);
			model.addElement(foundation[i]);
		}
		
		//adding 5 crosses
		for(int i=1;i<=5;i++){
			cross[i]=new Pile("pile"+i);
			model.addElement(cross[i]);
		}
		
		//initial dealing card
		stock.shuffle(seed); 
		foundation[1].add(stock.get()); //add base card
		basenumber=foundation[1].peek().getRank();//initialize base number
		for(int i=1;i<=5;i++){
			cross[i].add(stock.get());
		}
		
		this.updateScore(6);
		this.updateNumberCardsLeft(46);
		
	}
	
	private void initializeView(){
		CardImages ci = getCardImages();
		int w = ci.getWidth();
		int h = ci.getHeight();
		int hh = h/3;
		
		stockview=new DeckView(stock);
		stockview.setBounds(140+4*w,40+h,w,h);
		container.addWidget(stockview);
		
		wastepileview=new PileView(wastepile);
		wastepileview.setBounds(140+4*w,60+2*h,w,h);
		container.addWidget(wastepileview);
		
		scoreview=new IntegerView (getScore());
		scoreview.setBounds(140+4*w,20,w,hh);
		container.addWidget(scoreview);
		
		numleftview =new IntegerView(getNumLeft());
		numleftview.setBounds(140+4*w, 40+hh, w, hh);
		container.addWidget(numleftview);
		
		//initializing foundation views
		for(int i=1;i<=4;i++){
			foundationview[i]=new PileView(foundation[i]);
			switch(i){
				case 1:
					foundationview[i].setBounds(60+w,20,w,h);
					break;
				case 2:
					foundationview[i].setBounds(100+3*w,20,w,h);
					break;
				case 3:
					foundationview[i].setBounds(60+w,60+2*h,w,h);
					break;
				case 4:
					foundationview[i].setBounds(100+3*w,60+2*h,w,h);
					break;
				default:
					System.err.print("FourSeasons::initializeView(), default case");
			}
			container.addWidget(foundationview[i]);
		}
		
		//initializing 5 crosses
		for(int i=1;i<=3;i++){
			crossview[i]=new PileView(cross[i]);
			crossview[i].setBounds(80+2*w,20*i+(i-1)*h,w,h);
			container.addWidget(crossview[i]);
		}
		crossview[4]=new PileView(cross[4]);
		crossview[4].setBounds(60+w,40+h,w,h);
		container.addWidget(crossview[4]);
		
		crossview[5]=new PileView(cross[5]);
		crossview[5].setBounds(100+3*w,40+h,w,h);
		container.addWidget(crossview[5]);
	}
	
	
	private void initializeController(){
		
	}
	
	
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		GameWindow gw = Main.generateWindow(new FourSeasons(), 117);
		gw.setVisible(true);
	}

}

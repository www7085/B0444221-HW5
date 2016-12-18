package HW5;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	
		// TODO Auto-generated method stub
	    private ArrayList<Card> usedCard = new ArrayList<Card>();
		private ArrayList<Card> cards=new ArrayList<Card>();
		private ArrayList<Card> openCard=new ArrayList<Card>();
		public int nUsed;
		public Deck(int nDeck){
			
			for(int i=1;i<nDeck;i++)
			{
			for(Card.Suit j:Card.Suit.values())
			{
			 for(int rank=1; rank<=13; rank++)
			 {
			    	  Card c =new Card(j,rank);
			    	  cards.add(c);
			      }  
			      }
			}
shuffle();
		}	
		public Card getOneCard(boolean isOpened)     
		{
			int n =usedCard.size();
				if (n < 52)
				{
					usedCard.add(cards.get(n));
					if (isOpened) openCard.add(cards.get(n));
					nUsed ++;
					if (nUsed == 52)  
						{
							Card x = usedCard.get(51);
							shuffle();
							return x;
						}
				}
			return usedCard.get(n);
		}
		
		
		

		public void shuffle(){
			
			Random a = new Random();
			for(int n=1;n<=52;n++){
				int one=a.nextInt(51);
				int two=a.nextInt(51);
				Card z=cards.get(one);
				cards.set(one,cards.get(two));
				cards.set(two, z);
			}
			usedCard.clear();
			openCard.clear();
			nUsed=0;
		}
			public void printDeck(){
			for ( Card x : cards)
			{
				x.printCard();
			}

		}
		public ArrayList<Card> getAllCards(){
			return cards;
		}
		
		public ArrayList<Card> getOpenedCard(){
			return openCard;
		}
		}
		
	

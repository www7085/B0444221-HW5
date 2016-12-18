package HW5;

import HW5.Card.Suit;

public class Card {

	
	// TODO Auto-generated method stub
public enum Suit{Club,Diamond,Heart,Spade}
	private Suit suit; 
	private int rank;
	public Card(Suit s,int value){
		suit=s;
		rank=value;
		
	}	
	
	public void printCard(){
		System.out.println(getSuit()+" "+getRank());
			}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
		
	}



}
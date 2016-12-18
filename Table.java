package HW5;

import java.util.ArrayList;

public class Table {

	static final int MAXPLAYER = 4;
	private Deck AllCards;	
	private Player[] players ;		
	private Dealer dealer;		
	int[] pos_betArray ;		
	private Card face_up_card_of_dealer;
	
	public Table ( int nDeck){
		AllCards = new Deck(nDeck);
		players = new Player[MAXPLAYER];
	}
	
	public void set_player(int pos, Player p){
		players[pos] = p;
	}
	
	public Player[] get_player(){		
		return players;
	}

	public void set_dealer(Dealer d){
		dealer = d;
	}
	
	public  Card get_face_up_card_of_dealer(){
		return face_up_card_of_dealer;
	}
	
	private void ask_each_player_about_bets(){
		int n =0;
		pos_betArray =new int[players.length];
		for ( Player x : players)
		{
			x.say_hello();
			x.make_bet();
			pos_betArray[n] = x.bet;	
			n++;
		}
	}
	
	private void distribute_cards_to_dealer_and_players(){		
		for ( Player x : players)		
		{
			x.setOneRoundCard(AllCards.getOneCard(true));
			x.setOneRoundCard(AllCards.getOneCard(true));
		}
		dealer.setOneRoundCard(AllCards.getOneCard(false));	
		dealer.setOneRoundCard(AllCards.getOneCard(true));	
		face_up_card_of_dealer = dealer.getOneRoundCard().get(1);	
		System.out.print("Dealer's face up card is ");
		dealer.getOneRoundCard().get(1).printCard();
	}
	
	private void ask_each_player_about_hits(){	
		for (Player x : players)
		{
			System.out.println(x.get_name()+"'s Cards now:");
			x.printAllCard();
			while ( x.hit_me(this))		
			{
				x.setOneRoundCard(AllCards.getOneCard(true));		
				System.out.println("Hit! "+x.get_name()+"'s Cards now:");
				x.printAllCard();
			}
			System.out.println("Pass hit!");
			System.out.println(x.get_name()+"'s hit is over!");
		}
	}

	private void ask_dealer_about_hits(){		
		while ( dealer.hit_me(this))
		{
			dealer.setOneRoundCard(AllCards.getOneCard(true));		
		}
		System.out.println("Dealer's hit is over!");
	}

	private void calculate_chips(){
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");
		dealer.printAllCard();
		int d = dealer.getTotalValue();
		int n = 0;
		for ( Player x : players)
		{
			int p = x.getTotalValue();
			int b = pos_betArray[n];
			n++;
			System.out.println(x.get_name()+"'s Cards: ");
			x.printAllCard();
			System.out.print(x.get_name()+" card value is "+x.getTotalValue());
			
			if ( d >21	&&  p>21)		
			{
				System.out.println(", chips have no change! The Chips now is: "+x.get_current_chips());
			}
			else if ( d>21 && p<=21)		
			{
				x.increase_chips(b);
				System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips());
			}
			else if ( d<=21 && p>21)		
			{
				x.increase_chips(-b);
				System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips());
			}
			else if ( d<=21 && p<=21)		
			{
				if ( d > p)		
				{
					x.increase_chips(-b);
					System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips());
				}
				else if ( d < p)		
				{
					x.increase_chips(b);
					System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips());
				}
				else if ( d == p)	
				{
					System.out.println(", chips have no change! The Chips now is: "+x.get_current_chips());
				}
			}
		}
	}

	public int[] get_palyers_bet(){
		return pos_betArray;
	}
	
	public void play(){
		ask_each_player_about_bets();						
		distribute_cards_to_dealer_and_players();		
		ask_each_player_about_hits();						
		ask_dealer_about_hits();								
		calculate_chips();											
	}


}
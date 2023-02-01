package org.emeraldcraft.apcsa.poker;

// Card class represents a playing card

public class Card
{
	
	private String face; // face of card ie..."Ace","Deuce", "King"..etc
	private String suit; // suit of card ie "Heart", "Diamond"
	
	// two argument card constructor
	public Card( String cardFace, String cardSuit )
	{
		face = cardFace;
		suit = cardSuit;
	}
	
	public String toString()
	{
		return face + " of " + getUnicode();
	}
	private String getUnicode()
	{
		switch(suit) {
			case "Hearts": 
				return "\u2665";
			case "Diamonds":
				return "\u25C6";
			case "Spades":
				return "\u2660";
			case "Clubs":
				return "\u2663";
			default:
				throw new IllegalArgumentException("Invalid suit of " + suit + "!");
		}
	}
	
}

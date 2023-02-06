package org.emeraldcraft.apcsa.poker;

import static org.emeraldcraft.apcsa.poker.Card.Constants.CARD_VALUES;

import java.util.HashMap;

// Card class represents a playing card

public class Card {
	public static class Constants {
		public static final HashMap<String, Integer> CARD_VALUES = new HashMap<>();
		static {
			// lazy way out
			CARD_VALUES.put("?", -1);
			CARD_VALUES.put("Deuce", 2);
			CARD_VALUES.put("Three", 3);
			CARD_VALUES.put("Four", 4);
			CARD_VALUES.put("Five", 5);
			CARD_VALUES.put("Six", 6);
			CARD_VALUES.put("Seven", 7);
			CARD_VALUES.put("Eight", 8);
			CARD_VALUES.put("Nine", 9);
			CARD_VALUES.put("Ten", 10);
			CARD_VALUES.put("Jack", 11);
			CARD_VALUES.put("Queen", 12);
			CARD_VALUES.put("King", 13);
			CARD_VALUES.put("Ace", 14);
		}
	}

	private final String face; // face of card ie..."Ace","Deuce", "King"..etc
	private final String suit; // suit of card ie "Heart", "Diamond"

	private final int value;
	// two argument card constructor

	public Card(String cardFace, String cardSuit) {
		face = cardFace;
		suit = cardSuit;
		// Convert it to a value
		value = CARD_VALUES.get(cardFace);
	}
	public String toString() {
		return face + " of " + getSuitUnicode();
	}

	public String toShortString(){
		if(value == -1) return "?";
		if(value < 11) return value + "";
		return face.charAt(0) + "";
	}
	public int getFaceValue() {
		return value;
	}
	public String getSuitUnicode() {
		switch (suit) {
			case "Hearts":
				return "♥";
			case "Diamonds":
				return "◆";
			case "Spades":
				return "♠";
			case "Clubs":
				return "♣";
			case "?":
				return "?";
			default:
				throw new IllegalArgumentException("Invalid suit of " + suit + "!");
		}
	}

	public String getFace() {
		return face;
	}

	public String getSuit() {
		return suit;
	}
}

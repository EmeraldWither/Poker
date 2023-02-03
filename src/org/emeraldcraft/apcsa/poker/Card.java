package org.emeraldcraft.apcsa.poker;

import static org.emeraldcraft.apcsa.poker.Card.Constants.CARD_VALUES;

import java.util.HashMap;

// Card class represents a playing card

public class Card {
	public static class Constants {
		public static final HashMap<String, Integer> CARD_VALUES = new HashMap<>();
		static {
			// lazy way out
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
	public static class CardPrinter{
		public static void printDeck(Hand hand){
			Card[] cards = hand.getCards();
			StringBuilder card = new StringBuilder();
			//Add the top row
			for (int i = 0; i < 5; i++) card.append(" -----------      ");
			card.append("\n");
			//Print out the face row
			card.append(getFaceRow(cards));
			//print out suit
			card.append(getSuitRow(cards));
			//Put a wall
			for (int j = 0; j < 5; j++) {
				card.append("|           |     ");
			}
			card.append("\n");
			//do above but in reverse
			card.append(getSuitRow(cards));
			card.append(getFaceRow(cards));
			for (int i = 0; i < 5; i++) card.append(" -----------      ");
			System.out.println(card);
		}
		private static String getFaceRow(Card[] hand){
			StringBuilder card = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				if(hand[i].toShortString().length() == 2){
					//exists so if we get "10", it does not mess with the spacing of the cards
					card.append("| ").append(hand[i].toShortString()).append("     ").append(hand[i].toShortString()).append(" |     ");
					continue;
				}
				card.append("| ").append(hand[i].toShortString()).append("       ").append(hand[i].toShortString()).append(" |     ");
			}
			card.append("\n");
			return card.toString();
		}
		private static String getSuitRow(Card[] hand){
			StringBuilder card = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				card.append("| ").append(hand[i].getSuitUnicode()).append("       ").append(hand[i].getSuitUnicode()).append(" |     ");
			}
			card.append("\n");
			return card.toString();
		}


	}

	private final String face; // face of card ie..."Ace","Deuce", "King"..etc
	private final String suit; // suit of card ie "Heart", "Diamond"

	public int getValue() {
		return value;
	}
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
		if(value < 11){
			return value + "";
		}
		return face.charAt(0) + "";
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

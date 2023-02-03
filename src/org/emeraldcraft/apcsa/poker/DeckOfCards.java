package org.emeraldcraft.apcsa.poker;

import java.util.Random;

public class DeckOfCards
{
	private final Card[] deck;  // array of Card objects
	private int currentCard; // index of next Card to be dealt
	private final int NUMBER_OF_CARDS = 52;
	
	// Constructor fills the deck of Cards
	public DeckOfCards()
	{
		String faces[] = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven",
		"Eight", "Nine", "Ten", "Jack", "Queen", "King"};
		
		String suits[] = { "Hearts", "Diamonds", "Clubs", "Spades"};
	
		deck = new Card[NUMBER_OF_CARDS];  // create our array of card objects
	
		currentCard = 0;  //set currentCard so firstCard dealt is deck[0]
		
		// populate the deck with card objects
		
		for (int count=0; count<deck.length; count++)
		{
			deck[count] = new Card( faces[count % 13], suits[count/13] );
		}
	}
	
	public void shuffle()
	{
		Random generator = new Random();

		// for each Card, pick another random Card and swap them
		for ( int first=0; first < deck.length; first++)
		{
			// select a random number between 0 and 51
			int second = generator.nextInt(NUMBER_OF_CARDS);
			
			// swap current Card with randomly selected Card
			
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
		currentCard = 0;
	}
	
	
	public Card dealCard()
	{
		if (currentCard < deck.length)
		{
			return deck[currentCard++];
		}
		else
		{
			return null;
		}
	
	
	}
}

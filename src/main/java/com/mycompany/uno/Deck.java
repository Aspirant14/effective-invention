/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author torre
 */
package com.mycompany.uno;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
public class Deck {
    private ArrayDeque<Card> cards;
    final int ALL_COLORS = 5;
    final int START_SIZE = 108;
    final int ALL_RANKS = 15;
    public Deck() {
        final int WILD = 13;
        cards = new ArrayDeque<>();
        Color[] notBlack = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
        Rank[] twoOfEach = Arrays.copyOfRange(Rank.values(), 1, WILD);
        for (Color c : notBlack){
            cards.addLast(new Card(c, Rank.ZERO));
            for (Rank r : twoOfEach){
                cards.addLast(new Card(c, r));
                cards.addLast(new Card(c, r));
            }
            cards.addLast(new Card(Color.BLACK, Rank.WILD));
            cards.addLast(new Card(Color.BLACK, Rank.WILD_4));
        }
    }
    // Returns size of array, not contents of array.
    public int size(){return cards.size();}
    public Card pop(){return cards.pop();}
    public void push(Card c){cards.push(c);}
    private void reverseDeck(){
        int i = 0;
        Iterator<Card> backward = cards.descendingIterator();
        Card[] temp = new Card[START_SIZE];
        while(backward.hasNext()) temp[i++] = backward.next();
        cards.clear();
        for(Card c: temp) cards.addLast(c);
    }
    public void sortColor(boolean desc){
        int position;
        Card current;
        Color color;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map. Black is the fifth color.
        int[] prefixSums = new int[ALL_COLORS];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            color = it.next().getColor();
            if (desc) prefixSums[color.flipColor().ordinal()]++;
            else prefixSums[color.ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < ALL_COLORS; i++) prefixSums[i] += prefixSums[i - 1];
        // Reverse traverse
        while(backward.hasNext()){
            current = backward.next();
            color = current.getColor();
            /* The color of the card determines the sum needed.
               The colors are mapped to the prefix sums in reverse order.
               The sum is decremented.
               The prefix sum is one off from the intended position. */
            if (desc) position = prefixSums[color.flipColor().ordinal()]-- - 1;
            else position = prefixSums[color.ordinal()]-- - 1;
            temp[position] = current;
        }
        cards.clear();
        for(Card c: temp)     cards.addLast(c);
    }
    public void sortRank(boolean desc){
        int position;
        Card current;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map. Black is the fifth color.
        int[] prefixSums = new int[ALL_RANKS];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            current = it.next();
            if (desc) prefixSums[
                    switch(current.getRank()){
                        case Rank.ZERO -> 14;
                        case Rank.ONE -> 13;
                        case Rank.TWO -> 12;
                        case Rank.THREE -> 11;
                        case Rank.FOUR -> 10;
                        case Rank.FIVE -> 9;
                        case Rank.SIX -> 8;
                        case Rank.SEVEN -> 7;
                        case Rank.EIGHT -> 6;
                        case Rank.NINE -> 5;
                        case Rank.SKIP -> 4;
                        case Rank.REVERSE -> 3;
                        case Rank.DRAW_TWO -> 2;
                        case Rank.WILD -> 1;
                        case Rank.WILD_4 -> 0;
            }]++;
            else prefixSums[current.getRank().ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < ALL_RANKS; i++) prefixSums[i] += prefixSums[i - 1];
        // Reverse traverse
        while(backward.hasNext()){
            current = backward.next();
            /* The color of the card determines the sum needed.
               The colors are mapped to the prefix sums in reverse order.
               The sum is decremented.
               The prefix sum is one off from the intended position. */
            if (desc) position = prefixSums[
                    switch(current.getRank()){
                        case Rank.ZERO -> 14;
                        case Rank.ONE -> 13;
                        case Rank.TWO -> 12;
                        case Rank.THREE -> 11;
                        case Rank.FOUR -> 10;
                        case Rank.FIVE -> 9;
                        case Rank.SIX -> 8;
                        case Rank.SEVEN -> 7;
                        case Rank.EIGHT -> 6;
                        case Rank.NINE -> 5;
                        case Rank.SKIP -> 4;
                        case Rank.REVERSE -> 3;
                        case Rank.DRAW_TWO -> 2;
                        case Rank.WILD -> 1;
                        case Rank.WILD_4 -> 0;
            }]-- - 1;
            else position = prefixSums[current.getRank().ordinal()]-- - 1;
            
            temp[position] = current;
        }
        cards.clear();
        for(Card c: temp)     cards.addLast(c);
    }
    @Override
    public String toString(){
        String s = "";
        for (Card card : cards)
            s = s + card.toString() + "\n";
        return s.trim();
    }
}

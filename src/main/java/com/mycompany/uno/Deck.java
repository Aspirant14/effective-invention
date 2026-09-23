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
        }
        for (int i = 0; i < 4; i++)
            cards.addLast(new Card(Color.BLACK, Rank.WILD));
        for (int i = 0; i < 4; i++)
            cards.addLast(new Card(Color.BLACK, Rank.WILD_4));
    }
    // Returns size of array, not contents of array.
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
    public void shuffle(){
        int random;
        Card hold;
        Card[] temp = cards.toArray(Card[]::new);
        // For all except the first element:
        // Set the last index
        // Using the last index as a limit, choose a random index.
        // Swap the random index with the last index. Decrement last index.
        for (int last = cards.size() - 1; last > 1; last--){
            random = (int)(Math.random() * cards.size());
            hold = temp[random];
            temp[random] = temp[last];
            temp[last] = hold;
        }
        cards.clear();
        for (Card c : temp) cards.addLast(c);
    }
    public int size(){return cards.size();}
    public void sortColor(boolean desc){
        int position;
        Card current;
        Color color;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map.
        int[] prefixSums = new int[ALL_COLORS];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            current = it.next();
            color = desc ? current.getColor().flipColor() : current.getColor();
            prefixSums[color.ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < ALL_COLORS; i++) prefixSums[i] += prefixSums[i - 1];
        // Traverse the cards in reverse order and place them.
        while(backward.hasNext()){
            current = backward.next();
            color = desc ? current.getColor().flipColor() : current.getColor();
            /* The color of the card determines the sum needed.
               The colors are mapped to the prefix sums in reverse order.
               The sum is decremented.
               The prefix sum is one off from the intended position. */
            position = prefixSums[color.ordinal()]-- - 1;
            temp[position] = current;
        }
        cards.clear();
        for(Card c: temp)     cards.addLast(c);
    }
    public void sortRank(boolean desc){
        int position;
        Card current;
        Rank rank;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map.
        int[] prefixSums = new int[ALL_RANKS];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            current = it.next();
            rank = desc ? current.getRank().flipRank() : current.getRank();
            prefixSums[rank.ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < ALL_RANKS; i++) prefixSums[i] += prefixSums[i - 1];
        // Traverse the cards in reverse order and place them.
        while(backward.hasNext()){
            current = backward.next();
            /* The color of the card determines the sum needed.
               The colors are mapped to the prefix sums in reverse order.
               The sum is decremented.
               The prefix sum is one off from the intended position. */
            rank = desc ? current.getRank().flipRank() : current.getRank();
            position = prefixSums[rank.ordinal()]-- - 1;
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

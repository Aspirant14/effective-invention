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
    final int BLACK = 4;
    final int START_SIZE = 108;
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
    public void sortColorAsc(){
        int position;
        Card current;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map.
        int[] prefixSums = new int[BLACK + 1];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            current = it.next();
            prefixSums[current.getColor().ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < BLACK + 1; i++) prefixSums[i] += prefixSums[i - 1];
        // Reverse traverse
        while(backward.hasNext()){
            current = backward.next();
            // The color of the card determines the sum needed. 0-based array.
            position = prefixSums[current.getColor().ordinal()] - 1;
            temp[position] = current;
        }
        cards.clear();
        for(Card c : temp) cards.addLast(c);
    }
    public void sortColor(boolean desc){
        int position;
        Card current;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map. Black is the fifth color.
        int[] prefixSums = new int[BLACK + 1];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        if (cards.isEmpty()) return;
        while(it.hasNext()){
            current = it.next();
            if (desc) prefixSums[
                    switch(current.getColor()){
                        case Color.RED -> 4;
                        case Color.GREEN -> 3;
                        case Color.YELLOW -> 2;
                        case Color.BLUE -> 1;
                        case Color.BLACK -> 0;
            }]++;
            else prefixSums[current.getColor().ordinal()]++;
        }
        // Convert map to prefix sum
        for(int i = 1; i < BLACK + 1; i++) prefixSums[i] += prefixSums[i - 1];
        // Reverse traverse
        while(backward.hasNext()){
            current = backward.next();
            /* The color of the card determines the sum needed.
               The colors are mapped to the prefix sums in reverse order.
               The sum is decremented.
               The prefix sum is one off from the intended position. */
            if (desc) position = prefixSums[
                    switch(current.getColor()){
                        case Color.RED -> 4;
                        case Color.GREEN -> 3;
                        case Color.YELLOW -> 2;
                        case Color.BLUE -> 1;
                        case Color.BLACK -> 0;
                    }]-- - 1;
            else position = prefixSums[current.getColor().ordinal()]-- - 1;
            
            temp[position] = current;
        }
        cards.clear();
        for(Card c: temp)     cards.addLast(c);
    }
    public void sortRankAsc(){
        int position;
        Card current;
        Iterator<Card> it = cards.iterator(),
                backward = cards.descendingIterator();
        // Make a frequency map.
        int[] prefixSums = new int[4];
        // Make temporary array.
        Card[] temp = new Card[START_SIZE];
        while(it.hasNext()){
            prefixSums[it.next().getRank().ordinal()]++;
        }
        // Convert frequencies to prefix sums.
        for(int i = 1; i < 4; i++) prefixSums[i] += prefixSums[i - 1];
        // Reverse traverse
        while(backward.hasNext()){
            current = backward.next();
            // The color of the card determines the sum needed. 0-based array.
            position = prefixSums[current.getRank().ordinal()] - 1;
            temp[position] = current;
        }
        // Copy the sorted array.
        cards.clear();
        for (Card c : temp) cards.addLast(c);
    }
    public void sortRankDesc(){
        this.sortRankAsc();
        this.reverseDeck();
    }
    @Override
    public String toString(){
        String s = "";
        for (Card card : cards)
            s = s + card.toString() + "\n";
        return s.trim();
    }
}

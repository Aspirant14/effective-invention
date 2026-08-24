/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uno;

/**
 *  Make this class iterable?
 * @author torre
 */
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
public class Deck {
    public Card[] cards = new Card[108];
    public Deck() {
        final int BLACK = 4;
        final int WILD = 13;
        int i = 0;
        Color[] notBlack = Arrays.copyOfRange(Color.values(), 0, BLACK);
        Rank[] twoOfEach = Arrays.copyOfRange(Rank.values(), 1, WILD);
        for (Color c : notBlack){
            cards[i++] = new Card(c, Rank.ZERO);
            for (Rank r : twoOfEach){
                cards[i++] = new Card(c, r);
                cards[i++] = new Card(c, r);
            }
            cards[i++] = new Card(Color.BLACK, Rank.WILD);
            cards[i++] = new Card(Color.BLACK, Rank.WILD_4);
        }
    }
    // Returns size of array, not contents of array.
    public int getLength(){return cards.length;}
    public void shuffle(){
        List<Card> list = Arrays.asList(cards);
        Collections.shuffle(list);
        list.toArray(cards);
    }
    public void sortColorAsc(){
        for(int i = 0; i < cards.length; i++){
            // Set the ith element as the minimum.
            int minimum = i;
            for (int current = i; current < cards.length; current++){
                // This assumes that the Rank & Color enums are ordered.
                if (cards[current].getColor().ordinal() < minimum)
                    minimum = i;
            }
            if (cards[i].getColor().ordinal()
                    < cards[minimum].getColor().ordinal())
                swap(i, minimum);
        }
    }
    public void sortColorDesc(){
        for (int i = 0; i < cards.length; i++){
            int maximum = i;
            for (int current = i; current < cards.length; current++){
                if (cards[current].getColor().ordinal() > maximum)
                    maximum = i;
            }
            if (cards[i].getColor().ordinal()
                    > cards[maximum].getColor().ordinal())
                swap(i, maximum);
        }
    }
    public void sortRankAsc(){
        for (int i = 0; i < cards.length; i++){
            int minimum = i;
            for (int current = i; current < cards.length; current++) {
                if (cards[current].getRank().ordinal() < minimum)
                    minimum = i;
            }
            if (cards[i].getRank().ordinal() < minimum)
                swap(i, minimum);
        }
    }
    public void sortRankDesc(){
        for (int i = 0; i < cards.length; i++){
            int maximum = i;
            for (int current = i; current < cards.length; current++) {
                if (cards[current].getRank().ordinal() < maximum)
                    maximum = i;
            }
            if (cards[i].getRank().ordinal() > maximum)
                swap(i, maximum);
        }
    }
    public void sort(boolean type, boolean asc){
        for (int i = 0; i < cards.length; i++){
            int minimum = i;
            if (asc){
                for (int current = i; current < cards.length; current++){
                    if((type ? cards[current].getColor().ordinal()
                            : cards[current].getRank().ordinal()) < minimum)
                      minimum = i;
                }
            }
            for (int current = i; current < cards.length; current++){
                if ((type ? cards[current].getColor().ordinal()
                        : cards[current].getRank().ordinal()) > minimum)
                    minimum = i;
            }
            if (cards[i].getColor().ordinal()
                    > cards[minimum].getColor().ordinal())
                swap(i, minimum);
        }
    }
    private void swap(int a, int b){
        Card temp = cards[a];
        cards[a] = cards[b];
        cards[b] = temp;
    }
    @Override
    public String toString(){
        String s = "";
        for (Card card : cards)
            s = s + card.toString() + "\n";
        return s.trim();
    }
}

package com.company;


public class Deck  {

    private Card [] cards;
    private int length;
    {
        length = 36;
        cards = new Card[36];
    }

    public Deck(){
        for (int i = 0; i <cards.length ; i++) {
            cards[i] = new Card(Rank.values[i % Rank.values.length],
                    Suit.values[i % Suit.values.length]);
        }
    }

    private void swap(int ind1, int ind2){
        Card swap;
        swap = cards[ind1];
        cards[ind1] = cards[ind2];
        cards[ind2] = swap;
    }

    private void shuffle() {
        for (int i = 0; i <length ; i++) {
            swap(i,(int)(Math.random()*(length-1)));
        }
    }

    private boolean isBigger(int first , int second){
        int firstsuit = -1 , secondsuit = -1;
        for (int i = 0; i <Suit.values.length ; i++) {
            if(cards[first].getSuit() == Suit.values[i]) firstsuit = i;
            if(cards[second].getSuit() == Suit.values[i]) secondsuit = i;
        }
        if(firstsuit < secondsuit) return true;
        else if(firstsuit > secondsuit) return false;
        else {
            int firstrank = -1, secondrank = -1;
            for (int i = 0; i <Rank.values.length ; i++) {
                if(cards[first].getRank() == Rank.values[i]) firstrank = i;
                if(cards[second].getRank() == Rank.values[i]) secondrank = i;
            }
            return firstrank < secondrank;
        }
    }

    private void order() {
        for (int i = 0; i < length - 1 ; i++) {
            for (int j = 0; j < length - i - 1 ; j++) {
                if(!(isBigger(j,j+1)))
                    swap(j,j+1);
            }
        }
    }

    private boolean hasNext() {
        int counter;
        {
            counter = 0;
        }
        return counter < length;
    }

    private Card drawOne() {
        while (hasNext()) {
            return cards[--length];
        }
        return null;
    }
}

package exempl;

import java.util.SortedSet;
import java.util.TreeSet;

public class PokerHand implements Comparable<PokerHand> {

    private TreeSet<Card> cards = new TreeSet<Card>(); 
    private int[] values;
    
    public PokerHand() {}
    
    PokerHand (String str) {
        this.values = new int[5];

        String[] s = str.split(" ");
        for (String i : s) {
            char value = i.charAt(0);
            char suit = i.charAt(1);
            if ((value>='1' && value <= '9' || value == 'T' || value =='J' || value == 'Q' || value == 'K' || value == 'A') 
            		&& (suit == 'S' || suit == 'H' || suit == 'D' || suit == 'C') && i.length() == 2) {
                Card temp = new Card(i);
                this.cards.add(temp);
            }
        }

        if (this.cards.size()!=5)
            throw new IllegalArgumentException("Only five cards in hand!");

        int k = 0;
        for (Card i : this.cards) {
            this.values[k] = i.getValueToInt();
            k++;
        }
    }
    
    public TreeSet<Card> getCards() {
        return this.cards;
    }


    public void setCards(TreeSet<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return this.cards.toString();
    }

    public int compareTo(PokerHand o) {
        int result1;
        int result2;
        int resultHighcard = this.checkToHighcard(o);

        result1 = o.checkToRoyalFlush();
        result2 = this.checkToRoyalFlush();
        if (result1 != result2) {
            return result1 - result2;
        }

        result1 = o.checkToStraightFlush();
        result2 = this.checkToStraightFlush();
        if (result1 != result2) {
            return result1 - result2;
        }

        result1 = o.checkToFour();
        result2 = this.checkToFour();
        if (result1 != result2) {
            return result1 - result2;
        }
        if ((result1 != 0) && (result2 != 0) && (resultHighcard > 0)) {
            return resultHighcard;
        }

        result1 = o.checkToFullHouse();
        result2 = this.checkToFullHouse();
        if (result1 != result2) {
            return result1 - result2;
        }

        result1 = o.checkToFlush();
        result2 = this.checkToFlush();
        if (result1 != result2) {
            return result1 - result2;
        }

        result1 = o.checkToStraight();
        result2 = this.checkToStraight();
        if (result1 != result2) {
            return result1 - result2;
        }

        result1 = o.checkToThree();
        result2 = this.checkToThree();
        if (result1 != result2) {
            return result1 - result2;
        }
        if ((result1 != 0) && (result2 != 0) && (resultHighcard > 0)) {
            return resultHighcard;
        }

        result1 = o.checkToTwo();
        result2 = this.checkToTwo();
        if (result1 != result2) {
            return result1 - result2;
        }
        if ((result1 != 0) && (result2 != 0) && (resultHighcard > 0)) {
            return resultHighcard;
        }

        result1 = o.checkToPair();
        result2 = this.checkToPair();
        if (result1 != result2) {
            return result1 - result2;
        }
        if ((result1 != 0) && (result2 != 0) && (resultHighcard > 0)) {
            return resultHighcard;
        }

        return resultHighcard;
    }

    //Выдача старшей карты для двух рук
    public int checkToHighcard(PokerHand o) { 
        for (int i = this.values.length-1; i >= 0; i--) {
            if (o.values[i] != this.values[i]) {
                return o.values[i] - this.values[i];
            }
        }
        return 0;
    }


    //проверка на Пару
    public int checkToPair() { //2

        if (this.values[3] == this.values[4]) {
            return this.values[4];
        }
        if (this.values[2] == this.values[3]) {
            return this.values[3];
        }
        if (this.values[1] == this.values[2]) {
            return this.values[2];
        }
        if (this.values[0] == this.values[1]) {
            return this.values[1];
        }
        return 0;
    }

    //проверка на Две Пары
    public int checkToTwo() { 

        if ((this.values[1] == this.values[2]) && (this.values[3] == this.values[4])) {
            return this.values[4]*15 + this.values[2];
        }
        if ((this.values[0] == this.values[1]) && (this.values[3] == this.values[4])) {
            return this.values[4]*15+this.values[1];
        }
        if ((this.values[0] == this.values[1]) && (this.values[2] == this.values[3])) {
            return this.values[3]*15 + this.values[1];
        }
        return 0;
    }

    //Проверка на Сет
    public int checkToThree() { 

        if ((this.values[0] == this.values[1]) && (this.values[1] == this.values[2])) {
            return this.values[0];
        }
        if ((this.values[1] == this.values[2]) && (this.values[2] == this.values[3])) {
            return this.values[1];
        }
        if ((this.values[2] == this.values[3]) && (this.values[3] == this.values[4])) {
            return this.values[2];
        }
        return 0;
    }


    //Проверка на Стрит
    public int checkToStraight() { 

        for (int i=0; i < this.values.length - 1; i++) {
            if (this.values[i+1] != this.values[i] + 1) {
                return 0;
            }
        }
        return this.values[4];
        
    }

    //Проверка на Флеш
    public int checkToFlush() { 

        char tempSuit = this.cards.first().getSuit();

        for (Card current : this.cards) {
            if (current.getSuit() != tempSuit) { 
                return 0;
            }
        }
        return this.cards.last().getValueToInt();

    }


    //Проверка Фул Хаус
    public int checkToFullHouse() { //7

        if ((this.values[0] == this.values[1]) && ((this.values[2] == this.values[3]) && (this.values[3] == this.values[4]))) {
            return this.values[4]*15 + this.values[0];
        }
        if ((this.values[4] == this.values[3]) && ((this.values[2] == this.values[1]) && (this.values[1] == this.values[0]))) {
            return this.values[0]*15 + this.values[4];
        }
        return 0;
    }

    //ПРоверка на Каре
    public int checkToFour() {
        if ((this.values[0] == this.values[1]) &&
                (this.values[1] == this.values[2]) &&
                (this.values[2] == this.values[3])) {
            return this.values[0];
        }
        if ((this.values[1] == this.values[2]) &&
                (this.values[2] == this.values[3]) &&
                (this.values[3] == this.values[4])) {
            return this.values[4];
        }
        return 0;

    }

    //Проверка на Стрит Флеш
    public int checkToStraightFlush() { 
        char tempSuit = this.cards.first().getSuit();
        int tempValue = this.cards.first().getValueToInt();

        for (Card current : this.cards) {
            if ((current.getValueToInt() != tempValue) || (current.getSuit()!=tempSuit)) {
                return 0;
            }
            tempValue++;
        }
        return this.cards.last().getValueToInt();
    }

    //Проверка на Роял Флеш
    public int checkToRoyalFlush() { 

        char tempSuit = this.cards.first().getSuit();
        int tempValue = 10;

        for (Card current : this.cards) {
            if ((current.getValueToInt() != tempValue) || (current.getSuit() != tempSuit)) {
                return 0;
            }
            tempValue++;
        }
        return this.cards.last().getValueToInt();
    }

}

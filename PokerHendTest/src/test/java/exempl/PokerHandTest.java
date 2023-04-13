package exempl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PokerHandTest {
    //•	первый символ — это номинал карты. Допустимые значения: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce);
    //•	второй символ — масть. Допустимые значения: S(pades), H(earts), D(iamonds), C(lubs).

    @Test
    
    void checkToHighcard() {
        assertAll(
                () -> assertEquals(-2, new PokerHand("AS AC AH 2S 2D").checkToHighcard(new PokerHand("AS AC QH 2S 2D"))), 
                () -> assertEquals(0, new PokerHand("AS AC AH 2S 2D").checkToHighcard(new PokerHand("AS AC AH 2S 2D"))) 
        );
    }

    @Test
    void checkToPair() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH 2S 2D").checkToPair()), 
                () -> assertEquals(2, new PokerHand("2C 2D 2H AS 2S").checkToPair()), 
                () -> assertEquals(9, new PokerHand("2D 5S 5D 9H 9D").checkToPair()), 
                () -> assertEquals(9, new PokerHand("2D 5S 9D 9H 9S").checkToPair()), 
                () -> assertEquals(5, new PokerHand("2D 5S 5D 8H 9D").checkToPair()), 
                () -> assertEquals(14, new PokerHand("2D AS AD 9H KD").checkToPair()), 

                () -> assertEquals(0, new PokerHand("8C 3C 7D 6C 2S").checkToPair()), 
                () -> assertEquals(0, new PokerHand("9C KC AS 7C 8H").checkToPair()) 
        );
    }

    @Test
    void checkToTwo() {
        assertAll(
                () -> assertEquals(212, new PokerHand("AS AC AH 2S 2D").checkToTwo()), 
                () -> assertEquals(32, new PokerHand("2C 2D 2H AS 2S").checkToTwo()), 
                () -> assertEquals(140, new PokerHand("2D 5S 5D 9H 9D").checkToTwo()), 
                () -> assertEquals(219, new PokerHand("2D AS AD 9H 9D").checkToTwo()), 

                () -> assertEquals(0, new PokerHand("8C 8S 7D 6C 2S").checkToTwo()), 
                () -> assertEquals(0, new PokerHand("9C KC AS 7C 8H").checkToTwo()) 
        );
    }

    @Test
    void checkToThree() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH 2S 2D").checkToThree()), 
                () -> assertEquals(2, new PokerHand("2C 2D 2H AS 2S").checkToThree()),
                () -> assertEquals(9, new PokerHand("2D 5S 9D 9H 9C").checkToThree()), 
                () -> assertEquals(8, new PokerHand("8C JC 8D 6C 8S").checkToThree()), 

                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToThree()) 
        );
    }

    @Test
    void checkToStraight() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToStraight()), 
                () -> assertEquals(12, new PokerHand("9D TD JD 8D QD").checkToStraight()), 
                () -> assertEquals(12, new PokerHand("9S TH JS 8C QD").checkToStraight()), 
                () -> assertEquals(9, new PokerHand("9H 7C 5H 6H 8S").checkToStraight()), 

                () -> assertEquals(0, new PokerHand("9C 8C 7C 6C AH").checkToStraight()), 
                () -> assertEquals(0, new PokerHand("AH 2C 3C 5C 4C").checkToStraight()) 
        );
    }

    @Test
    void checkToFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToFlush()),
                () -> assertEquals(12, new PokerHand("9D TD JD 8D QD").checkToFlush()), 
                () -> assertEquals(13, new PokerHand("3S TS 7S 8S KS").checkToFlush()),
                () -> assertEquals(14, new PokerHand("AH 7H 2H 6H TH").checkToFlush()), 
                () -> assertEquals(12, new PokerHand("2D 4D 7D 6D QD").checkToFlush()), 
                () -> assertEquals(14, new PokerHand("2C 8C TC AC 5C").checkToFlush()), 


                () -> assertEquals(0, new PokerHand("2H 9S 7S 9C 2S").checkToFlush()), 
                () -> assertEquals(0, new PokerHand("8S 8H 7H 6H JH").checkToFlush()), 
                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToFlush()) 
        );
    }

    @Test
    void checkToFullHouse() {
        assertAll(
                () -> assertEquals(212, new PokerHand("AS AC AH 2S 2D").checkToFullHouse()), 
                () -> assertEquals(44, new PokerHand("2C AH 2H AS 2S").checkToFullHouse()), 
                () -> assertEquals(0, new PokerHand("2D 9S 9D 9H 9C").checkToFullHouse()), 
                () -> assertEquals(0, new PokerHand("8C 8D 7D 6C 8S").checkToFullHouse()), 
                () -> assertEquals(0, new PokerHand("9C KC 9S 7C 8H").checkToFullHouse()) 
        );
    }

    @Test
    void checkToFour() {
        assertAll(
                () -> assertEquals(14, new PokerHand("AS AC AH AD QD").checkToFour()), 
                () -> assertEquals(2, new PokerHand("2C 2H 5C 2D 2S").checkToFour()), 
                () -> assertEquals(9, new PokerHand("2D 9S 9D 9H 9C").checkToFour()), 
                () -> assertEquals(0, new PokerHand("8C 8D 7D 6C 8S").checkToFour()), 
                () -> assertEquals(0, new PokerHand("9C KC 7S KD 5H").checkToFour()) 
        );
    }

    @Test
    void checkToStraightFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToStraightFlush()), 
                () -> assertEquals(12, new PokerHand("9S TS JS 8S QS").checkToStraightFlush()),
                () -> assertEquals(9, new PokerHand("9H 7H 5H 6H 8H").checkToStraightFlush()), 
                () -> assertEquals(6, new PokerHand("2D 4D 3D 6D 5D").checkToStraightFlush()), 
                () -> assertEquals(9, new PokerHand("9C 8C 7C 6C 5C").checkToStraightFlush()), 
                () -> assertEquals(0, new PokerHand("9C 8C 7C 6C 5H").checkToStraightFlush()), 
                () -> assertEquals(0, new PokerHand("9H 8C 7C 6C 5C").checkToStraightFlush()) 
        );
    }

    @Test
    void checkToRoyalFlush() {
        assertAll(
                () -> assertEquals(14, new PokerHand("KS TS JS AS QS").checkToRoyalFlush()), 
                () -> assertEquals(14, new PokerHand("TH AH KH QH JH").checkToRoyalFlush()), 
                () -> assertEquals(14, new PokerHand("AD KD QD JD TD").checkToRoyalFlush()),
                () -> assertEquals(14, new PokerHand("TC JC QC KC AC").checkToRoyalFlush()), 
                () -> assertEquals(0, new PokerHand("TH JC QC KC AC").checkToRoyalFlush()), 
                () -> assertEquals(0, new PokerHand("TC JC QC KC AD").checkToRoyalFlush()), 
                () -> assertEquals(0, new PokerHand("2C JC QC KC AC").checkToRoyalFlush()) 
        );
    }


    @Test
    void compareTo() {
        assertAll(
                () -> assertEquals(2, new PokerHand("2S 7C 6H AS TD").compareTo(new PokerHand("AS QC 2H 5S 8D"))), 

                () -> assertEquals(3, new PokerHand("2S 6C 6H AS TD").compareTo(new PokerHand("AS QC 2H 9S 9D"))), 
                () -> assertEquals(1, new PokerHand("2S 9C 9H KS TD").compareTo(new PokerHand("AS QC 2H 9S 9D"))),

                () -> assertEquals(9, new PokerHand("8S TC TH 9S 8D").compareTo(new PokerHand("2S 2C 6H JS JD"))), 
                () -> assertEquals(1, new PokerHand("5S JC JH 9S 5D").compareTo(new PokerHand("6S 6C 3H JS JD"))),
                () -> assertEquals(1, new PokerHand("6S JC JH 9S 6D").compareTo(new PokerHand("6S 6C TH JS JD"))),

                () -> assertEquals(4, new PokerHand("2S JC 2H 9S 2D").compareTo(new PokerHand("6S 6C TH JS 6D"))), 
                () -> assertEquals(1, new PokerHand("6S 7C 6H 9S 6D").compareTo(new PokerHand("6S 6C TH 8S 6D"))), 

                () -> assertEquals(1, new PokerHand("3S 2C 5H 6S 4D").compareTo(new PokerHand("5S 3C 4H 7S 6D"))),

                () -> assertEquals(4, new PokerHand("9S 2S TS 6S 4S").compareTo(new PokerHand("5S AS TS 7S 9S"))), 

                () -> assertEquals(3, new PokerHand("4S AC 4H AS 4D").compareTo(new PokerHand("5S 5C 2H 2S 5D"))), 
                () -> assertEquals(1, new PokerHand("5S KC 5H KS 5D").compareTo(new PokerHand("5S 5C AH AS 5D"))),

                () -> assertEquals(1, new PokerHand("TS TC TH AS TD").compareTo(new PokerHand("JS JC JH 2S JD"))), 
                () -> assertEquals(1, new PokerHand("TS TC TH 7S TD").compareTo(new PokerHand("TS TC TH 8S TD"))),

                () -> assertEquals(5, new PokerHand("6S 2S 5S 3S 4S").compareTo(new PokerHand("JS 8S TS 7S 9S"))), 
                () -> assertEquals(11, new PokerHand("9S 2S TS 6S 4S").compareTo(new PokerHand("JS 8S TS 7S 9S"))), 

                () -> assertEquals(14, new PokerHand("6S 2S 5S 3S 4S").compareTo(new PokerHand("JS KS TS AS QS"))) 
        );
    }
}

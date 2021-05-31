package com.KayraDev;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    static ArrayList<String> deck = new ArrayList<String>();
    static HashMap<String, Integer> valueOfCards = new HashMap<String, Integer>();
    static Scanner obj = new Scanner(System.in);
    static int sumHouse = 0;
    static int sumPlayer = 0;
    static int balance = 100;
    static int bet;
    static ArrayList<String> house = new ArrayList<String>();
    static ArrayList<String> player = new ArrayList<String>();

    public static void main(String[] args) {

        System.out.println("Hello there! This is a Blackjack game");
        System.out.println("1- Start new game");
        System.out.println("2- Exit");
        int input = obj.nextInt();
        if (input==1){
            createDecks();
            startNewGame();
        }
    }
    //This starts a new game.
    // This line added from github.com
    public static void startNewGame(){
        if(deck.size() < 52){
            deck.clear();
            createDecks();
        }

        System.out.println("Please enter your bet!");
        bet = obj.nextInt();
        if (bet<=balance) {
            game();
        } else {
            System.out.println("You dont have enough money. Please enter a valid amount!!!");
            startNewGame();
        }
    }

    public static void game(){
        player.add(deck.get(0));
        deck.remove(0);
        house.add(deck.get(0));
        deck.remove(0);
        player.add(deck.get(0));
        deck.remove(0);
        house.add(deck.get(0));
        deck.remove(0);

        printHands();
        gameChecker();

    }

    public static void printHands(){
        sumHouse = 0;
        sumPlayer = 0;
        System.out.println("House's hand "+house);

        for (int i = 0; i < house.size(); i++) {
            sumHouse= sumHouse + valueOfCards.get(house.get(i));
        }
        System.out.println("House's hand value is "+sumHouse);
        System.out.println("Player's hand "+player);
        for (int i = 0; i < player.size(); i++) {
            sumPlayer = sumPlayer + valueOfCards.get(player.get(i));
        }
        System.out.println("Player's hand value is "+sumPlayer);
    }

    public static void gameChecker() {
        if (sumHouse >=21 || sumPlayer>=21){
            gameOver();
        } else {
            System.out.println("One more card?");
            System.out.println("1- Yes");
            System.out.println("2- No");
            int input = obj.nextInt();
            if (input == 1){
                player.add(deck.get(0));
                deck.remove(0);
                printHands();
                gameChecker();
            } else {
                while(sumHouse<17){
                    house.add(deck.get(0));
                    deck.remove(0);
                    printHands();
                }

                gameOver();
            }
        }
    }

    public static void createDecks(){
        String suits[] = {"Heart", "Diamonds", "Spade", "Club"};
        String cards[] = {"Joker", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int values[] = {10, 10, 10, 1, 2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < 4; i++){
            for (String suit : suits) {
                for (String card : cards){
                    deck.add(card + " of " + suit);
                }
            }
            for (int z =0; z<52; z++){
                valueOfCards.put(deck.get(z),values[z%13]);
            }
            System.out.println(valueOfCards);
        }
        Collections.shuffle(deck);
    }

    public static void gameOver() {



        if (sumHouse==21 && sumPlayer==21 ){

            System.out.println("game is even");
            System.out.println("you have $" +balance);
        }
        else if( sumHouse==21 && sumPlayer!=21){
            System.out.println("house won");
            balance = balance - bet;
            System.out.println("you have $" +balance);
            System.out.println("game over");
        }
        else if( sumHouse!=21 && sumPlayer==21){
            System.out.println("player won");
            balance = balance + bet;
            System.out.println("you have $" +balance);
            System.out.println("game over");
        }
        else if(sumPlayer>21){
            System.out.println("house won");
            balance = balance - bet;
            System.out.println("you have $" +balance);
        }
        else if(sumHouse>21) {
            System.out.println("player won");
            balance = balance + bet;
            System.out.println("you have $" +balance);
        }
        else if (sumHouse>sumPlayer){
            System.out.println("house won");
            balance = balance - bet;
            System.out.println("you have $" +balance);
        }
        else if (sumPlayer>sumHouse){
            System.out.println("player won");
            balance = balance + bet;
            System.out.println("you have $" +balance);
        }
        else if (sumPlayer==sumHouse){
            System.out.println("game is even");
            System.out.println("you have $" +balance);
        }
        house.clear();
        player.clear();
        System.out.println("deck has " + deck.size());
        if(balance>0) {
            System.out.println("Do you want to continue?");
            System.out.println("1- Yes");
            System.out.println("2- No");
            int input = obj.nextInt();
            if (input == 1){
                startNewGame();
            } else {
                System.out.println("Game Over");
            }
        }

    }
}

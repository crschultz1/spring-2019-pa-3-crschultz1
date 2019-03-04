package com.company;
import java.util.Random;
import java.util.Scanner;


public class Main {
    //Main method
    public static void main(String[] args){
        System.out.println("\n*Welcome to Jolly Jackpot Land!*\n"); //Display welcome message
        int startPoints = 500;  //Set starting points to 500
        selectMenu(startPoints);    //Enter menu method
    }

    //Menu selection method... display menu and return user selection
    public static void selectMenu(int totalPoints){
        Scanner keyboard = new Scanner(System.in);      //New scanner object -> read in values from keyboard
        String userChoice = getMenuChoice(keyboard);    //Take in user's choice from getMenuChoice method
        while (!userChoice.equals("1") && !userChoice.equals("2") && !userChoice.equals("3") && !userChoice.equals("4") && !userChoice.equals("5") && !userChoice.equals("6")){
            System.out.println("Invalid selection, please select a valid option.\n"); //Check to see if user entered valid value
            userChoice = getMenuChoice(keyboard);   //Update userChoice
        }
        if (totalPoints == 0 && ("1".equals(userChoice) || "2".equals(userChoice) || "3".equals(userChoice))){ //Check to see if user has 0 points, if so prompt them to restart or exit
            System.out.println("You have 0 points, please select restart(5) or exit(6).\n");
            userChoice = getMenuChoice(keyboard);
            while(!"4".equals(userChoice) && !"5".equals(userChoice) && !"6".equals(userChoice)){               // If user tries to play a game with 0 points, remind them that they need to restart or exit
                System.out.println("You don't have enough points to do that, please select restart(4) or exit(5).\n");
                userChoice = getMenuChoice(keyboard);   //Update user option
            }
        }
        if ("1".equals(userChoice) || "Slot Machine".equals(userChoice)){   //If user selects 1 or Slot Machine -> Slot Machine Method
            slotMachine(totalPoints);
        }
        if ("2".equals(userChoice) || "Dice Game".equals(userChoice)){      //If user selects 2 or Dice Game -> Dice Game Method
            diceGame(totalPoints);
        }
        if ("3".equals(userChoice) || "Black Jack".equals(userChoice)){     //If user selects 3 or Black Jack -> Black Jack Method
            blackJack(totalPoints);
        }
        if ("4".equals(userChoice) || "Show Points".equals(userChoice)){    //If user selects 4 or Show Points -> Show user totalPoints
            System.out.println("You currently have : " + totalPoints + " points.\n");
            selectMenu(totalPoints);
        }
        if ("5".equals(userChoice) || "Restart".equals(userChoice)){        //If user selects 5 or Restart -> set totalPoints = 500
            System.out.println("Restarting game, you now have 500 points.\n");
            selectMenu(500);
        }
        if ("6".equals(userChoice) || "Exit".equals(userChoice)) {          //If user selects 6 or Exit -> Exit the program
            System.exit(0);
        }
    }

    //Slot machine method...
    public static void slotMachine(int totalPoints){    //Takes in totalPoints value so I can keep track of total points throughout the program
        Scanner keyboard = new Scanner(System.in);  //Declare and Initialize Variables
        int amountWon = 0;                          //Total amount won in each session
        int amountRisk;                             //Amount user would like to risk
        int diffPoints = totalPoints;               //Int variable to keep track of how many points user started with
        String wordOne;                             //First random word
        String wordTwo;                             //Second random word
        String wordThree;                           //Third random word
        int randomNumber;                           //Random number variable
        String yesNo;                               //String variable to keep track of whether the user would like to keep playing
        String randomWords[] = {"Elephant", "Computer", "Football", "Resume", "Capstone", "Crimson"};   //Populate string array with possible words
        System.out.println("~Welcome to the slot machine!~\n\nThe rules can be found below: \n");       //Display Rules
        System.out.println("Our system will randomly generate three words out six words, the payouts will be as follows\n");
        System.out.println("\tNo matching words -> Lose risked amount\n\tTwo matching words -> Earn double risked amount\n\tAll words match -> Earn triple risked amount\n");
        System.out.println("\nWould you like to play? (Y/N)");  //Ask user if they would like to play
        yesNo = keyboard.nextLine();                 //Set yesNo equal to user response
        while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){    //Keep taking in user response until user responds with Y or N
            System.out.println("Invalid response, would you like to play? (Y/N)\n");
            yesNo = keyboard.nextLine();
        }
        while("Y".equals(yesNo) || "y".equals(yesNo)) {   //While user wants to keep playing
            if (totalPoints == 0){                        //Check if user has more than 0 points, if they have 0 points, send them back to main menu
                System.out.println("\nYou have 0 points! Returning you to the main menu.\n");
                selectMenu(totalPoints);
            }
            System.out.println("\nYou currently have " + totalPoints + " points.\n");   //Display current points before allowing user to enter wager
            System.out.println("Please enter the amount you would like to risk: ");     //Prompt user to enter wager amount
            amountRisk = keyboard.nextInt();                                            //Set user response to amountRisk (wager amount)
            while (amountRisk>totalPoints){                                             //While user does not have that many points, keep prompting them to enter a valid wager amount
                System.out.println("You don't have that many points to bet, please enter a valid amount: ");
                amountRisk = keyboard.nextInt();
            }
            Random r = new Random();                      //New random variable
            randomNumber = r.nextInt(randomWords.length); //Generate random number within size of String array randomWords[]
            wordOne = randomWords[randomNumber];          //Generate random word by using random index
            randomNumber = r.nextInt(randomWords.length); //Generate random number within size of String array randomWords[]
            wordTwo = randomWords[randomNumber];          //Generate random word by using random index
            randomNumber = r.nextInt(randomWords.length); //Generate random number within size of String array randomWords[]
            wordThree = randomWords[randomNumber];        //Generate random word by using random index
            System.out.println(wordOne + " " + wordTwo + " " + wordThree + "\n");   //Display 3 random words
            if (wordOne.equals(wordTwo) && wordOne.equals(wordThree)) { //Check to see if all three words are the same
                System.out.println("Congratulations!\nYou have won three times the amount you have bet.\n");
                totalPoints = totalPoints + (amountRisk * 2);   //Update totalPoints by adding amount won (risk*2)
                checkCongrats(totalPoints);               //Check to see if user exceeds 2000 points, if so congratulate
            } else if (wordOne.equals(wordTwo) || wordOne.equals(wordThree) || wordTwo.equals(wordThree)) { //Check to see if user matched 2 words
                System.out.println("Nice!\nYou have won twice the amount you have bet.\n");
                totalPoints = totalPoints + amountRisk;   //Update totalPoints by adding amount won (risk)
                checkCongrats(totalPoints);               //Check to see if user exceeds 2000 points, if so congratulate
            } else {
                System.out.println("Bad luck!\nYou have lost your initial bet!\n"); //If neither of the conditions above are true, user did not match any of the words
                totalPoints = totalPoints - amountRisk;   //Update totalPoints by subtracting amount risked
            }
            System.out.println("Would you like to continue? (Y/N)\n");  //Ask the user if they would like to continue
            yesNo = keyboard.next();                      //Scan in next keyboard value, update while loop
            while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){    //Make sure value is Y or N
                System.out.println("Invalid response, would you like to play? (Y/N)\n");
                yesNo = keyboard.next();
            }
        }
        amountWon = totalPoints - diffPoints;             //Calculate amount won in the session, (points ended with - points started with)
        if (amountWon >= 0 ) {                            //If amountWon is positive, "you won x amount"
            System.out.println("\nYou won " + amountWon + " points in your previous session!\n");
        }
        else if (amountWon < 0 ){                         //If amount is negative, "you lost x amount"
            System.out.println("\nYou lost " + Math.abs(amountWon) + " points in your previous session!\n");
        }
        selectMenu(totalPoints);                          //Considering user selected N for yesNo, return to main menu
    }

    //Dice game method... game choice #2
    public static void diceGame(int totalPoints){   //Takes in totalPoints value so I can keep track of points throughout entire program
        Scanner keyboard = new Scanner(System.in);  //New scanner object to read in from keyboard
        int amountRisk;                             //Amount user wants to wager
        int pointsWon = 0;                          //Total amount user won from each round
        int diffPoints = totalPoints;               //Int variable to keep track of how many points user started with
        int amountWon = 0;                          //Total amount of points user won from each session in total
        String yesNo;                               //String variable to keep track of whether the user would like to keep playing
        String gameChoice;                          //String variable to check which game user would like to play
        System.out.println("\n~Welcome to the Dice Game!~\n");  //Display rules
        System.out.println("\nThe rules can we found below: \n");
        System.out.println("\nOur system will roll two six-sided dice, you will be given the option two select one of two game modes:\n");
        System.out.println("\n*Game Mode #1 : Range*\n\nThe user will select either high or low: \nLow -> (2-5)\nHigh -> (9-12)\nIf the user hits they win double the risk amount.\n");
        System.out.println("\n*Game mode #2 : Amount*\n\nThe user will enter a digit between 2 and 12: \nIf the user hits the selected digit they will win triple the risk amount.\n");
        System.out.println("Would you like to play one of the following games? (Y/N)"); //Ask the user if they would like to play the game
        yesNo = keyboard.nextLine();    //Read in value for yesNo
        while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){    //Keep prompting user to enter yesNo value until Y or N is entered
            System.out.println("Invalid response, would you like to play? (Y/N)\n");    //Display error if user enters invalid response
            yesNo = keyboard.next();    //Update yesNo variable
        }
        while("Y".equals(yesNo) || "y".equals(yesNo)) { //While player wants to keep playing
            if (totalPoints == 0) {                     //If user has no points, send them back to main menu
                System.out.println("\nYou have 0 points! Returning you to the main menu.\n");
                selectMenu(totalPoints);
            }
            System.out.println("\nYou currently have " + totalPoints + " points.\n");       //Display total points
            System.out.println("Please select the game mode you would like to play (Enter \"Menu\" to return to the menu).\n"); //Prompt user to choose which game they would like to play
            gameChoice = keyboard.next();   //Read in gameChoice value
            while (!"Range".equals(gameChoice) && !"Amount".equals(gameChoice) && !"Menu".equals(gameChoice)){  //While value entered is invalid, take in another value until valid
                System.out.println("Invalid response, please enter one of the following options :\nRange, Amount, Menu\n");
                gameChoice = keyboard.next();   //Update gameChoice value
            }
            if ("Menu".equals(gameChoice) || "menu".equals(gameChoice)){    //If user selects Menu, return user back to main menu
                selectMenu(totalPoints);
            }
            System.out.println("\nPlease enter the amount you would like to risk: \n"); //Prompt user to enter wager amount
            amountRisk = keyboard.nextInt();    //Set amountRisk to value user entered
            while (amountRisk > totalPoints) {  //While wager amount is more than what the user has to bet, send error and read in another value until valid
                System.out.println("You don't have that many points to bet, please enter a valid amount: ");
                amountRisk = keyboard.nextInt();//Update wager amount
            }
            if ("Menu".equals(gameChoice)) {    //If user enters menu, return user to menu
                    selectMenu(totalPoints);
            } else if ("Range".equals(gameChoice)) {    //If user enters Range, enter Range method
                pointsWon = gameRange(amountRisk);      //Pass risked amount into gameRange method and set equal to points won for the round
                totalPoints = totalPoints + pointsWon;  //Update total points
                checkCongrats(totalPoints);             //Check if user exceeds 2000 points, if so congratulate
            } else if ("Amount".equals(gameChoice)) {   //If user enters Amount, enter Amount method
                pointsWon = gameAmount(amountRisk);     //Pass risked amount into gameAmount method and set equal to points won for the round
                totalPoints = totalPoints + pointsWon;  //Update total points
                checkCongrats(totalPoints);             //Check if user exceeds 2000 points, if so congratulate
            }
            System.out.println("Would you like to continue? (Y/N)\n");  //Ask user if they would like to continue
            yesNo = keyboard.next();    //Update yesNo for while loop
            while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){    //Make sure user enters valid response
                System.out.println("Invalid response, would you like to play? (Y/N)\n");
                yesNo = keyboard.next();    //Update yesNo for while loop
            }
        }
        amountWon = totalPoints - diffPoints;   //Calculate points won for the session by subtracting starting points by finishing points
        if (amountWon >= 0 ) {                  //If points won in the round is positive, display "you won x amount"
            System.out.println("\nYou won " + amountWon + " points in your previous session!\n");
        }
        else if (amountWon < 0 ){               //If points won in the round is negative, display "you lost x amount"
            System.out.println("\nYou lost " + Math.abs(amountWon) + " points in your previous session!\n");
        }
        selectMenu(totalPoints);                //Considering user decided to stop playing, re-enter main menu method
    }

    //Game range method... game mode within dice game
    public static int gameRange(int risk){      //gameRange method, taking in risk as arguement, returning  points won
        Scanner keyboard = new Scanner(System.in);  //New scanner object to read in from keyboard
        int pointsWon =0;   //Points won from one round
        String highLow;     //String variable to decide if user wants to select high or low
        int diceOne = diceRoll(1,6);    //Call to diceRoll method to randomize number 1 through 6
        int diceTwo = diceRoll(1,6);    //Call to diceRoll method to randomize number 1 through 6
        int totalRoll = diceOne + diceTwo;  //Add up both dice rolls to calculate sum of both dice
        System.out.println("Please select high or low:\n"); //Prompt user to enter high or low
        highLow = keyboard.next();  //Read in user response
        while(!"high".equals(highLow) && !"High".equals(highLow) && !"low".equals(highLow) && !"Low".equals(highLow)){  //Check to see if user response is valid
            System.out.println("Invalid response, please enter a valid command:\n");
            highLow = keyboard.next();  //Update while loop
        }
        if ("high".equals(highLow) || "High".equals(highLow)){  //If user selects high
            System.out.println("You have selected high... Rolling the dice:\nDice 1 -> " + diceOne + "\nDice 2 -> " + diceTwo + "\nTotal -> " + totalRoll);
            if (totalRoll >= 9){    //If user rolls 9 or higher, user won
                System.out.println("Congratulations!\nYou have won twice the risked amount!\n"); //
                pointsWon = risk; //Set points won = risk
            }
            else {
                System.out.println("\nBad luck!\n");    //Else user lost
                pointsWon = pointsWon - risk; //Set points won to points won - risk
            }
        }
        else if ("low".equals(highLow) || "Low".equals(highLow)){   //If user selects low
            System.out.println("You have selected low... Rolling the dice:\nDice 1 -> " + diceOne + "\nDice 2 -> " + diceTwo + "\nTotal -> " + totalRoll);
            if (totalRoll <= 5){            // if user rolls 5 or lower, user won
                pointsWon = risk;  //Set points won = risk
                System.out.println("Congratulations!\nYou have won twice the risked amount!\n");
            }
            else {
                pointsWon = pointsWon - risk;   //Else user lost
                System.out.println("\nBad luck!\n");
            }
        }
        return pointsWon;   //Return points won in the round
    }

    //Game amount method... game mode within dice game
    public static int gameAmount(int risk){     //Take amount risked as arguement, return points won in the round
        Scanner keyboard = new Scanner(System.in);  //New scanner object
        int pointsWon=0;    //Total points won in the round
        int diceOne = diceRoll(1,6);    //Call to diceRoll method to randomize number 1-6, set to diceOne
        int diceTwo = diceRoll(1,6);    //Call to diceRoll method to randomize number 1-6, set to diceTwo
        int totalRoll = diceOne + diceTwo;  //Calculate totalRoll = sum of both dice rolls
        int userNum;   //Number entered by user to guess sum of both dice
        System.out.println("\nPlease enter a number 2-12:\n");  //Prompt user to enter number 2-12
        userNum = keyboard.nextInt();   //Read in user response
        System.out.println("Rolling the dice.... \nDice 1 -> " + diceOne + "\nDice 2 -> " + diceTwo + "\nTotal - > " + totalRoll);
        if (userNum == totalRoll){  //If user correctly guessed the number, user won
            System.out.println("Congratulations!\nYou have won three times your risked amount!\n");
            pointsWon = risk * 2; //Set points won to risk * 2
        }
        else {  //Else, user lost
            System.out.println("Bad luck!\n");
            pointsWon = pointsWon - risk;   //Set points won to points one - risk
        }
        return pointsWon;   //Return points won
    }


    public static void blackJack(int totalPoints){  //Black Jack method, takes in totalPoints as arguement
        Scanner keyboard = new Scanner(System.in);  //New scanner object to read in user inputs
        int gameOver = 0;   //Variable to see if round is over
        int drawCard;       //Variable to draw each card
        int diffPoints = totalPoints;   //Variable to keep track of how many points started with
        int amountWon = 0;  //Calculate points won in the session
        String hitStay;     //User choice to Hit or Stay
        String yesNo;       //User choice to continue/start playing
        int userTotal;      //Count variable to keep track of users card total
        int dealerTotal = 0;//Count variable to keep track of dealers card total
        int amountRisked;   //Amount user wants to risk
        System.out.println("~Welcome to the Black Jack table!~\n\nThe rules can be found below: \n");   //Display rules
        System.out.println("Our system will randomly draw two cards for you (a sum of between 4 and 21) , you will then have the option to hit (H) or (S)");
        System.out.println("The objective of the game is to get as close to 21 as possible.\nEach hit (H) will draw one additional card (2-11) on to your total count, and if your count exceeds 21, you busted.. in which will result in the loss of your initial bet.");
        System.out.println("Once you decide to stay (S), the dealer will have a chance to either A. Get a higher number than you or B. Bust\n");
        System.out.println("The dealer will receive cards in the same fashion as the player, two cards to begin (4-21) and one card (2-11) until they achieve a higher number or bust.");
        System.out.println("If the dealer gets a higher number than you, you lose your bet.\nIf the dealer busts (goes over 21), you win twice the amount you bet.");
        System.out.println("If you draw a 21 from the start, you hit Black Jack and will receive three times your initial bet.");
        System.out.println("If the you and the dealer draw the same cards, your bet will be pushed, and no payout will be given.\n");
        System.out.println("\nWould you like to play? (Y/N)");  //Ask user if they would like to play
        yesNo = keyboard.nextLine();    //Read in user choice to play
        while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){        //While user input is invalid, send error
            System.out.println("Invalid response, would you like to play? (Y/N)\n");
            yesNo = keyboard.nextLine();
        }
        while("Y".equals(yesNo) || "y".equals(yesNo)) { //While user wants to play
            gameOver = 0;   //reset variable to 0 to keep track of gameplay
            if (totalPoints == 0){  //If user has 0 points, send back to main menu
                System.out.println("\nYou have 0 points! Returning you to the main menu.\n");
                selectMenu(totalPoints);
            }
            System.out.println("\nYou currently have " + totalPoints + " points.\n");   //Display current user points
            System.out.println("Please enter the amount you would like to risk: "); //Prompt user to enter wager amount
            amountRisked = keyboard.nextInt();  //Read in user wager amount
            while (amountRisked>totalPoints){   //While user enters more risked points than they have
                System.out.println("You don't have that many points to bet, please enter a valid amount: ");
                amountRisked = keyboard.nextInt();  //Update amount risked
            }
            System.out.println("\nDrawing first two card: \n");
            userTotal = diceRoll(4,21); //Display random number 4-21
            System.out.println("User : " + userTotal);
            if (userTotal == 21){   //If user hits a 21, they win
                System.out.println("You hit Black Jack!\n");
                totalPoints = totalPoints + (amountRisked * 2); //Update total points
                checkCongrats(totalPoints); //Check congratulations
                gameOver = 1;   //Set game over to 1
            }
            while (gameOver != 1) { //While game is not over
                System.out.println("Would you like to hit (H) or stay (S)?");   //Prompt user to Hit or Stay
                hitStay = keyboard.next();  //Read in user choice
                while (!"s".equals(hitStay) && !"S".equals(hitStay) && !"h".equals(hitStay) && !"H".equals(hitStay)) { //If not valid command, send error
                    System.out.print("Invalid command, please enter a valid command... Hit (H) or Stay (S).\n");
                    hitStay = keyboard.next();
                }
                while (("h".equals(hitStay) || "H".equals(hitStay)) && (userTotal < 21)) {  //While user enters H, and userTotal is less than 21
                    drawCard = diceRoll(2, 11); //Draw one more card 2-11
                    System.out.println("You drew a " + drawCard);
                    System.out.println("Your total is now " + (userTotal + drawCard)); //Display user total
                    userTotal = userTotal + drawCard;   //Update user total
                    if (userTotal > 21) {   //If user total above 21, user busted and lose
                        System.out.println("You busted, better luck next time!\n");
                        totalPoints = totalPoints - amountRisked;   //Update total points
                        gameOver = 1;   //Set game over to 1, or true
                    }
                    if (gameOver != 1) {    //If game is not over, ask user if they would like to Hit or Stay
                        System.out.println("Would you like to hit (H) or stay (S)?");
                        hitStay = keyboard.next();
                        while (!"s".equals(hitStay) && !"S".equals(hitStay) && !"h".equals(hitStay) && !"H".equals(hitStay)) { //If user input invalid, prompt user to re-enter
                            System.out.print("Invalid command, please enter a valid command... Hit (H) or Stay (S).\n");
                            hitStay = keyboard.next();
                        }
                    }
                }
                if (gameOver != 1) {    //If game is not over yet, draw cards for dealer
                    System.out.println("Drawing two cards for the dealer: ");
                    dealerTotal = diceRoll(4, 21);  //Generate random number 4-21 for dealer
                    System.out.println("User : " + userTotal + "\nDealer : " + dealerTotal);    //Print out user and dealer score
                }
                if (dealerTotal > userTotal && (dealerTotal <= 21)) {   //If dealer total is higher than user total and less than or equal to 21, dealer won
                    System.out.println("The dealer got closer to 21 than you did! Bad luck!");
                    totalPoints = totalPoints - amountRisked;   //Update totalPoints
                    gameOver = 1;   //Set game over to 1, or true
                }
                while (dealerTotal <= 21 && (gameOver != 1)) {  //While user total less than or equal to 21 and game is not over, continue to draw cards
                    drawCard = diceRoll(2,11);  //Draw card 2-11
                    dealerTotal = dealerTotal + drawCard;   //Update dealer total
                    System.out.println("\nDrawing another card...\n");  //Output card totals
                    System.out.println("The dealer drew a " + drawCard);
                    System.out.println("\nUser : " + userTotal + "\nDealer : " + dealerTotal);
                    if (dealerTotal == userTotal) { //If dealer total == user total, no payout
                        System.out.println("\nYou pushed! No payout!\n");
                        gameOver = 1;
                    }
                    if (dealerTotal > userTotal && (dealerTotal<=21)) {     //Check if dealer total is valid and high than user total
                        System.out.println("\nThe dealer got closer to 21 than you did! Bad luck!\n");
                        gameOver = 1;   //Set game over to 1
                        totalPoints = totalPoints - amountRisked;   //Update total points
                    } else if (dealerTotal > 21) {  //If dealer total over 21, user wins
                        System.out.println("The dealer busted!\n");
                        totalPoints = totalPoints + amountRisked;   //Update total points
                        checkCongrats(totalPoints); //Check congratulations
                        gameOver = 1;   //Game over to 1, or true
                    }
                }
            }

            System.out.println("Would you like to continue? (Y/N)\n");  //Ask user if they would like to continue
            yesNo = keyboard.next();
            while(!"Y".equals(yesNo) && !"y".equals(yesNo) && !"N".equals(yesNo) && !"n".equals(yesNo)){
                System.out.println("Invalid response, would you like to play? (Y/N)\n");
                yesNo = keyboard.next();
            }
        }
        amountWon = totalPoints - diffPoints;   //Calculate amount won in the round
        if (amountWon >= 0 ) {  //If amount won positive, "you won x amount"
            System.out.println("\nYou won " + amountWon + " points in your previous session!\n");
        }
        else if (amountWon < 0 ){   //If amount won negative, "you lost x amount"
            System.out.println("\nYou lost " + Math.abs(amountWon) + " points in your previous session!\n");
        }
        selectMenu(totalPoints);    //Main menu

    }
    public static String getMenuChoice(Scanner keyboard){   //Method to display menu options and return a user choice
        //System.out.println("**Welcome to Jolly Jackpot Land!**");
        System.out.println("Please select an option from the menu:\n");
        System.out.println("1. Slot Machine\n2. Dice Game\n3. Black Jack\n4. Show Points\n5. Restart\n6. Exit");
        String userChoice = keyboard.nextLine();

        return userChoice;
    }

    public static int diceRoll(int min, int max){   //Method to generate random number between two bounds
        Random rand = new Random();
        int randomNum = rand.nextInt((max-min) + 1) + min;
        return randomNum;
    }

    public static void checkCongrats(int totalPoints){  //Method to check is total points is greater than 2000
        if (totalPoints >= 2000){
            System.out.println("Congratulations, you are doing great!\n");
        }
    }

}
/*RunTimesMatrixMulitplication.java
        Authors: Dohmen, RJH (i6250494)
        Schnabel, CT (i6255807)
 */

public class Santa {

    public static void main(String[] args){

        IntegerStack randomStack;

        //counter vars
        int santaWins = 0;
        int alabWins = 0;
        int ties = 0;

        //execute loop 100k times
        for (int i = 0; i <= 100000; i++){
            randomStack = randomStack(12, 12);
            int outcome = santaGame(randomStack);
           if (outcome == -1){
               alabWins += 1;
           } else if (outcome == 1){
               santaWins += 1;
           } else{ ties += 1;}
           } //close loop
        // print output
            System.out.println("The game was played 100.000 times, each time using a new randomly generated stack of presents.");
            System.out.println("Of those 100.000 times, Santa won " + santaWins + " times, Alabaster won " + alabWins + " times, and there were " + ties + " ties.");


    } //close main



    //ﬁrst write a method that creates a random stack of 12 red and 12 blue presents
    public static IntegerStack randomStack (int blue,  int red){
       IntegerStack returnStack = new IntegerStack();

        int countBlue = blue; //red is 0 //12
       int countRed = red; //blue is 1 //12
       double probability; //initialize probability

       while (returnStack.size() != 24){
           probability = ((double) countRed) / ((double) (countRed + countBlue));
            if (Math.random() <= probability && countRed > -1){
                returnStack.push(0); //red
                countRed --;
            } else if (Math.random() > probability && countBlue > -1){
                returnStack.push(1); //blue
                countBlue --;
            } else if (returnStack.size() == 24) {break;}

       } //close loop

        return returnStack;
    } //close method



    public static  int santaGame(IntegerStack inputStack) {
        //RED IS ZERO, BLUE IS 1
        int outcome = 0;
        int santaPresents = 0;
        int alabasterPresents = 0;

        for (int dayCount = 0; dayCount  <= 24; dayCount++) {
            double coinFlip = Math.random();
                // Days 1 - 23
                while (inputStack.size() > 1 && !inputStack.isEmpty()) {
                    if (coinFlip <= 0.5) { // Santa's turn
                        int firstPresent1 = inputStack.pop();
                        int secondPresent1 = inputStack.pop();
                        if (firstPresent1 == 1 && secondPresent1 == 1) { //case: 2 blue presents
                            inputStack.push(1);
                        } else if (firstPresent1 == 0 || secondPresent1 == 0) { //cases: red and blue present & 2 red presents
                            santaPresents++;
                            inputStack.push(1);
                        }
                    } else if (coinFlip > 0.5) {//Alabaster's turn

                        int firstPresent2 = inputStack.pop();
                        int secondPresent2 = inputStack.pop();
                        if (firstPresent2 == 1 && secondPresent2 == 1) {  //case: 2 blue presents
                            alabasterPresents++;
                            inputStack.push(0);
                        } else if (firstPresent2 == 0 && secondPresent2 == 0) { //case: 2 red presents
                            inputStack.push(1);
                        } else {                      //case: presents of different color
                            alabasterPresents++;
                            inputStack.push(0);
                        }
                    }
                } //close inner while loop
            // Day 24 //Santa: red, Alab: blue
            if (dayCount == 24 && !inputStack.isEmpty()) {
                int lastPresent = inputStack.pop();
                if (coinFlip <= 0.5) { // Santa's turn
                     if (lastPresent == 0){
                            santaPresents += 1;
                     }
                    } else { // ALabs's turn
                    if (lastPresent == 0){
                        alabasterPresents += 1;
                    }
                }
            }
        } //close outer for loop
            // outcome 0  (tie) is initializer value, only changed here if we have a winner
            if (santaPresents > alabasterPresents) {
                outcome = 1;
            } else if (alabasterPresents > santaPresents) {
                outcome = -1;
            }

    return outcome;
    } //close method

} //close class

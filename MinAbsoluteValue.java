/*RunTimesMatrixMulitplication.java
        Authors: Dohmen, RJH (i6250494)
        Schnabel, CT (i6255807)

        Write two methods minAbsoluteValueLinear and minAbsoluteValueBinary.
        Both methods take as input a sorted array of integer values and return
        the smallest absolute value of the entries in the array. The ﬁrst method
        should go through the whole array from the ﬁrst to the last element, while
         the second method should use binary search.

        Write a program that can generate random sorted integer arrays and that can test
        and compare the runtimes of the two methods above for arrays of varying length. */

import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class MinAbsoluteValue {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int n = in.nextInt();   //length of input array
        int[] array = new int[n]; //init array
        array = randomIntArray(n);
        Arrays.sort(array);
            //runtime check
            double startTime1 = System.currentTimeMillis();
            int minValue1 = minAbsoluteValueLinear(array);
            double endTime1 = System.currentTimeMillis();
            double totalTime1 = endTime1 - startTime1;

            double startTime2 = System.currentTimeMillis();
            int minValue2 = minAbsoluteValueBinary(array);
            double endTime2 = System.currentTimeMillis();
            double totalTime2 = endTime2 - startTime2;

        System.out.println("Algorithm 1 took " + (totalTime1) + " milliseconds.");
        System.out.println("Algorithm 2 took " + (totalTime2) + " milliseconds.");
        System.out.println("To verify: \n Random Array: " + Arrays.toString(array) + "\n Abs Min Linear Method: " + minValue1 + "\n Abs Min Binary Method: " +minValue2);


    } //close main

    public static int minAbsoluteValueLinear(int[] array) {
        int minValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (Math.abs(array[i]) < Math.abs(minValue) ) {
                minValue = Math.abs(array[i]);
            }
        }
        return minValue;
    } //close method

    public static int minAbsoluteValueBinary(int[] array) {
        int minValue = array[0];
        int low = 0;
        int high = array.length -1;
        while (low+1 < high ) {
            int mid = (high + low) / 2;

            if (Math.abs(array[mid]) < Math.abs(minValue)) {
                minValue = array[mid];
            } else if (Math.abs(array[mid]) == Math.abs(minValue)) {
                break;
            } else if (Math.abs(array[mid]) > Math.abs(minValue)) {
                low = mid;
            }
        }

        return minValue;
    } //close method

        // returns an array of length n containing random double values
        public static int[] randomIntArray(int n){
            Random random = new Random();
            int[] array = new int[n];
            for(int i=0; i < n; i++){
                array[i] = random.nextInt();
            }
            return array;
        } //close method

    public static int min(int[] array){
        int min = int.POSITIVE_INFINITY;
        for(int i=0; i< array.length; i++){
            if (array[i] < min){
                min = array[i];
            }
        }
        return min;
    } //close method

    public static int max(int[] array){
        int max = int.NEGATIVE_INFINITY;
        for(int i=0; i< array.length; i++){
            if (array[i] > max){
                max = array[i];
            }
        }
        return max;
    } //close method
} // closeclass

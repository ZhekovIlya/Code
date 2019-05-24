package com.ua.zhekov;

import java.math.BigInteger;
import java.util.stream.Stream;

/*
Find the sum of the digits in the number 100! (i.e. 100 factorial)
{Correct answer: 648}
*/
public class Task3 {


    /**
     * Using bigInteger bc such big factorials > bigger than 20 cant fill in long range.
     * Iterating from 1 to number. Then Multiply all of numbers to get one.
     * After that get chars of this big number firstly casted this to a String
     * then using map to get numeric values and count sum of it
     *
     * @param number of which we are going to find factorial and then count its digits
     */
    public static void factorial(int number) {
        int result = String.valueOf(Stream.iterate(BigInteger.valueOf(1), i -> i.add(BigInteger.ONE))
                .limit(number)
                .reduce(BigInteger::multiply)
                .get())
                .chars()
                .map(Character::getNumericValue)
                .sum();
        System.out.println(result);
    }

    public static void main(String[] args) {
        factorial(100);
    }
}

package com.ua.zhekov;


public class Task1 {

    private int count;

    public int validBracketsCounter(int bracketsCount) {
        count = 0;
        brackets(bracketsCount, 0);
        return count;
    }

    /**
     * Recursive method. On invoke counting how many VALID open--close stock pairs could be with
     * such amount of needed open stocks.
     *
     * @param openBracket  count of openStocks should be added
     * @param closeBracket count of closeBracket should be  added 0 from start increments when openBracket decrements.
     *                     bc there is no need to add closeBracket before open one.
     */
    public void brackets(int openBracket, int closeBracket) {
        if (openBracket == 0 && closeBracket == 0) {
            count++;
        }
        if (openBracket > 0) {
            brackets(openBracket--, closeBracket++);
        }
        if (closeBracket > 0) {
            brackets(openBracket, closeBracket--);
        }
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        System.out.println(task1.validBracketsCounter(1));
        System.out.println(task1.validBracketsCounter(2));
        System.out.println(task1.validBracketsCounter(3));
        System.out.println(task1.validBracketsCounter(4));
        System.out.println(task1.validBracketsCounter(6));
    }


}

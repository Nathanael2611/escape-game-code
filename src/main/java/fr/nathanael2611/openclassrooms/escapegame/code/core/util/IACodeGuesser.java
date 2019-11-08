package fr.nathanael2611.openclassrooms.escapegame.code.core.util;

import java.util.ArrayList;

public class IACodeGuesser
{
    /* Simply store the researched code size */
    private int codeSize;

    /* Contains the possibilities to generate random code */
    private GivenSizeList<ArrayList<Integer>> possibilities;

    /**
     * Constructor
     * @param codeSize the researched code size
     */
    public IACodeGuesser(int codeSize)
    {
        this.codeSize = codeSize;
        this.possibilities = GivenSizeList.withSize(codeSize, getDefaultPossibilities());
        for (int i = 0; i < this.possibilities.size(); i++) this.possibilities.set(i, getDefaultPossibilities());
    }

    /**
     * Try to retrieve the secret code
     * @return an integer array that represent the tested code
     */
    public int[] tryPossibilities()
    {
        int[] array = new int[this.codeSize];
        int[] i = new int[1];
        possibilities.forEach(intArray ->{
            if(intArray.size() == 1) array[i[0]] = intArray.get(0);
            else array[i[0]] = AppHelper.generateRandomByPossibilities(intArray);
            i[0] ++;
        });
        return array;
    }

    /**
     * Used to say to the code guesser that the solution is smaller than a given number
     * @param index the index of the possibility array
     * @param n the number that the solution is smaller than
     */
    public void smallerThan(int index, int n)
    {
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (Integer number : possibilities.get(index))
        {
            if(number >= n) toRemove.add(number);
        }
        toRemove.forEach(integer -> possibilities.get(index).remove(integer));
    }

    /**
     * Used to say to the code guesser that the solution is bigger than a given number
     * @param index the index of the possibility array
     * @param n the number that the solution is bigger than
     */
    public void biggerThan(int index, int n)
    {
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (Integer number : possibilities.get(index))
        {
            if(number <= n) toRemove.add(number);
        }
        toRemove.forEach(integer -> possibilities.get(index).remove(integer));
    }

    /**
     * Used to say to the code-guesser that a specified number is the searched one.
     * @param index the index of the possibility array
     * @param n the number that we search
     */
    public void equal(int index, int n)
    {
        ArrayList<Integer> newPossibilities = new ArrayList<>();
        newPossibilities.add(n);
        possibilities.set(index, newPossibilities);
    }

    /**
     * Remove a possibility from possibility list
     * @param index the index of the possibility array
     * @param n the number that we want to remove
     */
    public void isNot(int index, int n)
    {
        possibilities.get(index).remove((Object) n);
    }

    /**
     * @return a default possibility list. [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
     */
    private ArrayList<Integer> getDefaultPossibilities()
    {
        ArrayList<Integer> possibilities = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            possibilities.add(i);
        }
        return possibilities;
    }

}

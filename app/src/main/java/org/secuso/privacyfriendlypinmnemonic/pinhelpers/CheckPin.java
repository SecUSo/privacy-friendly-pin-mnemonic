package org.secuso.privacyfriendlypinmnemonic.pinhelpers;

import android.content.Context;
import android.util.Log;

import org.secuso.privacyfriendlypin.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by yonjuni on 27.10.15.
 */
public class CheckPin {

    private String firstTwo;
    private String secondTwo;
    private String[] input;
    private Context context;
    private int[] userInput;

    public CheckPin(String pin, Context context) {

        this.context = context;

        input = new String[4];

        for (int i = 0; i < 4; i++) {
            input[i] = Character.toString(pin.charAt(i));
        }

        String tmp1 = input[0];
        String tmp2 = input[2];
        firstTwo = tmp1 += input[1];
        secondTwo = tmp2 += input[3];
    }

    public String determineDate() {

        String resultDate = "0" + input[0] + "-" + "0" + input[1]  + "-" + "(19)" + secondTwo;

        WordDictionary wordDictionaryClass = new WordDictionary();

        String[] monthsWordsArray = wordDictionaryClass.getMonths(Locale.getDefault().getLanguage());;
        String[] monthsArray = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] daysArray = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29", "30", "31"};

        String[] yearsArray = createYearsArray();

        for (int i = 0; i < monthsArray.length; i++) {
            for (int j = 0; j < yearsArray.length; j++) {
                if ((firstTwo.equals(monthsArray[i])) && (secondTwo.equals(yearsArray[j]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(firstTwo)] + " " + firstTwo + " (19)" + secondTwo;
                }
            }
        }

        for (int i = 0; i < daysArray.length; i++) {
            for (int j = 0; j < monthsArray.length; j++) {
                if ((firstTwo.equals(daysArray[i])) && (secondTwo.equals(monthsArray[j]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(secondTwo)] + " " + firstTwo + "-" + secondTwo;
                    break;
                } else if ((firstTwo.equals(monthsArray[j])) && (secondTwo.equals(daysArray[i]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(firstTwo)] + " " + firstTwo + "-" + secondTwo;
                    break;
                } else if (Integer.parseInt(firstTwo) == 19) {
                    resultDate = context.getString(R.string.display_date_year_1900s);
                } else if ((Integer.parseInt(firstTwo) == 20) && (Integer.parseInt(secondTwo) <= 15)) {
                    resultDate = context.getString(R.string.display_date_year_2000s);
                }
            }
        }
        Log.d("ANSWER ", resultDate);
        return resultDate;
    }

    public String determineWord() {

        String word = "";

        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        int d = Integer.parseInt(input[3]);

        userInput = new int[]{a, b, c, d};

        WordDictionary wordDictionaryClass = new WordDictionary();
        String[] wordDictionary = wordDictionaryClass.getWordDictionary(Locale.getDefault().getLanguage());

        String[] getKeysArray = intArrayToStringArray(userInput, wordDictionaryClass);

        List<String> wordList = mapKeysToWords(getKeysArray);

        String[] wordArray = wordList.toArray(new String[wordList.size()]);

        List<String> possibleWords = findWords(wordArray, wordDictionary);

        int random = (int) (Math.random() * possibleWords.size());

        // assign the number to a mapped word
        for (int i = 0; i < userInput.length; i++) {

            if (userInput[i] == 1 || userInput[i] == 0) {
            } else if (possibleWords.size() == 0) {
            } else {
                word = possibleWords.get(random);
            }

        }
        if (word.equals("")) {
            return context.getString(R.string.display_no_word);
        }
        return word.toUpperCase();
    }

    public String determineCalculation() {

        String resultCalculation = context.getString(R.string.display_no_math);

        if (firstTwo.equals("00") && secondTwo.equals("00")) {
            return resultCalculation;
        }

        int tmp1 = Integer.parseInt(firstTwo);
        int tmp2 = Integer.parseInt(secondTwo);

        if (firstTwo.equals("00")) {
            tmp1 = 1;
        } else if (secondTwo.equals("00")) {
            tmp2 = 1;
        }

        int firstHalf = tmp1;
        int secondHalf = tmp2;

        int differenceAB = firstHalf - secondHalf;
        int differenceBA = secondHalf - firstHalf;

        for (int i = 2; i < 11; i++) {
            if (secondHalf != 0 && firstHalf % secondHalf == 0) {
                if (firstHalf == i * secondHalf) {
                    resultCalculation =
                            firstTwo + " = " + secondTwo + " x " + Integer.toString(i);
                }
            }
        }

        for (int j = 2; j < 11; j++) {
            if (firstHalf != 0 && secondHalf % firstHalf == 0) {
                if (secondHalf == j * firstHalf) {
                    resultCalculation =
                            secondTwo + " = " + firstTwo + " x " + Integer.toString(j);
                }
            }
        }

        if ((differenceBA == 22 || differenceAB == 22) || (differenceBA == 44 || differenceAB == 44)) {
            resultCalculation = context.getString(R.string.display_math_stepping) + " " + firstTwo + secondTwo;
        }

        for (int k = 3; k > 0; k--) {
            if (differenceAB == k) {
                resultCalculation =
                        firstTwo + " = " + secondTwo + " + " + Integer.toString(k);
            } else if (differenceBA == k) {
                resultCalculation =
                        firstTwo + " = " + secondTwo + " - " + Integer.toString(k);
            }
        }

        if (userInput[0] + userInput[1] == secondHalf) {
            resultCalculation = input[0] + " + " +  input[1] + " = " + secondTwo;
        }

        if (userInput[0] + userInput[1] + userInput[2] == userInput[3]) {
            resultCalculation = input[0] + " + " +  input[1] + " + " +  input[2] + " = " + userInput[3];
        }
        return resultCalculation;
    }

    //Helper Functions
    public String[] createYearsArray() {
        String[] years = new String[100];

        for (int i = 0; i < 10; i++) {
            String zero = "0";
            years[i] = zero += Integer.toString(i);
        }

        for (int i = 10; i < 100; i++) {
            years[i] = Integer.toString(i);
        }
        return years;
    }

    public String[] intArrayToStringArray(int[] intArray, WordDictionary wordDictionaryClass) {

        if (intArray.length == 0) {
            return new String[0];
        }

        String[] result = new String[4];
        String[] keyboard = wordDictionaryClass.getKeyboard(Locale.getDefault().getLanguage());

        for (int i = 0; i < intArray.length; i++) {
            result[i] = keyboard[intArray[i]];
        }

        return result;
    }

    public List<String> mapKeysToWords(String[] keys) {

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < keys[0].length(); i++) {
            for (int j = 0; j < keys[1].length(); j++) {
                for (int k = 0; k < keys[2].length(); k++) {
                    for (int l = 0; l < keys[3].length(); l++) {

                        result.add(Character.toString(keys[0].charAt(i))
                                + Character.toString(keys[1].charAt(j))
                                + Character.toString(keys[2].charAt(k))
                                + Character.toString(keys[3].charAt(l)));
                    }
                }
            }
        }
        return result;
    }

    public List<String> findWords(String[] one, String[] two) {
        HashSet<String> map = new HashSet<String>();
        List<String> duplicates = new ArrayList<String>();

        for (String i : one)
            map.add(i);
        for (String i : two)
            if (map.contains(i)) {
                duplicates.add(i);
            }

        return duplicates;

    }

}

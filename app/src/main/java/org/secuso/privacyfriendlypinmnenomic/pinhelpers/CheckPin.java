package org.secuso.privacyfriendlypinmnenomic.pinhelpers;

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

    String firstTwo;
    String secondTwo;
    String[] input;
    Context context;
    public boolean isWord;
    public boolean isCalculatable;
    public boolean isDate;

    public CheckPin(String pin, Context context) {

        this.context = context;
        this.isCalculatable = false;
        this.isDate = false;
        this.isWord = false;

        input = new String[4];

        for (int i = 0; i < 4; i++) {
            input[i] = Character.toString(pin.charAt(i));
        }

        String tmp1 = input[0];
        String tmp2 = input[2];
        firstTwo = tmp1 += input[1];
        secondTwo = tmp2 += input[3];

        //for (int i = 0; i < 4; i++) {
        //    System.out.println("INPUT: " + input[i]);
        //}
    }

    public String determineDate() {

        String resultDate = context.getString(R.string.display_no_date);

        String[] monthsWordsArray = {"", "(Jan)", "(Feb)", "(Mar)", "(Apr)", "(May)", "(Jun)", "(Jul)", "(Aug)", "(Sep)", "(Oct)", "(Nov)", "(Dec)"};
        String[] monthsArray = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] daysArray = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29", "30", "31"};

        String[] yearsArray = createYearsArray();

        for (int i = 0; i < monthsArray.length; i++) {
            for (int j = 0; j < yearsArray.length; j++) {
                if ((firstTwo.equals(monthsArray[i])) && (secondTwo.equals(yearsArray[j]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(firstTwo)] +" " + firstTwo +" " +"(19)" + secondTwo;
                    this.isDate = true;
                }
            }
        }

        for (int i = 0; i < daysArray.length; i++) {
            for (int j = 0; j < monthsArray.length; j++) {
                if ((firstTwo.equals(daysArray[i])) && (secondTwo.equals(monthsArray[j]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(secondTwo)] + " " + firstTwo + "-" + secondTwo;
                    this.isDate = true;
                    break;
                } else if ((firstTwo.equals(monthsArray[j])) && (secondTwo.equals(daysArray[i]))) {
                    resultDate = monthsWordsArray[Integer.parseInt(firstTwo)] + firstTwo + "-" + secondTwo;
                    this.isDate = true;
                    break;
                } else if (Integer.parseInt(firstTwo) == 19) {
                    resultDate = context.getString(R.string.display_date_year_1900s);
                    this.isDate = true;
                } else if ((Integer.parseInt(firstTwo) == 20) && (Integer.parseInt(secondTwo) <= 15)) {
                    resultDate = context.getString(R.string.display_date_year_2000s);
                    this.isDate = true;
                } else if ((Integer.parseInt(firstTwo) < 20) && (Integer.parseInt(firstTwo) > 9)) {
                    resultDate = context.getString(R.string.display_date_year_any);
                    this.isDate = true;
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

        int[] userInput = new int[]{a, b, c, d};

        WordDictionary wordDictionaryClass = new WordDictionary();
        String[] wordDictionary = wordDictionaryClass.getWordDictionary(Locale.getDefault().getLanguage());

        String[] getKeysArray = intArrayToStringArray(userInput, wordDictionaryClass);
        //System.out.println("PIN AHHHHHHHHH " + Integer.toString(a));

        List<String> wordList = mapKeysToWords(getKeysArray);

        String[] wordArray = wordList.toArray(new String[wordList.size()]);

        List<String> possibleWords = findWords(wordArray, wordDictionary);

        int random = (int) (Math.random() * possibleWords.size());
        //System.out.println("Random is " + random);

        // assign the number to a mapped word
        for (int i = 0; i < userInput.length; i++) {

            if (userInput[i] == 1 || userInput[i] == 0) {
            } else if (possibleWords.size() == 0) {
            } else {
                word = possibleWords.get(random);
            }

        }
        //System.out.println("Your word is " + word);
        if (word.equals("")) {
            return context.getString(R.string.display_no_word);
        }
        isWord = true;
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
            if (secondHalf !=0 && firstHalf % secondHalf == 0) {
                if (firstHalf == i * secondHalf) {
                    resultCalculation =
                            firstTwo + " " + context.getString(R.string.display_math_is)
                                    + " " + Integer.toString(i)
                                    + context.getString(R.string.display_math_large) + " " + secondTwo;
                    isCalculatable = true;
                    System.out.println(resultCalculation);
                }
            }
        }

        for (int j = 2; j < 11; j++) {
            if (firstHalf !=0 && secondHalf % firstHalf == 0) {
                if (secondHalf == j * firstHalf) {
                    resultCalculation =
                            secondTwo + " " + context.getString(R.string.display_math_is)
                                    + " " + Integer.toString(j) + " "
                                    + context.getString(R.string.display_math_large) + " " + firstTwo;
                    isCalculatable = true;
                    System.out.println(resultCalculation);
                }
            }
        }

        if (differenceBA == 22 || differenceAB == 22) {
            resultCalculation = context.getString(R.string.display_math_stepping) + " " + firstTwo + secondTwo;
            isCalculatable = true;
            System.out.println(resultCalculation);
        }

        for (int k = 3; k > 0; k--) {
            if (differenceAB == k) {

                resultCalculation =
                        firstTwo + " " + context.getString(R.string.display_math_large)
                                + " " + secondTwo + " " + context.getString(R.string.display_math_by)
                                + " " + Integer.toString(k);
                isCalculatable = true;
                System.out.println(resultCalculation);
            } else if (differenceBA == k) {
                resultCalculation =
                        firstTwo + " " + context.getString(R.string.display_math_small)
                                + " " + secondTwo + " " + context.getString(R.string.display_math_by)
                                + " " + Integer.toString(k);
                isCalculatable = true;
                System.out.println(resultCalculation);
            }
        }
        return resultCalculation;
    }

    public void assignSymbol () {

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
            //System.out.println(i + " Pin Digit " + intArray[i] + " Keypad " + result[i]);
            //System.out.println("Keyboardstelle " + keyboard[intArray[i]]);
        }

		/*for (int i=0; i<result.length; i++) {
            System.out.println("ENDE Methode " + result [i]);
		}*/

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

                        //System.out.println("MIX: "
                                //+ Character.toString(keys[0].charAt(i))
                               // + Character.toString(keys[1].charAt(j))
                                //+ Character.toString(keys[2].charAt(k))
                               // + Character.toString(keys[3].charAt(l)));
                    }
                }
            }
        }
        //System.out.println("ANSWER " + Arrays.toString(result.toArray()));
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

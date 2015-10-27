package de.tudarmstadt.informatik.secuso.privacyfriendlypin;

/**
 * Created by yonjuni on 27.10.15.
 */
public class CheckPin {

    public String pin;
    String firstTwo = "12";
    String secondTwo = "12";
    String result = "";

    public boolean determineDate() {

        boolean isDate = false;
        String resultDate = "";

        //String[] monthsArrayWords = new String[] {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        String[] monthsArray = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] daysArray = new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29", "30", "31"};
        String[] yearsArray = createYearsArray();

        for (int i=0; i<monthsArray.length;i++) {
            for (int j=0; j<yearsArray.length;j++) {
                if ((firstTwo.equals(monthsArray[i])) && (secondTwo.equals(yearsArray[j]))) {
                    resultDate = "Date in MM-YY format: " + firstTwo + secondTwo;
                    isDate = true;
                }
            }
        }

        for (int i=0; i<daysArray.length;i++) {
            for (int j=0;j<monthsArray.length;j++) {
                if ((firstTwo.equals(daysArray[i])) && (secondTwo.equals(monthsArray[j]))) {
                    resultDate ="Date in DD-MM format: " + firstTwo + secondTwo;
                    isDate = true;
                    break;
                } else if ((firstTwo.equals(monthsArray[j])) && (secondTwo.equals(daysArray[i]))) {
                    resultDate ="Date in MM-DD format: " + firstTwo + secondTwo;
                    isDate = true;
                    break;
                } else if (Integer.parseInt(firstTwo) == 19) {
                    resultDate = "Valid year in 1900s";
                    isDate = true;
                } else if ((Integer.parseInt(firstTwo) == 20) && (Integer.parseInt(secondTwo) <= 15)) {
                    resultDate = "Valid year in this century.";
                    isDate = true;
                }
            }
        }
        result = resultDate;
        System.out.println("ANSWER " + resultDate);
        return isDate;
    }

    public String[] createYearsArray() {
        String[] years = new String[100];

        for (int i=0; i<10; i++) {
            String zero = "0";
            years[i] = zero += Integer.toString(i);
        }

        for (int i=10; i < 100; i++) {
            years[i] = Integer.toString(i);
        }
        return years;
    }



}

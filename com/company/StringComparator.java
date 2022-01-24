package com.company;

public class StringComparator {


     static int[] compare(String one, String two) {
         int similarElements = 0;
         int identicalElements = 0;
         int [] comparisonResult = new int[2];  //[similarElements, identicalElements]

        for (int i = 0; i < one.length(); i++){
            char currentChar = one.charAt(i);

            for (int j = 0; j < two.length(); j++) {
                if (currentChar == two.charAt(j) && i == j) {
                    identicalElements++;
                } else if (currentChar == two.charAt(j) && i != j) {
                    similarElements++;
                }
            }
        }
        comparisonResult[1] = similarElements;
        comparisonResult[0] = identicalElements;
        return comparisonResult;
    }

}

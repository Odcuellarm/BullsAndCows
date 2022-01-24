package com.company;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

            int turns = 1;
            String secretCode;
            String userGuess;
            boolean invalidCodeLength;
            boolean invalidCodeSymbols;
            boolean isNotGuessed;
            Scanner scanner = new Scanner(System.in);
            SecretCode code = new SecretCode();

            do {
                System.out.println("Enter the length of the secret code: ");
                String lengthInput = scanner.next();
                try {
                    code.setCodeLength(Integer.parseInt(lengthInput));

                    if(code.getCodeLength() < 4 || code.getCodeLength() > 16) {
                        throw new IndexOutOfBoundsException();
                    }
                    invalidCodeLength = false;

                } catch (NumberFormatException e) {
                    invalidCodeLength = true;
                    System.out.println("Error: \"" + lengthInput + "\" isn't a valid number." );
                    System.exit(1);
                } catch (IndexOutOfBoundsException e) {
                    invalidCodeLength = true;
                    if(code.getCodeLength() < 4) {
                        System.out.println("Error: minimum length of the secret code is 1");
                    } else if (code.getCodeLength() > 16) {
                        System.out.println("Error: maximum length of the secret code is 16");
                    }
                    System.exit(1);
                }

            } while (invalidCodeLength);

            do {
                System.out.println("Input the number of possible symbols in the code: ");
                String symbolsInCode = scanner.next();
                try {
                    code.setCodeSymbols(Integer.parseInt(symbolsInCode));

                    if(code.getSymbolsInCode() > 36 || code.getSymbolsInCode() < code.getCodeLength()) {
                        throw new IndexOutOfBoundsException();
                    }
                    invalidCodeSymbols = false;

                } catch (NumberFormatException e) {
                    invalidCodeSymbols = true;
                    System.out.println("Error: \"" + symbolsInCode + "\" isn't a valid number." );
                    System.exit(1);
                } catch (IndexOutOfBoundsException e) {
                    invalidCodeSymbols = true;
                    if (code.getSymbolsInCode() < code.getCodeLength()) {
                        System.out.println("Error: it's not possible to generate a code with a length of " + code.getCodeLength() + " with " + code.getSymbolsInCode() + " unique symbols.");
                    } else if (code.getSymbolsInCode() > 36) {
                        System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    }
                    System.exit(1);
                }
            } while (invalidCodeSymbols);

            code.generateSecretCode();  //generates the secret-code
            secretCode = code.getSecretCode(); //returns the secret code as String
            //System.out.println(secretCode);
            System.out.println("The secret is prepared: " + code.getHiddenCode() + " " + code.getSymbolsReport() + ".");
            System.out.println("Okay, let's start the game!");

            do {
                System.out.println("Turn " + turns + ":");
                userGuess = scanner.next();
                isNotGuessed = bullsAndCows(StringComparator.compare(secretCode, userGuess), secretCode);
                turns++;
            } while (isNotGuessed);
        }

        static boolean bullsAndCows(int[] bullsAndCows, String secretCode) {
            int bulls = bullsAndCows[0];
            int cows = bullsAndCows[1];

            System.out.print("Grade: ");
            if (bulls != 0 && cows !=0) {
                System.out.print(bulls + " bull(s) and " + cows + " cow(s)\n");
            }else if (cows != 0) {
                System.out.print(cows + " cow(s)\n");
            }else{
                System.out.print(bulls + " bull(s)\n");
                if (bulls == secretCode.length()) {
                    System.out.println("Congratulations! You guessed the secret code.");
                    return false;
                }
            }
            return true;
        }
    }

package com.company;

import java.util.ArrayList;
import java.util.Random;

public class SecretCode {

    private String secretCode;
    private int codeLength;
    private int symbolsInCode;

    SecretCode() {
        this.codeLength = 0;
        this.symbolsInCode = 0;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public void setCodeSymbols(int symbolsInCode) {
       this.symbolsInCode = symbolsInCode;
    }

    public void generateSecretCode() {
        char symbols[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        ArrayList<Character> secret = new ArrayList<Character>();
        StringBuilder secretCode = new StringBuilder();
        Random random = new Random();
        int randomNum;
        int listSize = secret.size();

        while(listSize < this.codeLength) {
            randomNum = random.nextInt(symbolsInCode);
            if (!secret.contains(symbols[randomNum])) {
                secret.add(symbols[randomNum]);
                secretCode.append(symbols[randomNum]);
            }
            listSize = secret.size();
        }
        this.secretCode = secretCode.toString();
    }
    //////////////////////// GETTERS ///////////////////////////////////////
    public int getCodeLength() {
        return this.codeLength;
    }

    public int getSymbolsInCode() {
        return this.symbolsInCode;
    }

    public String getSecretCode() {
        return this.secretCode;
    }

    public String getSymbolsReport() {
        StringBuilder message = new StringBuilder("(0-9");
        char symbols[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        if (this.symbolsInCode >= 11) {
            message.append(", a");
        }
        if (this.symbolsInCode > 11) {
            message.append("-" +symbols[symbolsInCode - 1]);
        }
        message.append(")");
        return  message.toString();
    }

    public String getHiddenCode() {
        StringBuilder hiddenCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            hiddenCode.append("*");
        }
        return hiddenCode.toString();
    }

}

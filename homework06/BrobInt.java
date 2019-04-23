/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author ameyam
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrobInt {
    private final List<Integer> intArrayList;
    final boolean intNegative;
    public static final BrobInt ZERO = new BrobInt("0");
    public static final BrobInt ONE = new BrobInt("1");
    public static final BrobInt TWO = new BrobInt("2");
    public static final BrobInt THREE = new BrobInt("3");
    public static final BrobInt TEN = new BrobInt("10");

    // Constructor that takes String and converts to an ArrayList of integers
    //It also checks for sign and sets a boolean
    //Corrects the length accordingly
    public BrobInt(String value) {
        long intLength = value.length();
        int positionWithinString = 0;

        if (value.length() > 1 && value.substring(0, 1).equals("-")) {
            intNegative = true;
            positionWithinString++;
        } else {
            if (value.length() > 1 && value.substring(0, 1).equals("+")) {
                // skip over explicit '+' sign
                positionWithinString++;
            }
            intNegative = false;
        }
        boolean leadingZero = true;
        ArrayList<Integer> intArrayList = new ArrayList<>();
        for (int i = positionWithinString; i < intLength; i++) {
            try {
                int digit = Integer.parseInt(value.substring(i, i + 1));
                if (digit == 0 && leadingZero) {
                    // Eliminate leading zeroes.
                    continue;
                }
                intArrayList.add(digit);
                leadingZero = false;
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
                System.exit(0);
            }
        }

        if (leadingZero) {
            intArrayList.add(0); // Only zeroes encountered. Add at least one.
        }
        this.intArrayList = Collections.unmodifiableList(intArrayList);
    }

    private BrobInt(BrobInt value) {
        this.intNegative = value.intNegative;
        this.intArrayList = value.intArrayList; // intArrayList is immutable, so no copy needed.
    }

    private BrobInt(BrobInt value, boolean negate) {
        this.intNegative = value.intNegative ^ negate;
        this.intArrayList = value.intArrayList; // intArrayList is immutable, so no copy needed.
    }

    private BrobInt(List<Integer> digits, boolean negative) {
        this.intArrayList = digits.size() == 0 ? Collections.singletonList(0) : Collections.unmodifiableList(digits);
        this.intNegative = negative;
    }

    public BrobInt add(BrobInt value) {

        if (intNegative == value.intNegative) {//if signs of both are same
            if (intNegative) {// if they are negative add as positives and add negative sign
                BrobInt temp = abs(this).addPositiveInt(abs(value));
                return new BrobInt(temp, true);
            }
            return abs(this).addPositiveInt(abs(value)); //if both positive
        } else if (abs(this).compareTo(abs(value)) == 1) {
            if (intNegative) {
                BrobInt temp = abs(this).subtractPositiveInt(abs(value));
                return new BrobInt(temp, true);
            } else {
                return abs(new BrobInt(this).subtractPositiveInt(abs(value)));
            }
        } else {
            if (!(intNegative)) {
                BrobInt temp = abs(new BrobInt(this)).subtractPositiveInt(abs(new BrobInt(value)));
                return new BrobInt(temp, true);
            } else {
                return abs(new BrobInt(this)).subtractPositiveInt(abs(new BrobInt(value)));
            }
        }
    }

    public BrobInt addPositiveInt(BrobInt value) {
        int carryOver = 0;
        int counter = 0;
        String strTemp = "";

        int size = this.intArrayList.size();
        if (size > value.intArrayList.size()) {
            size = value.intArrayList.size();
        }
        for (int index = size - 1; index >= 0; index--) {
            int bitAddition = value.intArrayList.get(value.intArrayList.size() - 1 - counter) + intArrayList.get(intArrayList.size() - 1 - counter) + carryOver;
            carryOver = (int) (bitAddition / 10);
            bitAddition = (int) bitAddition % 10;
            strTemp = bitAddition + strTemp + "";
            counter++;
        }
        List<Integer> largerBrobIntList = ((this.intArrayList.size() > value.intArrayList.size()) ? intArrayList : value.intArrayList);
        int currentIndex = largerBrobIntList.size() - counter - 1;
        for (int index = currentIndex; index >= 0; index--) {
            if (carryOver != 0) {
                int temp = largerBrobIntList.get(index) + carryOver;
                carryOver = (temp == 10 ? 1 : 0);
                temp = (temp == 10 ? 0 : temp);
                strTemp = temp + strTemp + "";
            } else {
                strTemp = largerBrobIntList.get(index) + strTemp + "";
            }
        }
        if (carryOver != 0) {
            strTemp = carryOver + strTemp + "";
        }
        if (strTemp.equals("") || strTemp.equals(" ")) {
            return ZERO;
        }
        return new BrobInt(strTemp);
    }

    public BrobInt subtract(BrobInt value) {
        if (compareTo(value) == 0) {
            return ZERO;
        } else if (intNegative == value.intNegative) {// Check if both have same sign
            if (intNegative) {
                return add(abs(value));
            }
            return add(new BrobInt(value, true));
        } else if (abs(this).compareTo(abs(value)) == 1) {
            if (intNegative) {
                BrobInt temp = abs(this).addPositiveInt(abs(value));
                return new BrobInt(temp, true);
            } else {
                return abs(this).addPositiveInt(abs(value));
            }
        } else {
            if (intNegative) {
                BrobInt temp = abs(this).addPositiveInt(abs(value));
                return new BrobInt(temp, true);
            } else {
                return abs(this).addPositiveInt(abs(value));
            }
        }
    }

    public BrobInt subtractPositiveInt(BrobInt value) {
        int rowSubtraction;
        ArrayList<Integer> calculationIntList = new ArrayList<>();
        List<Integer> largerValueBrobIntList = compareTo(value) == 1 ? intArrayList : value.intArrayList;
        List<Integer> smallerValueBrobIntList = compareTo(value) == 1 ? value.intArrayList : intArrayList;
        int size = smallerValueBrobIntList.size() < largerValueBrobIntList.size() ? smallerValueBrobIntList.size() : largerValueBrobIntList.size();
        int carry = 0;
        int count = 0;
        for (int index = size - 1; index >= 0; index--) {
            if (compareTo(value) == 1 ? true : false) {
                rowSubtraction = largerValueBrobIntList.get(intArrayList.size() - 1 - count) - smallerValueBrobIntList.get(value.intArrayList.size() - 1 - count) - carry;
            } else {
                rowSubtraction = largerValueBrobIntList.get(value.intArrayList.size() - 1 - count) - smallerValueBrobIntList.get(intArrayList.size() - 1 - count) - carry;
            }
            carry = 0;
            if (rowSubtraction < 0 && !(index == 0 && this.intArrayList.size() == value.intArrayList.size())) {
                rowSubtraction += 10;
                carry = 1;
            }
            calculationIntList.add(0, rowSubtraction);
            count++;
        }
        List<Integer> largerBrobIntList = ((this.intArrayList.size() > value.intArrayList.size()) ? intArrayList : value.intArrayList);
        int currentIndex = largerBrobIntList.size() - count - 1;
        for (int index = currentIndex; index >= 0; index--) {
            if (carry != 0) {
                int temp = largerBrobIntList.get(index) - carry;
                if (temp < 0) {
                    temp += 10;
                    carry++;
                }
                calculationIntList.add(0, temp);
                carry--;
            } else {
                calculationIntList.add(0, largerBrobIntList.get(index));
            }
        }
        if (calculationIntList.get(0) == 0) {
            calculationIntList.remove(0);
        }
        return removeLeadingZeroes(abs(new BrobInt(calculationIntList, false)));
    }

    public BrobInt multiply(BrobInt value) {
        BrobInt calc = new BrobInt(abs(this).multiplyPositiveInt(abs(value)));
        if (value.equals(ZERO) || equals(ZERO)) {
            return ZERO;
        }
        if (value.intNegative == intNegative) {
            return calc;
        } else {
            return new BrobInt(calc, true);
        }
    }

    public BrobInt multiplyPositiveInt(BrobInt value) {
        ArrayList<List<Integer>> additionValues = new ArrayList<>();
        List<Integer> largerBrobIntList = (this.intArrayList.size() > value.intArrayList.size()) ? intArrayList : value.intArrayList;
        List<Integer> smallerBrobIntList = (this.intArrayList.size() > value.intArrayList.size()) ? value.intArrayList : intArrayList;
        int count = 0;
        for (int index = smallerBrobIntList.size() - 1; index >= 0; index--) {
            int carry = 0;
            ArrayList<Integer> newDigits = new ArrayList<>();
            additionValues.add(0, newDigits);
            for (int i = 0; i < count; i++) {
                newDigits.add(0, 0);
            }
            for (int bigIndex = largerBrobIntList.size() - 1; bigIndex >= 0; bigIndex--) {
                int temp = largerBrobIntList.get(bigIndex) * smallerBrobIntList.get(index) + carry;

                carry = (int) (temp / 10);
                temp = (int) (temp % 10);
                newDigits.add(0, temp);
            }
            if (carry != 0) {
                newDigits.add(0, carry);
            }
            count++;
        }

        BrobInt calculation = ZERO;
        for (int i = 0; i < additionValues.size(); i++) {
            calculation = calculation.addPositiveInt(new BrobInt(additionValues.get(i), false));
        }

        return abs(calculation);
    }

    public BrobInt divide(BrobInt value) {
        BrobInt counter = ZERO;
        BrobInt temp = ZERO;
        while (true) {
            temp = new BrobInt(temp.add(abs(value)));
            if (abs(this).compareTo(temp) == -1) {
                break;
            }
            counter = counter.add(ONE);
        }
        if (intNegative != value.intNegative) {
            return new BrobInt(counter, true);
        }
        return counter;
    }

    public BrobInt remainder(BrobInt value) {
        if (abs(this).compareTo(abs(value)) == -1) {
            return this;
        }

        BrobInt calculation = removeLeadingZeroes(abs(this).subtractPositiveInt(abs(value).multiply(abs(this).divide(abs(value)))));
        if (intNegative != value.intNegative) {
            return new BrobInt(abs(calculation), true);
        }
        return abs(calculation);
    }

    public BrobInt removeLeadingZeroes(BrobInt value) {
        for (int i = 0; i < value.intArrayList.size(); i++) {
            if (value.intArrayList.get(i) != 0) {
                return new BrobInt(value.intArrayList.subList(i, value.intArrayList.size()), value.intNegative);
            }
        }
        return ZERO;
    }

    public BrobInt abs(BrobInt value) {
        if (value.intArrayList.isEmpty()) {
            return ZERO;
        }
        if (value.intNegative) {
            return new BrobInt(value, true);
        } else {
            return value;
        }
    }

    public boolean allZeroDetect(BrobInt value) {
        for (int digit : value.intArrayList) {
            if (digit != 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String str = intNegative ? "-" : "";
        int i = 0;
        if (intArrayList.size() > 0) {
            str = str + intArrayList.get(i);
            i++;
        }
        for (; i < intArrayList.size(); i++) {
            str = str + Math.abs(intArrayList.get(i));
        }
        return str;
    }

    public int compareTo(BrobInt value) {
        //System.out.println(this.toString());
        //System.out.println(value.toString());
        int sign = intNegative ? -1 : 1;
        if (value.intNegative != this.intNegative) {
            return sign;
        }

        if (value.intArrayList.size() < intArrayList.size()) {
            return sign;
        } else if (value.intArrayList.size() > intArrayList.size()) {
            return -sign;
        } else {
            for (int i = 0; i < this.intArrayList.size(); i++) {
                if (value.intArrayList.get(i) < intArrayList.get(i)) {
                    return sign;
                }
                if (value.intArrayList.get(i) > intArrayList.get(i)) {
                    return -sign;
                }
            }
            return 0;
        }
    }

    public boolean equals(Object x) {
        return x.toString().equals(toString());
    }

    public static BrobInt valueOf(long value) {
        String strValue = "" + value;
        BrobInt br = new BrobInt(strValue);
        return br;
    }
}

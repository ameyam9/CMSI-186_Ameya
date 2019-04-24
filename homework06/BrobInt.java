

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrobInt {
    private final List<Integer> intArrayList;
    public final boolean intNegative;
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
        this.intArrayList = new ArrayList<>(value.intArrayList);
    }

    private BrobInt(List<Integer> digits, boolean negative) {
        this.intArrayList = digits.size() == 0 ? Collections.singletonList(0) : Collections.unmodifiableList(digits);
        this.intNegative = negative;
    }

    public BrobInt add(BrobInt value) {
        if (intNegative == value.intNegative) {
            // Both sign are the same so the result will have the same sign.
            return new BrobInt(addDigits(this.intArrayList, value.intArrayList), intNegative);
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

    private List<Integer> addDigits(List<Integer> a1, List<Integer> a2) {
        int carryOver = 0;

        int size = Math.max(a1.size(), a2.size());
        ArrayList<Integer> result = new ArrayList<>();
        for (int counter = 0; counter < size; counter++) {
            int d1 = counter >= a1.size() ? 0 : a1.get(a1.size() - 1 - counter);
            int d2 = counter >= a2.size() ? 0 : a2.get(a2.size() - 1 - counter);
            int bitAddition = d1 + d2 + carryOver;
            carryOver = bitAddition / 10;
            bitAddition = bitAddition % 10;
            result.add(0, bitAddition);
        }
        if (carryOver > 0) {
            result.add(0, carryOver);
        }

        return result;
    }

    public BrobInt subtract(BrobInt value) {
        return add(new BrobInt(value, true));
    }

    private BrobInt subtractPositiveInt(BrobInt value) {
        int rowSubtraction;
        ArrayList<Integer> calculationIntList = new ArrayList<>();
        List<Integer> largerValue;
        List<Integer> smallerValue;
        if (compareTo(value) == 1) {
            largerValue = intArrayList;
            smallerValue = value.intArrayList;
        } else {
            largerValue = value.intArrayList;
            smallerValue = intArrayList;
        }

        int size = Math.max(smallerValue.size(), largerValue.size());
        int carryOver = 0;
        int counter;
        for (counter = 0; counter < size; counter++) {
            int d1 = counter >= largerValue.size() ? 0 : largerValue.get(largerValue.size() - 1 - counter);
            int d2 = counter >= smallerValue.size() ? 0 : smallerValue.get(smallerValue.size() - 1 - counter);
            rowSubtraction = d1 - d2 - carryOver;
            if (rowSubtraction < 0) {
                rowSubtraction += 10;
                carryOver = 1;
            } else {
                carryOver = 0;
            }
            calculationIntList.add(0, rowSubtraction);
        }

        if (carryOver != 0) {
            rowSubtraction = 10 - carryOver;
            calculationIntList.add(0, rowSubtraction);
        }

        return removeLeadingZeroes(abs(new BrobInt(calculationIntList, false)));
    }

    public BrobInt multiply(BrobInt value) {
        BrobInt calculation = new BrobInt(abs(this).multiplyPositiveInt(abs(value)));
        if (value.equals(ZERO) || equals(ZERO)) {
            return ZERO;
        }
        if (value.intNegative == intNegative) {
            return calculation;
        } else {
            return new BrobInt(calculation, true);
        }
    }

    public BrobInt multiplyPositiveInt(BrobInt value) {
        ArrayList<List<Integer>> additionValues = new ArrayList<>();
        List<Integer> largerBrobIntList = (this.intArrayList.size() > value.intArrayList.size()) ? intArrayList : value.intArrayList;
        List<Integer> smallerBrobIntList = (this.intArrayList.size() > value.intArrayList.size()) ? value.intArrayList : intArrayList;
        int counter = 0;
        for (int index = smallerBrobIntList.size() - 1; index >= 0; index--) {
            int carryOver = 0;
            ArrayList<Integer> newDigits = new ArrayList<>();
            additionValues.add(0, newDigits);
            for (int i = 0; i < counter; i++) {
                newDigits.add(0, 0);
            }
            for (int bigIndex = largerBrobIntList.size() - 1; bigIndex >= 0; bigIndex--) {
                int temp = largerBrobIntList.get(bigIndex) * smallerBrobIntList.get(index) + carryOver;

                carryOver = temp / 10;
                temp = temp % 10;
                newDigits.add(0, temp);
            }
            if (carryOver != 0) {
                newDigits.add(0, carryOver);
            }
            counter++;
        }

        List<Integer> calculation = new ArrayList<>();
        for (int i = 0; i < additionValues.size(); i++) {
            calculation = addDigits(calculation, additionValues.get(i));
        }

        return new BrobInt(calculation, false);
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
        if (allZeroDetect(this)) {
            return "0";
        }
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

    public boolean equals(Object b) {
        return b.toString().equals(toString());
    }

    public static BrobInt valueOf(long value) {
        return new BrobInt(Long.toString(value));
    }
}

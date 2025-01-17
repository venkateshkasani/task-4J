import java.util.*;

public class Question {

    public static void main(String[] args) {
        int timeLimit = 13; 

        List<String> combinations = generateCombinations(timeLimit);

        String bestCombination = "";
        int maxProfit = 0;

        for (String combination : combinations) {
            int profit = calculateProfit(combination, timeLimit);
            if (profit > maxProfit) {
                maxProfit = profit;
                bestCombination = combination;
            }
        }

        System.out.println("Best Combination: " + bestCombination);
        System.out.println("Maximum Profit: " + maxProfit);
    }

    private static List<String> generateCombinations(int timeLimit) {
        List<String> combinations = new ArrayList<>();

        for (int tCount = 0; tCount * 5 <= timeLimit; tCount++) {
            for (int pCount = 0; pCount * 4 <= timeLimit; pCount++) {
                for (int cCount = 0; cCount * 10 <= timeLimit; cCount++) {
                    int timeUsed = tCount * 5 + pCount * 4 + cCount * 10;
                    if (timeUsed <= timeLimit) {
                        String combination = "T" + tCount + "P" + pCount + "C" + cCount;
                        combinations.add(combination);
                    }
                }
            }
        }
        return combinations;
    }

    private static int calculateProfit(String combination, int timeLimit) {
        int profit = 0;
        int currentTime = 0;

        for (char c : combination.toCharArray()) {
            if (Character.isDigit(c)) continue;
            int count = Character.getNumericValue(combination.charAt(combination.indexOf(c) + 1));
            int timePerUnit = (c == 'T') ? 5 : (c == 'P') ? 4 : 10;
            int earningPerUnitTime = (c == 'T') ? 1500 : (c == 'P') ? 1000 : 3000;

            for (int i = 0; i < count; i++) {
                currentTime += timePerUnit;
                if (currentTime <= timeLimit) {
                    profit += earningPerUnitTime * (timeLimit - currentTime);
                }
            }
        }

        return profit;
    }
}

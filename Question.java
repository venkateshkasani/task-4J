import java.util.*;

public class Question {

    public static void main(String args[]) {
        int timeLimit = 7; 

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

        Map<Character, Integer> buildingCounts = new HashMap<>();
        for (char c : combination.toCharArray()) {
            if (Character.isDigit(c)) continue;
            buildingCounts.put(c, buildingCounts.getOrDefault(c, 0) + 1);
        }

        int tCount = buildingCounts.getOrDefault('T', 0);
        int pCount = buildingCounts.getOrDefault('P', 0);
        int cCount = buildingCounts.getOrDefault('C', 0);

        profit += tCount * 1500 * (timeLimit - 5 * tCount);
        profit += pCount * 1000 * (timeLimit - 4 * pCount);
        profit += cCount * 3000 * (timeLimit - 10 * cCount);

        return profit;
    }
}

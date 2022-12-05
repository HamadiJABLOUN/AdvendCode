package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day2Part2 {
    public static void main(String[] args) {
        List<String> rounds = InputUtils.getLines("input.day2");

        HashMap<String, String> judge = new HashMap<>();
        //<== lose
        // ==> win
        judge.put("A", "C");
        judge.put("B", "A");
        judge.put("C", "B");

        HashMap<String, Integer> scoreScale = new HashMap<>();
        scoreScale.put("A", 1);
        scoreScale.put("B", 2);
        scoreScale.put("C", 3);

        int score = 0;
        for (String round : rounds) {
            String[] hands = round.split(" ");
            String opponentHand = hands[0];
            String resultOfRound = hands[1];

            String myHand = getMyHandToPlay(opponentHand, resultOfRound, judge);

            score += scoreScale.get(myHand);

            if (myHand.equals(opponentHand)) {
                score += 3;
                continue;
            }

            String handToLose = judge.get(opponentHand);

            if (!handToLose.equals(myHand)) {
                score += 6;
            }
        }
        System.out.println("total score ==> " + score);
    }


    private static String getMyHandToPlay(String adv, String resultOfRound, HashMap<String, String> judge) {
        switch (resultOfRound) {
            case "X" -> {
                return judge.get(adv);
            }
            case "Y" -> {
                return adv;
            }
            case "Z" -> {
                return judge
                        .entrySet()
                        .stream()
                        .filter(entry -> Objects.equals(entry.getValue(), adv))
                        .map(Map.Entry::getKey).collect(Collectors.joining());
            }
        }

        return "";
    }
}



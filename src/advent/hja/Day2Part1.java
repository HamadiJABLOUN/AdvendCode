package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Day2Part1 {
    public static void main(String[] args) {

        List<String> rounds = InputUtils.getLines("input.day2");

        HashMap<String, String> judge = new HashMap<>();
        judge.put("A", "Z");
        judge.put("B", "X");
        judge.put("C", "Y");

        HashMap<String, Integer> scoreScale = new HashMap<>();
        scoreScale.put("X", 1);
        scoreScale.put("Y", 2);
        scoreScale.put("Z", 3);

        int score = 0;
        for (String round : rounds) {
            String[] s1 = round.split(" ");
            String opponentHand = s1[0];
            String myHand = s1[1];

            score += scoreScale.get(myHand);

            switch (round) {
                case "A X", "B Y", "C Z" -> {
                    score += 3;
                    continue;
                }
            }

            String handToLose = judge.get(opponentHand);

            if (!Objects.equals(handToLose, myHand)) {
                score += 6;
            }
        }
        System.out.println("total score == " + score);
    }
}

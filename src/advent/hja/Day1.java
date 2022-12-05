package advent.hja;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static advent.hja.utils.InputUtils.getLines;

public class Day1 {

    public static void main(String[] args) {
        List<Integer> sumCalories = getSumCalories(getLines("input.day1"));
        System.out.println("Part 1 ==> " + sumCalories.get(0));
        System.out.println("Part 2 ==> " + sumCalories.get(0) + sumCalories.get(1) + sumCalories.get(2));
    }

    private static List<Integer> getSumCalories(List<String> input) {
        List<Integer> calories = new ArrayList<>();
        int somme = 0;
        for (String calorie : input) {
            if (calorie.isBlank()) {
                calories.add(somme);
                somme = 0;
            } else {
                somme += Integer.parseInt(calorie);
            }
        }
        calories.add(somme);
        calories.sort(Comparator.reverseOrder());
        return calories;
    }
}

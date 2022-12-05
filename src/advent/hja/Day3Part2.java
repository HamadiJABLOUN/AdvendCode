package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.*;

public class Day3Part2 {

    public static void main(String[] args) {
        Integer sumCalories = getSumPriorities(InputUtils.getLines("input.day3"));
        System.out.println(sumCalories);
    }

    //2342
    private static Integer getSumPriorities(List<String> input) {
        int somme = 0;
        String alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaLower = alphaUpper.toLowerCase();

        var size = input.size();

        for (int i = 0; i < size / 3; i++) {

            List<String> compartment1 = Arrays.stream(input.get(0).split("")).toList();
            List<String> compartment2 = Arrays.stream(input.get(1).split("")).toList();
            List<String> compartment3 = Arrays.stream(input.get(2).split("")).toList();

            input = input.subList(3, input.size());

            List<String> result = compartment1.stream()
                    .distinct()
                    .filter(compartment2::contains).toList();

            List<String> result2 = result.stream()
                    .distinct()
                    .filter(compartment3::contains).toList();

            var j = alphaLower.indexOf(result2.get(0)) + 1;
            if (j == 0) {
                j = alphaUpper.indexOf(result2.get(0)) + 27;
            }
            somme = somme + j;
        }
        return somme;
    }
}

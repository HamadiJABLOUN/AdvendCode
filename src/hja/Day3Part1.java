package hja;

import java.time.temporal.ValueRange;
import java.util.*;
import java.util.stream.IntStream;

import static hja.utils.InputUtils.getLines;

public class Day3Part1 {

    public static void main(String[] args) {
        Integer sumCalories = getSumPriorities(getLines("input.day3"));
        System.out.println(sumCalories);
    }


    private static Integer getSumPriorities(List<String> input) {
        var somme = 0;

        String alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaLower = alphaUpper.toLowerCase();

        for (String sac : input) {
            List<String> compartment1 = Arrays.stream(sac.substring(0, sac.length() / 2).split("")).toList();
            List<String> compartment2 = Arrays.stream(sac.substring((sac.length() / 2)).split("")).toList();

            var type = compartment1.stream()
                    .distinct()
                    .filter(compartment2::contains).toList().get(0);

            var i = alphaLower.indexOf(type) + 1;
            if (i == 0) {
                i = alphaLower.indexOf(type) + 27;
            }
            somme += i;
        }
        return somme;
    }
}

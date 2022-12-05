package advent.hja;

import advent.hja.type.Interval;
import advent.hja.utils.InputUtils;

import java.util.List;

public class Day4Part1 {
    //584
    public static void main(String[] args) {
        Integer sumCalories = getSumSections(InputUtils.getLines("input.day4"));
        System.out.println("Part 1 ==> " + sumCalories);
    }

    private static Integer getSumSections(List<String> input) {
        int somme = 0;
        for (String intervals : input) {
            String[] interval = intervals.split(",");

            Interval intervalElf1 = getInterval(interval, 0);
            Interval intervalElf2 = getInterval(interval, 1);

            if ((intervalElf1.min() <= intervalElf2.min()) && intervalElf1.max() >= intervalElf2.max()
                    || (intervalElf2.min() <= intervalElf1.min()) && intervalElf2.max() >= intervalElf1.max()) {
                somme++;
            }

        }
        return somme;
    }

    private static Interval getInterval(String[] split, int x) {
        double borne0 = Double.parseDouble(split[x].split("-")[0]);
        double borne1 = Double.parseDouble(split[x].split("-")[1]);
        return new Interval(borne0, borne1);
    }
}

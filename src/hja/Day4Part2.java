package hja;

import hja.utils.Interval;

import java.util.List;

import static hja.utils.InputUtils.getLines;

public class Day4Part2 {
    //933
    public static void main(String[] args) {
        Integer sectionsSum = getSumSections(getLines("input.day4"));
        System.out.println("Part 2 ==> " + sectionsSum);
    }

    private static Integer getSumSections(List<String> input) {
        int somme = 0;
        for (String intervals : input) {
            String[] interval = intervals.split(",");

            Interval intervalElf1 = getInterval(interval, 0);
            Interval intervalElf2 = getInterval(interval, 1);

            if (intervalElf1.intersects(intervalElf2)) {
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

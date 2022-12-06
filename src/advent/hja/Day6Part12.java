package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Day6Part12 {

    public static void main(String[] args) {
        var markerStartedIndexPart1 =
                getMarkerStartedIndex(InputUtils.getLines("input.day6"), 4);//1707
        var markerStartedIndexPart2 =
                getMarkerStartedIndex(InputUtils.getLines("input.day6"), 14);//3697
        System.out.println("Result Part 1 ==> " + markerStartedIndexPart1);
        System.out.println("Result Part 2 ==> " + markerStartedIndexPart2);
    }

    private static Integer getMarkerStartedIndex(List<String> input, int lengthOfMarker) {
        String message = input.get(0);
        String marker = "";
        while (message.length() >= lengthOfMarker) {
            marker = message.substring(0, lengthOfMarker);
            if (isContainsDouble(marker)) {
                break;
            }
            message = message.substring(1);
        }
        message = input.get(0);

        return message.lastIndexOf(marker) + lengthOfMarker;
    }

    private static boolean isContainsDouble(String stacksLine) {
        List<String> stringAsList = Arrays.stream(stacksLine.split("")).toList();
        Set<String> targetSet = Set.copyOf(stringAsList);
        return stringAsList.size() == targetSet.size();
    }
}
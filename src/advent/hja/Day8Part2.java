package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Day8Part2 {

    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day8");
        System.out.println("Result ==> " + getTreeScore(lines, 99));
    }

    private static Integer getTreeScore(List<String> input, int size) {
        int[][] grid = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] tree = input.get(i).replaceAll("\\r", "").split("");
            for (int j = 0; j < size; j++) {
                grid[i][j] = Integer.parseInt(tree[j]);
            }
        }

        List<Integer> score = new ArrayList<>();
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                score.add(checkNumberOfArbre(grid, i, j, size));
            }
        }

        return score
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);//284_648


    }

    private static int checkNumberOfArbre(int[][] terre, int i, int j, int size) {
        int right = checkRight(terre, i, j, size);
        int left = checkLeft(terre, i, j);
        int down = checkDown(terre, i, j, size);
        int high = checkHigh(terre, i, j);
        System.out.println(terre[i][j] + "==> high = " + right + "| " + "left = " + left + " | down = " + down + " | high = " + high);
        return right *
                left *
                down *
                high;
    }

    private static int checkDown(int[][] terre, int i, int j, int size) {
        int numberOfTree = 0;

        for (int d = i + 1; d < size; d++) {
            if (terre[i][j] <= terre[d][j]) {
                return numberOfTree + 1;
            }
            numberOfTree++;
        }
        return numberOfTree;
    }

    private static int checkRight(int[][] terre, int i, int j, int size) {
        int numberOfTree = 0;
        for (int d = j + 1; d < size; d++) {
            if (terre[i][j] <= terre[i][d]) {
                return numberOfTree + 1;
            }
            numberOfTree++;
        }
        return numberOfTree;
    }

    private static int checkLeft(int[][] terre, int i, int j) {
        int numberOfTree = 0;

        for (int d = j - 1; d >= 0; d--) {
            if (terre[i][j] <= terre[i][d]) {
                return numberOfTree + 1;
            }
            numberOfTree++;

        }
        return numberOfTree;
    }


    private static int checkHigh(int[][] terre, int i, int j) {
        int numberOfTree = 0;

        for (int d = i - 1; d >= 0; d--) {
            if (terre[i][j] <= terre[d][j]) {
                return numberOfTree + 1;
            }
            numberOfTree++;

        }
        return numberOfTree;
    }
}
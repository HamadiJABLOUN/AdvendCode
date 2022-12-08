package advent.hja;

import advent.hja.utils.InputUtils;

import java.util.List;


public class Day8Part1 {

    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day8");
        System.out.println("Result ==> " + getTreeNumber(lines, 99));
    }

    private static Integer getTreeNumber(List<String> input, int gridSize) {

        int[][] grid = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            String[] split1 = input.get(i).replaceAll("\\r", "").split("");
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = Integer.parseInt(split1[j]);
            }
        }

        int numberOfTree = (gridSize * 4) - 4;

        for (int i = 1; i < gridSize - 1; i++) {
            for (int j = 1; j < gridSize - 1; j++) {
                if (checkTreeIsVisible(grid, i, j, gridSize)) {
                    numberOfTree = numberOfTree + 1;
                }
            }
        }
        return numberOfTree;//1733
    }

    private static boolean checkTreeIsVisible(int[][] terre, int i, int j, int size) {
        boolean right = checkRight(terre, i, j, size);
        boolean left = checkLeft(terre, i, j);
        boolean down = checkDown(terre, i, j);
        boolean high = checkHigh(terre, i, j);
        System.out.println(terre[i][j] + "==> right = " + right + "| " + "left = " + left + " | down = " + down + " | high = " + high);
        return right ||
                left ||
                down ||
                high;
    }

    private static boolean checkRight(int[][] terre, int i, int j, int size) {
        for (int d = j + 1; d < size; d++) {
            if (terre[i][j] <= terre[i][d]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkLeft(int[][] terre, int i, int j) {
        for (int d = j - 1; d >= 0; d--) {
            if (terre[i][j] <= terre[i][d]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkHigh(int[][] terre, int i, int j) {
        for (int d = i - 1; d >= 0; d--) {
            if (terre[i][j] <= terre[d][j]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDown(int[][] terre, int i, int j) {
        for (int d = i - 1; d >= 0; d--) {
            if (terre[i][j] <= terre[d][j]) {
                return false;
            }
        }
        return true;
    }
}
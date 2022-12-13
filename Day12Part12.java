package advent.hja;

import advent.hja.type.Corr;
import advent.hja.type.day12.Node;
import advent.hja.utils.InputUtils;

import java.util.*;


public class Day12Part12 {

    public static final String UP = "Up";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";
    public static final String DOWN = "Down";
    public static final int SIZE_I = 41;
    public static final int SIZE_J = 159;
    public static final String alphaLower = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();

    public static Corr end = null;

    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day12");
        System.out.println("Result PART 1 ==> " + getTreeScore(lines, "#"));
        System.out.println("Result PART 2 ==> " + getTreeScore(lines, "a"));
    }


    private static Integer getTreeScore(List<String> inputs, String caseToStart) {

        List<Corr> listA = new ArrayList<>();
        String[][] matrix = getMatrix(inputs, caseToStart, listA);

        List<Integer> result = new ArrayList<>();
        for (Corr startCase : listA) {

            boolean[][] visited = new boolean[SIZE_I][SIZE_J];
            List<Corr> possible = verifCase(end, matrix, visited);
            Corr toCheck = getElevationEnd(possible, matrix);

            visited[startCase.getI()][startCase.getJ()] = true;
            Queue<Node> q = new ArrayDeque<>();
            q.add(new Node(startCase.getI(), startCase.getJ(), 0));

            while (!q.isEmpty()) {
                Node node = q.poll();
                Corr currentCase = new Corr(node.x, node.y);
                int dist = node.dist;

                if (isaArrived(toCheck, currentCase)) {
                    result.add(dist + 1);
                }

                List<Corr> casePossibleToGo = verifCase(currentCase, matrix, visited);
                List<Corr> filteredCase = filterCasePossible(matrix, currentCase, casePossibleToGo);

                filteredCase.forEach(c -> {
                    visited[c.getI()][c.getJ()] = true;
                    q.add(new Node(c.getI(), c.getJ(), dist + 1));
                });
            }
        }
        Collections.sort(result);
        return result.get(0);
    }

    private static String[][] getMatrix(List<String> inputs, String caseToStart, List<Corr> listA) {
        String[][] matrices = new String[SIZE_I][SIZE_J];
        int i = 0;
        for (String line : inputs) {
            String[] split = line.replaceAll("\\r", "").split("");
            for (var k = 0; k < split.length; k++) {
                if (split[k].equals("S")) {
                    listA.add(new Corr(i, k));
                }
                if (split[k].equals("E")) {
                    end = new Corr(i, k);
                }
                if (split[k].equals(caseToStart)) {
                    listA.add(new Corr(i, k));
                }
                matrices[i][k] = split[k];
            }
            i++;
        }
        return matrices;
    }

    private static boolean isaArrived(Corr toCheck, Corr currentCase) {
        return currentCase.getI() == toCheck.getI() && currentCase.getJ() == toCheck.getJ();
    }


    private static Corr getElevationEnd(List<Corr> ends, String[][] matrices) {
        Corr coordinate = new Corr(end.getI() + 1, end.getJ());
        var max = Integer.MIN_VALUE;
        for (Corr end : ends) {
            var i = alphaLower.indexOf(matrices[end.getI()][end.getJ()]);
            if (i > max) {
                max = i;
                coordinate = end;
            }
        }
        return coordinate;
    }

    private static List<Corr> filterCasePossible(String[][] matrices, Corr currentCase, List<Corr> corrs) {
        String currentCaseValue = matrices[currentCase.getI()][currentCase.getJ()];
        List<Corr> possible = new ArrayList<>();
        if (currentCaseValue.equals("S") || currentCaseValue.equals("E")) {
            return corrs;
        }
        corrs.forEach(corr -> {
            int iCurrentCase = alphaLower.indexOf(currentCaseValue);
            int iToGoCase = alphaLower.indexOf(matrices[corr.getI()][corr.getJ()]);
            if (((iToGoCase - iCurrentCase) <= 1)) {
                possible.add(corr);
            }
        });

        return possible;
    }

    private static List<Corr> verifCase(Corr start, String[][] matrices, boolean[][] visited) {
        //0,0 ==> will check 0,1 and 1,0
        //1,1 ==> will check line ==> (1,0 and 1,2) and  column ==>  (0,1) and (2,1)
        //2,3 ==> will check line ==> (2,4 and 2,2) and  column ==>  (1,3) and (3,3)
        //i,j ==> will check line ==> (2,J+1 and i,j-1) and  column ==>  (i-1,j) and (i-1,j)
        //left ==> gauche

        List<Corr> possibleCorr = getPossibleCorr(start);
        List<Corr> finalBlockedStep = new ArrayList<>();

        for (Corr corr : possibleCorr) {
            String content = matrices[corr.getI()][corr.getJ()];

            if (!(visited[corr.getI()][corr.getJ()]) && !(content.equals("S")) && !(content.equals("E"))) {
                finalBlockedStep.add(corr);
            }
        }


        return finalBlockedStep;

    }

    private static List<Corr> getPossibleCorr(Corr start) {
        List<Corr> possibleCorr = new ArrayList<>();
        List<String> blockedStep = new ArrayList<>();
        if (start.getI() == 0) {
            blockedStep.add(UP);
        }
        if (start.getJ() == 0) {
            blockedStep.add(LEFT);

        }
        if (start.getJ() == (SIZE_J - 1)) {
            blockedStep.add(RIGHT);

        }
        if (start.getI() == (SIZE_I - 1)) {
            blockedStep.add(DOWN);
        }

        if (!blockedStep.contains(UP)) {
            possibleCorr.add(move(start, UP));
        }
        if (!blockedStep.contains(DOWN)) {
            possibleCorr.add(move(start, DOWN));
        }
        if (!blockedStep.contains(RIGHT)) {
            possibleCorr.add(move(start, RIGHT));
        }
        if (!blockedStep.contains(LEFT)) {
            possibleCorr.add(move(start, LEFT));
        }
        return possibleCorr;
    }

    public static Corr move(Corr corr, String pas) {
        return switch (pas) {
            case UP -> new Corr(corr.getI() - 1, corr.getJ());
            case DOWN -> new Corr(corr.getI() + 1, corr.getJ());
            case LEFT -> new Corr(corr.getI(), corr.getJ() - 1);
            case RIGHT -> new Corr(corr.getI(), corr.getJ() + 1);
            default -> new Corr(0, 0);
        };
    }

}

package advent.hja;

import advent.hja.type.Coordinate;
import advent.hja.type.Queue;
import advent.hja.utils.InputUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day9Part1 {

    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day9");
        System.out.println("Result ==> " + getTreeScore(lines));
    }

    private static Integer getTreeScore(List<String> input) {
        Queue queue = new Queue(new Coordinate(12, 6), new Coordinate(12, 6));
        Set<String> result = new HashSet<>();

        for (String in : input) {
            String[] move = in.replaceAll("\\r", "").split(" ");
            String direction = move[0];
            switch (direction) {
                case "R" -> moveR(move, queue, result);
                case "L" -> moveL(move, queue, result);
                case "D" -> moveD(move, queue, result);
                case "U" -> moveU(move, queue, result);
            }
            System.out.println("que H ==> " + queue.getH().getI() + "," + queue.getH().getJ());
            System.out.println("que T ==> " + queue.getT().getI() + "," + queue.getT().getJ());
        }

        return result.size() + 1;//6522


    }

    static boolean isNotTouching(Queue queue) {
        return !(Math.abs(queue.getH().getI() - queue.getT().getI()) <= 1 && Math.abs(queue.getH().getJ() - queue.getT().getJ()) <= 1);
    }

    private static void moveR(String[] move, Queue queue, Set<String> result) {
        int oldTJ = queue.getT().getJ();
        int pas = Integer.parseInt(move[1]);
        queue.getH().setJ(queue.getH().getJ() + pas);
        if (isNotTouching(queue)) {

            queue.getT().setI(queue.getH().getI());
            queue.getT().setJ(queue.getH().getJ() - 1);

            int tailPas = queue.getT().getJ() - oldTJ;

            var indexJ = queue.getT().getJ();
            for (int j = 0; j < tailPas; j++) {
                result.add(queue.getT().getI() + "," + (indexJ));
                indexJ = indexJ - 1;
            }

        }
    }

    private static void moveD(String[] move, Queue queue, Set<String> result) {
        var oldTI = queue.getT().getI();
        int pas = Integer.parseInt(move[1]);

        queue.getH().setI(queue.getH().getI() + pas);

        if (isNotTouching(queue)) {
            queue.getT().setI(queue.getH().getI() - 1);
            queue.getT().setJ(queue.getH().getJ());
            int pasT = Math.abs(queue.getT().getI() - oldTI);
            int indexI = queue.getT().getI();
            for (int i = 0; i < pasT; i++) {
                result.add(indexI + "," + queue.getT().getJ());
                indexI = indexI - 1;
            }
        }
    }


    private static void moveU(String[] move, Queue queue, Set<String> result) {
        int oldHI = queue.getT().getI();
        int pas = Integer.parseInt(move[1]);

        queue.getH().setI(queue.getH().getI() - pas);

        if (isNotTouching(queue)) {
            queue.getT().setI(queue.getH().getI() + 1);
            queue.getT().setJ(queue.getH().getJ());

            int pasRapide = Math.abs(queue.getT().getI() - oldHI);

            int indexI = queue.getT().getI();
            for (int i = 0; i < pasRapide; i++) {
                result.add(indexI + "," + queue.getT().getJ());
                indexI = indexI + 1;
            }
        }
    }


    private static void moveL(String[] move, Queue queue, Set<String> result) {
        int oldTJ = queue.getT().getJ();
        int pas = Integer.parseInt(move[1]);
        queue.getH().setJ(queue.getH().getJ() - pas);


        if (isNotTouching(queue)) {

            queue.getT().setI(queue.getH().getI());
            queue.getT().setJ(queue.getH().getJ() + 1);

            int pasRapide = Math.abs(queue.getT().getJ() - oldTJ);
            int indexJ = queue.getT().getJ();

            for (int j = 0; j < pasRapide; j++) {
                System.out.println("case : " + queue.getT().getI() + "," + indexJ + "]");
                result.add(queue.getT().getI() + "," + indexJ);
                indexJ = indexJ + 1;
            }
        }
    }


}

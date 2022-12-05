package advent.hja;

import advent.hja.type.Move;
import advent.hja.utils.InputUtils;
import advent.hja.type.Ship;

import java.util.*;

public class Day5Part12 {

    public static void main(String[] args) {
        String stacksHead = getStacksHead(InputUtils.getLinesWithEmpty("input.day51.txt"), 9);
        System.out.println("Result ==> " + stacksHead);
    }

    private static String getStacksHead(List<String> input, int stackSize) {
        Ship ship = new Ship(new ArrayList<>());
        Deque<String> stack = new LinkedList<>();
        List<Move> moves = new ArrayList<>();
        int index = 0;
        boolean createMoves = true;

        for (int j = 0; j < stackSize; j++) {
            for (String stacksLine : input) {
                if (!stacksLine.contains("1") && !stacksLine.contains("move") && !stacksLine.isBlank()) {
                    String substring;
                    if (index + 3 > stacksLine.length()) {
                        substring = stacksLine.substring(index - 1);
                    } else {
                        substring = stacksLine.substring(index, index + 3);
                    }
                    if (!substring.isBlank()) {
                        stack.add(substring);
                    }
                } else if (stacksLine.contains("move") && createMoves) {
                    String[] move = stacksLine.split(" ");
                    moves.add(new Move(Integer.parseInt(move[1]),
                            Integer.parseInt(move[3]),
                            Integer.parseInt(move[5])));
                }
            }
            createMoves = false;
            ship.stacks().add(stack);
            stack = new LinkedList<>();
            index = index + 4;
        }
        List<String> cratesToMove = new ArrayList<>();

        for (Move move : moves) {

            Deque<String> stackFrom = ship.stacks().get(move.stackFrom() - 1);
            Deque<String> stackTo = ship.stacks().get(move.stackTo() - 1);

            for (int i = 1; i <= move.numberOfCratesToMove(); i++) {
                cratesToMove.add(stackFrom.poll()); //ZFSJBPRFP
                //stackTo.addFirst(stackFrom.poll()); Part 1 without cratesToMove
            }

            Collections.reverse(cratesToMove); //remove this for part 1 //SPFMVDTZT

            for (String crate : cratesToMove) {
                stackTo.addFirst(crate);
            }
            cratesToMove = new ArrayList<>();
        }

        StringBuilder result = new StringBuilder();
        List<Deque<String>> stacks = ship.stacks();
        for (Deque<String> oneStack : stacks) {
            assert oneStack.peek() != null;
            result.append(oneStack.peek().replaceAll("[\\[\\]]", ""));
        }


        return result.toString();
    }

}

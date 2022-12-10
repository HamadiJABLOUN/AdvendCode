package advent.hja;

import advent.hja.type.Cycle;
import advent.hja.utils.InputUtils;

import java.util.*;

import static java.util.List.*;


public class Day10Part12 {
    // x= 1
    // on save tt les cycle
    // encours cycle + 1 ==> xtem
    //finencour cycle + 1+1 ==> xtem
    // tempX = tempX + x
    //x += temX ; tempx  = 0 ;
    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day10");
        System.out.println("Result ==> " + getResult(lines));
    }

    private static Integer getResult(List<String> input) {
        int xTemp = 1;
        List<Cycle> cycles = new ArrayList<>(of(new Cycle( 1)));
        getCycles(input, xTemp, cycles);

        var cycle = 1;
        List<String> display = new ArrayList<>();
        for (var i = 0; i < 6; ++i) {
            String crt = "";
            for (var j = 0; j < 40; ++j) {
                //X  définit la position horizontale du milieu de ce sprite.
                //donc  X de chaque cycle est la position dans la ligne
                // donc Si visible dans la 40 pixels ==> #
                if (isVisible(cycles, cycle, j)) {
                    crt = crt + "#";
                } else {
                    crt = crt + " ";
                }
                cycle ++ ;
            }
            display.add(crt);
        }
        for (String displayLine : display) {
            System.out.println("     " +displayLine);
        }

        return
                (cycles.get(20).xValue * 20) +
                        (cycles.get(60).xValue * 60) +
                        (cycles.get(100).xValue * 100) +
                        (cycles.get(140).xValue * 140) +
                        (cycles.get(180).xValue * 180) +
                        (cycles.get(220).xValue * 220);

    }

    private static void getCycles(List<String> input, int xTemp, List<Cycle> cycles) {
        for (String signal : input) {
            String[] command = signal.replaceAll("\\r", "").split(" ");
            String instruction = command[0];
           // si noop on inc cycle 1
            if (instruction.equals("noop")) {
                cycles.add(new Cycle(xTemp));
            } else {
                // si add encours + current
                cycles.add(new Cycle(xTemp));
                cycles.add(new Cycle(xTemp));
                xTemp += Integer.parseInt(command[1]);
            }
        }
    }

    private static boolean isVisible(List<Cycle> cycles, int cycle, int j) {
        return Math.abs(cycles.get(cycle).xValue - j) <= 1;
    }
}

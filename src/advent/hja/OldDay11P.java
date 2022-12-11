package advent.hja;

import advent.hja.utils.InputUtils;

import javax.script.ScriptException;
import java.util.*;


public class OldDay11P {
/* WITH INTEGER must change to BIGINTEGER TO PART2
    public static final String OLD = "old";

    public static void main(String[] args) throws ScriptException {
        List<String> lines = InputUtils.getLines("input.day11");
        System.out.println("Result ==> " + getResult(lines));
    }

    private static Integer getResult(List<String> input) throws ScriptException {
        List<Monkey> monkeys = new ArrayList<>();
// worrlevel = 79*19 = 1501
        // 1501 / 3 = 500
        //500 divible / 23
        int fromIndex = 0;
        int toIndex = 6;
        extracted(input, monkeys, fromIndex, toIndex);
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);
        map.put(7, 0);
        for (int r = 1; r < 21; r++) {
            List<Item> itemToDelete;

            for (Monkey monkey : monkeys) {
                int numberitem = 0;
                itemToDelete = new ArrayList<>();
                for (Item item : monkey.items) {
                    numberitem++;
                    String operation = monkey.operations;

                    String operation1 = operation.replaceAll(OLD, String.valueOf(item.worrongLevel));

                    int worryLevel = getWorryLevel(operation1);

                    int worryLevelDivideThree = Math.round(worryLevel / 3);
                    item.worrongLevel = worryLevelDivideThree;
                    if ((worryLevelDivideThree % Integer.parseInt(monkey.test.test) == 0)) {
                        monkeys.get(monkey.test.isTrue).items.add(item);
                        itemToDelete.add(item);

                    } else {
                        monkeys.get(monkey.test.isFalse).items.add(item);
                        itemToDelete.add(item);
                    }

                }

                monkey.items.removeAll(itemToDelete);
                Integer integer = map.get(monkeys.indexOf(monkey));
                int sommeX = numberitem + integer;
                map.put(monkeys.indexOf(monkey), sommeX);
                System.out.println("End Round ");

            }
        }

        List<Integer> score = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            score.add(entry.getValue());
        }
        List<Integer> integers = score.stream().sorted(Comparator.reverseOrder()).toList();
        int i = integers.get(0) * integers.get(1);
        return
                i;
    }

    public static Integer getWorryLevel(String operation) {


        if (operation.contains("*")) {
            String[] split = operation.split("\\*");
            return Integer.parseInt(split[1]) * Integer.parseInt(split[0]);
        }
        if (operation.contains("+")) {
            String[] split = operation.split("\\+");
            int i = Integer.parseInt(split[1]) + Integer.parseInt(split[0]);
            return i;
        } else
            return 0;


    }

    private static void extracted(List<String> input, List<Monkey> monkeys, int fromIndex, int toIndex) {
        for (int j = 0; j < 8; j++) {

            List<String> subString = input.subList(fromIndex, toIndex);
            Monkey monkey = new Monkey();
            Test test = new Test();
            for (String round : subString) {
                String roundIngore = round.replaceAll(" ", "").replaceAll("\\r", "");
                if (roundIngore.startsWith("Starting")) {
                    roundIngore = roundIngore.replaceAll("Startingitems:", "");
                    List<Item> items = new ArrayList<>();
                    String[] split = roundIngore.split(",");

                    for (int i = 0; i < split.length; i++) {
                        Item item = new Item();
                        item.worrongLevel = Integer.valueOf(split[i]);
                        item.item = Integer.valueOf(split[i]);
                        items.add(item);
                    }
                    monkey.items = items;
                } else if (roundIngore.startsWith("Operation")) {
                    roundIngore = roundIngore.replaceAll("Operation:new=", "");
                    monkey.operations = roundIngore;
                } else if (roundIngore.startsWith("Test:")) {
                    String[] s = round.split(" ");
                    String s1 = s[s.length - 1];
                    test.test = s1;
                } else if (roundIngore.startsWith("Iftrue:throwtomonkey")) {
                    String[] s = round.split(" ");
                    String s1 = s[s.length - 1];
                    test.isTrue = Integer.parseInt(s1);
                } else if (roundIngore.startsWith("Iffalse:throwtomonkey")) {
                    String[] s = round.split(" ");
                    String s1 = s[s.length - 1];
                    test.isFalse = Integer.parseInt(s1);
                }
            }
            monkey.test = test;
            monkeys.add(monkey);

            fromIndex = toIndex + 1;
            toIndex = toIndex + 7;
        }
    }
*/
}

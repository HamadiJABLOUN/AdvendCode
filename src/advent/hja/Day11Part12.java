package advent.hja;

import advent.hja.type.day11.Item;
import advent.hja.type.day11.Monkey;
import advent.hja.type.day11.Test;
import advent.hja.utils.InputUtils;

import javax.script.ScriptException;
import java.math.BigInteger;
import java.util.*;


public class Day11Part12 {

    public static final String OLD = "old";

    public static void main(String[] args) throws ScriptException {
        List<String> lines = InputUtils.getLines("input.day11");
        System.out.println("Result ==> " + getResult(lines)); //19309892877
    }

    private static BigInteger getResult(List<String> input) {
        List<Monkey> monkeys = new ArrayList<>();
        int numberOfMonkey = 8;

        int fromIndex = 0;
        int toIndex = 6; //end monkey line
        getMonkeys(input, monkeys, fromIndex, toIndex,numberOfMonkey);
        Map<Integer, BigInteger> monkeyItemNumber = new LinkedHashMap<>();
        for (var i = 0; i< numberOfMonkey; i++)
        {
            monkeyItemNumber.put(i, BigInteger.valueOf(0));
        }


        BigInteger k = BigInteger.valueOf(1); //lcm
        for (Monkey m : monkeys) {
            k = k.multiply(m.test.div);
            // k = Integer.valueOf(m.test.test) * k; //9699690 must change all for BigInteger sinon des nombres négatif
        }

        for (var r = 0; r < 10000; ++r) {
            List<Item> itemToDelete;

            for (Monkey monkey : monkeys) {
                BigInteger itemNumber = BigInteger.valueOf(0);
                itemToDelete = new ArrayList<>();
                for (Item item : monkey.items) {
                    itemNumber = itemNumber.add(BigInteger.ONE);
                    String operation =  monkey.operations.replaceAll(OLD, String.valueOf(item.worryLevel));
                    BigInteger worryLevel = getWorryLevel(operation);
                    // int worryLevelDivideThree = Math.round(worryLevel / 3); //part 1
                    BigInteger newWorryLevel = worryLevel.divideAndRemainder(k)[1];
                    item.worryLevel = newWorryLevel;

                    if ((newWorryLevel.divideAndRemainder(monkey.test.div)[1].equals(BigInteger.ZERO))) {
                        monkeys.get(monkey.test.isTrue).items.add(item);
                        itemToDelete.add(item);
                    } else {
                        monkeys.get(monkey.test.isFalse).items.add(item);
                        itemToDelete.add(item);
                    }

                }

                monkey.items.removeAll(itemToDelete);
                monkeyItemNumber.put(monkeys.indexOf(monkey), itemNumber.add(monkeyItemNumber.get(monkeys.indexOf(monkey))));//add round item to old number item
            }
        }

        List<BigInteger> score = new ArrayList<>();
        monkeyItemNumber.forEach((key, value) -> score.add(value));
        List<BigInteger> integers = score.stream().sorted(Comparator.reverseOrder()).toList();
        return integers.get(0).multiply(integers.get(1));
    }

    public static BigInteger getWorryLevel(String operation) {
        if (operation.contains("*")) {
            String[] split = operation.split("\\*");
            BigInteger bigInteger = BigInteger.valueOf(Integer.parseInt(split[1]));
            BigInteger bigInteger1 = BigInteger.valueOf(Integer.parseInt(split[0]));

            return bigInteger1.multiply(bigInteger);
        }
        if (operation.contains("+")) {
            String[] split = operation.split("\\+");
            BigInteger bigInteger = BigInteger.valueOf(Integer.parseInt(split[1]));
            BigInteger bigInteger1 = BigInteger.valueOf(Integer.parseInt(split[0]));
            return bigInteger.add(bigInteger1);
        } else
            return BigInteger.valueOf(0);
    }

    private static void getMonkeys(List<String> input, List<Monkey> monkeys, int fromIndex, int toIndex, int numberOfMonkey) {
        for (int j = 0; j < numberOfMonkey; j++) {
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
                        item.worryLevel = BigInteger.valueOf(Integer.valueOf(split[i]));
                        item.item = BigInteger.valueOf(Integer.valueOf(split[i]));
                        items.add(item);
                    }
                    monkey.items = items;
                } else if (roundIngore.startsWith("Operation")) {
                    roundIngore = roundIngore.replaceAll("Operation:new=", "");
                    monkey.operations = roundIngore;
                } else if (roundIngore.startsWith("Test:")) {
                    String[] s = round.split(" ");
                    String s1 = s[s.length - 1];
                    test.div = BigInteger.valueOf(Integer.parseInt(s1));
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

}

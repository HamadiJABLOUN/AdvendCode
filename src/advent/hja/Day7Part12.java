package advent.hja;

import advent.hja.type.Tree;
import advent.hja.utils.InputUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Day7Part12 {
    //TODO : need REFACTO :-)

    //if cd name ==> change selectedDirectory
    //if cd .. ==> selectedDirectory.parent
    //if ls =>
        //if file : add file to selectedDirectory
        //else if dir : add child to selectedDirectory
    //==> get only directory ==> calcul sum for each directory

    public static void main(String[] args) {
        List<String> lines = InputUtils.getLines("input.day7");
        System.out.println("Result ==> " + getMarkerStartedIndex(lines));
    }

    private static Integer getMarkerStartedIndex(List<String> input) {
        String[] s = input.get(0).split(" ");
        String firstDirectory = s[2];

        Tree subTree = new Tree();
        subTree.name = firstDirectory.replaceAll("\\r", "");
        subTree.parent = new Tree();
        Tree selectedTree = subTree;

        for (int i = 1; i < input.size(); i++) {
            String command = input.get(i).replaceAll("\\r", "");

            if (command.startsWith("$ ls")) {
            } else if (command.startsWith("$ cd")) {
                String[] ss = command.split(" ");
                String name = ss[2];
                if (name.equals("..")) {
                    selectedTree = selectedTree.parent;
                } else {
                    selectedTree =
                            selectedTree.children.stream().filter(customer -> name.equals(customer.name)).findAny().orElse(null);
                }

            } else {
                if (command.startsWith("dir")) {
                    addDirectory(selectedTree, command);
                } else {
                    addFile(selectedTree, command);
                }
            }

        }
        List<Tree> listOfDirectory = new ArrayList<>();
        putSizeToEachTree(subTree, listOfDirectory);

        Integer size = listOfDirectory.stream().filter(customer -> "/".equals(customer.name)).findAny().orElse(null).getSize();//part2
        int space = 70000000 - size;//part 2
        int needSpace = 30000000 - space;//part 2
        // return listOfDirectory.stream().filter(d -> d.size < 100000).mapToInt(d -> d.size).sum();//Part 1
        return listOfDirectory.stream().filter(d -> d.size >= needSpace).min(Comparator.comparingInt(a -> a.size)).orElseThrow().size;//Part 2

    }

    private static void putSizeToEachTree(Tree trees, List<Tree> listOfDirectory) {
        for (Tree tree : trees.children) {
            putSizeToEachTree(tree, listOfDirectory);
        }
        if (trees.isDirectory()) {
            trees.size = trees.children.stream().mapToInt(e -> e.size).sum();
            listOfDirectory.add(trees);
        }
    }

    private static void addFile(Tree selectedDirectory, String command) {
        String[] s = command.split(" ");
        int size = Integer.parseInt(s[0]);
        String name = s[1];
        Tree tree = new Tree();
        tree.name = name;
        tree.size = size;
        selectedDirectory.addChild(tree);
    }

    private static void addDirectory(Tree selectedTree, String command) {
        String[] s = command.split(" ");
        String name = s[1];
        Tree tree = new Tree();
        tree.name = name;
        selectedTree.addChild(tree);
    }
}
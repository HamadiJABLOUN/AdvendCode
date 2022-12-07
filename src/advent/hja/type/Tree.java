package advent.hja.type;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public String name;
    public Integer size = 0;
    public Tree parent;
    public List<Tree> children = new ArrayList<>();

    public void addChild(Tree child) {
        Tree childNode = new Tree();
        childNode.parent = this;
        childNode.name = child.name;
        childNode.size = child.size;
        this.children.add(childNode);
    }

    public Integer getSize() {
        return size;
    }

    public boolean isDirectory() {
        return this.children.size() >= 1;
    }
}

package advent.hja.type;

public class Queue {
    private Coordinate h;
    private Coordinate t;

    public Queue(Coordinate h, Coordinate t) {
        this.h = h;
        this.t = t;
    }

    public void setH(Coordinate h) {
        this.h = h;
    }

    public void setT(Coordinate t) {
        this.t = t;
    }

    public Coordinate getH() {
        return h;
    }

    public Coordinate getT() {
        return t;
    }
}

package advent.hja.type;

public  class Coordinate {
    private  int i;
    private int j;

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setI(int i) {
        this.i = i;
    }
}

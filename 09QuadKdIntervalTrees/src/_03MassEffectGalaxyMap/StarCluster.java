package _03MassEffectGalaxyMap;

public class StarCluster implements Comparable<StarCluster> {

    private int X;
    private int Y;
    private String name;
    private Rectangle bounds;

    public StarCluster(String name, int x, int y) {
        this.name = name;
        this.setX(x);
        this.setY(y);
        this.bounds = new Rectangle(x, y, 0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return this.X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean isInside(Rectangle rectangle) {
        return this.bounds.isInside(rectangle);
    }

    @Override
    public int compareTo(StarCluster that) {
        if (this.X < that.X) return -1;
        if (this.X > that.X) return +1;
        if (this.Y < that.Y) return -1;
        if (this.Y > that.Y) return +1;

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        StarCluster that = (StarCluster) obj;
        return this.X == that.X && this.Y == that.Y;
    }
}

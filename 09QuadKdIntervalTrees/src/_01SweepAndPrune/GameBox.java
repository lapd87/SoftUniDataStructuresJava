package _01SweepAndPrune;

public class GameBox implements Boundable, Comparable<GameBox> {

    public static final int WIDTH = 10;

    public static final int HEIGHT = 10;

    private String name;

    private Rectangle bounds;

    public GameBox(String name, int x, int y) {
        this(name, x, y, WIDTH, HEIGHT);
    }

    public GameBox(String name, int x, int y, int width, int height) {
        this.name = name;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void move(int x, int y) {
        this.bounds = new Rectangle(x, y,
                this.bounds.getWidth(),
                this.bounds.getHeight());
    }

    @Override
    public int compareTo(GameBox o) {
        return this.bounds.getX1() - o.bounds.getX1();
    }
}

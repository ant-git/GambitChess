package game;

/**
 * Created by antant on 23/01/16.
 */
public class Move {
    private int x;
    private int y;
    private int newX;
    private int newY;

    public Move(int x, int y, int newX, int newY) {
        this.x = x;
        this.y = y;
        this.newX = newX;
        this.newY = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    @Override
    public String toString() {
        return "From: " + x + " " + y + " to " + newX + " " + newY;
    }
}

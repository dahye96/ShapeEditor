
public class Point {
    private float x;
    private float y;

    public Point() { }

    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() { return y; }

    public void setY(float y) {
        this.y = y;
    }

    public float getDistance(Point p) {
        float deltaX = p.getX() - x;
        float deltaY = p.getY() - y;

        return (deltaX * deltaX) + (deltaY * deltaY);
    }

    public float getGradient(Point p) {
        return (this.getY() - p.getY()) / (this.getX() - p.getX());
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

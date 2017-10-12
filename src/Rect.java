public class Rect extends Shape {

    Rect(int x, int y) {
        super(x, y);
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }
}

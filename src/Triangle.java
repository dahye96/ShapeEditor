public class Triangle extends Shape {

    Triangle(int x, int y) {
        super(x, y);
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }
}

public class Circle extends Shape {

    Circle(int x, int y) {
        super(x, y);
    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }
}

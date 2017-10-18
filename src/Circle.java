import processing.core.PApplet;

public class Circle extends Shape {

    private int radius = Constant.CIR_RADIUS;

    Circle() {
        super();
    }

    Circle(float posX, float posY) {
        super(posX, posY);
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);
        pApplet.ellipse(super.getPosition().getX(), super.getPosition().getY(), radius*2, radius*2);
    }

    @Override
    public void setChangedInfo() { }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

    @Override
    boolean checkCollision(float x, float y) {
        return this.getPosition().getDistance(new Point(x, y)) < (radius * radius);
    }
}

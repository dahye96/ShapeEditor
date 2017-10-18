import processing.core.PApplet;

public class Triangle extends Shape {

    private Point first_point;
    private Point second_point;
    private Point third_point;

    Triangle() {
        super();
        first_point = new Point();
        second_point = new Point();
        third_point = new Point();
    }

    Triangle(float posX, float posY) {
        super(posX, posY);
        first_point = new Point(posX, posY - (Constant.TRI_SIDE / Constant.TRI_GRADIENT));
        second_point = new Point(posX - (Constant.TRI_SIDE / 2), posY + (Constant.TRI_SIDE * Constant.TRI_GRADIENT /6));
        third_point = new Point(posX + (Constant.TRI_SIDE / 2), posY + (Constant.TRI_SIDE * Constant.TRI_GRADIENT /6));
    }

    public Point getFirst_point() {
        return first_point;
    }

    public void setFirst_point() {
        first_point.setX(this.position.getX());
        first_point.setY(this.position.getY() - (Constant.TRI_SIDE / Constant.TRI_GRADIENT));
    }

    public Point getSecond_point() {
        return second_point;
    }

    public void setSecond_point() {
        second_point.setX(this.position.getX() - (Constant.TRI_SIDE / 2));
        second_point.setY(this.position.getY() + (Constant.TRI_SIDE * Constant.TRI_GRADIENT /6));
    }

    public Point getThird_point() {
        return third_point;
    }

    public void setThird_point() {
        third_point.setX(this.position.getX() + (Constant.TRI_SIDE / 2));
        third_point.setY(this.position.getY() + (Constant.TRI_SIDE * Constant.TRI_GRADIENT /6));
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        pApplet.triangle(first_point.getX(), first_point.getY(), second_point.getX(), second_point.getY(), third_point.getX(), third_point.getY());
    }

    @Override
    public void setChangedInfo() {
        this.setFirst_point();
        this.setSecond_point();
        this.setThird_point();
    }

    @Override
    public Triangle clone() {
        Triangle clone = (Triangle) super.clone();
        clone.setChangedInfo();
        return clone;
    }


    @Override
    boolean checkCollision(float x, float y) {

        Point p = new Point(x, y);
        boolean collided;

        if( second_point.getX() < x && x < third_point.getX() && first_point.getY() < y && y < second_point.getY() ) {
            if(x < first_point.getX()) {
                collided= (second_point.getGradient(p) >= -Constant.TRI_GRADIENT) && (second_point.getGradient(p) <= 0);
            } else if(x == first_point.getX()) {
                collided = true;
            } else {
                collided = (third_point.getGradient(p) <= Constant.TRI_GRADIENT) && (third_point.getGradient(p) >= 0);
            }
        } else {
            collided = false;
        }

        return collided;
    }
}

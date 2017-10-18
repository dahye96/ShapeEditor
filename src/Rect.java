import processing.core.PApplet;

class Rect extends Shape {

    private int width = Constant.RECT_WIDTH;
    private int height = Constant.RECT_HEIGHT;
    private Point start_point;
    private Point end_point;

    Rect() {
        super();
        start_point = new Point();
        end_point = new Point();
    }

    Rect(float posX, float posY) {
        super(posX, posY);
        start_point = new Point(posX-width/2, posY-height/2);
        end_point = new Point(posX+width/2, posY+height/2);
    }

    public Point getStart_point() {
        return start_point;
    }

    public void setStart_point() {
        start_point.setX(this.position.getX()-width/2);
        start_point.setY(this.position.getY()-height/2);
    }

    public Point getEnd_point() {
        return end_point;
    }

    public void setEnd_point() {
        end_point.setX(this.position.getX()+width/2);
        end_point.setY(this.position.getY()+height/2);
    }

    @Override
    public void draw(PApplet pApplet) {
        super.draw(pApplet);

        pApplet.rect(start_point.getX(), start_point.getY(), width, height);
    }

    @Override
    public void setChangedInfo() {
        this.setStart_point();
        this.setEnd_point();
    }

    @Override
    public Rect clone() {
        Rect clone = (Rect) super.clone();
        clone.setChangedInfo();
        return clone;
    }

    @Override
    boolean checkCollision(float x, float y) {
        return x > start_point.getX() && x < end_point.getX() && y > start_point.getY() && y < end_point.getY();
    }

    @Override
    public String toString() {
        return "Rect{" +
                "width=" + width +
                ", height=" + height +
                ", start_point=" + start_point +
                ", end_point=" + end_point +
                ", color=" + color +
                ", position=" + position +
                '}';
    }
}

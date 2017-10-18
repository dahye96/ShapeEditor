import processing.core.PApplet;

abstract class Shape implements Cloneable {
    Color color;
    Point position;

    Shape(){
        color = new Color();
        position = new Point();
    }

    Shape(float x, float y){
        position = new Point(x, y);
        color = new Color();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }


    @Override
    public Shape clone() {
        Shape clone = null;
        try {
            clone = (Shape) super.clone();
            clone.setPosition(new Point(this.getPosition().getX() + 20, this.getPosition().getY() + 20));
            clone.setColor(new Color());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public void draw(PApplet pApplet) {
        color.setColor(pApplet);
        setChangedInfo();
    }

    abstract void setChangedInfo();

    abstract boolean checkCollision(float x, float y);

    @Override
    public String toString() {
        return "Shape{" +
                "color=" + color +
                ", position=" + position +
                '}';
    }
}


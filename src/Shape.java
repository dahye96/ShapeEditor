public class Shape implements Cloneable {
    private int width;
    private int height;

    Shape(int x, int y){
        .x = x;
        this.y = y;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void draw() {

    }

    public boolean checkCollision() {

    }

}

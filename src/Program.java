import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import processing.core.PApplet;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Program extends PApplet{

    private String event = "no";
    private float clickedX;
    private float clickedY;

    private boolean loadMode = false;

    private boolean collided = false;

    List<Shape> shapes = new ArrayList<>();
    Shape selectedShape;
    ShapeTypeAdapter adapter;


    @Override
    public void settings() {
        this.size(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {
        this.noStroke();
        this.background(0);
        this.fill(233, 30, 99);
        this.rect(0,0, 799, Constant.DRAW_FIELD_BORDER);

        setButtons();
    }


    @Override
    public void draw() {
        repaintBackground();

        for(Shape shape : shapes) {
            if(!loadMode) {
                if (shape.checkCollision(mouseX, mouseY)) {
                    this.fill((float) (shape.getColor().getRandomRed() * 1.5), (float) (shape.getColor().getRandomGreen() * 1.5), (float) (shape.getColor().getRandomBlue() * 1.5));
                    this.stroke(255);
                    if (this.mousePressed) {
                        if (!event.equals("copy")) {
                            this.strokeWeight(5);
                            this.cursor(MOVE);
                        }
                        selectedShape = shape;
                    } else {
                        this.strokeWeight(2);
                        this.cursor(HAND);
                    }
                } else {
                    this.fill(shape.getColor().getRandomRed(), shape.getColor().getRandomGreen(), shape.getColor().getRandomBlue());
                    this.cursor(ARROW);
                    this.noStroke();
                }
            } else {
                loadMode = false;
            }
            shape.draw(this);
        }

    }

    @Override
    public void mouseClicked() {
        clickedX = mouseX;
        clickedY = mouseY;

        if ((clickedX > Constant.COPY_BUTTON_START_X && mouseX < Constant.COPY_BUTTON_END_X) &&
                (clickedY > Constant.COPY_BUTTON_START_Y && clickedY < Constant.COPY_BUTTON_END_Y)) {
            event = "copy";
        } else if ((clickedX > Constant.CIR_BUTTON_START_X && clickedX < Constant.CIR_BUTTON_START_X + Constant.BUTTON_WIDTH)
                && (clickedY > Constant.BUTTON_POINT_Y && clickedY < Constant.BUTTON_POINT_Y + Constant.BUTTON_HEIGHT)) {
            event = "cir";
        } else if ((clickedX > Constant.RECT_BUTTON_START_X && clickedX < Constant.RECT_BUTTON_START_X + Constant.BUTTON_WIDTH)
                && (clickedY > Constant.BUTTON_POINT_Y && clickedY < Constant.BUTTON_POINT_Y + Constant.BUTTON_HEIGHT)) {
            event = "rect";
        } else if ((clickedX > Constant.TRI_BUTTON_START_X && clickedX < Constant.TRI_BUTTON_START_X + Constant.BUTTON_WIDTH)
                && (clickedY > Constant.BUTTON_POINT_Y && clickedY < Constant.BUTTON_POINT_Y + Constant.BUTTON_HEIGHT)) {
            event = "tri";
        }

        if (clickedY > Constant.DRAW_FIELD_BORDER) {
            for(Shape shape : shapes) {
                collided = shape.checkCollision(clickedX, clickedY);
                if(collided){
                    break;
                }
            }

            if(!collided) {
                makeShapes();
            }
        }

        if(event.equals("copy") && selectedShape != null) {
            shapes.add(selectedShape.clone());
            selectedShape = null;
        }
    }

    @Override
    public void mouseDragged() {
        if (!event.equals("copy")) {
            if(selectedShape != null) {
                selectedShape.getPosition().setX(this.lerp(selectedShape.getPosition().getX(), mouseX, 0.2f));
                selectedShape.getPosition().setY(this.lerp(selectedShape.getPosition().getY(), mouseY, 0.2f));
            }
        }
    }

    @Override
    public void mouseReleased() {
        if (!event.equals("copy") && selectedShape != null) {
            selectedShape = null;
        }
    }

    @Override
    public void keyPressed() {
        String input = String.valueOf(key).toUpperCase();

        try {
            if (input.equals("S")) {
                saveStatus();
            } else if (input.equals("O")) {
                readStatus();
                loadMode = true;
            } else if (input.equals("R")) {
                removeAll();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setButtons() {
        // copy button
        this.fill(0);
        this.rect(Constant.COPY_BUTTON_START_X, Constant.BUTTON_POINT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT, Constant.BUTTON_RADIUS);
        this.textSize(38);
        this.fill(255);
        this.text("COPY", 76, 93);

        // circle button
        this.fill(0);
        this.rect(Constant.CIR_BUTTON_START_X, Constant.BUTTON_POINT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT, Constant.BUTTON_RADIUS);
        this.fill(255);
        this.textSize(32);
        this.text("circle", 318, 93);

        // square button
        this.fill(0);
        this.rect(Constant.RECT_BUTTON_START_X, Constant.BUTTON_POINT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT, Constant.BUTTON_RADIUS);
        this.fill(255);
        this.text("square", 475, 93);

        // triangle button
        this.fill(0);
        this.rect(620, Constant.BUTTON_POINT_Y, Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT, Constant.BUTTON_RADIUS);
        this.fill(255);
        this.text("triangle", 635, 93);
    }


    // gson 참고 사이트 ) http://www.javacreed.com/gson-typeadapter-example/

    public void saveStatus() throws Exception {
        adapter = new ShapeTypeAdapter();
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Shape.class, adapter)
                .create();
        try (Writer writer = new FileWriter("shape.json")) {
            gson.toJson(shapes, writer);
        }
    }

    public void readStatus() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("Shape.json"));
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Shape.class, adapter)
                .create();

        Type type = new TypeToken<List<Shape>>(){}.getType();
        shapes = gson.fromJson(br, type);
    }

    public void removeAll() {
        shapes.clear();
    }

    public void repaintBackground() {
        setup();
    }


    public void makeShapes() {
        if (event.equals("cir")) {
            Circle circle = new Circle(clickedX, clickedY);
            shapes.add(circle);
        } else if (event.equals("rect")) {
            Rect rect = new Rect(clickedX, clickedY);
            shapes.add(rect);
        } else if (event.equals("tri")) {
            Triangle triangle = new Triangle(clickedX, clickedY);
            shapes.add(triangle);
        }
    }


    public static void main(String[] args) {
        PApplet.main("Program");
    }


}

class ShapeTypeAdapter extends TypeAdapter<Shape> {

    @Override
    public void write(JsonWriter writer, Shape shape) throws IOException {
        writer.beginObject();
        writer.name("type").value(shape.getClass().getName());
        writer.name("posX").value(shape.getPosition().getX());
        writer.name("posY").value(shape.getPosition().getY());
        writer.name("red").value(shape.getColor().getRandomRed());
        writer.name("green").value(shape.getColor().getRandomGreen());
        writer.name("blue").value(shape.getColor().getRandomBlue());
        writer.endObject();
    }

    @Override
    public Shape read(JsonReader reader) throws IOException {
        reader.beginObject();

        Shape shape = null;
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "type":
                    String type = reader.nextString();
                    if (type.equals("Rect"))
                        shape = new Rect();
                    else if (type.equals("Circle"))
                        shape = new Circle();

                    else if (type.equals("Triangle"))
                        shape = new Triangle();
                    break;
                case "posX":
                    if (shape != null)
                        shape.getPosition().setX((float)reader.nextDouble());
                    break;
                case "posY":
                    if (shape != null)
                        shape.getPosition().setY((float)reader.nextDouble());
                    break;
                case "red":
                    if (shape != null)
                        shape.getColor().setRandomRed(reader.nextInt());
                    break;
                case "green":
                    if (shape != null)
                        shape.getColor().setRandomGreen(reader.nextInt());
                    break;
                case "blue":
                    if (shape != null)
                        shape.getColor().setRandomBlue(reader.nextInt());
                    break;
            }
        }

        reader.endObject();
        return shape;
    }
}
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ShapeTypeAdapter extends TypeAdapter<Shape> {

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

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program extends PApplet{

    boolean copyEvent = false;
    boolean cirEvent = false;
    boolean squEvent = false;
    boolean triEvent = false;

    int x;
    int y;

    List<Shape> shapes = new ArrayList<>();

    @Override
    public void setup() {
        this.background(255);
        this.fill(228,247,186);
        this.noStroke();
        this.rect(0,0, 799,150);

        // copy button
        this.fill(255);
        this.stroke(230);
        this.rect(50, 50, 150, 60, 30);
        this.textSize(38);
        this.fill(0);
        this.text("COPY", 76, 93);

        // circle button
        this.fill(255);
        this.stroke(230);
        this.rect(280, 50, 150, 60, 30);
        this.fill(0);
        this.textSize(32);
        this.text("circle", 318, 93);

        // square button
        this.fill(255);
        this.stroke(230);
        this.rect(450, 50, 150, 60, 30);
        this.fill(0);
        this.text("square", 475, 93);

        // triangle button
        this.fill(255);
        this.stroke(230);
        this.rect(620, 50, 150, 60, 30);
        this.fill(0);
        this.text("triangle", 635, 93);
    }

    @Override
    public void settings() {

        this.size(800, 800);


    }

    @Override
    public void draw() {   }

    @Override
    public void mouseClicked() {
        if ((mouseX > 50 && mouseX < 200) && (mouseY > 50 && mouseY < 110)) {
            copyEvent = true;
        } else if ((mouseX > 280 && mouseX < 430) && (mouseY > 50 && mouseY < 110)) {
            cirEvent = true;
        } else if ((mouseX > 450 && mouseX < 600) && (mouseY > 50 && mouseY < 110)) {
            squEvent = true;
        } else if ((mouseX > 620 && mouseX < 770) && (mouseY > 50 && mouseY < 110)) {
            triEvent = true;
        }

        if(copyEvent) {
            textSize(20);
            text("Please press the number", 240, 140);
        }else if(cirEvent){
            x = makeRandNum();
            y = makeRandNum();
            shapes.add(new Circle(x,y));
            this.fill(255,228,0);
            this.ellipse(x, y,50,50);
            cirEvent = false;
        }else if(squEvent){
            x = makeRandNum();
            y = makeRandNum();
            shapes.add(new Rect(x,y));
            this.fill(255,0,0);
            this.rect(x, y,50,50);
            squEvent = false;
        }else if(triEvent){
            x = makeRandNum();
            y = makeRandNum();
            shapes.add(new Triangle(x,y));
            this.fill(0,0,255);
            this.triangle(x,y,x-25,y+50,x+25,y+50);
            triEvent = false;
        }
    }

    @Override
    public void keyPressed() {
        if(copyEvent){
            int copyIdx = ((int)key-48);
            System.out.println(copyIdx);

            Shape shape = shapes.get(copyIdx).clone();
            System.out.println(shape);
            x = shape.getCoordi()[0] + 15;
            y = shape.getCoordi()[1] + 15;

            String first = shape.toString().substring(0,1);
            System.out.println(first);

            shape.setCoordi(x, y);
            shapes.add(shape);

            if(first.equals("C")){
                this.fill(255,228,0);
                this.ellipse(x, y,50,50);
            }else if(first.equals("R")){
                this.fill(255,0,0);
                this.rect(x, y,50,50);
            }else{
                this.fill(0,0,255);
                this.triangle(x,y,x-25,y+50,x+25,y+50);
            }
            copyEvent = false;
        }

    }

    public static void main(String[] args) {
        PApplet.main("Program");
    }

    // 랜덤 좌표를 위한 랜덤 값 생성
    public int makeRandNum(){
        int randnum = (int) (Math.random() * (750 - 150 + 1)) + 150;
        return randnum;
    }

}


import processing.core.PApplet;

public class Color {

    private int randomRed;
    private int randomGreen;
    private int randomBlue;

    Color() {
        randomRed = (int) (Math.random() * 255);
        randomGreen = (int) (Math.random() * 255);
        randomBlue = (int) (Math.random() * 255);
    }

    public void setColor(PApplet pApplet) {
        pApplet.fill(randomRed, randomGreen, randomBlue);
    }

    public int getRandomRed() {
        return randomRed;
    }

    public int getRandomGreen() {
        return randomGreen;
    }

    public int getRandomBlue() {
        return randomBlue;
    }

    public void setRandomRed(int randomRed) {
        this.randomRed = randomRed;
    }

    public void setRandomGreen(int randomGreen) {
        this.randomGreen = randomGreen;
    }

    public void setRandomBlue(int randomBlue) {
        this.randomBlue = randomBlue;
    }
}

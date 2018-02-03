package de.android.ayrathairullin.objects;


import java.util.Random;

public class Web extends Moving{
    private Random r;
    private float groundY;

    public Web(float x, float y, int width, int height, float movSpeed, float groundY) {
        super(x, y, width, height, movSpeed);
        r = new Random();
        this.groundY = groundY;
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = r.nextInt(90) + 15;
    }
}

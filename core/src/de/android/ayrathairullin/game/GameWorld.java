package de.android.ayrathairullin.game;


import de.android.ayrathairullin.objects.Fly;

public class GameWorld {
    private Fly fly;

    private int midPointY;
    private int midPointX;

    public Fly getFly() {
        return fly;
    }

    public GameWorld(int midPointY, int midPointX) {
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        fly = new Fly(33, midPointY - 5, 17, 12);
    }

    public void update(float delta){
        fly.update(delta);
    }
}

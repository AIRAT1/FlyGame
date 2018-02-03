package de.android.ayrathairullin.game;


import de.android.ayrathairullin.objects.Fly;
import de.android.ayrathairullin.objects.MoveHandler;

public class GameWorld {
    private Fly fly;
    private MoveHandler moveHandler;

    private int midPointY;
    private int midPointX;

    public GameWorld(int midPointY, int midPointX) {
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        fly = new Fly(33, midPointY - 5, 17, 12);
        moveHandler = new MoveHandler(this, midPointY + 66);
    }

    public void update(float delta){
        fly.update(delta);
        moveHandler.update(delta);
    }

    public Fly getFly() {
        return fly;
    }

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }
}

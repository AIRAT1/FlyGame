package de.android.ayrathairullin.game;


import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import de.android.ayrathairullin.loader.ResourceLoader;
import de.android.ayrathairullin.objects.Fly;
import de.android.ayrathairullin.objects.MoveHandler;

public class GameWorld {
    private Fly fly;
    private MoveHandler moveHandler;
    private Rectangle ground;

    private int midPointY;
    private int midPointX;

    private GameRender render;

    public GameWorld(int midPointY, int midPointX) {
        this.midPointX = midPointX;
        this.midPointY = midPointY;

        fly = new Fly(33, midPointY - 5, 17, 12);
        moveHandler = new MoveHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta){
        fly.update(delta);
        moveHandler.update(delta);

        if (moveHandler.collides(fly) && fly.isAlive()) {
            moveHandler.stop();
            fly.die();
            fly.cling();
            ResourceLoader.fall.play();
            render.prepareTransition(255, 255, 255, 0.3f);
        }
        if (Intersector.overlaps(fly.getCircle(), ground)) {
            if (fly.isAlive()) {
                ResourceLoader.dead.play();
                fly.die();
                render.prepareTransition(255, 255, 255, 0.3f);
            }
            moveHandler.stop();
            fly.cling();
        }
    }

    public MoveHandler getMoveHandler() {
        return moveHandler;
    }

    public Fly getFly() {
        return fly;
    }

    public void setRender(GameRender render) {
        this.render = render;
    }
}

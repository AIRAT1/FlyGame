package de.android.ayrathairullin.objects;


import de.android.ayrathairullin.game.GameWorld;

public class MoveHandler {
    public static final int MOVE_SPEED = - 60;
    public static final int WEB_GAP = 60;

    private Grass frontGrass, backGrass;
    private Web web1, web2, web3;
    private GameWorld gameWorld;

    public MoveHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        frontGrass = new Grass(0, yPos, 143, 11, MOVE_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, MOVE_SPEED);
        web1 = new Web(210, 0, 22, 60, MOVE_SPEED, yPos);
        web2 = new Web(web1.getTailX() + WEB_GAP, 0, 22, 70, MOVE_SPEED, yPos);
        web3 = new Web(web2.getTailX() + WEB_GAP, 0, 22, 60, MOVE_SPEED, yPos);
    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        web1.update(delta);
        web2.update(delta);
        web3.update(delta);

        if (web1.isScrolledLeft()) {
            web1.reset(web3.getTailX() + WEB_GAP);
        }else if (web2.isScrolledLeft()) {
            web2.reset(web1.getTailX() + WEB_GAP);
        }else if (web3.isScrolledLeft()) {
            web3.reset(web2.getTailX() + WEB_GAP);
        }

        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());
        }else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());
        }
    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Web getWeb1() {
        return web1;
    }

    public Web getWeb2() {
        return web2;
    }

    public Web getWeb3() {
        return web3;
    }
}

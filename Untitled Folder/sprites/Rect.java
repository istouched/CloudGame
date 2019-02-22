package ru.mustplay.coub.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import ru.mustplay.coub.Assets;
import ru.mustplay.coub.CoubGame;
import ru.mustplay.coub.World;


public class Rect {

    public static int velocity = 400;

    //Vector3 тут потому что camera.unproject() работает только с ним
    public Vector3 posRect;

    public Rectangle rect;

    public Rect() {
        posRect = new Vector3(CoubGame.WIDTH / 2 - 50, 250, 0);

        rect = new Rectangle(posRect.x, posRect.y, 100, 100);
    }

    public void handleInput(){
        if (Gdx.input.justTouched()){
            velocity = -velocity;
        }
    }

    public void update(float dt, Bonus b){
        handleInput();

        posRect.add(velocity * dt, 0, 0);

        rect.setPosition(posRect.x, posRect.y);

        collision();
        bonusCollision(b);
    }

    private void collision(){
        if (posRect.y < World.posDeadLine + 10)
            World.gameOver = true;

        if (posRect.x >= 620) posRect.x = 620;
        if (posRect.x <= 0) posRect.x = 0;
    }

    public void bonusCollision(Bonus bonus){
        if (bonus.rect.overlaps(rect)) {
            posRect.add(0, 20, 0);
            World.bonusUp.position.set(-200, 0);
        }
    }

    public void render(SpriteBatch sb){
        sb.draw(Assets.rect, posRect.x, posRect.y);
    }
}

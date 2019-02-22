package ru.mustplay.coub.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ru.mustplay.coub.Assets;

public class Bonus {
    public Rectangle rect;
    public static int velocity = 200;
    public Vector2 position;

    public Bonus(float x, float y){
        position = new Vector2(x, y);
        rect = new Rectangle(x, y, 50, 70);
    }

    public void update(float dt){
        position.add(0, -velocity * dt);
        rect.setPosition(position);
    }

    public void render(SpriteBatch sb){
        sb.draw(Assets.bonusUp, position.x, position.y);
    }

    public void generateBonus(int randPos){
        position.set(70 * randPos + 10, 1480);
    }
}

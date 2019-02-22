package ru.mustplay.coub.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.mustplay.coub.Assets;

public class Diamond {

    private static final int VELOCITY = -450;

    public Vector2 position;

    public Diamond(float x, float y){
        position = new Vector2(x, y);
    }

    public void update(float dt){
        position.add(0, VELOCITY * dt);
    }

    public void render(SpriteBatch sb){
        sb.draw(Assets.diamond, position.x, position.y);
    }
}

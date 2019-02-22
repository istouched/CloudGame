package ru.mustplay.coub.sprites;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ru.mustplay.coub.Assets;
import sun.rmi.runtime.Log;

public class Platform {

    public static short velocity = 200;

    private TextureRegion texture;
    public Vector2 position;
    public Rectangle rect;

    public Platform(int type, float x, float y, float width, float height) {
        position = new Vector2(x, y);
        rect = new Rectangle(x, y, width, height);
        switch (type){
            case 0 : texture = new TextureRegion(Assets.platform);
                break;
            case 1 : texture = new TextureRegion(Assets.dangerPlatform);
                break;
            default : Log.getLog("Something wrong with texture choice!", "Something wrong with texture choice!", false);
        }
    }

    public void update(float dt){
        position.add(0, -velocity * dt);
        rect.setPosition(position);
    }

    public void render(SpriteBatch sb){
        sb.draw(texture, position.x, position.y, rect.width, rect.height);
    }
}

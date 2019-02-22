package ru.mustplay.coub.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.mustplay.coub.Assets;
import ru.mustplay.coub.CoubGame;
import ru.mustplay.coub.World;
import ru.mustplay.coub.sprites.Rect;


public class PlayState extends State {

    private World world;

    public static Rect rect;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, CoubGame.WIDTH, CoubGame.HEIGHT);

        rect = new Rect();
        world = new World();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()){
            if (World.gameOver) {
                world.gameOver();
                gsm.set(new PlayState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        if (!World.gameOver) {
            rect.update(dt, World.bonusUp);
            world.update(dt);
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(Assets.bg, 0, 0, CoubGame.WIDTH, CoubGame.HEIGHT);

        world.render(sb);
        rect.render(sb);

        if (World.gameOver){
            sb.draw(Assets.gameOverMenu, (float) CoubGame.width / 2 - 210, (float) CoubGame.height / 2 - 260);
            World.scoreBatch(sb, World.bestScore, (int) CoubGame.width / 2 - 20, (int) CoubGame.height / 2 + 70);
            World.scoreBatch(sb, World.score, (int) CoubGame.width / 2 - 20, (int) CoubGame.height / 2 - 60);
        }

        sb.end();
    }

    @Override
    public void dispose() {
    }
}

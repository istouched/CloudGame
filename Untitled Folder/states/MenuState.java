package ru.mustplay.coub.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.mustplay.coub.Assets;
import ru.mustplay.coub.CoubGame;
import ru.mustplay.coub.MyFile;
import ru.mustplay.coub.World;

public class MenuState extends State {
    private int timeShowLogo;
    private boolean showLogo = true;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        camera.setToOrtho(false, CoubGame.WIDTH, CoubGame.HEIGHT);
        if (!Gdx.files.local("file.txt").exists()) {
            MyFile.createFile();
            MyFile.save(0);
        } else {
            MyFile.createFile();
        }
        World.bestScore = MyFile.load();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() && !showLogo) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        if (showLogo == true){
            Gdx.gl.glClearColor(0, 0, 0, 1);
            //sb.draw(Assets.logo, 178, 480);
            if (timeShowLogo > 50) showLogo = false;
            timeShowLogo++;
        } else {
            sb.draw(Assets.bg, 0, 0, CoubGame.WIDTH, CoubGame.HEIGHT);
            sb.draw(Assets.best, CoubGame.WIDTH / 2 - Assets.best.getRegionWidth() / 2, 900);
            sb.draw(Assets.startButton, CoubGame.WIDTH / 2 - Assets.startButton.getRegionWidth() / 2, 500);
            World.scoreBatch(sb, World.bestScore, 350, 800);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}

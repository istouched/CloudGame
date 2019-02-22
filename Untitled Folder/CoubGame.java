package ru.mustplay.coub;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.mustplay.coub.states.GameStateManager;
import ru.mustplay.coub.states.MenuState;

public class CoubGame extends ApplicationAdapter {
	public static int WIDTH = 720;
	public static int HEIGHT = 1280;

	public static double k;
	public static double width;
	public static double height;

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.load();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		k = (double) Gdx.graphics.getWidth() / (double) 720;
		gsm.push(new MenuState(gsm));
		width = 720 * CoubGame.k;
		height = 1280 * CoubGame.k;
		Gdx.gl.glViewport(0, 0, (int)width, (int)height);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void pause(){
		World.pause();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}

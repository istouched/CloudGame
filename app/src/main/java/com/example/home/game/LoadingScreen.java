package com.example.home.game;

import com.example.home.game.Graphics.PixmapFormat;

public class LoadingScreen extends Screen{

    public LoadingScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g=game.getGraphics();
        Assets.background=g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.ufo=g.newPixmap("ufo.png", PixmapFormat.ARGB4444);
        Assets.cloud=g.newPixmap("cloud.png", PixmapFormat.ARGB4444);
        Assets.gameOver=g.newPixmap("gameOver.png", PixmapFormat.RGB565);
        Assets.menu=g.newPixmap("menu.png", PixmapFormat.RGB565);
        game.setScreen(new MainMenu(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

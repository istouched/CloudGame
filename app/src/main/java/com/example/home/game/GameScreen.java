package com.example.home.game;

import java.util.List;

import com.example.home.game.Input.TouchEvent;

public class GameScreen extends Screen{

    int ufoX=Assets.background.getWidth()/2-Assets.ufo.getWidth()/2;
    Graphics g=game.getGraphics();
    float turn;
    boolean gameOver=false;

    Overcast overcast=new Overcast();

    public GameScreen(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.ufo, ufoX, 400);
    }

    @Override
    public void present(float deltaTime) {
        List<TouchEvent> touchEvents=game.getInput().getTouchEvents();
        if (gameOver)
            gameOver(touchEvents);
        else
            gameRun(deltaTime);
    }

    public void gameOver(List<TouchEvent> touchEvents){
        g.drawPixmap(Assets.gameOver, 150, 150);
        int len=touchEvents.size();
        for (int i=0; i<len; i++){
            TouchEvent event=touchEvents.get(i);
            if (event.type==TouchEvent.TOUCH_DOWN){
                if (event.x>150 && event.y>150 && event.x<150+Assets.gameOver.getWidth() && event.y<150+Assets.gameOver.getHeight()){
                    game.setScreen(new MainMenu(game));
                }
            }
        }
    }

    public void gameRun(float deltaTime){
        for (int i = 0; i < 5; i++) {
            Cloud cloud = overcast.clouds.get(i);
            g.drawPixmap(Assets.cloud, cloud.x, cloud.y);
            overcast.advance(deltaTime);

            if ((ufoX>=cloud.x && ufoX<=cloud.x+Assets.cloud.getWidth())||(ufoX+Assets.ufo.getWidth()>=cloud.x && ufoX+Assets.ufo.getWidth()<=cloud.x+Assets.cloud.getWidth())) {
                if ((cloud.y+Assets.cloud.getHeight()>=400 && cloud.y+Assets.cloud.getHeight()<=400+Assets.ufo.getHeight()))
                    gameOver = true;
            }
        }
        ufo();
    }

    protected void ufo(){
        turn=game.getInput().getAccelX();
        ufoX-=(int)(turn*1.5);
        if (ufoX<0)
            ufoX=0;
        if (ufoX>320-Assets.ufo.getWidth())
            ufoX=320-Assets.ufo.getWidth();
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

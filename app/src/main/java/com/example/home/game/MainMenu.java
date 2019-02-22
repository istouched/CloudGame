package com.example.home.game;


import java.util.ArrayList;
import java.util.List;

import com.example.home.game.Input.TouchEvent;

public class MainMenu extends Screen{

    Graphics g=game.getGraphics();

    public MainMenu(Game game){
        super(game);
    }

    @Override
    public void update(float deltaTime) {
       g.drawPixmap(Assets.menu,0,0);
    }

    @Override
    public void present(float deltaTime) {
        List<TouchEvent> touchEvents=game.getInput().getTouchEvents();
        int len=touchEvents.size();
        for (int i=0; i<len; i++){
            TouchEvent event=touchEvents.get(i);
            if (event.type==TouchEvent.TOUCH_DOWN){
                if (event.x>50 && event.x<280 && event.y>265 && event.y<365){
                    game.setScreen(new GameScreen(game));
                }
            }
        }
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

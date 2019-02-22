package com.example.home.game;

public class MainActivity extends AndroidGame {

    public Screen getStartScreen(){
        return new LoadingScreen(this);
    }
}

package ru.mustplay.coub;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture items;
    public static TextureRegion bg;
    public static TextureRegion rect;
    public static TextureRegion platform;
    public static TextureRegion dangerPlatform;
    public static TextureRegion startButton;
    public static TextureRegion best;
    public static TextureRegion diamond;
    public static TextureRegion bonusUp;
    public static TextureRegion deadLine;
    public static TextureRegion gameOverMenu;
   // public static TextureRegion logo;
    public static TextureRegion[] number = new TextureRegion[10];

    public static void load(){
        items = new Texture("items.png");
        bg = new TextureRegion(items, 0, 0, 100, 100);
        rect = new TextureRegion(items, 100, 0, 100, 100);
        platform = new TextureRegion(items, 200, 0, 100, 100);
        dangerPlatform = new TextureRegion(items, 300, 0, 100, 100);
        startButton = new TextureRegion(items, 0, 100, 160, 160);
        best = new TextureRegion(items, 160, 100, 140, 50);
        diamond = new TextureRegion(items, 50, 260, 50, 180);
        bonusUp = new TextureRegion(items, 0, 260, 50, 70);
        deadLine = new TextureRegion(items, 300, 0, 100, 100);
        gameOverMenu = new TextureRegion(items, 300, 100, 420, 520);
        //logo = new TextureRegion(items, 0, 1280, 364, 320);
        for (int i = 0; i < 10; i++) {
            number[i] = new TextureRegion(items, 400 + 63*i, 0, 63, 100);
        }
    }
}

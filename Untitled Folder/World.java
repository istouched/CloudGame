package ru.mustplay.coub;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Random;

import ru.mustplay.coub.sprites.Bonus;
import ru.mustplay.coub.sprites.Diamond;
import ru.mustplay.coub.sprites.Platform;
import ru.mustplay.coub.states.PlayState;

public class World {
    public static int score;
    public static int bestScore;
    public static int progress = 4;
    private int theNumberSmall = 14;
    private int theNumberLarge = 12;
    public static float posDeadLine = 0;

    public static long dTime = 2000000000;

    private int prevType = 0, newType = 0;

    private int b = 0, s = 0, m = 0, d = 0;

    public static boolean gameOver;

    private ArrayList<Platform> smallPlatform;
    private ArrayList<Platform> mediumPlatform;
    private ArrayList<Platform> bigPlatform;
    private ArrayList<Platform> dangerPlatform;

    public ArrayList<Diamond> diamonds;
    public static Bonus bonusUp;
    private int countDiamonds = 0;
    private long lastGenDimondTime = 0;
    private short lastPosGenDiam;
    private short nowPosGenDiam;
    private long dtDiamond = 2500000000l;

    private long lastGenTime = 0;

    Random rand;

    public World() {
        gameOver = false;

        smallPlatform = new ArrayList<Platform>();
        mediumPlatform = new ArrayList<Platform>();
        bigPlatform = new ArrayList<Platform>();
        dangerPlatform = new ArrayList<Platform>();

        diamonds = new ArrayList<Diamond>();

        score = -1;
        bestScore = MyFile.load();

        rand = new Random();

        for (int i = 0; i < theNumberSmall; i++){
            Platform platformS = new Platform(0, 0, -200, 120, 100);
            smallPlatform.add(platformS);
            Platform platformM = new Platform(0, 0, -200, 200, 100);
            mediumPlatform.add(platformM);
        }
        for (int i = 0; i < theNumberLarge; i++){
            Platform platformB = new Platform(0, 0, -200, 321, 100);
            bigPlatform.add(platformB);
            Platform platformD = new Platform(1, 0, -200, 120, 100);
            dangerPlatform.add(platformD);
        }

        for (int i = 0; i < 20; i++){
            Diamond diamond = new Diamond(0, -200);
            diamonds.add(diamond);
        }

        bonusUp = new Bonus(-100, 0);
    }

    public void update(float dt){
        for (int i = 0; i < theNumberSmall; i++){
            smallPlatform.get(i).update(dt);
            collision(smallPlatform.get(i));
            mediumPlatform.get(i).update(dt);
            collision(mediumPlatform.get(i));
        }
        for (int i = 0; i < theNumberLarge; i++){
            bigPlatform.get(i).update(dt);
            collision(bigPlatform.get(i));
            dangerPlatform.get(i).update(dt);
            if (dangerPlatform.get(i).rect.overlaps(PlayState.rect.rect))
                gameOver = true;
        }

        for (int i = 0; i < diamonds.size(); i++){
            diamonds.get(i).update(dt);
        }

        if (TimeUtils.nanoTime() - lastGenDimondTime > dtDiamond) {
            generateDimonds();
            if (countDiamonds == 3)
                dtDiamond = 500000000;
        }

        bonusUp.update(dt);

        if(TimeUtils.nanoTime() - lastGenTime > dTime) {
            generatePlatform();
            score++;
        }


        if (score % 11 == 0){
                bonusUp.generateBonus(rand.nextInt(10));
        }

        if (posDeadLine + 10 < CoubGame.width / 2)
            posDeadLine += CoubGame.width / 1000 * dt;
    }

    public void render(SpriteBatch sb){
        for (int i = 0; i < diamonds.size(); i++){
            diamonds.get(i).render(sb);
        }

        for (int i = 0; i < theNumberSmall; i++){
            smallPlatform.get(i).render(sb);
            mediumPlatform.get(i).render(sb);
        }
        for (int i = 0; i < theNumberLarge; i++){
            bigPlatform.get(i).render(sb);
            dangerPlatform.get(i).render(sb);
        }

        bonusUp.render(sb);

        scoreBatch(sb, score, 350, 800);
        sb.draw(Assets.deadLine, 0, posDeadLine, (float) CoubGame.width, 10);
    }

    public static void scoreBatch(SpriteBatch sb, int score, int x, int y){
        if (score < 10 && score >= 0){
            sb.draw(Assets.number[score], x, y);
        } else if (score < 100 && score >= 10){
            sb.draw(Assets.number[score % 10], x, y);
            sb.draw(Assets.number[score / 10], x - 40, y);
        } else if (score < 1000 && score >= 100){
            sb.draw(Assets.number[score % 10], x + 40, y);
            sb.draw(Assets.number[score % 10], x, y);
            sb.draw(Assets.number[score / 10], x - 40, y);
        } else
            sb.draw(Assets.number[0], x, y);
    }

    private void generatePlatform(){
        if (score % 10 == 0 && progress < 12) {
            progress++;
            if (Platform.velocity < 300){
                Platform.velocity += 20;
                dTime -= 180000000;
                Bonus.velocity += 20;
            }
        }
        while (prevType == newType){
            newType = rand.nextInt(progress);
        }
        prevType = newType;
        switch (newType){
            case 0 : bigPlatform.get(b % theNumberLarge).position.set(0, 1280);
                b++;
                break;
            case 1 : bigPlatform.get(b % theNumberLarge).position.set(399, 1280);
                b++;
                break;
            case 2 : mediumPlatform.get(m % theNumberSmall).position.set(520, 1280);
                m++;
                mediumPlatform.get(m % theNumberSmall).position.set(0, 1280);
                m++;
                break;
            case 3 : bigPlatform.get(b % theNumberLarge).position.set(200, 1280);
                b++;
                break;
            case 4 : smallPlatform.get(s % theNumberSmall).position.set(0, 1280);
                s++;
                dangerPlatform.get(d % theNumberLarge).position.set(600, 1280);
                d++;
                break;
            case 5 :  smallPlatform.get(s % theNumberSmall).position.set(0, 1280);
                s++;
                smallPlatform.get(s % theNumberSmall).position.set(300, 1280);
                s++;
                smallPlatform.get(s % theNumberSmall).position.set(600, 1280);
                s++;
                break;
            case 6 : dangerPlatform.get(d % theNumberLarge).position.set(180, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(300, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(420, 1280);
                d++;
                break;
            case 7 : dangerPlatform.get(d % theNumberLarge).position.set(0, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(600, 1280);
                d++;
                break;
            case 8 :  smallPlatform.get(s % theNumberSmall).position.set(0, 1280);
                s++;
                dangerPlatform.get(d % theNumberLarge).position.set(300, 1280);
                d++;
                smallPlatform.get(s % theNumberSmall).position.set(600, 1280);
                s++;
                break;
            case 9 : dangerPlatform.get(d % theNumberLarge).position.set(480, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(600, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(360, 1280);
                d++;
                break;
            case 10 : dangerPlatform.get(d % theNumberLarge).position.set(0, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(120, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(240, 1280);
                d++;
                break;
            case 11 : dangerPlatform.get(d % theNumberLarge).position.set(0, 1280);
                d++;
                dangerPlatform.get(d % theNumberLarge).position.set(600, 1280);
                d++;
                break;
            default: smallPlatform.get(s % theNumberSmall).position.set(0, 1280);
                s++;
                break;
        }

        lastGenTime = TimeUtils.nanoTime();
    }

    public void collision(Platform platform){
        if (platform.position.y > -100 && platform.position.y < CoubGame.width) {
            if (platform.rect.overlaps(PlayState.rect.rect)) {
                if (platform.rect.x > PlayState.rect.rect.x && platform.rect.y < PlayState.rect.rect.y + 95)
                    PlayState.rect.posRect.x = platform.rect.getX() - PlayState.rect.rect.getWidth();
                else if (platform.rect.x + platform.rect.width < PlayState.rect.rect.x + 100 && platform.rect.y < PlayState.rect.rect.y + 95)
                    PlayState.rect.posRect.x = platform.rect.getX() + platform.rect.getWidth();
                else
                    PlayState.rect.posRect.y = platform.position.y - 100;
            }
        }
    }

    private void generateDimonds(){
            while (nowPosGenDiam == lastPosGenDiam)
                nowPosGenDiam = (short) rand.nextInt(14);
            lastPosGenDiam = nowPosGenDiam;
            diamonds.get(countDiamonds % 20).position.set(50 * nowPosGenDiam + 10, 1280);
            countDiamonds++;
        lastGenDimondTime = TimeUtils.nanoTime();
    }

    public void gameOver(){
        Platform.velocity = 200;
        Bonus.velocity = 200;
        dTime = 2000000000;
        progress = 4;
        posDeadLine = 0;
        if (score > bestScore) {
            MyFile.save(score);
            bestScore = score;
        }
    }

    public static void pause(){
        if (score > bestScore) {
            MyFile.save(score);
        }
    }
}

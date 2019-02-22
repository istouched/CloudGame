package com.example.home.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Overcast {
    public List<Cloud> clouds=new ArrayList<Cloud>();

    Random random=new Random();
    float tick=0;

    public Overcast(){
        for(int i=0; i<5; i++) {
            clouds.add(new Cloud(110 *random.nextInt(3) , -150*i));
        }
    }

    public void advance(float deltaTime){
        tick+=50*deltaTime;
        for (int i=0; i<5; i++){
            Cloud cloudThis=clouds.get(i);
            Cloud cloudBefor;
            if (i==0) {
                cloudBefor = clouds.get(clouds.size() - 1);
            }
            else {
                cloudBefor = clouds.get(i - 1);
            }
            cloudThis.y+=(int)tick;
            if (cloudThis.y>520) {
                cloudThis.y = cloudBefor.y-150;
                cloudThis.x=110*random.nextInt(3);
            }
        }
        if (tick>1)
            tick=0;
    }
}

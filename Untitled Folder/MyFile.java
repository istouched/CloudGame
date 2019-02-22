package ru.mustplay.coub;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class MyFile {
    static FileHandle handle;

    public static void createFile(){
        handle = Gdx.files.local("file.txt");
    }

    public static void save(int score){
        String st = Integer.toString(score);
        handle.writeString(st, false);

    }

    public static int load(){

        return Integer.parseInt(handle.readString());
    }
}

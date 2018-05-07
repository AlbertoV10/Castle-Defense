package com.example.alber.castledefense;

import android.content.Context;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.logging.Logger;

/**
 * Created by Adam Massie on 4/16/2018.
 */

public class SaveInformation extends AppCompatActivity implements Serializable
{

    String autoSave="autoSave";
    Tower towers[];
    Hero hero;
    Town town;
    int currentWave;
    int currentMoney;



    SaveInformation(Tower towers[],Hero hero, Town town,int currentWave, int money)
    {
        this.towers=towers;
        this.hero=hero;
        this.town=town;
        this.currentWave=currentWave;
        this.currentMoney=money;

    }

    SaveInformation()
    {

        towers=new Tower[3];
        hero=new Hero();
        town=new Town();
        Load();
    }

    public void Save()
    {
        Save(autoSave);
    }

    public void Save(String fileName)
    {
        try {
            FileOutputStream writingToFile = new FileOutputStream(fileName);//this is needed to initialize
            writingToFile=openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectWriter= new ObjectOutputStream(writingToFile);
            objectWriter.writeObject(this);
            objectWriter.close();
            writingToFile.close();

        }
        catch(FileNotFoundException FNF)
        {
            //error can't save
            Log.d("ERROR","File not found",FNF);
        }
        catch(IOException IO)
        {
            Log.d("ERROR","IO exeption",IO);

        }
    }


    public void Load()
    {
        Load(autoSave);
    }

    public void Load(String fileName)
    {
        try {
            FileInputStream readingFromFile = new FileInputStream(fileName);//need to initialize
            readingFromFile=openFileInput(fileName);
            ObjectInputStream objectReader= new ObjectInputStream(readingFromFile);
            SaveInformation loading=(SaveInformation)objectReader.readObject();
            this.town=loading.town;
            this.hero=loading.hero;
            this.towers=loading.towers;
            this.currentMoney=loading.currentMoney;
            this.currentWave=loading.currentWave;
            objectReader.close();
            readingFromFile.close();

        }
        catch(FileNotFoundException FNF)
        {
            //error can't save
            Log.d("ERROR","File not found",FNF);
        }
        catch(IOException IO)
        {
            Log.d("ERROR","IO exeption",IO);

        }
        catch(ClassNotFoundException CNFE)
        {
            Log.d("ERROR","Class Not Found exeption",CNFE);
        }

    }

    public Tower[] getLoadedTowers()
    {
        return this.towers;
    }

    public Hero getLoadedHero()
    {
        return this.hero;
    }

    public Town getLoadedTown()
    {
        return this.town;
    }

    public int getLoadedMoney(){return this.currentMoney;}

    public int getLoadedWave(){return this.currentWave;}


}

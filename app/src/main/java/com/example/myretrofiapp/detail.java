package com.example.myretrofiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

public class detail extends AppCompatActivity {
    TextView txt_play_With_Computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_play_With_Computer = (TextView) findViewById(R.id.txt_playWithComputerUser);

    }

    public void buClick(View view) {
        Button buSelected=(Button) view;
        int cellId=0;
        switch(buSelected.getId()){
            case R.id.button:
                cellId=1;
                break;
            case R.id.button2:
                cellId=2;
                break;
            case R.id.button3:
                cellId=3;
                break;
            case R.id.button4:
                cellId=4;
                break;
            case R.id.button5:
                cellId=5;
                break;
            case R.id.button6:
                cellId=6;
                break;
            case R.id.button7:
                cellId=7;
                break;
            case R.id.button8:
                cellId=8;
                break;
            case R.id.button9:
                cellId=9;
                break;
        }
        playGame(cellId,buSelected);
    }
    boolean single=true;
    int active_player=1;
    ArrayList<Integer> Player1=new ArrayList<Integer>();
    ArrayList<Integer> Player2=new ArrayList<Integer>();
    void playGame(int cellId,Button button){
        Log.d( "Player:",String.valueOf(cellId));
        if(active_player==1){
            button.setBackgroundResource(R.drawable.x);
            Player1.add(cellId);
            active_player=2;
            if(single)
                AutoPlay();
        }
        else if(active_player==2){
            button.setBackgroundResource(R.drawable.o);
            Player2.add(cellId);
            active_player=1;
        }
        button.setEnabled(false);
        checkWin();
    }

    void checkWin(){
        int winner=0;
        if(Player1.contains(1) && Player1.contains(2) && Player1.contains(3))
            winner=1;
        else if(Player1.contains(4) && Player1.contains(5) && Player1.contains(6))
            winner=1;
        else if(Player1.contains(7) && Player1.contains(8) && Player1.contains(9))
            winner=1;
        else if(Player1.contains(1) && Player1.contains(4) && Player1.contains(7))
            winner=1;
        else if(Player1.contains(2) && Player1.contains(5) && Player1.contains(8))
            winner=1;
        else if(Player1.contains(3) && Player1.contains(6) && Player1.contains(9))
            winner=1;
        else if(Player1.contains(1) && Player1.contains(5) && Player1.contains(9))
            winner=1;
        else if(Player1.contains(3) && Player1.contains(5) && Player1.contains(7))
            winner=1;
        else if(Player2.contains(1) && Player2.contains(2) && Player2.contains(3))
            winner=2;
        else if(Player2.contains(4) && Player2.contains(5) && Player2.contains(6))
            winner=2;
        else if(Player2.contains(7) && Player2.contains(8) && Player2.contains(9))
            winner=2;
        else if(Player2.contains(1) && Player2.contains(4) && Player2.contains(7))
            winner=2;
        else if(Player2.contains(2) && Player2.contains(5) && Player2.contains(8))
            winner=2;
        else if(Player2.contains(3) && Player2.contains(6) && Player2.contains(9))
            winner=2;
        else if(Player2.contains(1) && Player2.contains(5) && Player2.contains(9))
            winner=2;
        else if(Player2.contains(3) && Player2.contains(5) && Player2.contains(7))
            winner=2;


        if(winner!=0){
            if(winner==1){
                Toast.makeText(this,"Player1 is the winner",Toast.LENGTH_LONG).show();
                finish();
            }
            else if(winner==2){
                Toast.makeText(this,"Player2 is the winner",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    void AutoPlay(){

        ArrayList<Integer> EmptyCells= new ArrayList<Integer>();

        for (int cellID=1; cellID<10;cellID++){
            if (!( Player1.contains(cellID) || Player2.contains(cellID))){
                EmptyCells.add(cellID);
            }
        }

        Random r= new Random();
        int  RandIndex=r.nextInt(EmptyCells.size()- 0)+ 0;
        int CellID=EmptyCells.get(RandIndex);

        Button buSelected;
        switch (CellID){

            case 1 :
                buSelected=(Button) findViewById(R.id.button);
                break;

            case 2:
                buSelected=(Button) findViewById(R.id.button2);
                break;

            case 3:
                buSelected=(Button) findViewById(R.id.button3);
                break;

            case 4:
                buSelected=(Button) findViewById(R.id.button4);
                break;

            case 5:
                buSelected=(Button) findViewById(R.id.button5);
                break;

            case 6:
                buSelected=(Button) findViewById(R.id.button6);
                break;

            case 7:
                buSelected=(Button) findViewById(R.id.button7);
                break;

            case 8:
                buSelected=(Button) findViewById(R.id.button8);
                break;

            case 9:
                buSelected=(Button) findViewById(R.id.button9);
                break;
            default:
                buSelected=(Button) findViewById(R.id.button);
                break;

        }
        playGame(CellID, buSelected);
    }
    public void modeClick(View view) {
        single=true;
    }
}

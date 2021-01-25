package com.example.itcompanies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itcompanies.importFiles.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends Activity implements View.OnClickListener {
    Context context;
    AssetManager myAssetManager;
    String LOG = "qwert";

    Button btn_add;
    TextView tv_res;
    private ArrayList<String> listFileOnDevice = new ArrayList<>();

    GridView gvMain;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        myAssetManager = getApplicationContext().getAssets();

        btn_add = findViewById(R.id.btn_add);
        tv_res = findViewById(R.id.tv_res);

        importFiles imFile = new importFiles();
        imFile.listAsset(context, myAssetManager);

        listNameCompany();

        btn_add.setOnClickListener(this);


        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, listFileOnDevice);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG, String.valueOf(position));
                withdrawInfo(listFileOnDevice.get(position));
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_add:
                Intent anIntent = new Intent(this, addCopany.class);
                startActivity(anIntent);
                super.finish();
                break;
        }
    }


    void listNameCompany(){
        File yourDir = new File(context.getFilesDir().getPath());
        for (File f : yourDir.listFiles()) {
            if (f.isFile()) {
                String name = f.getName();
                listFileOnDevice.add(name.substring(0, name.length() - 4));
            }
        }
    }



    void withdrawInfo(String FileName) {

        String filePath, text = "";
        tv_res.setText("");
        filePath = getFilesDir().getPath().toString() + "/" + FileName + ".txt";
        text += " Компания: " + FileName + "\n\n О компании: ";
        try {
            Scanner fIn = new Scanner(
                    new BufferedInputStream(
                            new FileInputStream(filePath)));
            while (fIn.hasNextLine())
                text += fIn.nextLine() + "\n";
            fIn.close();
            tv_res.setText(text);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
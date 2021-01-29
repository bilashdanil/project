package com.example.itcompanies;

import android.app.Activity;
import android.app.FragmentTransaction;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends Activity implements View.OnClickListener {

    private ShowCompany showCompany;
    private AddCompany addCompany;
    private FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showCompany = new ShowCompany();
        addCompany = new AddCompany();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragment_menu, showCompany);
        fTrans.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_add:
                Intent anIntent = new Intent(this, AddCopany.class);
                startActivity(anIntent);
                super.finish();
                break;
        }
    }


}
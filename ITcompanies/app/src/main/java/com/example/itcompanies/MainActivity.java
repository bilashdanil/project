package com.example.itcompanies;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Fragment_ShowCompany showCompany;
    private Fragment_AddCompany addCompany;
    private Fragment_First fragment_first;
    private FragmentTransaction fTrans;
    Button btn_add, btn_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add= findViewById(R.id.btn_add);
        btn_show= findViewById(R.id.btn_show);

        btn_add.setOnClickListener(this);
        btn_show.setOnClickListener(this);

        showCompany = new Fragment_ShowCompany();
        addCompany = new Fragment_AddCompany();
        fragment_first = new Fragment_First();


        fTrans = getFragmentManager().beginTransaction();
        fTrans.replace(R.id.fragment_menu, fragment_first);
        fTrans.commit();
    }


    @Override
    public void onClick(View v) {
        fTrans = getFragmentManager().beginTransaction();

        switch (v.getId()) {

            case R.id.btn_add:
                fTrans.replace(R.id.fragment_menu, addCompany);
                break;

            case R.id.btn_show:
                fTrans.replace(R.id.fragment_menu, showCompany);
                break;
        }

        fTrans.commit();
    }


}
package com.example.itcompanies;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_ShowCompany#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_ShowCompany extends Fragment {
    Context context;
    AssetManager myAssetManager;
    String LOG = "qwert";

    TextView tv_res;
    public ArrayList<String> listFileOnDevice = new ArrayList<>();

    GridView gvMain;
    ArrayAdapter<String> adapter;


    public Fragment_ShowCompany() {
    }

    public static Fragment_ShowCompany newInstance(String param1, String param2) {
        Fragment_ShowCompany fragment = new Fragment_ShowCompany();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_show_company, container, false);
        listFileOnDevice.clear();

        context = getActivity();
        myAssetManager = getActivity().getAssets();
        tv_res = rootView.findViewById(R.id.tv_res);

        ImportFiles imFile = new ImportFiles();
        imFile.listAsset(context, myAssetManager);
        listNameCompany(); // Получение названий компаний
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item, R.id.tvText, listFileOnDevice);

        gvMain = (GridView) rootView.findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                withdrawInfo(listFileOnDevice.get(position));
            }
        });
        return rootView;
    }

    void listNameCompany() {
        File yourDir = new File(getActivity().getFilesDir().getPath());
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
        filePath = getActivity().getFilesDir().getPath().toString() + "/" + FileName + ".txt";
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
            Toast.makeText(getActivity(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
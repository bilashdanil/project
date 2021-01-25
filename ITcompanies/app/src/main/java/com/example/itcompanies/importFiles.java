package com.example.itcompanies;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class importFiles {

    void listAsset(Context context, AssetManager myAssetManager) {
        // Получаем названия файлов которые находятся в папке assets и переносим в память устройства
        String[] Files = new String[0];
        try {
            Files = myAssetManager.list(""); // массив имён файлов
            for (int i = 0; i < Files.length; i++) {
                if (Files[i].equals("images") || Files[i].equals("webkit")) {
                } else {
                    String outFileName = context.getFilesDir().getPath() + "/" + Files[i];
                    InputStream in = context.getAssets().open(Files[i]);
                    OutputStream out = new FileOutputStream(outFileName);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {

                        out.write(buffer, 0, length);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

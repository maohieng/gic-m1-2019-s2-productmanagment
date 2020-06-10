package com.example.app_specific_storage;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class InternalStorageHelper {

    final Context context;

    public InternalStorageHelper(Context context) {
        this.context = context;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Persistent files
    ///////////////////////////////////////////////////////////////////////////

    public File getFile(String fileName) {
        return new File(context.getFilesDir(), fileName);
    }

    public void writeTextFile(String fileName, String content) throws IOException {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            throw e;
        }
    }

    public String readTextFile(String fileName) throws IOException {
        FileInputStream fis = context.openFileInput(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        String content = null;

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            content = stringBuilder.toString();
        }

        return content;
    }

    public List<String> listFiles() {
        return Arrays.asList(context.fileList());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Cache files
    ///////////////////////////////////////////////////////////////////////////

    public File getCacheFile(String fileName) {
        return new File(context.getCacheDir(), fileName);
    }

    public List<String> listCacheFiles() {
        return Arrays.asList(context.getCacheDir().list());
    }

    public File createCacheFile(String fileName) throws IOException {
        return File.createTempFile(fileName, null, context.getCacheDir());
    }

    public boolean deleteCacheFile(String fileName) {
        File cacheFile = getCacheFile(fileName);
        return cacheFile.delete();
    }

    public void clearAllCacheFiles() {
        File cacheDir = context.getCacheDir();
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
}

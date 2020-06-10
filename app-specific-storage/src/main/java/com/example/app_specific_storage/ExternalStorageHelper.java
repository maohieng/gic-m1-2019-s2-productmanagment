package com.example.app_specific_storage;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class ExternalStorageHelper {

    final Context context;

    public ExternalStorageHelper(Context context) {
        this.context = context;
    }

    public boolean isWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public boolean isReadable() {
        return isWritable() ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Persistent files
    ///////////////////////////////////////////////////////////////////////////

    public File getFile(String fileName) {
        return new File(context.getExternalFilesDir(null), fileName);
    }

    public void writeTextFile(String fileName, String content) throws IOException {
        if (!isWritable()) {
            throw new IOException("Unable to write to external storage.");
        }

        File file = getFile(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            throw e;
        }
    }

    @Nullable
    public String readTextFile(String fileName) throws IOException {
        if (!isReadable()) {
            throw new IOException("Unable to read from external storage.");
        }

        File file = getFile(fileName);
        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = new FileInputStream(file);
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
        if (isReadable()) {
            return Arrays.asList(context.getExternalFilesDir(null).list());
        } else {
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Cache files
    ///////////////////////////////////////////////////////////////////////////

    public File getCacheFile(String fileName) {
        return new File(context.getExternalCacheDir(), fileName);
    }

    public List<String> listCacheFiles() {
        if (isReadable()) {
            return Arrays.asList(context.getExternalCacheDir().list());
        } else {
            return null;
        }
    }

    public File createCacheFile(String fileName) throws IOException {
        if (isWritable()) {
            return File.createTempFile(fileName, null, context.getExternalCacheDir());
        } else {
            throw new IOException("Unable to write to external storage.");
        }
    }

    public boolean deleteCacheFile(String fileName) {
        if (isWritable()) {
            File cacheFile = getCacheFile(fileName);
            return cacheFile.delete();
        } else {
            return false;
        }
    }

    public void clearAllCacheFiles() {
        if (isWritable()) {
            File cacheDir = context.getExternalCacheDir();
            File[] files = cacheDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}

package ua.nure.danylenko.task5.Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteClass {

    public static RandomAccessFile rac;

    public WriteClass(String path){
        deleteFile(path);
        try {
            rac = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public RandomAccessFile getFile(){
        return rac;
    }

    private static void deleteFile(String file){
        File name = new File(file);
        if(name.exists()) {
            if(!name.delete()){
                System.err.println("Не удалоось удалить файл!");
            }
        }
    }

    public void readFile(String path) throws IOException {
        rac = new RandomAccessFile(path, "r");
        StringBuilder res = new StringBuilder();
        int b = rac.read();
        // побитово читаем символы и плюсуем их в строку
        while(b != -1){
            res.append((char)b);
            b = rac.read();
        }
        rac.close();
        System.out.println(res.toString());
    }
}


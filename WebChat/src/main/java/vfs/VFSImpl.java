package vfs;

import base.VFS;

import java.io.*;
import java.util.*;

public class VFSImpl implements VFS {
    private String root;

    public VFSImpl(String root) {
        this.root = root;

    }

    @Override
    public boolean isExist(String path) {
        return new File(root + File.separator + path).exists();
    }

    @Override
    public boolean isDirectory(String path) {
        return new File(root + File.separator + path).isDirectory();
    }

    @Override
    public String getAbsolutePath(String file) {
        return new File(root + File.separator + file).getAbsolutePath();
    }

    @Override
    public byte[] getBytes(String file) {
        File newFile = new File(root + File.separator + file);
        byte[] text = new byte[(int) newFile.length()];
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(newFile))) {

            bis.read(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public String getUTF8Text(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(root + File.separator + file)), "UTF8"))) {
            String nextString;
            while ((nextString = br.readLine()) != null) {
                stringBuilder.append(nextString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<String> getIterator(String startDir) {
        return new FileIterator(startDir);
    }

    public List<String> getFilesOrDirectory(String startDir, boolean isDirectory) {
        List<String> list = new LinkedList<>();
        Iterator<String> iter = new FileIterator(startDir);
        while (iter.hasNext()) {
            String str = iter.next();
            File file = new File(str);
            if (isDirectory){
                if (checkType(file)) {
                    list.add(str);
                }
            } else {
                if (!checkType(file)) {
                    list.add(str);
                }
            }

        }
        return list;
    }

    private boolean checkType(File file) {
        return file.isDirectory();
    }

    private class FileIterator implements Iterator<String> {

        private Queue<File> files = new LinkedList<>();

        public FileIterator(String path) {
            files.add(new File(root + File.separator + path));
        }

        @Override
        public boolean hasNext() {
            return !files.isEmpty();
        }

        @Override
        public String next() {
            File file = files.peek();
            if (file.isDirectory()) {
                List<File> fileList = Arrays.asList(file.listFiles());
                if (!fileList.isEmpty()) {
                    files.addAll(fileList);
                }
            }
            return files.poll().getAbsolutePath();
        }
    }
}

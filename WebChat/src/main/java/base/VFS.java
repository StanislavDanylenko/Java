package base;

import java.util.Iterator;
import java.util.List;

public interface VFS {
    boolean isExist(String path);

    boolean isDirectory(String path);

    String getAbsolutePath(String file);

    byte[] getBytes(String files);

    String getUTF8Text(String text);

    Iterator<String> getIterator(String startDir);

    public List<String> getFilesOrDirectory(String startDir, boolean isDirectory);
}

package resources;

import base.ResourceProvider;
import base.VFS;
import sax.ReadXMLFileSAX;
import vfs.VFSImpl;

import java.util.LinkedList;
import java.util.List;

public class ResourceProviderImpl implements ResourceProvider {

    private static List<Object> resources;
    private static List<String> nameResoures;

    private static ResourceProviderImpl instance;

    private ResourceProviderImpl() {
        this.resources = new LinkedList<>();
        nameResoures.add("H2Configuration");
    }

    public static ResourceProviderImpl getInstance() {
        if (instance == null) {
            instance = new ResourceProviderImpl();
        }
        return instance;
    }

    @Override
    public int loadResources(VFS VFSystem) {
        List<String> filesConfig = VFSystem.getFilesOrDirectory("config", false);
        for (String str : filesConfig) {
            resources.add(ReadXMLFileSAX.readXML(str));
        }
        return resources.size();
    }

    public int loadResources() {
        VFSImpl VFSystem = new VFSImpl(".");
        return loadResources(VFSystem);
    }

    public <T> T getResource(Class<T> clazz) {
        for (Object obj : resources) {
            if (obj.getClass() == clazz) {
                return (T) obj;
            }
        }
        return null;
    }
}

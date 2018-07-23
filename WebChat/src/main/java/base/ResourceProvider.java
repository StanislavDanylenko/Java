package base;

public interface ResourceProvider {

    public int loadResources(VFS VFSystem);

    public <T> T getResource(Class<T> clazz);

}

package base;

public interface Context {

    public void add(Class<?> clazz, Object object);

    public <T> T get(Class<T> clazz);
}

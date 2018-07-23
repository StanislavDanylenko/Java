package sessions;

import base.Context;

import java.util.HashMap;
import java.util.Map;

public class WebContext implements Context {

    private Map<Class<?>, Object> context = new HashMap<>();

    public void add(Class<?> clazz, Object object) {
        if (context.containsKey(clazz)) {
            return;
        }
        context.put(clazz, object);
    }

    public <T> T get(Class<T> clazz) {
        return (T) context.get(clazz);
    }
}

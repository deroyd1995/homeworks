package t1.core;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.currentThread;

public enum CucumberContext {
    CUCUMBER_CONTEXT;

    @Getter
    private final Map<String, Object> context = new ConcurrentHashMap<>();

    CucumberContext() {
    }

    public void deleteKey(String key) {
        key = wrapKey(key);
        context.remove(key);
    }

    public <T> void put(String key, T object) {
        key = wrapKey(key);
        context.put(key, object);
    }

    public Object get(String key) {
        key = wrapKey(key);
        Object object;
        try {
            object = context.get(key);
        } catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return object;
    }

    public String getString(String key) {
        key = wrapKey(key);
        Object object;
        try {
            object = context.get(key);
        } catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return object != null ? object.toString() : null;
    }

    public <T> T get(String key, Class<T> userClass) {
        key = wrapKey(key);
        Object object;
        try {
            object = context.get(key);
        } catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return userClass.cast(object);
    }

    private String wrapKey(String key) {
        return currentThread().getId() + ":" + key;
    }

}

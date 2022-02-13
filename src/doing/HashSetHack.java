package doing;

import Super.HackedConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

public class HashSetHack implements HackedConstructor {
    @Override
    public HashSet<?> HashSetter() {
        HashSet<?> hashSet = null;
        Constructor<?> constructor;
        try {
            Class<?> clazz = Class.forName("java.util.HashSet");
            constructor = clazz.getDeclaredConstructor(int.class, float.class, boolean.class);
            constructor.setAccessible(true);
            hashSet = (HashSet<?>) constructor.newInstance(16, 0.75f, true);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return hashSet;
    }
}

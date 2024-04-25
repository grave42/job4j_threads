package ru.job4j.atom;

public final class Cache {
    private static Cache cache;

    public static synchronized Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}

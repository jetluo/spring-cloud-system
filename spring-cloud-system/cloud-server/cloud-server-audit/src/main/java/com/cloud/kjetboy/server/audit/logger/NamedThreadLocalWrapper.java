package com.cloud.kjetboy.server.audit.logger;

import org.springframework.core.NamedThreadLocal;

import java.util.function.Supplier;

/**
 * @author jet
 */
public class NamedThreadLocalWrapper<T> {
    private final NamedThreadLocal<T> namedThreadLocal;

    public NamedThreadLocalWrapper(String name) {
        this.namedThreadLocal = new NamedThreadLocal<>(name);
    }

    public NamedThreadLocalWrapper(String name, final Supplier<T> supplier) {
        this.namedThreadLocal = new NamedThreadLocal<T>(name) {
            @Override
            protected T initialValue() {
                return supplier.get();
            }
        };
    }

    public T get() {
        return namedThreadLocal.get();
    }

    public void set(T value) {
        namedThreadLocal.set(value);
    }

    public void remove() {
        namedThreadLocal.remove();
    }
}

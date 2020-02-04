package ru.otus.appcontainer;

import org.apache.commons.lang3.tuple.Pair;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        try {
            Object instance = configClass.getDeclaredConstructor().newInstance();
            List<Pair<Integer, Method>> list = getMethodsOrdered(configClass.getMethods());
            for (Pair<Integer, Method> pair : list) {
                Method m = pair.getRight();
                Class<?>[] params = m.getParameterTypes();
                Object o;
                if (params.length == 0) {
                    o = m.invoke(instance);
                } else {
                    Object[] paramComponent = Arrays.stream(params).map(this::getAppComponent).toArray();
                    o = m.invoke(instance, paramComponent);
                }
                appComponents.add(o);
                appComponentsByName.put(getComponentName(m), o);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private List<Pair<Integer, Method>> getMethodsOrdered(Method[] methods) {
        return Arrays.stream(methods).filter(method -> method.getAnnotation(AppComponent.class) != null)
            .map(method -> Pair.of(method.getAnnotation(AppComponent.class).order(), method))
            .sorted(Comparator.comparingInt(Pair::getLeft))
            .collect(Collectors.toList());
    }

    private String getComponentName(Method m) {
        AppComponent annotation = m.getAnnotation(AppComponent.class);
        return annotation.name();
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        return (C) appComponents.stream()
            .filter(component -> componentClass.isAssignableFrom(component.getClass()))
            .findAny().orElseThrow(() -> new RuntimeException("Component is not found"));
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}

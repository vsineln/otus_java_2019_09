package ru.otus.appcontainer;

import org.apache.commons.lang3.tuple.Pair;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.exception.ComponentsContainerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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
            List<Method> list = getMethodsOrdered(configClass.getMethods());
            for (Method m : list) {
                Class<?>[] params = m.getParameterTypes();
                Object[] paramComponent = Arrays.stream(params).map(this::getAppComponent).toArray();
                Object o = m.invoke(instance, paramComponent);
                appComponents.add(o);

                String componentName = getComponentName(m);
                if (appComponentsByName.containsKey(componentName)) {
                    throw new ComponentsContainerException("Duplicated component: " + componentName);
                }
                appComponentsByName.put(componentName, o);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ComponentsContainerException(e.getMessage());
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    private List<Method> getMethodsOrdered(Method[] methods) {
        List<Method> methodList = new LinkedList<>();
        Arrays.stream(methods).filter(method -> method.getAnnotation(AppComponent.class) != null)
            .map(method -> Pair.of(method.getAnnotation(AppComponent.class).order(), method))
            .sorted(Comparator.comparingInt(Pair::getLeft))
            .forEach(pair -> methodList.add(pair.getRight()));
        return methodList;
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

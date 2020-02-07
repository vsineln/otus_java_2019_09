package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.exception.ComponentsContainerException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final Map<String, Object> appComponentsByName = new HashMap<>();
    private final Map<Class, Object> appComponentsByInterface = new HashMap<>();

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

                String componentName = getComponentName(m);
                if (appComponentsByName.containsKey(componentName)) {
                    throw new ComponentsContainerException(String.format("Duplicated component: %s", componentName));
                }
                appComponentsByName.put(componentName, o);
                appComponentsByInterface.put(m.getReturnType(), o);
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
        return Arrays.stream(methods).filter(method -> method.isAnnotationPresent(AppComponent.class))
            .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
            .collect(Collectors.toList());
    }

    private String getComponentName(Method m) {
        AppComponent annotation = m.getAnnotation(AppComponent.class);
        return annotation.name();
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if(!appComponentsByInterface.containsKey(componentClass)){
            throw new ComponentsContainerException(String.format("Component is not found: %s", componentClass.getSimpleName()));
        }
        return (C) appComponentsByInterface.get(componentClass);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if(!appComponentsByName.containsKey(componentName)){
            throw new ComponentsContainerException(String.format("Component is not found: %s", componentName));
        }
        return (C) appComponentsByName.get(componentName);
    }
}

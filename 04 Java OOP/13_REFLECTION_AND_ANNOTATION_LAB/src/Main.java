import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Class<Reflection> clazz = Reflection.class;

        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));

         Method[] declaredMethods = clazz.getDeclaredMethods();

         Arrays.stream(declaredMethods)
                .filter(m -> m.getReturnType() != void.class)
                .filter(method -> !Modifier.isPublic(method.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> System.out.println(method.getName() + " have to be public!"));


        Arrays.stream(declaredMethods)
                .filter(m -> m.getReturnType() == void.class)
                .filter(method -> !Modifier.isPrivate(method.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(method -> System.out.println(method.getName() + " have to be private!"));


        List<Anotation> annotated = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getAnnotation(Anotation.class) != null)
                .map(method -> method.getAnnotation(Anotation.class))
                .collect(Collectors.toList());

        annotated.forEach(a -> System.out.println(a.name())); // Връщане на анотации
    }
}

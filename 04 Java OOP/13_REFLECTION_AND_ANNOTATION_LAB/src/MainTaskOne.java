import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MainTaskOne {
    public static void main(String[] args) throws NoSuchMethodException {


        Class<Reflection> clazz = Reflection.class;

        System.out.println(clazz.getSimpleName()); // print type
        System.out.println(clazz.getSuperclass().getSimpleName()); // print type of base class

        try {
            Arrays.stream(clazz.getInterfaces())
                    .map(Class::getSimpleName)
                    .forEach(System.out::println); // print all interfaces

            Constructor<Reflection> constructor = clazz.getDeclaredConstructor();
            Reflection reflection = constructor.newInstance(); // new instance // •	Instantiate object

            System.out.println(reflection);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException exception){
            System.out.println(exception.getMessage());
        }
    }

}

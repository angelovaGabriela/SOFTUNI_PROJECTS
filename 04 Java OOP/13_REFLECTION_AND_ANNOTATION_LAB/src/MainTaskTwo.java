import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class MainTaskTwo {
    private static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method f, Method s) {
            boolean firstIsGetter = f.getName().startsWith("get");
            boolean secondIsGetter = s.getName().startsWith("get");

            if (firstIsGetter && secondIsGetter){
                return f.getName().compareTo(s.getName());
            }

            boolean firstIsSetter = f.getName().startsWith("set");
            boolean secondIsSetter = s.getName().startsWith("set");

            if (firstIsSetter && secondIsSetter){
                return f.getName().compareTo(s.getName());
            }

            return Boolean.compare(secondIsGetter, firstIsGetter);

        }
    }
    public static void main(String[] args) {
        Class<Reflection> clazz = Reflection.class; // get the Reflection class

        Method[] allMethods = clazz.getDeclaredMethods(); // get all the methods

        Arrays.stream(allMethods)
                .filter(method -> !method.getName().equals("toString"))
                .sorted(new MethodComparator())
                .forEach(MainTaskTwo :: printMethodInfo);


    }

    private static void printMethodInfo(Method method) {
   String output = method.getName().startsWith("get")
           ? String.format("%s will return class %s", method.getName(), method.getReturnType().getSimpleName())
           : String.format("%s and will set field of class %s",
           method.getName(), method.getParameterTypes()[0].getSimpleName());
        System.out.println(output);

    }
}

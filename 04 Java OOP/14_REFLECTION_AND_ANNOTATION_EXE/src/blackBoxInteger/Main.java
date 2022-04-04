package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
         Class<BlackBoxInt> clazz = BlackBoxInt.class; // Get meta info about BlackBoxInt.class

        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor(); // get empty constructor
        constructor.setAccessible(true); // access allowed
        BlackBoxInt blackBoxInt = constructor.newInstance(); // new instance

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Field innerValue = clazz.getDeclaredField("innerValue"); // get meta regarding innerValue
        innerValue.setAccessible(true); // access allowed

        while (!input.equals("END")){
            String[] methodData = input.split("_");
            String methodName = methodData[0];
            int value = Integer.parseInt(methodData[1]);

            Method method = clazz.getDeclaredMethod(methodName , int.class); // get method by it's signature (name, list of args)
            method.setAccessible(true);
            method.invoke(blackBoxInt, value);

            System.out.println(innerValue.get(blackBoxInt)); // get the innerValue of blackBoxInt (the new instance we've created)
            input = scanner.nextLine();
        }
    }
}

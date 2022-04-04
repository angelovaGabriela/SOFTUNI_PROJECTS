package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] declaredFields = clazz.getDeclaredFields();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("HARVEST")){

            switch (input){
                case "public":
                    Arrays.stream(declaredFields)
                            .filter(field -> Modifier.isPublic(field.getModifiers()))
                            .forEach(field -> {
                                printField(field); // name of the field
                            });
                    break;
                case "private":
                    Arrays.stream(declaredFields)
                            .filter(field -> Modifier.isPrivate(field.getModifiers()))
                            .forEach(field -> {
                                printField(field); // name of the field
                            });
                    break;
                case "protected":
                    Arrays.stream(declaredFields)
                            .filter(field -> Modifier.isProtected(field.getModifiers()))
                            .forEach(field -> {
                                printField(field); // name of the field
                            });
                    break;
                case "all":
                    Arrays.stream(declaredFields)
                            .forEach(field -> {
                                printField(field); // name of the field
                            });

                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static void printField(Field field) {
         System.out.printf("%s %s %s%n",
                Modifier.toString(field.getModifiers()), // modifier
                field.getType().getSimpleName() // type of field (String/int....)
                , field.getName());
    }
}

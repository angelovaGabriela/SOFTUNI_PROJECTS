import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        Invalid
        Person p = new Person(null, "Last Name", 13);
        Person m = new Person("First Name", null, 13);
        Person l = new Person("     ", "Last Name", 13);
        Person person = new Person("First Name", "    ", 13);
        Person pi = new Person("First Name", "Last Name", -1);
        Person po = new Person("First Name", "Last Name", 121);
        */

        Person pl = new Person("First Name", "Last Name", 13);

        //Valid
        System.out.println(pl);
    }
}

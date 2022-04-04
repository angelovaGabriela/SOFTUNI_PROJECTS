import java.util.Scanner;

public class Exercise8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int length = Integer.parseInt(scanner.nextLine());
       int wide = Integer.parseInt(scanner.nextLine());
       int height = Integer.parseInt(scanner.nextLine());
       double percent = Double.parseDouble(scanner.nextLine());

       int volume = length * wide * height;
       double maxL = volume * 0.001;
       percent = percent * 0.01;
       double neededL = maxL * (1-percent);


        System.out.printf("%.2f", neededL);


        //изчисляваме обема на аквариума = дължина * широчината * височината
        //общо литри които ще събере = обема * 0.001
        //процент = процент * 0.01
        //литри, които реално ще трябват = общо литри, които ще събере * (1- percent)


    }
}

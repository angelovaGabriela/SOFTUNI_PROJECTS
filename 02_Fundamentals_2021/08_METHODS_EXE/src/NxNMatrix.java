import java.util.Scanner;

public class NxNMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        matrixNxN(n);
    }

    private static void matrixNxN(int n) {
int rows = n;
int columns = n;
        for (columns = 1; columns <= n; columns++) {
            for (rows= 1; rows < n ; rows++) {
                System.out.print(n + " ");
            }
            System.out.println(n);
        }
    }
}

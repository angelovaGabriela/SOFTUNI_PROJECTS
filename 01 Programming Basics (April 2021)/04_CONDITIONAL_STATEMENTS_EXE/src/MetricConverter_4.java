import java.util.Scanner;

public class MetricConverter_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1m = 1000 mm
        //1m = 100 cm
    double number = Double.parseDouble(scanner.nextLine());
    String inPutMetric = scanner.nextLine();
    String outPutMetric = scanner.nextLine();

    if (inPutMetric.equals("mm")) {
    number = number / 1000;
    } else if (inPutMetric.equals("cm")) {
        number = number / 100;
    }
    if (outPutMetric.equals("mm")){
        number = number * 1000;
    } else if (outPutMetric.equals("cm")){
        number = number * 100;

    }
        System.out.printf("%.3f", number);
}
}

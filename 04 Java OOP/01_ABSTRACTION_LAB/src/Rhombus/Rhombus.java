package Rhombus;

public class Rhombus {
final int n; // final защото никъде няма да го променям

public Rhombus (int n ){
    this.n = n;
}
    public String getFigure() {
       return printTop() +
        printMiddle()+
        printBottom();
    }

    private String printTop(){
        StringBuilder out = new StringBuilder();
        for (int r = 1; r < n; r++) {
            out.append(repeatString(n - r, " "))
                    .append(repeatString(r, "* "))
                    .append(System.lineSeparator());

        }
        return out.toString();
    }
    private   String  printBottom(){
    StringBuilder out = new StringBuilder();
        for (int r = 1; r < n; r++) {
            out.append(repeatString(r, " "))
            .append(repeatString(n - r, "* "))
            .append(System.lineSeparator());

        }
        return out.toString();
    }

    private String printMiddle (){
       return repeatString(n, "* ") + System.lineSeparator();

    }

    private   String repeatString(int count, String str){
        StringBuilder out = new StringBuilder();
    for (int i = 0; i < count; i++) {
            out.append(str);
        }
    return out.toString();

    }
}

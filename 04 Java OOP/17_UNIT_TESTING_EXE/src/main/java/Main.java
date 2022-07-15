import p01_Database.Database;

import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        Database database = new Database(1, 2, 3);

        database.add(5);
        database.remove();
        database.getElements();


    }
}

package jediGalaxy.jediGalaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field) {
        this.field = field;
    }
    public void moveEvil(int row, int col){
        while (row >= 0 && col >= 0) { // докато не излезем от матрицата
            if ( this.field.isInBounds(row, col)) { //инаваме през диагонала
            this.field.setValue(row, col, 0);
            }
           row--;
           col--; //зануляваме някакви полета
        }


    }
    public long moveJedi(int row, int col){
       long starsCollected = 0;

        while (row >= 0 && col < this.field.getColLength(1)) {
            if (this.field.isInBounds(row, col)) { // вижим се по диагонала на дясно
                starsCollected += this.field.getValue(row,col);
            }

            col++;
            row--;
        }

       return starsCollected;
    }
}

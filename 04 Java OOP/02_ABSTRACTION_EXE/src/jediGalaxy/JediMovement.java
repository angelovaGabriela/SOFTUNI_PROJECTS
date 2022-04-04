package jediGalaxy;

import jediGalaxy.jediGalaxy.Field;

public class JediMovement {
    public long move(int row, int col, Field field){
        long starsCollected = 0;

        while (row >= 0 && col < field.getColLength(1)) {
            if (field.isInBounds(row, col)) { // вижим се по диагонала на дясно
                starsCollected += field.getValue(row,col);
            }

            col++;
            row--;
        }

        return starsCollected;
    }
}

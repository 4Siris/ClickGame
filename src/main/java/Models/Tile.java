package Models;

import lombok.Getter;
import lombok.Setter;

public class Tile {
    @Getter
    @Setter
    private int amount;

    public Tile(){
        amount = (int) (Math.random()*7+3);
    }
    public Tile(int amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "" + amount;
    }
}

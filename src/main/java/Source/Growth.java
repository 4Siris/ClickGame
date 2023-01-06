package Source;

import lombok.Getter;
import lombok.Setter;

public class Growth {
    @Getter
    @Setter
    private int amount;

    public Growth(){
        amount = 1;
    }
    public Growth(int amount) {
        this.amount = amount;
    }
}

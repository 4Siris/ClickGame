package Models;

import lombok.Getter;
import lombok.Setter;

public class Card {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;

    public Card(){
        setName("None");
        setDescription("Nothing");
    }

    public Card(String name,String description){
        this.name = name;
        this.description = description;
    }


}

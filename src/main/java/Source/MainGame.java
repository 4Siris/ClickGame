package Source;

import Models.Card;
import Models.Tile;

import java.util.Scanner;

public class MainGame extends Thread{
    private int score;
    private Tile[][]map;
    private Growth growth;
    private final Scanner sin = new Scanner(System.in);


    public MainGame(int x,int y){
        if (x<1||x>15) throw new NumberFormatException();
        if (y<1||y>15) throw new NumberFormatException();
        score = 0;
        growth = new Growth();
        generateMap(x,y);
    }
    private void generateMap(int x,int y){
        map = new Tile[x][y];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile();
            }
        }
    }

    public void run() {
        int count = 3;
        String choice;
        do {
            showMap();
            System.out.println("Start(y/n)?");
            choice = sin.next();
            if ("y".equals(choice))break;
            if ("n".equals(choice))generateMap(map.length,map[0].length);
        }while (true);
        do {
            showMap();
            System.out.println(score);
            chooseTile();
            nextTick();
            if(hasZero())break;
            count--;
            if(count==0){
                chooseUpgrade();
                count=3;
            }
        }while (!isInterrupted());
        System.out.println("<====Thanks for playing!====>");
        System.out.println(score);
    }

    private void chooseTile(){
        int x,y;
        do {
            System.out.println("Choose tile");
            if(sin.hasNextInt()) {
                x = sin.nextInt();
                if (x>0&&x<=map[0].length) {
                    if (sin.hasNextInt()) {
                        y = sin.nextInt();
                        if(y>0&&y<=map.length) break;
                    }else {
                        sin.nextLine();
                    }
                }else {
                    sin.nextLine();
                }
            }
        }while (true);
        growTile(x-1,y-1);
    }
    private void growTile(int x,int y){
        map[y][x].setAmount(map[y][x].getAmount()+growth.getAmount());
    }

    private void chooseUpgrade(){
        Card[] cards = new Card[3];
        for(int i=0;i<cards.length;i++){
            cards[i] = new Card();
        }
        int choice;
        do {
            System.out.println("Choose card");
            for (int i = 0; i < cards.length; i++) {
                System.out.println(i + 1 + "." + cards[i].getName());
                System.out.println(cards[i].getDescription());
            }
            if(sin.hasNextInt()){
                choice = sin.nextInt();
                if(choice<1||choice> cards.length){
                    sin.nextLine();
                }else {
                    break;
                }
            }
        }while (true);
        upgrade(cards[choice-1]);
    }
    private void upgrade(Card card){
        String[] words = card.getDescription().split(" ");
        for(int i=0;i<words.length;i++){
            if("growth".equals(words[i])){
                String amount = words[i-2];
                char smb = amount.charAt(0);
                if ('+'==smb)growth.setAmount(growth.getAmount()+Integer.parseInt(amount.substring(1)));
                else growth.setAmount(growth.getAmount()-Integer.parseInt(amount.substring(1)));
            }
        }
    }

    private void nextTick(){
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                score+=tile.getAmount();
                tile.setAmount(tile.getAmount() - 1);
            }
        }
    }

    private boolean hasZero(){
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                if (tile.getAmount() == 0) return true;
            }
        }
        return false;
    }

    private void showMap(){
        System.out.print(" |");
        for(int i=0;i<map[0].length;i++){
            System.out.print(i+1);
            System.out.print("|");
        }
        System.out.println();
        for (int i=0;i<map.length;i++) {
            for(int j=0;j<(map[0].length+1)*2;j++){
                System.out.print("-");
            }
            System.out.println();
            System.out.print(i+1+"|");
            for (int j=0;j<map[i].length;j++) {
                System.out.print(map[i][j].toString());
                System.out.print("|");
            }
            System.out.println();
        }
        for(int i=0;i<(map[0].length+1)*2;i++){
            System.out.print("-");
        }
        System.out.println();
    }
}

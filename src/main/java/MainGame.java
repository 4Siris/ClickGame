import Models.Tile;

import java.util.Scanner;

public class MainGame extends Thread{
    private int score;
    private Tile[][]map;
    private Scanner sin = new Scanner(System.in);


    public MainGame(int x,int y){
        if (x<1||x>15) throw new NumberFormatException();
        if (y<1||y>15) throw new NumberFormatException();
        score = 0;
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
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nextTick();
            if(hasZero())interrupt();
        }while (!isInterrupted());
        System.out.println("<====Thanks for playing!====>");
        System.out.println(score);
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
        System.out.print("*");
        for(int i=0;i<map[0].length*2-1;i++){
            System.out.print("-");
        }
        System.out.println("*");
        for (Tile[] tiles : map) {
            System.out.print("|");
            for (int i=0;i<tiles.length;i++) {
                System.out.print(tiles[i].toString());
                if(i!=tiles.length-1) System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.print("*");
        for(int i=0;i<map[0].length*2-1;i++){
            System.out.print("-");
        }
        System.out.println("*");
    }
}

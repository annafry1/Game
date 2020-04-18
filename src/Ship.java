import java.util.Random;

public class Ship {
    int size;
    boolean vertical = false;
    public static String image = "\uD83D\uDE80";
    private int row;
    private int col;
    private boolean sunk = false;
    private boolean printed = false;
    private String name;

    public Ship(int size, String name) {
        this.size = size;
        Random random = new Random();
        this.vertical = random.nextBoolean();
        this.name = name;
    }

    public void shipLoc(Board board){
        Random random = new Random();
        do {
            if (vertical) {
                row = random.nextInt(10 - this.size);
                col = random.nextInt(10);
            } else {
                row = random.nextInt(10);
                col = random.nextInt(10 - this.size);
            }
        } while (!board.isValidLocation(row,col,this));

        if (!vertical){
            for (int i = 0; i < this.size; i ++){
            board.grid[row][col+i] = Ship.image;
            }
        }else{
            for (int i = 0; i < this.size; i ++){
                board.grid[row+i][col] = Ship.image;
            }
        }
    }

    public void printHumMessage(Board board){
        if(!printed) System.out.println("You sunk a " + name + "!");
        this.printed = true;
    }

    public void printComMessage(Board board){
        if(!printed) System.out.println("Your " + name + " was sunk!");
        this.printed = true;
    }

    public boolean isSunk(Board board){
        if(this.sunk){
            return true;
        }

        int count = 0;
        if (vertical){
            for (int i = 0; i < this.size; i ++){
                if (board.grid[row+i][col].equals(Player.hit) ){
                    count ++;
                }
            }
        }else {
            for (int i = 0; i < this.size; i ++){
                if (board.grid[row][col+i].equals(Player.hit) ){
                    count ++;
                }
            }
        }

        if (count==this.size) {
            this.sunk = true;
            return true;
        } else {
            return false;
        }
    }


}

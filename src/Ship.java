import java.util.Random;
public class Ship {
    int size;
    String [] length = new String[size];
    boolean vertical = false;
    public static String image = "\uD83D\uDE80";


    public Ship(int size) {
        this.size = size;
        Random random = new Random();
        this.vertical = random.nextBoolean();
    }

    public String [] makeShip (){
        for(int i = 0; i < this.size; i++) {
            length[i] = Ship.image;
        }
        return length;
    }

    public void shipLoc(Board board){
        Random random = new Random();
        int row;
        int col;

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
                board.grid[row+i][col] = Ship.image ;
            }
        }
    }

}

import java.util.Random;
public class Ship {
    int size;
    String [] length = new String[size];
    int [][] position = new int[size][2];

    public Ship(int size) {
        this.size = size;
    }

    public String [] makeShip (){
        for(int i = 0; i < this.size; i++) {
            length[i] = "\uD83D\uDE80";
        }
        return length;
    }

    public void shipLoc(){
        Random random = new Random();
        int vertical = random.nextInt(2);
        int row = random.nextInt(10);
        int col = random.nextInt(10);
        if (vertical == 0){
            for (int i = 0; i < this.size; i ++){

            }
        }
    }

}

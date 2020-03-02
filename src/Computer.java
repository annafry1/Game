public class Computer extends Player{

    public Computer(String token) {
        super("Bit Bucket", token);
    }

    //very basic random move algorithm for now
    public int[] determineMove() {

        int row = (int )(Math.random() * 10);
        int col = (int )(Math.random() * 10);

        int[] move =  {row,col};

        return move;

    }
}

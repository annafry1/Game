enum Move{
    RANDOM, FINDDIRECTION, SINKING
        }

public class GameLogic {
    private boolean foundShip = false;
    private boolean sinkingShip = false;
    private boolean vertical = false;
    private int row=0,col=0;
    int testposition = 0;


    public void reset () {
        foundShip = false;
        sinkingShip = false;
        vertical = false;
        row=0;
        col=0;
        //0 = W, 1 = N, 2 = E, 3 = S
    }

    public int getTestposition(){
        return testposition;
    }

    public void find(int r, int c) {
        foundShip = true;
        row = r;
        col = c;
        testposition = 0;
    }

    public void sink(boolean v) {
        foundShip = true;
        sinkingShip = true;
        vertical = v;
        testposition = 0;
    }

    public void setRowCol(int r, int c) {
        row = r;
        col = c;
    }

    public int[] getNorth(){
        int [] move = new int[2];
        move[0]=row-1;
        move[1]=col;
        return move;
    }

    public int[] getEast(){
        int [] move = new int[2];
        move[0]=row;
        move[1]=col+1;
        return move;
    }

    public int[] getSouth(){
        int [] move = new int[2];
        move[0]=row+1;
        move[1]=col;
        return move;
    }

    public int[] getWest(){
        int [] move = new int[2];
        move[0]=row;
        move[1]=col-1;
        return move;
    }

    public void incrementTestPosition(){
        testposition++;
    }

    public int[] getNextMove(Board board){
        if(vertical){
            row ++;
        }else {
            col++;
        }
        int [] move = new int[2];
        move[0]=row;
        move[1]=col;
        return move;
    }

    Move whatAmIdoing() {
        if (!foundShip) {
            System.out.println("NEXT MOVE IS RANDOM");
            return Move.RANDOM;
        }  else if (sinkingShip) {
            System.out.println("NEXT MOVE IS SINKING");
            return Move.SINKING;
        } else {
            System.out.println("NEXT MOVE IS FINDING");
            return Move.FINDDIRECTION;
        }

    }
}

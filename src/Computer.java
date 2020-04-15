public class Computer extends Player{

    public Computer() {
        super("Bit Bucket");
    }
    private GameLogic logic = new GameLogic();

    //very basic random move algorithm for now
   public void resetlogic(){
       logic.reset();
   }

    public int[] determineMove(Board board) {
        int row,col;
        int[] move = new int [2];

        if (logic.whatAmIdoing()==Move.RANDOM) {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
            if (board.isHit(row,col, Ship.image)){
                logic.find(row,col);
                System.out.println("I GOT A HIT");
            }else{
                System.out.println("I MISSED");
            }

            move[0] = row;
            move[1] = col;
            System.out.println("Computer is making a RANDOM move at["+row+"]["+col+"]");
        } else if (logic.whatAmIdoing()== Move.FINDDIRECTION) {
            switch (logic.getTestposition()){
                case 0:
                    move = logic.getWest();
                    logic.incrementTestPosition();
                    if(board.isHit(move[0],move[1],Ship.image)){
                        logic.sink(move[0],move[1],false);
                    }
                    System.out.println("Computer is making a WEST move at["+move[0]+"]["+move[1]+"]");
                    break;
                case 1:
                    move = logic.getNorth();
                    logic.incrementTestPosition();
                    if(board.isHit(move[0],move[1],Ship.image)){
                        logic.sink(move[0],move[1],true);
                    }
                    System.out.println("Computer is making a NORTH move at["+move[0]+"]["+move[1]+"]");
                    break;
                case 2:
                    move = logic.getEast();
                    logic.incrementTestPosition();
                    if(board.isHit(move[0],move[1],Ship.image)){
                        logic.sink(move[0],move[1],false);
                    }
                    System.out.println("Computer is making an EAST move at["+move[0]+"]["+move[1]+"]");
                    break;
                default:
                    move = logic.getSouth();
                    logic.incrementTestPosition();
                    if(board.isHit(move[0],move[1],Ship.image)){
                        logic.sink(move[0],move[1],true);
                    }
                    System.out.println("Computer is making a SOUTH move at["+move[0]+"]["+move[1]+"]");
            }

        } else  {
            move = logic.getNextMove();
            if (board.isHit(move[0],move[1],Ship.image)){
                logic.sink(move[0],move[1],true);
                System.out.println("Computer is SINKING a ship at["+move[0]+"]["+move[1]+"]");
            } else {
                logic.reset();
                System.out.println("Computer is RESETTING at["+move[0]+"]["+move[1]+"]");

            }
        }

        return move;

    }
}

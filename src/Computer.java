public class Computer extends Player{

    public Computer() {
        super("Bit Bucket");
    }
    private GameLogic logic = new GameLogic();

    //very basic random move algorithm for now
   public void resetlogic(){
       logic.reset();
   }



   public int[] determineMove2(Board board) {
       int row,col;
       int[] move = new int [2];

       for (int i=0; i < 10; i++)
           for (int j=0; j < 10; j++) {
               move[0]=i;
               move[1]=j;
               if (board.isHit(i,j,Player.hit)) {
                   if (board.isValidMove(i-1,j)) {
                       move[0]=i-1;
                       System.out.println("Computer is making a NORTH move at["+move[0]+"]["+move[1]+"]");
                       return move;
                   }
                   if (board.isValidMove(i+1,j)) {
                       move [0]=i+1;
                       System.out.println("Computer is making a SOUTH move at["+move[0]+"]["+move[1]+"]");
                       return move;
                   }
                   if (board.isValidMove(i,j-1)) {
                       move [1]=j-1;
                       System.out.println("Computer is making a WEST move at["+move[0]+"]["+move[1]+"]");
                       return move;
                   }
                   if (board.isValidMove(i,j+1)){
                       move[1]=j+1;
                       System.out.println("Computer is making a EAST move at["+move[0]+"]["+move[1]+"]");
                       return move;
                   }
               }
           }

       do {
           row = (int) (Math.random() * 10);
           col = (int) (Math.random() * 10);
       } while (board.isValidMove(row,col));
       if (board.isHit(row,col, Ship.image)){
           System.out.println("I GOT A HIT");
       }else{
           System.out.println("I MISSED");
       }
       move[0] = row;
       move[1] = col;
       System.out.println("Computer is making a RANDOM move at["+row+"]["+col+"]");
       return move;
   }
    public int[] determineMove(Board board) {
        int row,col;
        int[] move = new int [2];

        if (logic.whatAmIdoing()==Move.RANDOM) {
            do {
                row = (int) (Math.random() * 10);
                col = (int) (Math.random() * 10);
            } while (board.isValidMove(row,col));
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
            do {
                switch (logic.getTestposition()) {
                    case 0:
                        move = logic.getWest();
                        logic.incrementTestPosition();
                        if (board.isHit(move[0], move[1], Ship.image)) {
                            logic.sink(false);
                            logic.setRowCol(move[0],move[1]);
                        }
                        System.out.println("Computer is making a WEST move at[" + move[0] + "][" + move[1] + "]");
                        break;
                    case 1:
                        move = logic.getNorth();
                        logic.incrementTestPosition();
                        if (board.isHit(move[0], move[1], Ship.image)) {
                            logic.sink(true);
                            logic.setRowCol(move[0],move[1]);
                        }
                        System.out.println("Computer is making a NORTH move at[" + move[0] + "][" + move[1] + "]");
                        break;
                    case 2:
                        move = logic.getEast();
                        logic.incrementTestPosition();
                        if (board.isHit(move[0], move[1], Ship.image)) {
                            logic.sink(false);
                            logic.setRowCol(move[0],move[1]);
                        }
                        System.out.println("Computer is making an EAST move at[" + move[0] + "][" + move[1] + "]");
                        break;
                    case 3:
                        move = logic.getSouth();
                        logic.incrementTestPosition();
                        if (board.isHit(move[0], move[1], Ship.image)) {
                            logic.sink(true);
                            logic.setRowCol(move[0],move[1]);
                        }
                        System.out.println("Computer is making a SOUTH move at[" + move[0] + "][" + move[1] + "]");
                    default:
                        move[0] = (int) (Math.random() * 10);
                        move[1] = (int) (Math.random() * 10);
                        logic.incrementTestPosition();
                        logic.reset();
                        System.out.println("Computer is making a RANDOM move at[" + move[0] + "][" + move[1] + "]");
                }
            } while(!board.isValidMove(move[0],move[1]));

        } else  {
            move = logic.getNextMove(board);
            if (board.isValidMove(move[0],move[1]) && board.isHit(move[0],move[1],Ship.image)){
                logic.setRowCol(move[0],move[1]);
                System.out.println("Computer is SINKING a ship at["+move[0]+"]["+move[1]+"]");
            } else {
                logic.reset();
                move[0] = (int) (Math.random() * 10);
                move[1] = (int) (Math.random() * 10);
                System.out.println("Computer is RESETTING at["+move[0]+"]["+move[1]+"]");

            }
        }

        return move;

    }
}

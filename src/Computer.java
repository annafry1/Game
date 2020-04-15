public class Computer extends Player{

    public Computer() {
        super("Bit Bucket");
    }


    //very basic random move algorithm for now

   public int[] determineMove(Board board) {
       int row,col;
       int[] move = new int [2];


       // Follow down
       for (int i=1; i < 10; i++)
           for (int j=0; j <10; j++) {
               if (board.isHit(i-1,j,Player.hit) &&
                       board.isValidMove(i,j)) {
                   move[0]=i;
                   move[1]=j;
                   System.out.println("Computer is making a DOWN move at["+move[0]+"]["+move[1]+"]");
                   return move;
               }
           }

       // Follow across
       for (int i=0; i < 10; i++)
           for (int j=1; j <10; j++) {
               if (board.isHit(i,j-1,Player.hit) &&
                       board.isValidMove(i,j)) {
                   move[0]=i;
                   move[1]=j;
                   System.out.println("Computer is making a ACROSS move at["+move[0]+"]["+move[1]+"]");
                   return move;
               }
           }

       // Brute force
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

       //Random
       do {
           row = (int) (Math.random() * 10);
           col = (int) (Math.random() * 10);
       } while (!board.isValidMove(row,col));

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

}

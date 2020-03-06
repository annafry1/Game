import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        //make instances
        Human h = new Human("Alby");
        Computer c = new Computer();
        Scanner sc =  new Scanner(System.in);

        c.display();
        h.display();

        //game loop - alternate moves
        while(!h.checkWin() && !c.checkWin()) {

            //set up vars
            boolean legalRow = false;
            boolean legalCol = false;
            int moveRow = -1;
            int moveCol = -1;

            //human move: validate input and move
            while(!legalRow || !legalCol) {

                //col move
                System.out.println("Enter col to move:");
                //hasNextInt will return true if user input is an int
                if(sc.hasNextInt()) {
                    moveCol += sc.nextInt();
                    legalCol = true;
                }
                else {
                    System.out.println("Enter a legal col!");
                    sc.next();  //very important, resets the input from the "bad" one
                }

                //row move
                System.out.println("Enter row to move:");
                //hasNextInt will return true if user input is an int
                if(sc.hasNextInt()) {
                    moveRow += sc.nextInt();
                    legalRow = true;
                }
                else {
                    System.out.println("Enter a legal row!");
                    sc.next();  //very important, resets the input from the "bad" one
                }



                if(legalRow && legalCol) {
                    c.b.makeMove(moveRow,moveCol);
                }
            }

            //computer move
            int[] cMove = c.determineMove();
            h.b.makeMove(cMove[0],cMove[1]);

            //all done moving, display and repeat
            c.display();
            h.display();

        }

        System.out.print("Game Over!");

    }

}
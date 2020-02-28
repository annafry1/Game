import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        //make instances
        Human h = new Human("Alby", "X");
        Computer c = new Computer("Z");
        Scanner sc =  new Scanner(System.in);

        //game loop - alternate moves
        while(!h.checkWin() && !c.checkWin()) {

            //set up vars
            boolean legalRow = false;
            boolean legalCol = false;
            int moveRow = -1;
            int moveCol = -1;

            //human move: validate input and move
            while(!legalRow || !legalCol) {

                //row move
                System.out.println("Enter row to move:");
                //hasNextInt will return true if user input is an int
                if(sc.hasNextInt()) {
                    moveRow = sc.nextInt();
                    legalRow = true;
                }
                else {
                    System.out.println("Enter a legal row!");
                    sc.next();  //very important, resets the input from the "bad" one
                }

                //col move
                System.out.println("Enter col to move:");
                //hasNextInt will return true if user input is an int
                if(sc.hasNextInt()) {
                    moveCol = sc.nextInt();
                    legalCol = true;
                }
                else {
                    System.out.println("Enter a legal col!");
                    sc.next();  //very important, resets the input from the "bad" one
                }

                if(legalRow && legalCol) {
                    h.b.makeMove(moveRow,moveCol,h.token);
                }
            }

            //computer move
            int[] cMove = c.determineMove();
            c.b.makeMove(cMove[0],cMove[1],c.token);

            //all done moving, display and repeat
            h.display();
            c.display();

        }

        System.out.print("Game Over!");

    }

}
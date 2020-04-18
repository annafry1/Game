import java.util.Scanner;

public class Runner {

    public static Boolean VERBOSE=false;
    public static Boolean TEST_ALGORITHMS=false;

    // This checks how efficient each algorithm is - set TEST_ALGORITHMS to true to run
    public static void testingAlgorithms() {
        System.out.print("Testing Random Parity Seek:"); algorithmTester(3);
        System.out.print("            Testing Random:"); algorithmTester(1);
        System.out.print("       Testing Brute Force:"); algorithmTester(2);
    }

    public static void algorithmTester(int algorithm) {
        System.out.print(" algo["+algorithm+"]");
        int numTimes=1000;
        int sum=0;
        int max=0;
        int min=1000;

        for (int i=0; i < numTimes; i++) {
            if (i%10==0)
                System.out.print(".");
            Human h = new Human("testBoard");
            Computer c = new Computer();
            int moveCount=0;
            while (!h.checkWin()) {
                moveCount++;
                int[] cMove;
                switch(algorithm) {
                    case 1:
                        cMove = c.randomMove(h.b);
                        break;
                    case 2:
                        cMove = c.bruteForce(h.b);
                        break;
                    default:
                        cMove = c.determineMove(h.b);
                }
                h.b.makeMove(cMove[0], cMove[1]);
            }
            //if (i%10==0) System.out.print("("+moveCount+")");
            if (moveCount > max)
                max = moveCount;
            if (moveCount < min)
                min = moveCount;
            sum=sum+moveCount;
            //h.display();
        }
        System.out.println(":avg moves="+sum/numTimes+" min="+min+" max="+max);
    }

    public static void main(String[] args) {

        if (TEST_ALGORITHMS)
            testingAlgorithms();
        //make instances
        Human h = new Human("Your");
        Computer c = new Computer();
        Scanner sc =  new Scanner(System.in);


        if (VERBOSE){
            h.displayTop();
            c.display();
        }
        c.displayTop();
        h.display();
        if(VERBOSE) System.out.println("Entering the main game loop");
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
                System.out.println("Enter column to move:");
                //hasNextInt will return true if user input is an int
                if (sc.hasNextInt()) {
                    int cc = sc.nextInt();
                    if (cc < 11 && cc > 0) {
                        moveCol += cc;
                        legalCol = true;
                    } else {
                        System.out.println("Enter a legal column!");
                        sc.next();  //very important, resets the input from the "bad" one
                    }
                } else {
                    System.out.println("Enter a legal column!");
                    sc.next();  //very important, resets the input from the "bad" one
                }


                //row move
                System.out.println("Enter row to move:");
                //hasNextInt will return true if user input is an int
                if (sc.hasNextInt()) {
                    int rr = sc.nextInt();
                    if (rr > 0 && rr < 11) {
                        moveRow += rr;
                        legalRow = true;
                    } else {
                        System.out.println("Enter a legal row!");
                        sc.next();  //very important, resets the input from the "bad" one
                    }

                } else {
                    System.out.println("Enter a legal row!");
                    sc.next();  //very important, resets the input from the "bad" one
                    legalRow = false;
                }

                //System.out.println("      ->>>>Player is moving");
                //Fix this and see why it doesn't work
                if (legalRow && legalCol) {
                    if (!c.b.grid[moveRow][moveCol].equals(Player.miss) && !c.b.grid[moveRow][moveCol].equals(Player.hit)) {
                        c.b.makeMove(moveRow, moveCol);
                    } else {
                        legalCol = false;
                        legalRow = false;
                        System.out.println("You have already shot this location. ");
                        moveCol = -1;
                        moveRow = -1;
                    }
                }
            }

            //System.out.println("      ->>>>Computer is moving");
            //computer move
            int[] cMove = c.determineMove(h.b);
            h.b.makeMove(cMove[0],cMove[1]);

            // Print sunk message one time for the human player
            if (c.carrier.isSunk(c.b)) c.carrier.printHumMessage(c.b);
            if (c.batlshp.isSunk(c.b) ) c.batlshp.printHumMessage(c.b);
            if (c.crusr.isSunk(c.b)) c.crusr.printHumMessage(c.b);
            if (c.sub.isSunk(c.b)) c.sub.printHumMessage(c.b);
            if (c.dest.isSunk(c.b)) c.dest.printHumMessage(c.b);

            // Print sunk message one time for the computer
            if (h.carrier.isSunk(h.b)) h.carrier.printComMessage(h.b);
            if (h.batlshp.isSunk(h.b)) h.batlshp.printComMessage(h.b);
            if (h.crusr.isSunk(h.b)) h.crusr.printComMessage(h.b);
            if (h.sub.isSunk(h.b)) h.sub.printComMessage(h.b);
            if (h.dest.isSunk(h.b)) h.dest.printComMessage(h.b);
            //all done moving, display and repeat

            // h.displayTop();
            // c.display();
            // Just display the top of the computer board and show the human board
            c.displayTop();
            h.display();



        }

        System.out.print("Game Over!");

    }

}


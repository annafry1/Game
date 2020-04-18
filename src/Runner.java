import java.util.Scanner;

public class Runner {

    public static Boolean VERBOSE=false;
    public static Boolean TEST_ALGORITHMS=false;

    public static void testingAlgorithms() {
        System.out.print("Testing Random Parity Seek:"); algorithmTester(3);
        System.out.print("            Testing Random:"); algorithmTester(1);

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
        }
        System.out.println(":avg moves="+sum/numTimes+" min="+min+" max="+max);
    }

    public static void main(String[] args) {

        if (TEST_ALGORITHMS)
            testingAlgorithms();
        //make instances
        Human h = new Human("Alby");
        Computer c = new Computer();
        Scanner sc =  new Scanner(System.in);

        //h.displayTop();
        c.display();
 //       c.displayTop();
        h.display();
        System.out.println("Entering the main game loop");
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

            if (c.carrier.isSunk(c.b)){
                System.out.println("You sunk a carrier!");
                c.carrier.sunk = true;
            }else if (c.batlshp.isSunk(c.b)){
                System.out.println("You sunk a battleship!");
                c.batlshp.sunk = true;
            }else if (c.crusr.isSunk(c.b)){
                System.out.println("You sunk a cruiser!");
                c.crusr.sunk = true;
            }else if (c.sub.isSunk(c.b)){
                System.out.println("You sunk a submarine!");
                c.sub.sunk = true;
            }else if (c.dest.isSunk(c.b)){
                System.out.println("You sunk a destroyer!");
                c.dest.sunk = true;
            }else if (h.carrier.isSunk(h.b)){
                System.out.println("Your carrier has been sunk!");
                h.carrier.sunk = true;
            }else if (h.batlshp.isSunk(h.b)){
                System.out.println("Your battleship has been sunk!");
                h.batlshp.sunk = true;
            }else if (h.crusr.isSunk(h.b)){
                System.out.println("Your cruiser has been sunk!");
                h.crusr.sunk = true;
            }else if (h.sub.isSunk(h.b)){
                System.out.println("Your submarine has been sunk!");
                h.sub.sunk = true;
            }else if (h.dest.isSunk(h.b)){
                System.out.println("Your destroyer has been sunk!");
                h.dest.sunk = true;
            }
            //all done moving, display and repeat
//            h.displayTop();
//            c.display();
            c.displayTop();
            h.display();



        }

        System.out.print("Game Over!");

    }

}


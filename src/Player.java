public class Player {

    public Board b = new Board();
    protected String name;
    public Ship carrier = new Ship(5, "carrier");
    protected Ship batlshp = new Ship(4, "battleship");
    protected Ship crusr = new Ship(3, "cruiser");
    protected Ship sub = new Ship(3, "submarine");
    protected Ship dest = new Ship(2, "destroyer");
    protected static String hit = "\uD83D\uDCA5";
    protected static String miss = "⚪";



    public Player(String name) {
        this.name = name;
        carrier.shipLoc(b);
        batlshp.shipLoc(b);
        crusr.shipLoc(b);
        sub.shipLoc(b);
        dest.shipLoc(b);
    }

    //display board with nice text intro
    public void display() {
        System.out.println(name + " board:");
        b.printBoard();
        System.out.println(" ");

    }

    public void displayTop(){
        System.out.println(name + "'s board:");
        b.printTop();
        System.out.println(" ");
    }

    public boolean checkSink(){
        return (carrier.isSunk(b));
    }


    //win check routine with basic "algorithm" for now
    public boolean checkWin() {
        if (Runner.VERBOSE)
            System.out.println(name+" carrier["+carrier.isSunk(b)+"]"+
                " battleship["+batlshp.isSunk(b)+"]"+
                " cruiser["+crusr.isSunk(b)+"]"+
                " sub["+sub.isSunk(b)+"]"+
                " dest["+dest.isSunk(b)+"]");
        return carrier.isSunk(b) && batlshp.isSunk(b) && crusr.isSunk(b) && sub.isSunk(b) && dest.isSunk(b);

    }


}

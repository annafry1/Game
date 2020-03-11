public class Player {

    public Board b = new Board();
    protected String name;
    public Ship carrier = new Ship(5);
    protected Ship batlshp = new Ship(4);
    protected Ship crusr = new Ship(3);
    protected Ship sub = new Ship(3);
    protected Ship dest = new Ship(2);
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
        System.out.println(name + "'s board:");
        b.printBoard();
        System.out.println(" ");

    }

    public void displayTop(){

        b.printTop();
        System.out.println(" ");
    }

    public boolean checkSink(){
        return (carrier.isSunk(b));
    }


    //win check routine with basic "algorithm" for now
    public boolean checkWin() {

        if(carrier.isSunk(b) && batlshp.isSunk(b) && crusr.isSunk(b) && sub.isSunk(b) && dest.isSunk(b)) {
            return true;
        }

        return false;

    }


}

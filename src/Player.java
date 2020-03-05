public class Player {

    protected Board b = new Board();
    protected String name;
    protected String token;
    protected Ship carrier = new Ship(5);
    protected Ship batlshp = new Ship(4);
    protected Ship crusr = new Ship(3);
    protected Ship sub = new Ship(3);
    protected Ship dest = new Ship(2);
    protected static String hit = "\uD83D\uDCA5";
    protected int hitNum = 0;
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
    }


    //win check routine with basic "algorithm" for now
    public boolean checkWin() {

        if(b.grid[0][0] == "0") {
            return true;
        }

        return false;

    }


}

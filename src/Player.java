public class Player {

    protected Board b = new Board();
    protected String name;
    protected String token;
    protected Ship carrier = new Ship(5);
    protected Ship batlshp = new Ship(4);
    protected Ship crusr = new Ship(3);
    protected Ship sub = new Ship(3);
    protected Ship dest = new Ship(2);



    public Player(String name, String token) {
        this.name = name;
        this.token = token;

    }

    //display board with nice text intro
    public void display() {
        System.out.println(name + "'s board:");
        b.printBoard();
    }

    //win check routine with basic "algorithm" for now
    //any move at 0,0 ends game
    public boolean checkWin() {

        if(b.grid[0][0] != "\uD83C\uDF20") {
            return true;
        }

        return false;

    }


}

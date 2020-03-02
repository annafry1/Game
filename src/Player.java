public class Player {

    protected Board b = new Board();
    protected String name;
    protected String token;
    protected String [] pieces = new String[5];



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

        if(b.grid[0][0] != "0") {
            return true;
        }

        return false;

    }
}

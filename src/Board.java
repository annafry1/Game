public class Board {
    int size = 10;
    String[][] grid = new String[size][size];
    String[][] top = new String[size][size];
    public static String emptyBoard = "\uD83C\uDF20";

    //constructor initializes the whole
    // grid with 0's when created
    public Board() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                grid[row][col] = emptyBoard;

            }
        }
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                top[row][col] = emptyBoard;

            }
        }
    }

    //printBoard will output a board to the console

    public boolean isHit(int row, int col, String square){
        if (row < 0 || row > 10 || col < 0 || col > 10 ) return false;
        return (this.grid[row][col]).equals(square);
    }

    public void printTop() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                System.out.print(top[row][col]);

            }
            System.out.print("\n");
        }
    }


    public void printBoard() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                System.out.print(grid[row][col]);

            }
            System.out.print("\n");
        }
    }

    public boolean isValidLocation(int row, int col, Ship ship) {
        if (ship.vertical) {
            for (int i = 0; i < ship.size; i++) {
                if (grid[row + i][col].equals(Ship.image) ) return false;
            }
            return true;
        } else {
            for (int i = 0; i < ship.size; i++) {
                if (grid[row][col + i].equals(Ship.image) ) return false;
            }
            return true;
        }
    }

        //makes a move on the board - changes a position to "token value"
    public void makeMove(int row, int col) {
        if (grid[row][col].equals(Ship.image) ){
            grid[row][col] = Player.hit;
            top[row][col] = Player.hit;
        }else {
            grid[row][col] = Player.miss;
            top[row][col] = Player.miss;
        }
    }


    public boolean isValidMove(int row, int col){
        return (row > 0 && row < 11 && col > 0 && col < 11 &&
                !(grid[row][col]).equals(Player.miss) &&
                !(grid[row][col]).equals(Player.hit));
    }
}


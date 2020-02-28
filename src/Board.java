public class Board {
    int size = 10;
    String [][] grid = new String [size][size];


    //constructor initializes the whole
    // grid with 0's when created
    public Board() {

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {

                grid[row][col] = "0";

            }
        }

    }

    //printBoard will output a board to the console
    public void printBoard() {

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {

                System.out.print(grid[row][col] + " ");

            }
            System.out.print("\n");
        }


    }

    //makes a move on the board - changes a position to "token value"
    public void makeMove(int row, int col, String token) {
        grid[row][col] = token;
    }
}

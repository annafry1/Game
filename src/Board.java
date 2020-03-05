public class Board {
    int size = 10;
    String[][] grid = new String[size][size];
    public static String emptyBoard = "\uD83C\uDF20";

    //constructor initializes the whole
    // grid with 0's when created
    public Board() {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                grid[row][col] = emptyBoard;

            }
        }

    }

    //printBoard will output a board to the console
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
                if (grid[row + i][col] == Ship.image) return false;
            }
            return true;
        } else {
            for (int i = 0; i < ship.size; i++) {
                if (grid[row][col + i] == Ship.image) return false;
            }
            return true;
        }
    }

        //makes a move on the board - changes a position to "token value"
    public void makeMove(int row, int col, String token) {
        grid[row][col] = token;
        }
    }


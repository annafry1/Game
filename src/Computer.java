public class Computer extends Player {

    public Computer() {
        super("Opponent");
    }


    // Use parity search to hit every other square randomly
    // Once you get a hit follow it to the right or down
    // Otherwise search all the squares around a hit


    // makes a random move
    public int[] randomMove(Board board) {
        int row, col;
        do {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        } while (!board.isValidMove(row, col));
        return new int[]{row, col};
    }

    // makes random move skipping every other square
    public int[] randomMoveWithParity(Board board) {
        int[] move = new int[2];
        do {
            move = randomMove(board);
        } while ((move[0] * 9 + move[1]) % 2 == 0);
        return move;
    }

    public int[] bruteForce(Board board){
        int[] move = new int[2];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (board.isValidMove(i, j)) {
                    move[0] = i;
                    move[1] = j;
                    if (Runner.VERBOSE)
                        System.out.println("Computer is making a move at[" + move[0] + "][" + move[1] + "]");
                    return move;
                }
            }
        return move;
    }

    // makes move down or across the board
    public int[] moveDownOrAcross(Board board) {
        int[] move = new int[]{-1, -1};
        // Follow down
        for (int i = 1; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (board.isHit(i - 1, j, Player.hit) &&
                        board.isValidMove(i, j)) {
                    move[0] = i;
                    move[1] = j;
                    if (Runner.VERBOSE)
                        System.out.println("Computer is making a DOWN move at[" + move[0] + "][" + move[1] + "]");
                    return move;
                }
            }

        // Follow across
        for (int i = 0; i < 10; i++)
            for (int j = 1; j < 10; j++) {
                if (board.isHit(i, j - 1, Player.hit) &&
                        board.isValidMove(i, j)) {
                    move[0] = i;
                    move[1] = j;
                    if (Runner.VERBOSE)
                        System.out.println("Computer is making a ACROSS move at[" + move[0] + "][" + move[1] + "]");
                    return move;
                }
            }
        return move;
    }

    // checks all the squares aroudn a hit
    public int[] checkAllSquares(Board board) {
        int[] move = new int[]{-1, -1};

        // Brute force
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                move[0] = i;
                move[1] = j;
                if (board.isHit(i, j, Player.hit)) {
                    if (board.isValidMove(i - 1, j)) {
                        move[0] = i - 1;
                        if (Runner.VERBOSE)
                            System.out.println("Computer is making a NORTH move at[" + move[0] + "][" + move[1] + "]");
                        return move;
                    }
                    if (board.isValidMove(i + 1, j)) {
                        move[0] = i + 1;
                        if (Runner.VERBOSE)
                            System.out.println("Computer is making a SOUTH move at[" + move[0] + "][" + move[1] + "]");
                        return move;
                    }
                    if (board.isValidMove(i, j - 1)) {
                        move[1] = j - 1;
                        if (Runner.VERBOSE)
                            System.out.println("Computer is making a WEST move at[" + move[0] + "][" + move[1] + "]");
                        return move;
                    }
                    if (board.isValidMove(i, j + 1)) {
                        move[1] = j + 1;
                        if (Runner.VERBOSE)
                            System.out.println("Computer is making a EAST move at[" + move[0] + "][" + move[1] + "]");
                        return move;
                    }
                }
            }
        return new int[]{-1,-1};
    }
    public int[] determineMove(Board board) {
        int row, col;
        int[] move = new int[2];

        move = moveDownOrAcross(board);
        if (board.isValidMove(move)) return move;

        move = checkAllSquares(board);
        if (board.isValidMove(move)) return move;

        move = randomMoveWithParity(board);
        if (Runner.VERBOSE) {
            if (board.isHit(move[0], move[1], Ship.image)) {
                System.out.println("I GOT A HIT");
            } else {
                System.out.println("I MISSED");
            }
            System.out.println("Computer is making a RANDOM move at[" + move[0] + "][" + move[1] + "]");
        }
        return move;
    }

}

import java.util.Scanner;

class TicTacToe {
    char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    void dispBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    boolean placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (board[row][col] == ' ') {
                board[row][col] = mark;
                return true;
            } else {
                System.out.println("This position is already occupied. Please try entering another position.");
                return false;
            }
        } else {
            System.out.println("Invalid position. Row and column must be between 1 and 3.");
            return false;
        }
    }

    boolean checkWin() {
        return (checkRowWin() || checkColWin() || checkDiagWin());
    }

    boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    boolean checkDiagWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

public class LaunchGame {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        char currentPlayerMark = 'X';
        boolean gameEnded = false;

        while (!gameEnded) {
            t.dispBoard();

            System.out.println("Player " + currentPlayerMark + ", it's your turn.");
            int row, col;
            boolean moveMadeSuccessfully = false;

            while (!moveMadeSuccessfully) {
                System.out.print("Enter row (1-3): ");
                row = scanner.nextInt() - 1;

                System.out.print("Enter column (1-3): ");
                col = scanner.nextInt() - 1;

                moveMadeSuccessfully = t.placeMark(row, col, currentPlayerMark);
            }

            if (t.checkWin()) {
                t.dispBoard();
                System.out.println("\nPlayer " + currentPlayerMark + " wins! Congratulations!");
                gameEnded = true;
            } else if (t.isBoardFull()) {
                t.dispBoard();
                System.out.println("\nIt's a draw! The board is full.");
                gameEnded = true;
            } else {
                currentPlayerMark = (currentPlayerMark == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
        System.out.println("\nGame Over!");
    }
}

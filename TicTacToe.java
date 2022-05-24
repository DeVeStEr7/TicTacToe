import java.util.Scanner;

public class TicTacToe {

    static int[][] board = new int[3][3];
    static Scanner input = new Scanner(System.in);
    static final int ROW = 0, COL = 1;
    static boolean player1Turn = true;

    public static void main(String[] args) {
        int spaces = 0;
        while((!player1Winner()) && !checkIfFull(board)){
            printBoard();
            getUserInput();
            spaces++;
        }
        printBoard();
        if(player1Turn)
            System.out.println("Player 2 wins");
        else if(player1Winner())
            System.out.println("Player 1 wins");
        else
            System.out.println("Cat's game");
    }

    private static boolean player1Winner() {
        int currentPlayer;
        if(!player1Turn) {
            currentPlayer = 1;
        }
        else
            currentPlayer = 2;
        return(horizontalWin(currentPlayer) || verticalWin(currentPlayer) || diagonalWin(currentPlayer));
    }

    private static boolean checkIfFull(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean diagonalWin(int num) {
        boolean winner;
        int i = 0;
        winner = true;
        for (int j = 0; j < board[i].length; j++) {
            i=j;
            if (board[i][j] != num)
                winner = false;
        }
        if(winner)
            return winner;

        winner = true;
        i=0;
        for (int j = 2; j >= 0; j--) {
            if (board[i][j] != num){
                winner = false;
            }
            i++;
        }
        if(winner)
            return winner;
        return false;
    }

    private static boolean horizontalWin(int num) {
        boolean winner;
        for(int i = 0; i < board.length; i++) {
            winner = true;
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != num)
                    winner = false;
            }
            if(winner)
                return winner;
        }
        return false;
    }
    private static boolean verticalWin(int num) {
        boolean winner;
        for(int i = 0; i < board.length; i++) {
            winner = true;
            for(int j = 0; j < board[i].length; j++) {
                if(board[j][i] != num)
                    winner = false;
            }
            if(winner)
                return winner;
        }
        return false;
    }

    private static void getUserInput() {
        if(player1Turn)
            System.out.println("Player 1 turn");
        else
            System.out.println("Player 2 turn");
        System.out.print("Where do you want to place your marker (row,col)? ");
        String answer = input.nextLine();
        int[] loc = parseInput(answer);
        placeMarker(loc);
    }

    private static boolean isValid(int[] loc) {
        return loc[ROW] >= 0 && loc[ROW] < board.length &&
                loc[COL] >= 0 && loc[COL] < board[0].length;
    }

    private static void placeMarker(int[] loc) {
        if(isValid(loc) && board[loc[ROW]][loc[COL]] == 0) {
            if (player1Turn)
                board[loc[ROW]][loc[COL]] = 1;
            else
                board[loc[ROW]][loc[COL]] = 2;
            player1Turn = !player1Turn;
        }
        else {
            System.out.println("Cannot play there.");
            if(player1Turn)
                System.out.println("Player 1 try again");
            else
                System.out.println("Player 2 try again");
        }
    }

    /*
     * Takes in a String of the format "num1, num2" and returns a 2-element array
     * with [0] = num1 and [1] = num2
     */
    private static int[] parseInput(String answer) {
        int[] loc = new int[2];
        int commaIndex = answer.indexOf(",");
        loc[0] = Integer.parseInt(answer.substring(0,commaIndex).trim());
        loc[1] = Integer.parseInt(answer.substring(commaIndex+1).trim());

        return loc;
    }

    private static void printBoard() {
        System.out.println();
        System.out.println();

        for(int row=0; row < board.length; row++) {
            System.out.print("   ");//leading space
            for(int col=0; col < board[row].length; col++) {
                System.out.print(board[row][col] + "    ");
                if(col != 2) {
                    System.out.print("|   ");
                }
            }
            if(row != 2) {
                System.out.println();
                System.out.println("-------------------------");
            }
        }

        System.out.println();
    }
}
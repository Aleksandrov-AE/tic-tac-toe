package org.example;

//игрок ходит первый
//игрок всегда ставит x
// бот всегда ставит 0
// бот выбирает случайную пустую клетку
// без ООП


import java.util.*;

public class Main {
    private final static  int  ROW_COUNT = 3;
    private final static  int COL_COUNT = 3;

    private static final String X_WIN = "Победил x";
    private static final String O_WIN = "Победил 0";
    private static final String DRAW = "Ничья";
    private static final String NOT_FINISH = "Игра не закончена";

    private final static  String  CELL_STATE_EMPTY = " ";
    private final static  String  CELL_STATE_X = "x";
    private final static  String  CELL_STATE_O = "o";
    private  static Random random = new Random();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            startRound();
            System.out.println("Если хотите начать заново нажмите Y:");
            if (!Objects.equals(scanner.nextLine(), "Y")) {
                return;
            }

        } while (true);
    }

    public static void startRound() {
        String[][] board = createBoard();
        startGameLoop(board);
    }

    public static String[][] createBoard() {
        String[][] gameBoard = new String[ROW_COUNT][COL_COUNT];
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                gameBoard[row][col] = CELL_STATE_EMPTY;
            }

        }
        return gameBoard;
    }

    public  static void startGameLoop(String[][] board) {
        boolean turnPlayer = true;
        do {

            if (turnPlayer) {
                makePlayerTurn(board);
                printBoard(board);
            } else {
                makeBotTurn(board);
                printBoard(board);
            }
            turnPlayer = !turnPlayer;
            String state = makeCheckGameState(board);
            if (!Objects.equals(state, NOT_FINISH)) {
                System.out.println(state);
                return;
            }
        } while (true);
        //player turn
        //bot turn
        //checkGameState(X_WIN,0_WIN,DRAW,GAME_NOT_START)
    }

    public static void makePlayerTurn(String[][] board) {
        // get input
        int[] coordinates = getInputCellCoordinates(board);
        //validate
        //x on board
        board[coordinates[0]][coordinates[1]] = CELL_STATE_X;
    }

    public static int[] getInputCellCoordinates(String[][] board) {
        do {
            System.out.println("Введите координаты через пробел (0_2)");
            String s = scanner.nextLine();
            if (!s.contains(" ")) {
                continue;
            }
            String[] input = s.split(" ");
            int row = Integer.parseInt(input[0]);
            int coll = Integer.parseInt(input[1]);
            if ((row < 0 || row >= ROW_COUNT) || (coll < 0 || coll >= COL_COUNT)) {
                System.out.println("Введите значения от 0 до 2");
             } else {
                if (board[row][coll] != CELL_STATE_EMPTY) {
                    System.out.println("Данная клетка занята");
                } else {
                    return new int[]{row, coll};
                }
            }

        } while (true);
    }

    public static void makeBotTurn(String[][] board) {
        System.out.println("Ход бота");
        int[] coordinates = getRandomEmptyCellCoordinates(board);
        board[coordinates[0]][coordinates[1]] = CELL_STATE_O;
    }

    public static int[] getRandomEmptyCellCoordinates(String[][] board) {
        //get random empty cell
        //0 on board
        do {
            int row = random.nextInt(ROW_COUNT);
            int coll = random.nextInt(COL_COUNT);
            if (Objects.equals(CELL_STATE_EMPTY, board[row][coll])) {
                return new int[]{row, coll};
            }
        } while (true);
    }

    public static void printBoard(String[][] board) {
        System.out.println("_________________");
        for (int i = 0; i < ROW_COUNT; i++) {
            String line = "|";
            for (int j = 0; j < COL_COUNT; j++) {
                line = line + board[i][j] + "|";
            }
            System.out.println(line);
        }
        System.out.println("_________________");
    }

    public static String makeCheckGameState(String[][] board) {
        // check rows
        List<Integer> states = new ArrayList<>();
        for (int row = 0; row < ROW_COUNT; row++) {
            int rowSum = 0;
            for (int coll = 0; coll < COL_COUNT; coll++) {
                rowSum += convertCellInValue(board[row][coll]);
                states.add(rowSum);
            }
        }
        //check colls
        for (int coll = 0; coll < COL_COUNT; coll++) {
            int collSum = 0;
             for (int row = 0; row < ROW_COUNT; row++) {
                collSum += convertCellInValue(board[row][coll]);
                states.add(collSum);
            }
        }
        int leftDiagonalSum = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            leftDiagonalSum += convertCellInValue(board[i][i]);
            states.add(leftDiagonalSum);

        }

        int rightDiagonal = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            rightDiagonal += convertCellInValue(board[i][(ROW_COUNT - 1) - i]);
            states.add(rightDiagonal);

        }

        if (states.contains(3)) {
            return X_WIN;
        }
        if (states.contains(-3)) {
            return O_WIN;
        }
        if (areAllCellTaken(board)) {
            return DRAW;
        }
        return NOT_FINISH;
        // X -> 1, O -> -1 , empty -> 0
        //count sum for rows, columns, diagonals
        //if(sum.contains(3)) -> x won
        //if(sum.contains(-3)) -> o won
        //if (allCellsBusy -> draw
        //else game not over
    }

    public static int convertCellInValue(String state) {
        if (state.equals(CELL_STATE_X)) {
            return 1;
        }
        if (state.equals(CELL_STATE_O)) {
            return -1;
        }
        return 0;
    }

    public static boolean areAllCellTaken(String[][] board) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int coll = 0; coll < COL_COUNT; coll++) {
                if (Objects.equals(board[row][coll], CELL_STATE_EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}


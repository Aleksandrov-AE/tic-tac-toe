package org.example;

//игрок ходит первый
//игрок всегда ставит x
// бот всегда ставит 0
// бот выбирает случайную пустую клетку
// без ООП


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public  static void gameLoop() {
        //while(GameNotOver)
        //player turn
        //bot turn
        //checkGameState(X_WIN,0_WIN,DRAW,GAME_NOT_START)
    }
    public static void playerTurn() {
        // get input
        //validate
        //x on board
    }

    public static void botTurn() {
        //get random empty cell
        //0 on board
    }

    public static void checkGameState() {
        // X -> 1, O -> -1 , empty -> 0
        //count sum for rows, columns, diagonals
        //if(sum.contains(3)) -> x won
        //if(sum.contains(-3)) -> o won
        //if (allCellsBusy -> draw
        //else game not over
    }
}


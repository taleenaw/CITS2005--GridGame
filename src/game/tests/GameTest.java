package game.tests;

import java.util.Collection;
import game.*;

/*this checks all the different wrong behaviours that could be demonstrated in GameImpl.*/

public class GameTest extends Test {
    public static void main(String[] args) {
        boolean caught = false;
        try {
            new GameImpl(0);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(true, caught);

        caught = false;
        try {
            new GameImpl(5);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        expect(false, caught);

        //---------my code---------//

        //sets up a test game object
        Game game = new GameImpl(5);

        //starting player should be WHITE
        expect(PieceColour.WHITE, game.currentPlayer());

        //this tests the alternation of players to make sure they        take in turn
        game.makeMove(new MoveImpl(0,0)); //W
        expect(PieceColour.BLACK, game.currentPlayer());
        game.makeMove(new MoveImpl(1,0)); //B
        expect(PieceColour.WHITE, game.currentPlayer());

        //this makes sure that the grid shows moves efficiently
        Grid grid = game.getGrid();
        expect(PieceColour.WHITE, game.getGrid().getPiece(0,0));
        expect(PieceColour.BLACK, game.getGrid().getPiece(1,0));

        //this tests for game over when the game should not be over
        expect(false, game.isOver());

        //this tests for winner when there should be no winner
        expect(PieceColour.NONE, game.winner());

        //should display an error if the user tries to override i       n the same spot in the grid
        boolean error = false;
        try 
        {
            game.makeMove(new MoveImpl(0,0)); //already a piece h       ere
        } catch (IllegalArgumentException x)
        {
            
            error = true;
        }
        expect(true, error);

        //what if theres an out of bounds move? eg. negatively indexed
        error = false;
        try 
        {
            game.makeMove(new MoveImpl(-1,0));
        } catch (IllegalArgumentException x)
        {
            
            error = true;
        }
        expect(true, error);

        //another out of bounds test to test if it is above the threshold
        error = false;
        try 
        {
            game.makeMove(new MoveImpl(5,5));
        } catch (IllegalArgumentException x)
        {
        
            error = true;
        }
        expect(true, error);

        //complete test of a game where white wins from left -> right
        game = new GameImpl(5);
        game.makeMove(new MoveImpl(2,0)); //W
        game.makeMove(new MoveImpl(3,0)); //B
        game.makeMove(new MoveImpl(2,1)); //W
        game.makeMove(new MoveImpl(3,1)); //B
        game.makeMove(new MoveImpl(2,2)); //W
        game.makeMove(new MoveImpl(3,2)); //B
        game.makeMove(new MoveImpl(2,3)); //W
        game.makeMove(new MoveImpl(3,3)); //B
        game.makeMove(new MoveImpl(2,4)); //W

        expect(true, game.isOver());
        expect(PieceColour.WHITE, game.winner());

        //complete test of a game where black wins from top -> bottom
        game = new GameImpl(5);
        game.makeMove(new MoveImpl(0,1)); //W
        game.makeMove(new MoveImpl(0,0)); //B
        game.makeMove(new MoveImpl(1,2)); //W
        game.makeMove(new MoveImpl(1,0)); //B
        game.makeMove(new MoveImpl(2,1)); //W
        game.makeMove(new MoveImpl(2,0)); //B
        game.makeMove(new MoveImpl(3,2)); //W
        game.makeMove(new MoveImpl(3,0)); //B
        game.makeMove(new MoveImpl(4,1)); //W
        game.makeMove(new MoveImpl(4,0)); //B

        expect(true, game.isOver());
        expect(PieceColour.BLACK, game.winner());

        //-----//
        Game a = new GameImpl(5);
        a.makeMove(new MoveImpl(1,1)); //WHITE
        expect(PieceColour.WHITE, a.getGrid().getPiece(1,1));
        //---//
        Game b = new GameImpl(5);
        b.makeMove(new MoveImpl(0,0)); //WHITE
        Game copy = b.copy();
        copy.makeMove(new MoveImpl(0,1)); //should not effect OG
        
        expect(PieceColour.NONE, b.getGrid().getPiece(0,1));
        //OG uneffected
        expect(PieceColour.BLACK, copy.getGrid().getPiece(0,1));//copy modified

        //checks moves in an empty state
        Game c = new GameImpl(5);
        Collection<Move> movee = c.getMoves();
        expect(25, movee.size());

        //checks move in a non-empty state
        Game d = new GameImpl(5);
        d.makeMove(new MoveImpl(0,0)); //W
        d.makeMove(new MoveImpl(0,1)); //B

        Collection<Move> moved = d.getMoves();
        expect(23, moved.size());

        //tests if the game ends in a draw
        game = new GameImpl(5);
        for (int x = 0; x <= 4; x++)
        {
            for (int y = 0; y <= 4; y++)
            {
                game.makeMove(new MoveImpl(x, y));
            }
        }

        expect(true, game.isOver());
        expect(PieceColour.NONE, game.winner());

        //tests for an empty state where there are no moves left
        Collection<Move> noMoves = game.getMoves();
        expect(0, noMoves.size());
        
        checkAllTestsPassed();
    }
}

package game;

import java.util.Collection;
import java.util.ArrayList;

public class GameImpl implements Game
{
    private Grid grid;
    private ArrayList<Move> moves;
    // true = WHITE's turn and false = BLACK's turn
    private boolean player;

    public GameImpl(int size)
    {
        this.moves = new ArrayList<Move>();

        if (size <= 0)
        {
            throw new IllegalArgumentException("Grid cannot be negative");
        }
        
        this.grid = new GridImpl(size);

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                this.moves.add(new MoveImpl(i, j));
            }
        }

        this.player = true;
    }
    
    @Override
    public boolean isOver()
    {    
        if(winner() != PieceColour.NONE ||
        this.moves.isEmpty())
        {
            return true;
        }

        return false;
    }

    @Override
    public PieceColour winner()
    {            
        if(PathFinder.topToBottom(this.grid.copy(), PieceColour.WHITE) 
        || PathFinder.leftToRight(this.grid.copy(), PieceColour.WHITE))
        {
            return PieceColour.WHITE;
        }
        else if(PathFinder.topToBottom(this.grid.copy(), PieceColour.BLACK) 
        || PathFinder.leftToRight(this.grid.copy(), PieceColour.BLACK))
        {
            return PieceColour.BLACK;
        }

        return PieceColour.NONE;
    }

    @Override 
    public PieceColour currentPlayer()
    {
        if(!isOver())
        {
            if(this.player)
            {
                return PieceColour.WHITE;
            }

            return PieceColour.BLACK;
        }
        
        return null;
    }

    @Override
    public Collection<Move> getMoves()
    {
        return this.moves;
    }

    @Override
    public void makeMove(Move move)
    {
        if(!this.isOver())
        {
            boolean contained = false;
            Move moveRef = null;

            for(Move m : this.moves)
            {
                if(m.getRow() == move.getRow() && m.getCol() == move.getCol())
                {
                    contained = true;
                    moveRef = m;
                    break;
                }
            }

            if(move.getRow() > this.grid.getSize() || move.getRow() < 0 
            || move.getCol() > this.grid.getSize() || move.getCol() < 0 
            || !contained)
            {
                throw new IllegalArgumentException("Move not valid");
            }

            this.grid.setPiece(moveRef.getRow(), moveRef.getCol(), this.currentPlayer());
            this.player = !this.player;
            this.moves.remove(moveRef);
        }
    }

    @Override
    public Grid getGrid()
    {
      return this.grid.copy();
    }

    @Override
    public Game copy()
    {
        GameImpl gameCopy = new GameImpl(this.grid.getSize());

        gameCopy.grid = this.grid.copy();

        gameCopy.moves = new ArrayList<Move>();
        for(Move move : this.getMoves())
        {
            gameCopy.moves.add(move);
        }

        gameCopy.player = this.player;

        return gameCopy;
    }
}

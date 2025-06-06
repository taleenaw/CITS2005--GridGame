package game;

public class GridImpl implements Grid
{
    private PieceColour[][] grid;

    public GridImpl(int size)
    {
        if(size <= 0)
        {
            throw new IllegalArgumentException("Grid size cannot be negative");
        }
        else
        {
            grid = new PieceColour[size][size];

            for(int i = 0; i < grid.length; i++)
            {
                for(int j = 0; j < grid[i].length; j++)
                {
                    grid[i][j] = PieceColour.NONE;
                }
            }
        }
    }

    @Override
    public int getSize()
    {
        return grid.length;
    }

    @Override
    public PieceColour getPiece(int row, int col)
    {
       try {
           return grid[row][col];
       } catch (ArrayIndexOutOfBoundsException e) {
           throw new IllegalArgumentException("Invalid grid coordinates");
       }
    }

    @Override 
    public void setPiece(int row, int col, PieceColour piece)
    {
       try {
           if (grid[row][col] != PieceColour.NONE) {
               throw new IllegalArgumentException("Square is already occupied.");
           }
           grid[row][col] = piece;
       } catch (ArrayIndexOutOfBoundsException e) {
           throw new IllegalArgumentException("Invalid grid coordinates");
       }
    }

    @Override
    public Grid copy()
    {
        Grid g = new GridImpl(this.grid.length);
        
        for(int i = 0; i < this.grid.length; i++)
        {
            for(int j = 0; j < this.grid[i].length; j++)
            {
                g.setPiece(i, j, this.getPiece(i, j));
            }
        }

        return g;
    }

    @Override
    public String toString()
    {
        String gridString = "";

        for(int i = 0; i < this.grid.length; i++)
        {
            for(int j = 0; j < this.grid[i].length; j++)
            {
                if(grid[i][j] == PieceColour.NONE)
                {
                    gridString += ".";
                }
                else if(grid[i][j] == PieceColour.BLACK)
                {
                    gridString += "B";
                }
                else
                {
                    gridString += "W";
                }

                if(j == grid[i].length-1)
                {
                    gridString += '\n';
                }
            }
        }

        return gridString;
    }
}

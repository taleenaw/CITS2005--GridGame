package game;

public class MoveImpl implements Move 
{
    private int row;
    private int column;

    public MoveImpl(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public int getCol()
    {
        return column;
    }

    @Override
    public String toString()
    {
        return("(" + row + "," + column + ")");
    }
}

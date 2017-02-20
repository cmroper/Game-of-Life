package gameoflife.bearslab.com.gameoflife;

public class Cell {
    private boolean currentState;
    private boolean nextState;

    public final int x;
    public final int y;

    /**
     * Create a new Cell object.
     * @param alive   if cell is alive
     */
    public Cell(boolean alive, int xCoord, int yCoord)
    {
        currentState = alive;
        x = xCoord;
        y = yCoord;
    }


    /**
     * Contains the rules for the cell
     */
    public void update(int neighbors)
    {
        if (currentState)
        {
            //cell is alive
            nextState = (neighbors == 2 || neighbors == 3);
        }
        else nextState = (neighbors == 3);
    }

    public void changeState()
    {
        currentState = nextState;
    }

    /**
     * returns the next state of the cell
     */
    public boolean getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(boolean alive)
    {
        currentState = alive;
    }


}


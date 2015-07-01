package com.bearslab.gameoflife;

public class Environment {
	public Cell[][] cells;

	private static int GRID_HEIGHT;
	private static int GRID_WIDTH;
	private static final int[][] NEIGHBOURS= {
		{-1,-1}, {0,-1}, {+1,-1},
		{-1,0},			 {+1, 0},
		{-1,+1}, {0,+1}, {+1,+1}			 };

	public Environment(int width, int height)
	{
		GRID_WIDTH = width;
		GRID_HEIGHT = height;
		//creates new grid of dead cells
		cells = new Cell[GRID_WIDTH][GRID_HEIGHT];
		for (int i=0; i<GRID_WIDTH; i++)
		{
			for (int j=0; j<GRID_HEIGHT; j++)
			{
				cells[i][j] = new Cell(false, i, j);
			}
		}
	}

	/**
	 * One iteration
	 * changes nextState of each cell
	 * then changes states
	 */
	public void step()
	{
		//checks neighbors of each cell
		//and updates nextState
		for (int j = 1; j < GRID_HEIGHT; j++)
		{
			for (int i = 1; i < GRID_WIDTH; i++)
			{
				int neighbors = checkNeighbors(i, j);
				cells[i][j].update(neighbors);
			}
		}

		//will update the states
		for (int j = 1; j < GRID_HEIGHT; j++)
		{
			for (int i = 1; i < GRID_WIDTH; i++)
			{
				cells[i][j].changeState();
			}
		}
	}


	/**
	 * returns state of cell located at x,y
	 */
	public boolean getState(int x, int y)
	{
		return cells[x][y].getCurrentState();
	}    

	public void makeCellAlive(int x, int y)
	{
		cells[x][y].setCurrentState(true);
	}

	/**
	 * checks the neighbors of cells[x][y]
	 * and accounts for boundary conditions
	 * @return   # of live neighbors
	 */
	public int checkNeighbors(int x, int y)
	{
		int n = 0;

		if (x == 0)
		{
			if (y == 0)
			{
				//check s,se,e
				if (getState(x, y+1)) { n++; }
				if (getState(x+1, y+1)) { n++; }
				if (getState(x+1, y)) { n++; }
			}
			else if (y == GRID_HEIGHT-1)
			{
				//check n,ne,e
				if (getState(x, y-1)) { n++; }
				if (getState(x+1, y-1)) { n++; }
				if (getState(x+1, y)) { n++; }
			}
			else
			{
				//check n,ne,e,se,s
				if (getState(x, y-1)) { n++; }
				if (getState(x+1, y-1)) { n++; }
				if (getState(x+1, y)) { n++; }
				if (getState(x+1, y+1)) { n++; }
				if (getState(x, y+1)) { n++; }
			}
		}
		else if (x == GRID_WIDTH-1)
		{
			if (y == 0)
			{
				//check s,sw,w
				if (getState(x, y+1)) { n++; }
				if (getState(x-1, y+1)) { n++; }
				if (getState(x-1, y)) { n++; }
			}
			else if (y == GRID_HEIGHT-1)
			{
				//check n,nw,w
				if (getState(x, y-1)) { n++; }
				if (getState(x-1, y-1)) { n++; }
				if (getState(x-1, y)) { n++; }
			}
			else
			{
				//check n,nw,w,sw,s
				if (getState(x, y-1)) { n++; }
				if (getState(x-1, y-1)) { n++; }
				if (getState(x-1, y)) { n++; }
				if (getState(x-1, y+1)) { n++; }
				if (getState(x, y+1)) { n++; }
			}
		}
		else if (y == 0)
		{
			//check se,s,sw
			if (getState(x+1, y+1)) { n++; }
			if (getState(x, y+1)) { n++; }
			if (getState(x-1, y+1)) { n++; }
		}
		else if (y == GRID_HEIGHT-1)
		{
			//check ne,n,nw
			if (getState(x+1, y-1)) { n++; }
			if (getState(x, y-1)) { n++; }
			if (getState(x-1, y-1)) { n++; }
		}


		else { 
			//check n,ne,e,se,s,sw,w,nw
			for (int[] offset : NEIGHBOURS) {
				if (getState(x + offset[0], y + offset[1]))
				{
					n++;
				}
			}     
			
		}
		return n;
	}

	//maybe useless? idk
	public boolean isValid(int x, int y) {
		return (x >= 0 && y >= 0 && x < GRID_WIDTH && y < GRID_HEIGHT);
	}




}

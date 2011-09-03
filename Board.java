public class Board
{
	// Variable to keep track of the board.
	private int[][] board;

	// The number of rows and columns of the board.
	private int row;

	private int column;

	private int[][] pointBoard;

	/**
	 * The constructor to create a new Board object.
	 * 
	 * @param rowInput
	 *            The number of rows.
	 * @param columnInput
	 *            The number of columns.
	 */
	public Board(int rowInput, int columnInput)
	{
		// Sets the number of rows and columns to be 2 more than the input
		// because the board will have a border of -1s around it.
		this.row = rowInput + 2;
		this.column = columnInput + 2;

		// Creates a new board with 0s to initialise each position.
		// A border of -1s will be placed around the board.
		board = new int[row][column];

		for (int currentRow = 0; currentRow < row; currentRow++)
		{
			for (int currentColumn = 0; currentColumn < column; currentColumn++)
			{
				// Adds a -1 if the current space is on the border.
				if (currentRow == 0 || currentColumn == 0
						|| currentRow == (row - 1)
						|| currentColumn == (column - 1))
					board[currentRow][currentColumn] = -1;
				// Adds a 0 if the current space is not on the border.
				else
					board[currentRow][currentColumn] = 0;
			}
		}
	}

	/**
	 * Places the piece onto the board.
	 * 
	 * @param row
	 *            The horizontal position of the piece.
	 * @param column
	 *            The vertical position of the piece.
	 * @param piece
	 *            1 to represent the first player or 2 to represent the second
	 *            player.
	 */
	public void setPiece(int row, int column, int piece)
	{
		board[row][column] = piece;
	}

	/**
	 * Clears the board.
	 */
	public void clearBoard()
	{
		// Clears the board by changing all the values not found on the border
		// to 0.
		for (int currentRow = 1; currentRow <= 15; currentRow++)
		{
			for (int currentColumn = 1; currentColumn <= 15; currentColumn++)
			{
				board[currentRow][currentColumn] = 0;
			}
		}
	}

	/**
	 * Checks to see whether or not there is an empty space.
	 * 
	 * @return True if there is an empty space, false if there isn't.
	 */
	public boolean isEmpty()
	{
		// Goes through every space and checks whether or not there is an empty
		// space.
		for (int currentRow = 1; currentRow < row - 1; currentRow++)
		{
			// Returns true is an empty space if found.
			for (int currentColumn = 1; currentColumn < column - 1; currentColumn++)
			{
				if (board[currentRow][currentColumn] == 0)
					return true;
			}
		}
		// Returns false if no empty spaces were found.
		return false;
	}

	/**
	 * Returns the value of the selected piece.
	 * 
	 * @param row
	 *            The horizontal position of the piece.
	 * @param column
	 *            The vertical position of the piece.
	 * @return The value of the piece.
	 */
	public int getValue(int row, int column)
	{
		return board[row][column];
	}

	/**
	 * Checks to see if there is a winner.
	 * 
	 * @param rowPlaced
	 *            The row the piece was placed in.
	 * @param columnPlaced
	 *            The column the piece was placed in.
	 * @return
	 */
	public boolean checkForWinner(int rowPlaced, int columnPlaced)
	{
		{
			// Begins checking from the position the piece was placed in.
			int lastColumn = columnPlaced;
			int lastRow = rowPlaced;

			// Keeps track of the value of the pieces being added up.
			int pieceValue = board[rowPlaced][columnPlaced];

			// The number of pieces found in a row.
			int pieceCount = 0;

			// Counts how many pieces are in a row horizontally to the right.
			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn++;
			}

			// Changes the column value to continue checking horizontally to the
			// left.
			lastColumn = columnPlaced - 1;

			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn--;
			}

			// Resets the number of pieces found in a row to 0.
			// Resets the column value to go back to the space the piece was
			// placed in.
			pieceCount = 0;
			lastColumn = columnPlaced;

			// Counts how many pieces are in a row vertically downwards.
			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastRow++;
			}

			// Changes the row value to continue checking vertically upwards.
			lastRow = rowPlaced - 1;

			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastRow--;
			}

			// Resets the number of pieces found in a row to 0.
			// Resets the row value to go back to the space the piece was placed
			// in.
			pieceCount = 0;
			lastRow = rowPlaced;

			// Counts how many pieces are in a row diagonally to the upper
			// right.
			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn++;
				lastRow--;
			}

			// Changes the row and column values to continue checking diagonally
			// to the lower left.
			lastColumn = columnPlaced - 1;
			lastRow = rowPlaced + 1;

			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn--;
				lastRow++;
			}

			// Resets the number of pieces found in a row to 0.
			// Resets the row and column values to go back to the space the
			// piece was placed in.
			pieceCount = 0;
			lastColumn = columnPlaced;
			lastRow = rowPlaced;

			// Counts how many pieces are in a row diagonally to the upper left.
			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn++;
				lastRow++;
			}

			// Changes the row and column values to continue checking diagonally
			// to the lower right.
			lastColumn = columnPlaced - 1;
			lastRow = rowPlaced - 1;

			while (board[lastRow][lastColumn] == pieceValue)
			{
				pieceCount++;

				if (pieceCount >= 5)
					return true;

				lastColumn--;
				lastRow--;
			}
		}
		return false;
	}

	/**
	 * Debugging the point board for the AI system
	 */
	public void AIdebugger()
	{

	}

	/**
	 * Debugging the point board for the AI system
	 */
	public void boardDebugger()
	{

	}

	/**
	 * Checks the best possible moves for the AI
	 * 
	 * @param difficulty
	 *            The level of computer AI. Level 3 is the highest, thus using
	 *            the most checks and finds a better move than a Level 1.
	 * @return the position on where to place the next piece
	 */
	public int[] AIchecker(int difficulty)
	{
		// Create the points board for debugging purposes.
		pointBoard = new int[row][column];

		// Use an array to return both the row and column of the highest points
		// location.
		int[] position = new int[2];

		// Keep track of the highest number of points as well as the
		// row / column it was in
		int highestPoints = 0;
		int highestRow = 1, highestColumn = 1;

		// Keeps track if the player has any 4-chains. Only used for a Level 3
		// computer.
		boolean isFourChain = false;

		for (int rowCount = 1; rowCount <= 15; rowCount++)
		{
			for (int columnCount = 1; columnCount <= 15; columnCount++)
			{
				// Have a point counter to determine the best position to
				// place the piece.
				int points = 0;

				// Checks if the piece on the board is empty or not.
				// (0 = empty. Thus check for total points on that piece.)
				if (board[rowCount][columnCount] == 0)
				{
					// Begins checking from the position the piece was placed
					// in.
					int lastRow = rowCount;
					int lastColumn = columnCount;

					// The number of pieces found in a row.
					int pieceCount = 0;

					// Player # 2 is the human player so it only looks for human
					// pieces.
					// Piece # 0 means it is an empty square, which is where we
					// want to check for moves on.

					// Counts how many pieces are in a row horizontally to the
					// right. X--->
					lastColumn++;
					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn++;
					}

					// Changes the column value to continue checking
					// horizontally to the left. <---X
					lastColumn = columnCount - 1;

					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn--;
					}
					// If the AI is harder, then more checks are done to make it
					// think more.

					// Adding more points to this situation, as this set-up
					// allows for a better advancement in the player's
					// pieces next round. Because of this, it has a greater
					// importance to block.
					// 0 = initial block. _ = possible expansion squares
					// 0XXX_ , _X0XX_ , _XX0X_ , _XXX0
					if (difficulty == 3 && pieceCount == 3)
					{
						if (columnCount <= 12
								&& board[rowCount][columnCount] == 0
								&& board[rowCount][columnCount + 4] == 0
								|| columnCount <= 13
								&& board[rowCount][columnCount - 1] == 0
								&& board[rowCount][columnCount + 3] == 0
								|| columnCount <= 14 && columnCount >= 2
								&& board[rowCount][columnCount - 2] == 0
								&& board[rowCount][columnCount + 2] == 0
								|| columnCount >= 3
								&& board[rowCount][columnCount - 3] == 0
								&& board[rowCount][columnCount + 1] == 0
								|| columnCount >= 4
								&& board[rowCount][columnCount - 4] == 0
								&& board[rowCount][columnCount] == 0)
							points += 25;
					}

					// Resets the number of pieces found in a row to 0.
					// Resets the column value to go back to the space the piece
					// was placed in.
					pieceCount = 0;
					lastColumn = columnCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					lastRow++;
					// Counts how many pieces are in a row vertically downwards.
					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastRow++;
					}

					// Changes the row value to continue checking vertically
					// upwards.
					lastRow = rowCount - 1;

					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastRow--;
					}
					// If the AI is harder, then more checks are done to make it
					// think more.

					// Adding more points to this situation, as this set-up
					// allows for a better advancement in the player's
					// pieces next round. Because of this, it has a greater
					// importance to block.
					if (difficulty == 3 && pieceCount == 3)
					{
						if (rowCount <= 12 && board[rowCount][columnCount] == 0
								&& board[rowCount + 4][columnCount] == 0
								|| rowCount <= 13
								&& board[rowCount - 1][columnCount] == 0
								&& board[rowCount + 3][columnCount] == 0
								|| rowCount <= 14 && rowCount >= 2
								&& board[rowCount - 2][columnCount] == 0
								&& board[rowCount + 2][columnCount] == 0
								|| rowCount >= 3
								&& board[rowCount - 3][columnCount] == 0
								&& board[rowCount + 1][columnCount] == 0
								|| rowCount >= 4
								&& board[rowCount - 4][columnCount] == 0
								&& board[rowCount][columnCount] == 0)
							points += 25;
					}

					// Resets the number of pieces found in a row to 0.
					// Resets the row value to go back to the space the piece
					// was placed in.
					pieceCount = 0;
					lastRow = rowCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					lastColumn++;
					lastRow--;
					// Counts how many pieces are in a row diagonally to the
					// upper right.
					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn++;
						lastRow--;
					}

					// Changes the row and column values to continue checking
					// diagonally to the lower left.
					lastColumn = columnCount - 1;
					lastRow = rowCount + 1;

					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn--;
						lastRow++;
					}
					// If the AI is harder, then more checks are done to make it
					// think more

					// Adding more points to this situation, as this set-up
					// allows for a better advancement in the player's
					// pieces next round. Because of this, it has a greater
					// importance to block.
					if (difficulty == 3 && pieceCount == 3)
					{
						if (rowCount <= 12 && columnCount >= 4
								&& board[rowCount][columnCount] == 0
								&& board[rowCount + 4][columnCount - 4] == 0
								|| rowCount <= 13 && columnCount >= 3
								&& board[rowCount - 1][columnCount + 1] == 0
								&& board[rowCount + 3][columnCount - 3] == 0
								|| rowCount <= 14 && columnCount >= 2
								&& rowCount >= 2 && columnCount <= 14
								&& board[rowCount - 2][columnCount + 2] == 0
								&& board[rowCount + 2][columnCount - 2] == 0
								|| rowCount >= 3 && columnCount <= 13
								&& board[rowCount - 3][columnCount + 3] == 0
								&& board[rowCount + 1][columnCount - 1] == 0
								|| rowCount >= 4 && columnCount <= 12
								&& board[rowCount - 4][columnCount + 4] == 0
								&& board[rowCount][columnCount] == 0)
							points += 25;
					}

					// Resets the number of pieces found in a row to 0.
					// Resets the row and column values to go back to the space
					// the piece was placed in.
					pieceCount = 0;
					lastColumn = columnCount;
					lastRow = rowCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					lastColumn++;
					lastRow++;
					// Counts how many pieces are in a row diagonally to the
					// upper left.
					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn++;
						lastRow++;
					}

					// Changes the row and column values to continue checking
					// diagonally to the lower right.
					lastColumn = columnCount - 1;
					lastRow = rowCount - 1;

					while (board[lastRow][lastColumn] == 2)
					{
						pieceCount++;

						if (pieceCount == 1)
							points++;
						if (pieceCount == 2)
							points += 2;
						if (pieceCount == 3)
							points += 30;
						if (pieceCount == 4)
						{
							points += 150;
							isFourChain = true;
						}

						lastColumn--;
						lastRow--;
					}
					// If the AI is harder, then more checks are done to make it
					// think more.

					// Adding more points to this situation, as this set-up
					// allows for a better advancement in the player's
					// pieces next round. Because of this, it has a greater
					// importance to block.
					if (difficulty == 3 && pieceCount == 3)
					{
						if (rowCount >= 4 && columnCount >= 4
								&& board[rowCount][columnCount] == 0
								&& board[rowCount - 4][columnCount - 4] == 0
								|| rowCount >= 3 && columnCount >= 3
								&& board[rowCount + 1][columnCount + 1] == 0
								&& board[rowCount - 3][columnCount - 3] == 0
								|| rowCount >= 2 && columnCount >= 2
								&& rowCount <= 14 && columnCount <= 14
								&& board[rowCount + 2][columnCount + 2] == 0
								&& board[rowCount - 2][columnCount - 2] == 0
								|| rowCount <= 13 && columnCount <= 13
								&& board[rowCount + 3][columnCount + 3] == 0
								&& board[rowCount - 1][columnCount - 1] == 0
								|| rowCount <= 12 && columnCount <= 12
								&& board[rowCount + 4][columnCount + 4] == 0
								&& board[rowCount][columnCount] == 0)
							points += 25;
					}
				} // If statement to make sure the piece on the board is empty
				// Saves the highest point value and which row / column it was
				// in.
				if (points > highestPoints)
				{
					highestPoints = points;
					highestRow = rowCount;
					highestColumn = columnCount;
				}

				pointBoard[rowCount][columnCount] = points;

				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

				// From here, make the computer check for its own 4-in a rows.
				// When it finds one, it will win.

				// Begins checking from the position the piece was placed in.
				// if (difficulty == 3 )
				// {               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				if (board[rowCount][columnCount] == 0)
				{
					int lastRow = rowCount;
					int lastColumn = columnCount;
					// The number of pieces found in a row.
					int pieceCount = 0;

					// Counts how many pieces are in a row horizontally to
					// the right.
					lastColumn++;
					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn++;
					}

					// Changes the column value to continue checking
					// horizontally to the left.
					lastColumn = columnCount - 1;

					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn--;
					}

					// Gives the location of where the winning piece goes
					if (pieceCount >= 4)
					{
						position[0] = rowCount;
						position[1] = columnCount;


						return position;
					}
					// If the computer has a 3-chain with no walls and
					// the player has no 4-in a rows, then the computer
					// can win in 2 moves. By setting up a 4-chain with
					// no walls, the player cannot block both sides and
					// cannot win as they do not have any 4-chains
					// themselves. Therefore, this leads the computer to
					// a victory.
					// 232 = the max points with only 3-chains.
					// 183 = the max points with only one 4-chain. The
					// computer HAS to block this, so this is taken care of
					// with a checker to make sure that there is no
					// 4-chains.
					if (pieceCount == 3 && highestPoints < 233 && !isFourChain)
						if (columnCount <= 12
								&& board[rowCount][columnCount] == 0
								&& board[rowCount][columnCount + 4] == 0
								|| columnCount <= 13
								&& board[rowCount][columnCount - 1] == 0
								&& board[rowCount][columnCount + 3] == 0
								|| columnCount <= 14 && columnCount >= 2
								&& board[rowCount][columnCount - 2] == 0
								&& board[rowCount][columnCount + 2] == 0
								|| columnCount >= 3
								&& board[rowCount][columnCount - 3] == 0
								&& board[rowCount][columnCount + 1] == 0
								|| columnCount >= 4
								&& board[rowCount][columnCount - 4] == 0
								&& board[rowCount][columnCount] == 0)
						{
							position[0] = rowCount;
							position[1] = columnCount;

							return position;
						}

					// Resets the number of pieces found in a row to 0.
					// Resets the column value to go back to the space the
					// piece was placed in.
					pieceCount = 0;
					lastColumn = columnCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					lastRow++;
					// Counts how many pieces are in a row vertically
					// downwards.
					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastRow++;
					}

					// Changes the row value to continue checking vertically
					// upwards.
					lastRow = rowCount - 1;

					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastRow--;
					}

					// Gives the location of where the winning piece goes
					if (pieceCount >= 4)
					{
						position[0] = rowCount;
						position[1] = columnCount;

						return position;
					}
					// If the computer has a 3-chain with no walls and
					// the player has no 4-in a rows, then the computer
					// can win in 2 moves. By setting up a 4-chain with
					// no walls, the player cannot block both sides and
					// cannot win as they do not have any 4-chains
					// themselves. Therefore, this leads the computer to
					// a victory.
					// 232 = the max points with only 3-chains
					if (pieceCount == 3 && highestPoints < 233 && !isFourChain)
						if (rowCount <= 12 && board[rowCount][columnCount] == 0
								&& board[rowCount + 4][columnCount] == 0
								|| rowCount <= 13
								&& board[rowCount - 1][columnCount] == 0
								&& board[rowCount + 3][columnCount] == 0
								|| rowCount <= 14 && rowCount >= 2
								&& board[rowCount - 2][columnCount] == 0
								&& board[rowCount + 2][columnCount] == 0
								|| rowCount >= 3
								&& board[rowCount - 3][columnCount] == 0
								&& board[rowCount + 1][columnCount] == 0
								|| rowCount >= 4
								&& board[rowCount - 4][columnCount] == 0
								&& board[rowCount][columnCount] == 0)
						{
							position[0] = rowCount;
							position[1] = columnCount;

							return position;
						}

					// Resets the number of pieces found in a row to 0.
					// Resets the row value to go back to the space the
					// piece was placed in.
					pieceCount = 0;
					lastRow = rowCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					lastColumn++;
					lastRow--;
					// Counts how many pieces are in a row diagonally to the
					// upper right.
					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn++;
						lastRow--;
					}

					// Changes the row and column values to continue
					// checking diagonally to the lower left.
					lastColumn = columnCount - 1;
					lastRow = rowCount + 1;

					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn--;
						lastRow++;
					}

					// Gives the location of where the winning piece goes
					if (pieceCount >= 4)
					{
						position[0] = rowCount;
						position[1] = columnCount;
	 
						return position;
					}
					// If the computer has a 3-chain with no walls and
					// the player has no 4-in a rows, then the computer
					// can win in 2 moves. By setting up a 4-chain with
					// no walls, the player cannot block both sides and
					// cannot win as they do not have any 4-chains
					// themselves. Therefore, this leads the computer to
					// a victory.
					// 232 = the max points with only 3-chains
					if (pieceCount == 3 && highestPoints < 233 && !isFourChain)
						if (rowCount <= 12 && columnCount >= 4
								&& board[rowCount][columnCount] == 0
								&& board[rowCount + 4][columnCount - 4] == 0
								|| rowCount <= 13 && columnCount >= 3
								&& board[rowCount - 1][columnCount + 1] == 0
								&& board[rowCount + 3][columnCount - 3] == 0
								|| rowCount <= 14 && columnCount >= 2
								&& rowCount >= 2 && columnCount <= 14
								&& board[rowCount - 2][columnCount + 2] == 0
								&& board[rowCount + 2][columnCount - 2] == 0
								|| rowCount >= 3 && columnCount <= 13
								&& board[rowCount - 3][columnCount + 3] == 0
								&& board[rowCount + 1][columnCount - 1] == 0
								|| rowCount >= 4 && columnCount <= 12
								&& board[rowCount - 4][columnCount + 4] == 0
								&& board[rowCount][columnCount] == 0)
						{
							position[0] = rowCount;
							position[1] = columnCount;
					       
							return position;
						}

					// Resets the number of pieces found in a row to 0.
					// Resets the row and column values to go back to the
					// space the piece was placed in.
					pieceCount = 0;
					lastColumn = columnCount;
					lastRow = rowCount;

					// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

					// Counts how many pieces are in a row diagonally to the
					// upper left.
					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn++;
						lastRow++;
					}

					// Changes the row and column values to continue
					// checking diagonally to the lower right.
					lastColumn = columnCount - 1;
					lastRow = rowCount - 1;

					while (board[lastRow][lastColumn] == 1)
					{
						pieceCount++;
						lastColumn--;
						lastRow--;
					}

					// Gives the location of where the winning piece goes
					if (pieceCount >= 4)
					{
						position[0] = rowCount;
						position[1] = columnCount;
					 
						return position;
					}
					// If the computer has a 3-chain with no walls and
					// the player has no 4-in a rows, then the computer
					// can win in 2 moves. By setting up a 4-chain with
					// no walls, the player cannot block both sides and
					// cannot win as they do not have any 4-chains
					// themselves. Therefore, this leads the computer to
					// a victory.
					// 232 = the max points with only 3-chains
					if (pieceCount == 3 && highestPoints < 233 && !isFourChain)
						if (rowCount >= 4 && columnCount >= 4
								&& board[rowCount][columnCount] == 0
								&& board[rowCount - 4][columnCount - 4] == 0
								|| rowCount >= 3 && columnCount >= 3
								&& board[rowCount + 1][columnCount + 1] == 0
								&& board[rowCount - 3][columnCount - 3] == 0
								|| rowCount >= 2 && columnCount >= 2
								&& rowCount <= 14 && columnCount <= 14
								&& board[rowCount + 2][columnCount + 2] == 0
								&& board[rowCount - 2][columnCount - 2] == 0
								|| rowCount <= 13 && columnCount <= 13
								&& board[rowCount + 3][columnCount + 3] == 0
								&& board[rowCount - 1][columnCount - 1] == 0
								|| rowCount <= 12 && columnCount <= 12
								&& board[rowCount + 4][columnCount + 4] == 0
								&& board[rowCount][columnCount] == 0)
						{
							position[0] = rowCount;
							position[1] = columnCount;
					  
							return position;
						}

					// }
				}
			} // For loop for the column Counter
		} // For loop for the row Counter

		position[0] = highestRow;
		position[1] = highestColumn;
		return position;
	}
}

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * ConnectFive game.
 * @authors Aaron Pang, Brandon Ma, Sina Jamalian
 * @version June 4, 2010
 */

public class ConnectFive extends JFrame implements ActionListener
{
	// Variables to keep track of the players.
	final int playerOne = 1;

	final int playerTwo = 2;

	private int startx = 7, endx = 7, starty = 7, endy = 7;

	// Board object to keep track of the board.
	Board board;

	// Variables for the menu.
	private JMenuItem newOption, exitOption, instructionOption, aboutOption;

	// Variables for the images.
	private Image pieceOne, pieceTwo, boardImage;

	private DrawingPanel drawingArea;

	// Variable to keep track of the current player.
	private int currentPlayer;

	// Variables for the selected row and column.
	private int selectedRow;

	private int selectedColumn;

	// Variables to keep track of whether or not the game is over.
	private boolean gameOver;

	/**
	 * Constructs a new Checkers frame and sets up the game.
	 */
	public ConnectFive()
	{
		// Sets values for the array which represents the board.
		board = new Board(15, 15);

		// Sets up the screen.
		Container contentPane = getContentPane();
		Dimension size = new Dimension(600, 600);
		drawingArea = new DrawingPanel(size);
		contentPane.add(drawingArea, BorderLayout.CENTER);
		setTitle("ConnectFive");
		setResizable(false);

		// Sets up the Menu.
		// Sets up the Game MenuItems.
		newOption = new JMenuItem("New");
		newOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		newOption.addActionListener(this);

		exitOption = new JMenuItem("Exit");
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		exitOption.addActionListener(this);

		// Sets up the Help MenuItems.
		instructionOption = new JMenuItem("Instructions");
		instructionOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		instructionOption.addActionListener(this);

		aboutOption = new JMenuItem("About");
		aboutOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		aboutOption.addActionListener(this);

		// Sets up the Game and Help Menus.
		JMenu gameMenu = new JMenu("Game");
		JMenu helpMenu = new JMenu("Help");

		// Add each MenuItem to the Game Menu (with a separator).
		gameMenu.add(newOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);

		// Add each MenuItem to the Help Menu (with a separator).
		helpMenu.add(instructionOption);
		helpMenu.addSeparator();
		helpMenu.add(aboutOption);

		// Adds the GameMenu and HelpMenu to the JMenuBar.
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		mainMenu.add(helpMenu);

		// Set the menu bar for this frame to mainMenu.
		setJMenuBar(mainMenu);

		// Starts a new game and makes the window visible.
		newGame();
	}

	/**
	 * Method that deals with the menu options.
	 * @param event
	 *        The event that triggered this method.
	 */
	public void actionPerformed(ActionEvent event)
	{
		// If the new option is selected, the board is reset and a new game
		// begins.
		if (event.getSource() == newOption)
			newGame();

		// Closes the game screen if the exit option is selected.
		else if (event.getSource() == exitOption)
			System.exit(0);

		// Displays the instructions if the instruction option is selected.
		else if (event.getSource() == instructionOption)
		{
			JOptionPane
					.showMessageDialog(
							this,
							"Select a square to place your"
									+ " piece.\nBoth players will take turns by placing one piece at"
									+ " a time.\nFirst player to make a row of 5 pieces either vertically,"
									+ " horizontally, or diagonally wins!",
							" Instructions ", JOptionPane.INFORMATION_MESSAGE);
		}

		// Displays copyright information if the about option is selected.
		else if (event.getSource() == aboutOption)
		{
			JOptionPane.showMessageDialog(this, "By: Aaron Pang, Brandon Ma,"
					+ " and Sina Jamalian \u00A9 2010", "About",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Starts a new game
	 */
	public void newGame()
	{
		// Clears the board.
		board.clearBoard();

		// Loads the image for the first player's game piece.
		pieceOne = new ImageIcon("firstPiece.gif").getImage();
		// Loads the image for the second player's game piece.
		pieceTwo = new ImageIcon("secondPiece.gif").getImage();
		// Loads the image for the board.
		boardImage = new ImageIcon("boardImage.gif").getImage();

		// Sets the current player to be player one.
		currentPlayer = 2;

		// Computer makes his first move here.
		board.setPiece(startx, starty, 1);

		// Sets gameOver to be false to start a new game.
		gameOver = false;

		// Redraws the board.
		repaint();
	}

	// Creates, draws, and responds to mouse and keyboard events in this
	// drawing panel.
	private class DrawingPanel extends JPanel
	{
		/**
		 * Constructs a new DrawingPanel object
		 */
		public DrawingPanel(Dimension size)
		{
			// Makes the screen size 600 by 600.
			setPreferredSize(size);
			setBackground(Color.white);

			// Adds mouse listeners and Key Listeners to the drawing panel.
			this.addMouseListener(new MouseHandler());
			this.setFocusable(true);

			// this.addKeyListener(new KeyHandler());
			this.requestFocusInWindow();
		}

		/**
		 * Repaint the drawing panel.
		 * @param g
		 *        The Graphics context.
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			// Draws the board.
			g.drawImage(boardImage, 0, 0, this);

			// Redraws the board with the current pieces.
			for (int row = 1; row <= 15; row++)
			{
				for (int column = 1; column <= 15; column++)
				{
					// Finds the x and y positions for each row and column.
					int xPos = (column * 40) - 40;
					int yPos = (row * 40) - 40;

					// Draws each piece depending on the value on the board.
					if (board.getValue(row, column) == 1)
						g.drawImage(pieceOne, xPos, yPos, this);
					else if (board.getValue(row, column) == 2)
						g.drawImage(pieceTwo, xPos, yPos, this);
				}
			}
		} // Paint Component Method
	}

	// Inner class to handle mouse events.
	private class MouseHandler extends MouseAdapter
	{
		/**
		 * Responds to a mousePressed event
		 * @param event
		 *        Information about the mouse pressed event.
		 */
		public void mousePressed(MouseEvent event)
		{
			// The selected row and column determined by the mouse click.
			selectedRow = (event.getY() / 40) + 1;
			selectedColumn = (event.getX() / 40) + 1;

			// Makes the move if the game is not over.
			if (gameOver == false)
				makeMove(selectedRow, selectedColumn);
		}
	}

	/**
	 * Computer makes their move
	 */
	public void computerTurn()
	{
		int row = 0;
		int column = 0;
		do
		{
			row = (int) (Math.random() * 5) + 1;
			column = (int) (Math.random() * 5) + 1;
		}
		while (board.getValue(row, column) != 0);

		board.setPiece(row, column, 1);

		currentPlayer = 2;
		checkBoard(row, column);
	}

	/**
	 * Checks the board for a winner or tie
	 * @param row
	 *        the last row placed on
	 * @param column
	 *        the last column placed
	 */
	public void checkBoard(int row, int column)
	{
		// Displays "Game Over" and the player that wins if a player makes a
		// row
		// of 5.
		if (board.checkForWinner(row, column) == true)
		{
			gameOver = true;

			if (currentPlayer == 2)
				JOptionPane.showMessageDialog(this, "Player 1 Wins!",
						"Game Over", JOptionPane.WARNING_MESSAGE);
			else if (currentPlayer == 1)
				JOptionPane.showMessageDialog(this, "Player 2 Wins!",
						"Game Over", JOptionPane.WARNING_MESSAGE);
		}

		// Displays "Game Over" and displays that it is a tie if there are
		// no
		// more empty spaces.
		if (board.isEmpty() == false)
		{
			gameOver = true;

			JOptionPane.showMessageDialog(this, "It's a tie!", "Game Over",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Makes a move on the board if possible.
	 * @param row
	 *        The row the player wants to move the piece to.
	 * @param column
	 *        The column the player wants to move the piece to.
	 */
	public void makeMove(int row, int column)
	{
		// Places the piece if the selected space is empty.
		if (board.getValue(row, column) == 0)
		{
			// Places the piece on the selected space.
			board.setPiece(row, column, currentPlayer);

			currentPlayer = 1;

			// Redraws the board and pieces.
			repaint();
			checkBoard(row, column);

			if (!gameOver)
			{
				computerTurn();
			}
		}
	}

	public static void main(String[] args)
	{
		ConnectFive frame = new ConnectFive();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

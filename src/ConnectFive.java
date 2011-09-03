import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ConnectFive game.
 * @authors Aaron Pang, Brandon Ma, Sina Jamalian
 * @version June 4, 2010
 */

public class ConnectFive extends JFrame implements ActionListener
{
	// 1. Computer makes 5 in a row
	// 2. Computer blocks my 4 in a row
	// 3. Computer makes 4 in a row with wins on two sides
	// 4. Computer blocks my 3 in a row with possible wins on two sides
	//
	// 5... Assign point values to these moves and do the move with the highest
	// overall point value.
	// But those 4 should occur in that order no matter what otherwise the
	// computer will not make the best move.

	// Variables to keep track of the players.
	final int playerOne = 1;

	final int playerTwo = 2;

	// The number of rows and columns for the board.
	final int NO_OF_ROWS = 15;

	final int NO_OF_COLUMNS = 15;

	// Board object to keep track of the board.
	Board board;

	// Variables for the menu.
	private JMenuItem newOption, exitOption, singleOption, multiOption,
			easyOption, intermediateOption, hardOption, smileyOption,
			oceanOption, desertOption, medievalOption, fruitOption,
			instructionOption, aboutOption, soundOnOption, soundOffOption;

	// Variables for the images.
	private Image pieceOne, pieceTwo, boardImage, playerOneTurn, playerTwoTurn,
			computerTurn;

	private DrawingPanel drawingArea;

	// Variable to keep track of the current player.
	private int currentPlayer;

	// Variables for the selected row and column.
	private int selectedRow;

	private int selectedColumn;

	// Variables to keep track of whether or not the game is over.
	private boolean gameOver;

	// Variables to keep track of the sound
	private boolean sound = true;

	// Keeps track of the selected mode. 1 for Single Player, 2 for Multiplayer.
	private int selectedMode;

	// Keeps track of the selected mode option for the current game.
	private int mode;

	// Keeps track of the selected difficulty. 1 for Easy, 2 for Intermediate, 3
	// for Hard.
	private int selectedDifficulty;

	// Keeps track of the selected difficulty option for the current game.
	private int difficulty;

	// Keeps track of the selected theme. 1 for Smiley, 2 for Ocean, 3 for
	// Desert,
	private int selectedTheme;

	// Keeps track of the selected theme option for the current game.
	private int theme;

	// Keeps track of the audio files used in the game
	private AudioClip bgMusic, playerOnePiece, playerTwoPiece, gameOverSound;

	/**
	 * Constructs a new Checkers frame and sets up the game.
	 */
	public ConnectFive()
	{
		bgMusic = Applet.newAudioClip(getCompleteURL("ssbm1.wav"));
		playerOnePiece = Applet.newAudioClip(getCompleteURL("playerOne.wav"));
		playerTwoPiece = Applet.newAudioClip(getCompleteURL("playerTwo.wav"));
		gameOverSound = Applet.newAudioClip(getCompleteURL("ff7victory1.wav"));
		bgMusic.loop();
		// Sets values for the array which represents the board.
		board = new Board(NO_OF_ROWS, NO_OF_COLUMNS);

		// Sets up the screen.
		Container contentPane = getContentPane();
		Dimension size = new Dimension(600, 650);
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

		// Set up the Mode MenuItems.
		ButtonGroup group1 = new ButtonGroup();

		singleOption = new JRadioButtonMenuItem("Single Player");
		singleOption.setSelected(true);
		group1.add(singleOption);
		singleOption.addActionListener(this);

		multiOption = new JRadioButtonMenuItem("Multiplayer");
		;
		group1.add(multiOption);
		multiOption.addActionListener(this);

		// Set up the Difficulty MenuItems.
		ButtonGroup group2 = new ButtonGroup();

		easyOption = new JRadioButtonMenuItem("Easy");
		easyOption.setSelected(true);
		group2.add(easyOption);
		easyOption.addActionListener(this);

		intermediateOption = new JRadioButtonMenuItem("Intermediate");
		group2.add(intermediateOption);
		intermediateOption.addActionListener(this);

		hardOption = new JRadioButtonMenuItem("Hard");
		group2.add(hardOption);
		hardOption.addActionListener(this);

		// Sets up the Theme MenuItems.
		ButtonGroup group3 = new ButtonGroup();

		smileyOption = new JRadioButtonMenuItem("Smiley");
		smileyOption.setSelected(true);
		group3.add(smileyOption);
		smileyOption.addActionListener(this);

		oceanOption = new JRadioButtonMenuItem("Ocean");
		group3.add(oceanOption);
		oceanOption.addActionListener(this);

		desertOption = new JRadioButtonMenuItem("Desert");
		group3.add(desertOption);
		desertOption.addActionListener(this);

		medievalOption = new JRadioButtonMenuItem("Medieval");
		group3.add(medievalOption);
		medievalOption.addActionListener(this);

		fruitOption = new JRadioButtonMenuItem("Fruit");
		group3.add(fruitOption);
		fruitOption.addActionListener(this);

		// Sets up the Sound Items
		ButtonGroup group4 = new ButtonGroup();

		soundOnOption = new JRadioButtonMenuItem("On");
		soundOnOption.setSelected(true);
		group4.add(soundOnOption);
		soundOnOption.addActionListener(this);

		soundOffOption = new JRadioButtonMenuItem("Off");
		group4.add(soundOffOption);
		soundOffOption.addActionListener(this);

		// Sets up the Help MenuItems.
		instructionOption = new JMenuItem("Instructions");
		instructionOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_MASK));
		instructionOption.addActionListener(this);

		aboutOption = new JMenuItem("About");
		aboutOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		aboutOption.addActionListener(this);

		// Sets up the Game, Mode, Difficulty, Theme and Help Menus.
		JMenu gameMenu = new JMenu("Game");
		JMenu modeMenu = new JMenu("Mode");
		JMenu soundMenu = new JMenu("Sound");
		JMenu difficultyMenu = new JMenu("Difficulty");
		JMenu themeMenu = new JMenu("Theme");
		JMenu helpMenu = new JMenu("Help");

		// Add each MenuItem to the Game Menu (with a separator).
		gameMenu.add(newOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);

		// Add each MenuItem to the Mode Menu (with a separator).
		modeMenu.add(singleOption);
		modeMenu.addSeparator();
		modeMenu.add(multiOption);

		// Add each sound item to the sound Menu (with a separator).
		soundMenu.add(soundOnOption);
		soundMenu.add(soundOffOption);

		// Add each MenuItem to the Difficulty Menu (with a separator).
		difficultyMenu.add(easyOption);
		difficultyMenu.addSeparator();
		difficultyMenu.add(intermediateOption);
		difficultyMenu.addSeparator();
		difficultyMenu.add(hardOption);

		// Add each MenuItem to the Theme Menu (with a separator).
		themeMenu.add(smileyOption);
		themeMenu.addSeparator();
		themeMenu.add(oceanOption);
		themeMenu.addSeparator();
		themeMenu.add(desertOption);
		themeMenu.addSeparator();
		themeMenu.add(medievalOption);
		themeMenu.addSeparator();
		themeMenu.add(fruitOption);

		// Add each MenuItem to the Help Menu (with a separator).
		helpMenu.add(instructionOption);
		helpMenu.addSeparator();
		helpMenu.add(aboutOption);

		// Adds the GameMenu, ModeMenu, DifficultyMenu, ThemeMenu and HelpMenu
		// to the JMenuBar.
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		mainMenu.add(modeMenu);
		mainMenu.add(difficultyMenu);
		mainMenu.add(themeMenu);
		mainMenu.add(soundMenu);
		mainMenu.add(helpMenu);

		// Set the menu bar for this frame to mainMenu.
		setJMenuBar(mainMenu);

		// Initialises the selected mode option as single player.
		selectedMode = 1;

		// Initialises the selected difficulty option as easy.
		selectedDifficulty = 1;

		// Initialises the selected theme option as ocean.
		selectedTheme = 1;

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
		{
			newGame();
		}

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
			JOptionPane.showMessageDialog(this, "By: Aaron Pang, Brandon Ma"
					+ " and Sina Jamalian \u00A9 2010", "About",
					JOptionPane.INFORMATION_MESSAGE);
		}

		// Single player option.
		else if (event.getSource() == singleOption)
		{
			selectedMode = 1;
			newGame();
		}

		// Multiplayer option.
		else if (event.getSource() == multiOption)
		{
			selectedMode = 2;
			newGame();
		}

		// Easy difficulty option.
		else if (event.getSource() == easyOption)
		{
			selectedDifficulty = 1;
		}

		// Intermediate difficulty option.
		else if (event.getSource() == intermediateOption)
		{
			selectedDifficulty = 2;
		}

		// Hard difficulty option.
		else if (event.getSource() == hardOption)
		{
			selectedDifficulty = 3;
		}

		// Smiley theme option.
		else if (event.getSource() == smileyOption)
		{
			selectedTheme = 1;
		}

		// Ocean theme option.
		else if (event.getSource() == oceanOption)
		{
			selectedTheme = 2;
		}

		// Desert theme option.
		else if (event.getSource() == desertOption)
		{
			selectedTheme = 3;
		}

		// Medieval theme option.
		else if (event.getSource() == medievalOption)
		{
			selectedTheme = 4;
		}

		// Fruit theme option.
		else if (event.getSource() == fruitOption)
		{
			selectedTheme = 5;
		}
		// Sound On Option
		else if (event.getSource() == soundOnOption)
		{
			sound = true;
			bgMusic.loop();
		}
		else if (event.getSource() == soundOffOption)
		{
			sound = false;
			bgMusic.stop();
		}
	}

	/**
	 * Starts a new game
	 */
	public void newGame()
	{
		if (sound)
		{
			// Stop the current music and play it again
			gameOverSound.stop();
			bgMusic.stop();
			bgMusic.loop();
		}
		// Clears the board.
		board.clearBoard();

		// Keeps track of the mode, difficulty, and theme of the current game.
		mode = selectedMode;
		difficulty = selectedDifficulty;
		theme = selectedTheme;

		// Loads the image for the first player's game piece.
		pieceOne = new ImageIcon("pieceOne" + theme + ".png").getImage();
		// Loads the image for the second player's game piece.
		pieceTwo = new ImageIcon("pieceTwo" + theme + ".png").getImage();
		// Loads the image for the board.
		boardImage = new ImageIcon("board" + theme + ".png").getImage();
		// Loads the image for player one's turn.
		playerOneTurn = new ImageIcon("playerone.png").getImage();
		// Loads the image for player two's turn.
		playerTwoTurn = new ImageIcon("playertwo.png").getImage();
		// Loads the image for computer player's turn.
		computerTurn = new ImageIcon("computer.png").getImage();

		// Sets gameOver to be false to start a new game.
		gameOver = false;

		// Computer places a piece if in single player mode.
		if (mode == 1)
		{
			board.setPiece(7, 7, 1);
			currentPlayer = 2;
		}
		// Sets the current player to be player one if in multiplayer mode.
		else
		{
			currentPlayer = 1;
		}

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
			g.drawImage(boardImage, 0, 50, this);

			// Redraws the board with the current pieces.
			for (int row = 1; row <= 15; row++)
			{
				for (int column = 1; column <= 15; column++)
				{
					// Finds the x and y positions for each row and column.
					int xPos = (column * 40) - 40;
					int yPos = (row * 40) + 10;

					// Draws each piece depending on the value on the board.
					if (board.getValue(row, column) == 1)
						g.drawImage(pieceOne, xPos, yPos, this);
					else if (board.getValue(row, column) == 2)
						g.drawImage(pieceTwo, xPos, yPos, this);
				}
			}

			// Displays whose turn it is.
			if (currentPlayer == 1 && mode == 2)
				g.drawImage(playerOneTurn, 0, 0, this);
			else if (currentPlayer == 2)
				g.drawImage(playerTwoTurn, 0, 0, this);
			else
				g.drawImage(computerTurn, 0, 0, this);

		} // Paint Component Method
	}

	/**
	 * Retrieves the URL for the file name
	 * @param fileName
	 *        The file name and type
	 * @return returns nothing to the user
	 */
	public URL getCompleteURL(String fileName)
	{
		try
		{
			return new URL("file:" + System.getProperty("user.dir") + "/"
					+ fileName);
		}

		catch (MalformedURLException e)
		{
			// System.err.println (e.getMessage ());
		}
		return null;
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
			selectedRow = (event.getY() - 50) / 40 + 1;
			selectedColumn = event.getX() / 40 + 1;

			// Makes the move if the game is not over.
			if (gameOver == false && event.getY() > 50)
				makeMove(selectedRow, selectedColumn);
		}
	}

	/**
	 * Computer makes its move
	 */
	public void computerTurn()
	{
		if (sound)
			playerTwoPiece.play();

		// Goes through each square in the grid to check whether there is a spot
		// to block or not

		// Every time you check a square, keep a counter on the number of
		// possible blocks and whatnot.
		// There are going to be 4 main types of expansion blocks:
		// Blocking 1 in a row ...... X_
		// Blocking 2 in a row ...... XX_
		// .......................... _XX
		// Blocking 3 in a row ...... XXX_
		// .......................... XX_X
		// .......................... X_XX
		// .......................... _XXX
		// Blocking 4 in a row ...... XXXX_
		// .......................... XXX_X
		// .......................... XX_XX
		// .......................... X_XXX
		// .......................... _XXXX

		// Each blocking style will be worth different points. Blocking
		// 1 = 1 point. 2 = 2 points. 3 = 9 points. 4 = 50 points.
		// This is because 1 block = 4 points total. 1 per
		// ............... 2 block = 12 points total. 2 per
		// ............... 3 block = 232 points total. 30 per / 50 per
		// ............... 4 block = 732 points total. 150 per
		// At a Level 3 difficulty, a 3 block gains 25 more points if there are
		// no walls (meaning there is an empty space) at the two ends of a
		// 3-chain. This is because no walls allows the player to gain a 2-turn
		// win by creating a non-walled 4-chain.

		// The number of points also include (from the 2 block and on)
		// the number of points gained from the previous # blocks as
		// well. Therefore, if you have 4 3-blocks at the end (XXX_),
		// then this also includes 4 2-blocks at the end (XX_) and 4
		// 1-blocks.

		//

		// Each time you check a new square, a similar method such as
		// the checkForWinner() method will be called. Using this
		// method, we check all the surrounding blocks to calculate the
		// number of points in that square. The higher the points, the
		// greater the risk potential.
		//
		// A 4-block will always have the first priority, thus its point
		// value is greater than the max number of 3-block points.
		// A 3-block is also important to block, as it can lead up to a
		// 2-way 4-block, which is then impossible to block. Therefore,
		// a single 3-block is worth more than the total 2-block points.

		// There should also be a secondary point tracker. This is
		// because for the 3 and 4-block, there are multiple ways to
		// block. < MAYBE NOT SURE IF IT WORKS

		// Random number from 1 to 10.
		// If the random number is 10 and the mode is Easy, the computer plays a
		// random move.
		int randomNumber = (int) (Math.random() * 10) + 1;

		if (difficulty != 1 || randomNumber != 10)
		{
			int[] position = board.AIchecker(difficulty);
			// System.out.println();
			board.AIdebugger(); // Checks the point board value
			System.out.println("\n\nBOARD DEBUGGEREEEEEEEE");
			// board.boardDebugger();
			// board.debugBoarder();

			int row = position[0];
			int column = position[1];

			// The computer places a piece on the board.
			System.out.println("\n\nrow : " + row + "  colulm " + column);

			board.setPiece(row, column, 1);

			// Checks for a win or tie.
			checkBoard(row, column);
		}
		else
		{
			int randomRow;
			int randomColumn;

			// Variable to keep track of whether or not the move is valid.
			boolean isValid = false;

			do
			{
				randomRow = (int) (Math.random() * 15) + 1;
				randomColumn = (int) (Math.random() * 15) + 1;

				if (board.getValue(randomRow, randomColumn) == 0)
				{
					board.setPiece(randomRow, randomColumn, 1);
					isValid = true;
				}
			}
			while (isValid == false);

			// Checks for a win or tie.
			checkBoard(randomRow, randomColumn);
		}

		// Changes over to the human player.
		if (!gameOver)
			currentPlayer = 2;

		// Redraws the board and pieces.
		repaint();

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
		// row of 5.
		if (board.checkForWinner(row, column) == true)
		{
			if (sound)
			{
				bgMusic.stop();
				gameOverSound.play();
			}
			gameOver = true;

			if (currentPlayer == 1 && mode == 2)
				JOptionPane.showMessageDialog(this, "Player 1 Wins!",
						"Game Over", JOptionPane.WARNING_MESSAGE);
			else if (currentPlayer == 2)
				JOptionPane.showMessageDialog(this, "Player 2 Wins!",
						"Game Over", JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Computer Wins!",
						"Game Over", JOptionPane.WARNING_MESSAGE);
		}

		// Displays "Game Over" and displays that it is a tie if there are
		// no more empty spaces.
		if (board.isEmpty() == false)
		{
			if (sound)
			{
				bgMusic.stop();
				gameOverSound.play();
			}
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

			// Redraws the board and pieces.
			repaint();

			// Checks for a win or tie.
			checkBoard(row, column);

			// Changes the player's turn.
			if (currentPlayer == 1)
			{
				if (sound)
					playerTwoPiece.play();
				currentPlayer = 2;
			}
			else
			{
				if (sound)
					playerOnePiece.play();
				currentPlayer = 1;
			}

			// AI plays if in Single Player mode.
			if (!gameOver && mode == 1)
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

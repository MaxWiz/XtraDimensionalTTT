import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Code base taken from Yashika Jain at https://www.codespeedy.com/tic-tac-toe-game-using-java-swing-in-java/
//and then modified by Maxwell Wisnieski
public class gen2Dgame implements ActionListener {
  
	String winner;
	ComplexNumber[][] board = new ComplexNumber[3][3];
	int mode;
	int negFlag = 1;
	boolean complexFlag = false;
	boolean rotFlag = false;
	boolean validFlag = false;
	
    JFrame frame = new JFrame(); // The frame that holds the game
    JPanel t_panel = new JPanel(); // The panel holding the top text I think?
    JPanel bt_panel = new JPanel(); // The panel holding the game squares
    JLabel textfield = new JLabel(); // The text field up top
    JButton[] bton = new JButton[9]; // An array that will contain the buttons used to play
    int tieFlag = 0; // A Tie determining variable
    Random random = new Random(); // Used for the random determination of the starting player
    boolean pl1_chance; // A variable used to determine the starting player, and swap the turn player back and forth in the actionPerformed method
    
    public gen2Dgame(int mode) {
    	this.mode = mode;
    	if (mode == 1) {
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board.length; j++) {
    				ComplexNumber c = new ComplexNumber(1, 0);
        			board[i][j] = c;
    			}
    		}
    		complexFlag = true;
    	}
    	else {
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board.length; j++) {
    				ComplexNumber c = new ComplexNumber(0, 0);
        			board[i][j] = c;
    			}
    		}
    	}
    	if (mode == 2) {
    		negFlag = -1;
    	}
    	if (mode == 3) {
    		rotFlag = true;
    	}
    	// Setup for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes it exit on close
        frame.setSize(900, 900); // Sets the window size
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setTitle("Xtra Dimensional Tic-Tac-Toe"); // Sets window name
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        textfield.setBackground(new Color(0, 0, 0));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("Plain", Font.PLAIN, 60));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic Tac Toe");
        textfield.setOpaque(true);
        
        t_panel.setLayout(new BorderLayout());
        t_panel.setBounds(0, 0, 800, 100);
        
        bt_panel.setLayout(new GridLayout(3, 3)); // Sets the grid layout
        bt_panel.setBackground(new Color(150, 150, 150)); // Does nothing, probably not visible
        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton();
            bt_panel.add(bton[i]);
            bton[i].setFont(new Font("Plain", Font.BOLD, 120));
            bton[i].setFocusable(false); // No clue
            bton[i].addActionListener(this); // Allows it to be interactable
        }
        
        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        frame.add(bt_panel);
        startGame(); // The game is started in the constructor for this class
    }
    
    public void startGame() {
        try { // Presumably to protect from problems?  Maybe unneeded?
            textfield.setText("Loading");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        int chance=random.nextInt(100); // Randomly determines the starting player
        if (chance%2 == 0) {
            pl1_chance = true;
            textfield.setText("Player 1 turn");
        } else {
            pl1_chance = false;
            textfield.setText("Player 2 turn");
        }
    }
    
    // Called when the game is over, displays the relevant information and allows the game to be restarted
    // Little need for adjustment
    public void gameOver(String s){
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board.length; j++) {
    			System.out.print(board[i] + ", ");
        		board[i][j].real = 0;
        		board[i][j].im = 0;
    		}
    	}
        tieFlag = 0;
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game Over\n"+s,"Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
            frame.dispose();
            new gen2Dgame(this.mode);
        }
        else{
            frame.dispose();
            new mainMenu();
        }
    }
    
    public boolean inBounds(int x, int y) {
    	boolean ret = (x >= 0) && (y >= 0) && (x < board[0].length) && (y < board[0].length);
    	return ret;
    }
    
//	System.out.println("(" + x + ", " + y +"): " + board[x][y].toString() + ", checking (" + (x+i) + ", " + (y+j) + ")");
//	System.out.println("Found: " + (x+i) + ", " + (y+j));
    public void matchCheck(int x, int y) {
    	for (int i = -1; i <= 1; i++) {
    		for (int j = -1; j <= 1; j++) {
    			if (inBounds(x+i, y+j) && (board[x+i][y+j].equals(board[x][y])) && !(i == 0 && j == 0)) {

    				if (inBounds(x+2*i, y+2*j) && board[x+2*i][y+2*j].equals(board[x][y])) {
    					if (board[x][y].im > 0.9) {
    						xWins(3*(x)+y, 3*(x+i)+y+j, 3*(x+2*i)+y+2*j);
    					}
    					if (board[x][y].im < -0.9) {
    						oWins(3*(x)+y, 3*(x+i)+y+j, 3*(x+2*i)+y+2*j);
    					}
    				}
    				if (inBounds(x-i, y-j) && board[x-i][y-j].equals(board[x][y])) {
    					if (board[x][y].im > 0.9) {
    						xWins(3*(x-i)+y-j, 3*(x)+y, 3*(x+i)+y+j);
    					}
    					if (board[x][y].im < -0.9) {
    						oWins(3*(x-i)+y-j, 3*(x)+y, 3*(x+i)+y+j);
    					}
    				}
    			}
    		}
    	}
    	if(tieFlag==9 && !complexFlag) {
    		winner = "";
            textfield.setText("It's a Tie!");
             gameOver("It's a Tie!");
        }
    }
    
    // Method controlling what happens if a player wins, uses arguments to highlight the relevant squares
    public void xWins(int x1, int x2, int x3) {
        bton[x1].setBackground(Color.RED);
        bton[x2].setBackground(Color.RED);
        bton[x3].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) {
            bton[i].setEnabled(false);
        }
        textfield.setText("Player 1 wins");
        winner = "P1";
        gameOver("Player 1 Wins");
    }
    
    // Method controlling what happens if a player wins, see above
    public void oWins(int x1, int x2, int x3) {
        bton[x1].setBackground(Color.RED);
        bton[x2].setBackground(Color.RED);
        bton[x3].setBackground(Color.RED);
        for (int i = 0; i < 9; i++) {
            bton[i].setEnabled(false);
        }
        textfield.setText("Player 2 Wins");
        winner = "P2";
        gameOver("Player 2 Wins");
    }
    
    public boolean flip(boolean b) {
    	boolean ret = b;
    	if (b == true) {
    		ret = false;
    	}
    	else if (b == false) {
    		ret = true;
    	}
    	return ret;
    }
    
    public void complexDisplay(int i) {
//    	System.out.println("a: " + a + ", b: " + b + ", d: " + d + ", e: " + e);
    	ComplexNumber c = board[Math.floorDiv(i, 3)][i%3];
    	int re = (int) Math.round(c.real);
    	int im = (int) Math.round(c.im);
    	if (re == 1 && im == 0) {
    		bton[i].setText("");
    	}
    	if (re == 0 && im == 1) {
    		bton[i].setForeground(new Color(255, 0, 0));
    		bton[i].setText("X");
    	}
    	if (re == -1 && im == 1) {
    		bton[i].setForeground(new Color(255, 0, 0));
    		bton[i].setText("i");
    	}
    	if (re == -1 && im == -1) {
    		bton[i].setForeground(new Color(0, 0, 255));
    		bton[i].setText("-i");
    	}
    	if (re == 0 && im == -1) {
    		bton[i].setForeground(new Color(0, 0, 255));
    		bton[i].setText("O");
    	}
    }
    
    public void regDisplay(int i) {
    	if (board[Math.floorDiv(i, 3)][i%3].im == 0) {
    		bton[i].setText("");
    	}
    	else if(board[Math.floorDiv(i, 3)][i%3].im == 1) {
    		bton[i].setForeground(new Color(255, 0, 0)); // Color of the piece
            bton[i].setText("X");
    	}
    	else if(board[Math.floorDiv(i, 3)][i%3].im == -1) {
    		bton[i].setForeground(new Color(0, 0, 255));
            bton[i].setText("O");
    	}
    }
    
    public void rotateBoard() {
    	ComplexNumber[][] bC = new ComplexNumber[3][3];
    	for (int j = 0; j < bC.length; j++) {
    		for (int k = 0; k < bC.length; k++) {
    			bC[j][k] = new ComplexNumber(0, 0);
    		}
    	}
    	bC[0][0] = board[0][1];
    	bC[0][1] = board[0][2];
    	bC[0][2] = board[1][2];
    	bC[1][2] = board[2][2];
    	bC[2][2] = board[2][1];
    	bC[2][1] = board[2][0];
    	bC[2][0] = board[1][0];
    	bC[1][0] = board[0][0];
    	bC[1][1] = board[1][1];
    	board = bC;
    	for (int i = 0; i < board.length*board.length; i++) {
    		regDisplay(i);
		}
    	printBoard();
    }
    
    public void checkFull() {
    	boolean ret = true;
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board.length; j++) {
    			if (board[i][j].im == 0) {
        			ret = false;
        		}
    		}
    	}
    	if (ret == true) {
    		textfield.setText("It's a Tie!");
            gameOver("It's a Tie!");
    	}
    }
    
    public void printBoard() {
    	for(int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board.length; j++)  {
    			System.out.print(board[i][j].im + ", ");
    		}
    		System.out.print("\n");
    	}
    	System.out.println();
    }
    
    // Controls the functionality of the buttons, also determines which player is making moves
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == bton[i]) { // Gets the relevant button
            	if (complexFlag == false && board[Math.floorDiv(i, 3)][i%3].im == 0) {
            		validFlag = true;
//            		System.out.println(board[Math.floorDiv(i, 3)][i%3]);
            		int mult = 1;
            		if (!pl1_chance) {
            			mult = -1;
            		}
            		ComplexNumber c = new ComplexNumber(0, negFlag*mult*1);
            		board[Math.floorDiv(i, 3)][i%3] = c;
            		printBoard();
            		regDisplay(i);
            		if (pl1_chance) {
                		textfield.setText("Player 2 turn");
                	}
                	else {
                		textfield.setText("Player 1 turn");
                	}
            		pl1_chance = flip(pl1_chance);
            		tieFlag++;
            		if (!rotFlag) {
            			matchCheck(Math.floorDiv(i, 3),i%3);
            		}
                }
            	else if (complexFlag == true) {
                	double a = negFlag*(Math.sqrt(5)-1)/4;
                	int mult = 1;
                	if (!pl1_chance) {
                		mult = -1;
                	}
                	double b = negFlag*mult*(Math.sqrt(10+2*Math.sqrt(5)))/4;
                	ComplexNumber c = new ComplexNumber(a, b);
//                	System.out.println("C: " + c.toString());
                	board[Math.floorDiv(i, 3)][i%3] = board[Math.floorDiv(i, 3)][i%3].multiply(c);
//                	System.out.println("Mult: " + board[i].toString());
                	complexDisplay(i);
                	if (pl1_chance) {
                		textfield.setText("Player 2 turn");
                	}
                	else {
                		textfield.setText("Player 1 turn");
                	}
                	pl1_chance = flip(pl1_chance);
                	matchCheck(Math.floorDiv(i, 3),i%3);
                }
            }
        }
        if (rotFlag == true && validFlag == true) {
			rotateBoard();
			for (int a = 0; a < board.length; a++) {
				for (int b = 0; b < board.length; b++) {
					matchCheck(a, b);
				}
			}
			checkFull();
			validFlag = flip(validFlag);
		}
    }
}
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Code base taken from Yashika Jain at https://www.codespeedy.com/tic-tac-toe-game-using-java-swing-in-java/
//and then modified by Maxwell Wisnieski
public class gen3Dgame implements ActionListener {
  
	String winner;
	ComplexNumber[][][] board = new ComplexNumber[3][3][3];
	int mode;
	int negFlag = 1;
	boolean complexFlag = false;
	boolean rotFlag = false;
	boolean validFlag = false;
	
    JFrame frame = new JFrame(); // The frame that holds the game
    JPanel t_panel = new JPanel(); // The panel holding the top text I think?
    JPanel z0Panel = new JPanel();
    JPanel z1Panel = new JPanel();
    JPanel z2Panel = new JPanel();
    JPanel holderPanel = new JPanel();
    JLabel textfield = new JLabel(); // The text field up top
    JButton[] bton = new JButton[27]; // An array that will contain the buttons used to play
    int tieFlag = 0; // A Tie determining variable
    Random random = new Random(); // Used for the random determination of the starting player
    boolean pl1_chance; // A variable used to determine the starting player, and swap the turn player back and forth in the actionPerformed method
    
    public gen3Dgame(int mode) {
    	this.mode = mode;
    	if (mode == 1) {
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board.length; j++) {
    				for (int k = 0; k < board.length; k++) {
    					ComplexNumber c = new ComplexNumber(1, 0);
            			board[i][j][k] = c;
    				}
    			}
    		}
    		complexFlag = true;
    	}
    	else {
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board.length; j++) {
    				for (int k = 0; k < board.length; k++) {
    					ComplexNumber c = new ComplexNumber(0, 0);
            			board[i][j][k] = c;
    				}
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
        frame.setSize(1800, 900); // Sets the window size
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
        
        holderPanel.setLayout(new GridLayout(2, 3, 100, 50));
        
        JLabel z0Text = new JLabel();
        z0Text.setBackground(new Color(255, 255, 250));
        z0Text.setForeground(new Color(255, 0, 0));
        z0Text.setFont(new Font("Plain", Font.PLAIN, 30));
        z0Text.setHorizontalAlignment(JLabel.CENTER);
        z0Text.setText("z = 0");
        z0Text.setOpaque(true);
        holderPanel.add(z0Text);
        
        JLabel z1Text = new JLabel();
        z1Text.setBackground(new Color(255, 255, 250));
        z1Text.setForeground(new Color(255, 0, 0));
        z1Text.setFont(new Font("Plain", Font.PLAIN, 30));
        z1Text.setHorizontalAlignment(JLabel.CENTER);
        z1Text.setText("z = 1");
        z1Text.setOpaque(true);
        holderPanel.add(z1Text);
        
        JLabel z2Text = new JLabel();
        z2Text.setBackground(new Color(255, 255, 250));
        z2Text.setForeground(new Color(255, 0, 0));
        z2Text.setFont(new Font("Plain", Font.PLAIN, 30));
        z2Text.setHorizontalAlignment(JLabel.CENTER);
        z2Text.setText("z = 2");
        z2Text.setOpaque(true);
        holderPanel.add(z2Text);
        
        z0Panel.setLayout(new GridLayout(3, 3)); // Sets the grid layout
        z0Panel.setBackground(new Color(150, 150, 150));
        z1Panel.setLayout(new GridLayout(3, 3)); // Sets the grid layout
        z1Panel.setBackground(new Color(150, 150, 150));
        z2Panel.setLayout(new GridLayout(3, 3)); // Sets the grid layout
        z2Panel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton();
            z0Panel.add(bton[i]);
            bton[i].setFont(new Font("Plain", Font.BOLD, 120));
            bton[i].setFocusable(false); // No clue
            bton[i].addActionListener(this); // Allows it to be interactable
        }
        for (int j = 9; j < 18; j++) {
            bton[j] = new JButton();
            z1Panel.add(bton[j]);
            bton[j].setFont(new Font("Plain", Font.BOLD, 120));
            bton[j].setFocusable(false); // No clue
            bton[j].addActionListener(this); // Allows it to be interactable
        }
        for (int k = 18; k < 27; k++) {
            bton[k] = new JButton();
            z2Panel.add(bton[k]);
            bton[k].setFont(new Font("Plain", Font.BOLD, 120));
            bton[k].setFocusable(false); // No clue
            bton[k].addActionListener(this); // Allows it to be interactable
        }
        
        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH);
        holderPanel.add(z0Panel);
        holderPanel.add(z1Panel);
        holderPanel.add(z2Panel);
        frame.add(holderPanel);
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
    			for (int k = 0; k < board.length; k++) {
    				System.out.print(board[i] + ", ");
            		board[i][j][k].real = 0;
            		board[i][j][k].im = 0;
    			}
    		}
    	}
        tieFlag = 0;
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game Over\n"+s,"Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
            frame.dispose();
            new gen3Dgame(this.mode);
        }
        else{
            frame.dispose();
            new mainMenu();
        }
    }
    
    public boolean inBounds(int x, int y, int z) {
    	boolean ret = (x >= 0) && (y >= 0) && (z >= 0) && (x < board[0].length) && (y < board[0].length) && (z < board[0].length);
    	return ret;
    }
    
    public void matchCheck(int x, int y, int z) {
    	for (int i = -1; i <= 1; i++) {
    		for (int j = -1; j <= 1; j++) {
    			for (int k = -1; k <= 1; k++) {
//    				System.out.println("(" + x + ", " + y +", " + z +"): " + board[x][y][z].toString() + ", checking (" + (x+i) + ", " + (y+j) + ", " + (z+k) +")");
        			if (inBounds(x+i, y+j, z+k) && (board[x+i][y+j][z+k].equals(board[x][y][z])) && !(i == 0 && j == 0 && k == 0)) {
//        				System.out.println("Found: " + (x+i) + ", " + (y+j) + ", " + (z+k));
        				if (inBounds(x+2*i, y+2*j, z+2*k) && board[x+2*i][y+2*j][z+2*k].equals(board[x][y][z])) {
        					if (board[x][y][z].im > 0.9) {
        						xWins(3*(x)+y+9*(z), 3*(x+i)+y+j+9*(z+k), 3*(x+2*i)+y+2*j+9*(z+2*k));
        					}
        					if (board[x][y][z].im < -0.9) {
        						oWins(3*(x)+y+9*(z), 3*(x+i)+y+j+9*(z+k), 3*(x+2*i)+y+2*j+9*(z+2*k));
        					}
        				}
        				if (inBounds(x-i, y-j, z-k) && board[x-i][y-j][z-k].equals(board[x][y][z])) {
        					if (board[x][y][z].im > 0.9) {
        						xWins(3*(x-i)+y-j+9*(z-k), 3*(x)+y+9*(z), 3*(x+i)+y+j+9*(z+k));
        					}
        					if (board[x][y][z].im < -0.9) {
        						oWins(3*(x-i)+y-j+9*(z-k), 3*(x)+y+9*(z), 3*(x+i)+y+j+9*(z+k));
        					}
        				}
        			}
    			}
    		}
    	}
    	if(tieFlag==Math.pow(board.length, 3) && !complexFlag) {
            textfield.setText("It's a Tie!");
             gameOver("It's a Tie!");
        }
    }
    
    // Method controlling what happens if a player wins, uses arguments to highlight the relevant squares
    public void xWins(int x1, int x2, int x3) {
        bton[x1].setBackground(Color.RED);
        bton[x2].setBackground(Color.RED);
        bton[x3].setBackground(Color.RED);
        for (int i = 0; i < 27; i++) {
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
        for (int i = 0; i < 27; i++) {
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
    	ComplexNumber c = board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)];
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
    	if (board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)].im == 0) {
    		bton[i].setText("");
    	}
    	else if(board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)].im == 1) {
    		bton[i].setForeground(new Color(255, 0, 0)); // Color of the piece
            bton[i].setText("X");
    	}
    	else if(board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)].im == -1) {
    		bton[i].setForeground(new Color(0, 0, 255));
            bton[i].setText("O");
    	}
    }
    
    public void rotateBoard(int z) {
    	ComplexNumber[][][] bC = new ComplexNumber[3][3][3];
    	for (int i = 0; i < bC.length; i++) {
    		for (int j = 0; j < bC.length; j++) {
    			for (int k = 0; k < bC.length; k++) {
    				bC[i][j][k] = new ComplexNumber(0, 0);
    				bC[i][j][k] = board[i][j][k];
    			}
    		}
    	}
    	bC[0][0][z] = board[0][1][z];
    	bC[0][1][z] = board[0][2][z];
    	bC[0][2][z] = board[1][2][z];
    	bC[1][2][z] = board[2][2][z];
    	bC[2][2][z] = board[2][1][z];
    	bC[2][1][z] = board[2][0][z];
    	bC[2][0][z] = board[1][0][z];
    	bC[1][0][z] = board[0][0][z];
    	bC[1][1][z] = board[1][1][z];
    	board = bC;
    	for (int a = 0; a < board.length*board.length*board.length; a++) {
    		regDisplay(a);
		}
    	printBoard();
    }
    
    public void checkFull() {
    	boolean ret = true;
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board.length; j++) {
    			for (int k = 0; k < board.length; k++) {
    				if (board[i][j][k].im == 0) {
            			ret = false;
            		}
    			}
    		}
    	}
    	if (ret == true) {
    		textfield.setText("It's a Tie!");
            gameOver("It's a Tie!");
    	}
    }
    
    public void printBoard() {
    	for(int k = 0; k < board.length; k++) {
    		for (int i = 0; i < board.length; i++)  {
    			for (int j = 0; j < board.length; j++) {
    				System.out.print(board[i][j][k].im + ", ");
    			}
    			System.out.print("\n");
    		}
    		System.out.print("\n");
    	}
    	System.out.println();
    }
    
    // Controls the functionality of the buttons, also determines which player is making moves
    @Override
    public void actionPerformed(ActionEvent e) {
    	int zPlace = 0;
        for (int i = 0; i < 27; i++) {
            if (e.getSource() == bton[i]) { // Gets the relevant button
            	zPlace = Math.floorDiv(i, 9);
            	System.out.println("Button: " + i + ", Z = " + zPlace);
            	if (complexFlag == false && board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)].im == 0) {
            		validFlag = true;
//            		System.out.println(board[Math.floorDiv(i, 3)][i%3]);
            		int mult = 1;
            		if (!pl1_chance) {
            			mult = -1;
            		}
            		ComplexNumber c = new ComplexNumber(0, negFlag*mult*1);
            		board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)] = c;
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
            			matchCheck(Math.floorDiv(i, 3)%3,i%3, Math.floorDiv(i, 9));
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
                	board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)] = board[Math.floorDiv(i, 3)%3][i%3][Math.floorDiv(i, 9)].multiply(c);
//                	System.out.println("Mult: " + board[i].toString());
                	complexDisplay(i);
                	if (pl1_chance) {
                		textfield.setText("Player 2 turn");
                	}
                	else {
                		textfield.setText("Player 1 turn");
                	}
                	pl1_chance = flip(pl1_chance);
                	matchCheck(Math.floorDiv(i, 3)%3, i%3, Math.floorDiv(1, 9));
                }
            }
        }
        if (rotFlag == true && validFlag == true) {
			rotateBoard(zPlace);
			for (int a = 0; a < board.length; a++) {
				for (int b = 0; b < board.length; b++) {
					for (int c = 0; c < board.length; c++) {
						matchCheck(a, b, c);
					}
				}
			}
			checkFull();
			validFlag = flip(validFlag);
		}
    }
}
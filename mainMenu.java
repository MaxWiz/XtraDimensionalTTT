import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class mainMenu implements ActionListener {
	int dim = 2;
	int mode = 0;
	
	JFrame frame = new JFrame();
	
    JPanel textPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel modePanel = new JPanel();
    JPanel startPanel = new JPanel();
    
    JLabel textField = new JLabel();
    
    ButtonGroup dimGroup = new ButtonGroup();
    JRadioButton[] dimButtons = new JRadioButton[4];
    
    ButtonGroup modeGroup = new ButtonGroup();
    JRadioButton[] modeButtons = new JRadioButton[4];
    
    JButton[] optionsButtons = new JButton[2];
    
    public mainMenu() {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.getContentPane().setBackground(new Color(255, 255, 250));
        frame.setTitle("Xtra Dimensional Tic-Tac-Toe");
        frame.setLayout(new GridLayout(4, 1, 0, 200));
        frame.setVisible(true);
        
        textField.setBackground(new Color(0, 0, 0));
        textField.setForeground(new Color(255, 0, 0));
        textField.setFont(new Font("Plain", Font.PLAIN, 60));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("XTRA Dimensional Tic-Tac-Toe");
        textField.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.setBounds(0, 0, 800, 50);
        textPanel.add(textField);
        
        JLabel dimText = new JLabel();
        dimText.setBackground(new Color(255, 255, 250));
        dimText.setForeground(new Color(255, 0, 0));
        dimText.setFont(new Font("Plain", Font.PLAIN, 30));
        dimText.setHorizontalAlignment(JLabel.CENTER);
        dimText.setText("Select Dimension: ");
        dimText.setOpaque(true);
        buttonPanel.add(dimText);
        
        JRadioButton ZeroDimButton = new JRadioButton("Zero");
        dimGroup.add(ZeroDimButton);
        buttonPanel.add(ZeroDimButton);
        ZeroDimButton.setFont(new Font("Plain", Font.BOLD, 30));
        ZeroDimButton.setFocusable(false);
        ZeroDimButton.setActionCommand("Zero");
        ZeroDimButton.addActionListener(this);
        dimButtons[0] = ZeroDimButton;
        
        JRadioButton OneDimButton = new JRadioButton("One");
        dimGroup.add(OneDimButton);
        buttonPanel.add(OneDimButton);
        OneDimButton.setFont(new Font("Plain", Font.BOLD, 30));
        OneDimButton.setFocusable(false);
        OneDimButton.setActionCommand("One");
        OneDimButton.addActionListener(this);
        dimButtons[1] = OneDimButton;
        
        JRadioButton TwoDimButton = new JRadioButton("Two");
        dimGroup.add(TwoDimButton);
        buttonPanel.add(TwoDimButton);
        TwoDimButton.setFont(new Font("Plain", Font.BOLD, 30));
        TwoDimButton.setFocusable(false);
        TwoDimButton.setActionCommand("Two");
        TwoDimButton.addActionListener(this);
        dimButtons[2] = TwoDimButton;
        
        JRadioButton ThreeDimButton = new JRadioButton("Three");
        dimGroup.add(ThreeDimButton);
        buttonPanel.add(ThreeDimButton);
        ThreeDimButton.setFont(new Font("Plain", Font.BOLD, 30));
        ThreeDimButton.setFocusable(false);
        ThreeDimButton.setActionCommand("Three");
        ThreeDimButton.addActionListener(this);
        dimButtons[3] = ThreeDimButton;
        
        JLabel modeText = new JLabel();
        modeText.setBackground(new Color(255, 255, 250));
        modeText.setForeground(new Color(255, 0, 0));
        modeText.setFont(new Font("Plain", Font.PLAIN, 30));
        modeText.setHorizontalAlignment(JLabel.LEFT);
        modeText.setText("Select Mode: ");
        modeText.setOpaque(true);
        modePanel.add(modeText);
        
        JRadioButton RegularButton = new JRadioButton("Regular");
        modeGroup.add(RegularButton);
        modePanel.add(RegularButton);
        RegularButton.setFont(new Font("Plain", Font.BOLD, 30));
        RegularButton.setFocusable(false);
        RegularButton.setActionCommand("Regular");
        RegularButton.addActionListener(this);
        modeButtons[0] = RegularButton;
        
        JRadioButton ImaginaryButton = new JRadioButton("Imaginary");
        modeGroup.add(ImaginaryButton);
        modePanel.add(ImaginaryButton);
        ImaginaryButton.setFont(new Font("Plain", Font.BOLD, 30));
        ImaginaryButton.setFocusable(false);
        ImaginaryButton.setActionCommand("Imaginary");
        ImaginaryButton.addActionListener(this);
        modeButtons[1] = ImaginaryButton;
        
        JRadioButton NegativeButton = new JRadioButton("Negative");
        modeGroup.add(NegativeButton);
        modePanel.add(NegativeButton);
        NegativeButton.setFont(new Font("Plain", Font.BOLD, 30));
        NegativeButton.setFocusable(false);
        NegativeButton.setActionCommand("Negative");
        NegativeButton.addActionListener(this);
        modeButtons[2] = NegativeButton;
        
        JRadioButton RotationButton = new JRadioButton("Rotation");
        modeGroup.add(RotationButton);
        modePanel.add(RotationButton);
        RotationButton.setFont(new Font("Plain", Font.BOLD, 30));
        RotationButton.setFocusable(false);
        RotationButton.setActionCommand("Rotation");
        RotationButton.addActionListener(this);
        modeButtons[3] = RotationButton;
        
        JLabel startText = new JLabel();
        startText.setBackground(new Color(255, 255, 250));
        startText.setForeground(new Color(255, 0, 0));
        startText.setFont(new Font("Plain", Font.PLAIN, 30));
        startText.setHorizontalAlignment(JLabel.LEFT);
        startText.setText("Start with current settings");
        startText.setOpaque(true);
        startPanel.add(startText);
        
        JButton StartButton = new JButton("Start!");
        startPanel.add(StartButton);
        StartButton.setFont(new Font("Plain", Font.BOLD, 30));
        StartButton.setFocusable(false);
        StartButton.setActionCommand("Start");
        StartButton.addActionListener(this);
        optionsButtons[0] = StartButton;
        
        JLabel spacerText = new JLabel();
        spacerText.setBackground(new Color(255, 255, 250));
        spacerText.setForeground(new Color(255, 0, 0));
        spacerText.setFont(new Font("Plain", Font.PLAIN, 30));
        spacerText.setHorizontalAlignment(JLabel.LEFT);
        spacerText.setText("                       ");
        spacerText.setOpaque(true);
        startPanel.add(spacerText);
        
        JLabel quitText = new JLabel();
        quitText.setBackground(new Color(255, 255, 250));
        quitText.setForeground(new Color(255, 0, 0));
        quitText.setFont(new Font("Plain", Font.PLAIN, 30));
        quitText.setHorizontalAlignment(JLabel.LEFT);
        quitText.setText("Quit");
        quitText.setOpaque(true);
        startPanel.add(quitText);
        
        JButton QuitButton = new JButton("Quit!");
        startPanel.add(QuitButton);
        QuitButton.setFont(new Font("Plain", Font.BOLD, 30));
        QuitButton.setFocusable(false);
        QuitButton.setActionCommand("Quit");
        QuitButton.addActionListener(this);
        optionsButtons[1] = QuitButton;
        
        buttonPanel.setBackground(new Color(255, 255, 250));        
        modePanel.setBackground(new Color(255, 255, 250));
        startPanel.setBackground(new Color(255, 255, 250));
        
        frame.add(textPanel);
        frame.add(buttonPanel);
        frame.add(modePanel);
        frame.add(startPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	String command = e.getActionCommand();
    	if (command.equals("Zero")) {
    		dim = 0;
    		System.out.println("dim: " + dim);
    	}
    	if (command.equals("One")) {
    		dim = 1;
    		System.out.println("dim: " +dim);
    	}
    	if (command.equals("Two")) {
    		dim = 2;
    		System.out.println("dim: " +dim);
    	}
    	if (command.equals("Three")) {
    		dim = 3;
    		System.out.println("dim: " +dim);
    	}
    	if (command.equals("Regular")) {
    		mode = 0;
    		System.out.println("mode: " +mode);
    	}
    	if (command.equals("Imaginary")) {
    		mode = 1;
    		System.out.println("mode: " +mode);
    	}
    	if (command.equals("Negative")) {
    		mode = 2;
    		System.out.println("mode: " +mode);
    	}
    	if (command.equals("Rotation")) {
    		mode = 3;
    		System.out.println("mode: " +mode);
    	}
    	if (command.equals("Start")) {
    		frame.dispose();
    		if (dim == 0) {
    			gen0Dgame g0D = new gen0Dgame(mode);
    		}
    		if (dim == 1) {
    			gen1Dgame g1D = new gen1Dgame(mode);
    		}
    		if (dim == 2) {
    			gen2Dgame g2d = new gen2Dgame(mode);
//    			Reg2Dgame r2d = new Reg2Dgame();
    		}
    		if (dim == 3) {
    			gen3Dgame g3d = new gen3Dgame(mode);
    		}
    		System.out.println("Starting with dim = " + dim + " and mode = " + mode);
    	}
    	if (command.equals("Quit")) {
    		frame.dispose();
    	}
    }
    
    
}

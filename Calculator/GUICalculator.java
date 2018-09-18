/**
 * Date:			Aug 11, 2017
 * Author:		    Mozhgan Goudarzi
 * Description:		A class that simulates a calculator.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
public class GUICalculator extends JFrame{

	
	Calculator calculator;
	private JPanel panel;
	private JTextField textField;
	private JMenuBar menuBar;
	private JMenuItem Item1;
	private JMenuItem Item2;
	private JMenuItem Item3;
	private JMenuItem Item4;
	private JMenuItem Item5;
	private JMenuItem Item6;
	private JMenuItem Item7;
       String initial="0.0";

	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		   try {
			   
			switch (e.getActionCommand())
			{
			case "+":
				calculator.buildExpression("+");
				break;
			case "/":
				calculator.buildExpression("/");
				break;
			case "x":
				calculator.buildExpression("x");
				break;
			case"=":
				calculator.calculate();
			    textField.setText(calculator.getoperand());
				break;
			case"C":
			   calculator.clear();
			   textField.setText(initial);
               break;
			case"sqrx":
				textField.setText(calculator.findSquerRoot(calculator.getoperand()));
				break;
				
			case"x2":
				textField.setText(calculator.findSquer(calculator.getoperand()));
				break;
				
			case "back":
				textField.setText(calculator.backspace(calculator.getoperand()));
				if(textField.getText().isEmpty())
				{
					   textField.setText(initial);
				}
				break;
			case "%":
				textField.setText(calculator.findPercentage(calculator.getoperand()));
				break;
			case"+/-":
				textField.setText(calculator.togglePlusMinus());
				break;
			default:
				//textField.setText(textField.getText() + e.getActionCommand());
				calculator.buildOperand(e.getActionCommand());
				textField.setText(calculator.getoperand());
            
			}
		   }
		   catch(LongOperandException ex)
		   {
			   
			   textField.setText("ERROR");
			   calculator.clear();
		   }
		   catch(EmptyOperandException ex)
		   {
			   textField.setText("ERROR");
			   calculator.clear();
		   }
		}
		
	}
	
	private class FileListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == Item1)
			{
				System.exit(0);
			}
		}
	}
	
	private class HelpListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == Item6)
				JOptionPane.showMessageDialog(null,
					    "Do you need help?","Help",JOptionPane.INFORMATION_MESSAGE);
				
				
		
			else
				JOptionPane.showMessageDialog(null,
					    "Mozhgan created this calculator","About",JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	private class ConvertListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
			if(e.getSource() == Item2)
			{
				textField.setText(calculator.convertHex(calculator.getoperand()));
			}
			if(e.getSource() == Item3)
			{
				textField.setText(calculator.getoperand());
			}
				
				if(e.getSource() == Item4)
				{
					textField.setText(calculator.convertOct(calculator.getoperand()));
				}
				if(e.getSource() == Item5)
				{
					textField.setText(calculator.convertBin(calculator.getoperand()));
				}
			}
			catch(EmptyOperandException ex)
			   {
				   textField.setText("ERROR");
				   calculator.clear();
			   }
		
			
		}
	}
	

	void addButton(String text, Font f, ActionListener listener)
	{
		JButton button = new JButton(text);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		button.setFont(f);
		button.setForeground(Color.BLACK);
		button.addActionListener(listener);
		panel.add(button);	
	}

	        GUICalculator(){
			super("Calculator");
			calculator = new Calculator();
			setSize(300,365);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			this.setLayout(new BorderLayout(0, 4));
			
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 4, 4, 4));
			ButtonListener listener = new ButtonListener();
			Font f = new Font("SansSerif", Font.PLAIN, 22);
            //add buttons
			addButton("C", f, listener);
			addButton("back", f, listener);
			addButton("%", f, listener);
			addButton("+/-", f, listener);
			addButton("x2", f, listener);
			addButton("sqrx", f, listener);
			addButton(" ", f, null);
			addButton("/", f, listener);
			addButton("7", f, listener);
			addButton("8", f, listener);
			addButton("9", f, listener);
			addButton("x", f, listener);
			addButton("4", f, listener);
			addButton("5", f, listener);
			addButton("6", f, listener);
			addButton("-", f, listener);
			addButton("1", f, listener);
			addButton("2", f, listener);
			addButton("3", f, listener);
			addButton("+", f, listener);
			addButton("", f, null);
			addButton("0", f, listener);
			addButton(".", f, listener);
			addButton("=", f, listener);
			Font f2 = new Font("SansSerif", Font.PLAIN, 40);
			textField = new JTextField();
			textField.setHorizontalAlignment(JTextField.RIGHT);
			textField.setFont(f2);
			textField.setEditable(false);
			textField.setBackground(Color.WHITE);
			textField.setText(initial);
		
			textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			this.add(textField, BorderLayout.PAGE_START);

			this.add(panel, BorderLayout.CENTER);
			//Create the menu bar.
			menuBar = new JMenuBar();
			
			//Build the  menus.
			JMenu fileMenu = new JMenu("File");
			JMenu convertMenu = new JMenu("Convert");
			ConvertListener convertListener = new ConvertListener();
			JMenu helpMenu = new JMenu("Help");
			ButtonGroup group= new ButtonGroup();
			Item1=new JMenuItem("Exit");
			FileListener fileListener = new FileListener();
		    Item1.addActionListener(fileListener);
			Item2=new JRadioButtonMenuItem("Hex");
			group.add(Item2);
			Item2.addActionListener(convertListener);
			Item3=new JRadioButtonMenuItem("Dec");
			group.add(Item3);
			Item3.addActionListener(convertListener);
		    Item4=new JRadioButtonMenuItem("Oct");
		    group.add(Item4);
			Item4.addActionListener(convertListener);
			Item5=new JRadioButtonMenuItem("Bin");
			group.add(Item5);
			Item5.addActionListener(convertListener);
			Item6=new JMenuItem("How To Use");
			Item7=new JMenuItem("About");
			HelpListener helpListener = new HelpListener();
			Item6.addActionListener(helpListener);
			Item7.addActionListener(helpListener);
			
		
			
		
		
			
			fileMenu.add(Item1);
			convertMenu.add(Item2);
			convertMenu.add(Item3);//convert
			convertMenu.add(Item4);
			convertMenu.add(Item5);
			helpMenu.add(Item6);
			helpMenu.add(Item7);
			//fileMenu3.add(Item8);
			
			menuBar.add(fileMenu);
			menuBar.add(convertMenu);
			menuBar.add(helpMenu);
			
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(400, 400);
			this.setResizable(true);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			this.setJMenuBar(menuBar);

			


		 
	                        }
 

}

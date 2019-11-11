package es;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	
	private JFrame frame;
	private JButton file;
	private JPanel panel1;
	private JPanel panel2;
	private JButton thresholds;
	private JButton new_rule;
	private JButton clear_file;
	
	public GUI() {
		frame = new JFrame("Avaliacao da qualidade de detecao de defeitos de desenho em projetos de software");
		create_GUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public void create_GUI() {
		
		frame.setLayout(new GridLayout(2,0));
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		clear_file = new JButton("Clear File");
		file = new JButton("Choose File");
		thresholds = new JButton("Change Thresholds");
		new_rule = new JButton("New Rule");
		
		clear_file.setPreferredSize(new Dimension(300,50));
		file.setPreferredSize(new Dimension(300,50));
		
		thresholds.setPreferredSize(new Dimension(300,50));
		new_rule.setPreferredSize(new Dimension(300,50));
		
		panel1.add(file);
		panel1.add(clear_file);
		panel2.add(thresholds);
		panel2.add(new_rule);
		frame.add(panel1);
		frame.add(panel2);
		
		
	}
	
	public void open() {
		frame.setSize(1500, 800);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}


	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.open();

	}

}

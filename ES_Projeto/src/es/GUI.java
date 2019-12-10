package es;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import es.DetailTable;




public class GUI extends JFrame {
	private MethodTable methodTable;

	private DetailTable detailTable;
	
	private JTable table;
	private JTextField is_L;
	private JTextField is_F;
	private JTextField loc = new JTextField("80");
	private JTextField cyclo =new JTextField("10");
	private JTextField atfd = new JTextField("4");
	private JTextField laa = new JTextField("0.42");

	private JTable dtable;
	
	private LMRule lmRule=new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
	private FERule feRule=new FERule(Symbol.MAIOR,4,Condition.AND,Symbol.MENOR,0.42);
	
	public void create_GUI() {
		methodTable = new MethodTable();

		detailTable = new DetailTable();
	    table = new JTable(getMethodTable());

	    dtable = new JTable(detailTable);
	    for (int i =0; i<getMethodTable().getColumnCount();i++) {
	         getTable().setDefaultRenderer(getTable().getColumnClass(i), new MethodCellRenderer());
	    }
		getTable().setFillsViewportHeight(true);
		getTable().setDefaultEditor(Object.class, null);
		for (int i =0; i<methodTable.getColumnCount();i++) {
	         dtable.setDefaultRenderer(dtable.getColumnClass(i), new MethodCellRenderer());
	    }
   dtable.setPreferredScrollableViewportSize(new Dimension(1180, 170));
	dtable.setFillsViewportHeight(true);
	dtable.setDefaultEditor(Object.class, null);

		
		
		JScrollPane scrollPane = new JScrollPane(getTable());

		JScrollPane scrollPane2 = new JScrollPane(dtable);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new GridLayout(2,0));
		
		JButton file = new JButton("Choose File");
		JButton clear = new JButton("Clear File");

		JButton thresholds = new JButton("Change Thresholds");

		clear.setPreferredSize(new Dimension(300, 50));
		file.setPreferredSize(new Dimension(300, 50));
		thresholds.setPreferredSize(new Dimension(300, 50));

		JPanel panel3 = new JPanel();

		
		file.addActionListener(new ExcelReader(getMethodTable(),getDetailTable()));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMethodTable().clear();
				getMethodTable().fireTableDataChanged();							
			}
			
		});

		thresholds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui_thresholds();

			}
		});
		
		JPanel rulesp=new JPanel();
		rulesp.setLayout(new GridLayout(0,2));
		is_L=new JTextField(getLmRule().toString());
		is_L.setEditable(false);
		is_L.setBackground(Color.LIGHT_GRAY);
		is_F=new JTextField(getFeRule().toString());
		is_F.setEditable(false);
		is_F.setBackground(Color.LIGHT_GRAY);
		rulesp.add(is_L);
		rulesp.add(is_F);
		JPanel bp=new JPanel();
		bp.add(thresholds);
		rulesPanel.add(rulesp);
		rulesPanel.add(bp);
		JPanel bp2=new JPanel();
		bp2.add(file);
		bp2.add(clear);
		panel1.add(bp2,BorderLayout.NORTH);
		panel1.add(scrollPane,BorderLayout.CENTER);
		panel1.add(scrollPane);
		JLabel lb=new JLabel("RESULTS");
		panel3.add(lb);
		panel3.add(scrollPane2);
		add(panel1);
		add(rulesPanel);
		add(panel3);
	}
	
	
	private void gui_thresholds() {
		
		JCheckBox locCheck = new JCheckBox();
		JCheckBox maiorloc = new JCheckBox();
		JCheckBox menorloc = new JCheckBox();
		JLabel maiqloc = new JLabel(">");
		JLabel menqloc = new JLabel("<");
		
		JCheckBox cycloCheck = new JCheckBox();
		JCheckBox maiorcyclo = new JCheckBox();
		JCheckBox menorcyclo = new JCheckBox();
		JLabel maiqcyclo = new JLabel(">");
		JLabel menqcyclo = new JLabel("<");
		
		JLabel andlong = new JLabel("AND");
		JLabel orlong = new JLabel("OR");
		JCheckBox andlongcheck = new JCheckBox();
		JCheckBox orlongcheck = new JCheckBox();
		
		JCheckBox atfdCheck = new JCheckBox();
		JCheckBox maioratfd = new JCheckBox();
		JCheckBox menoratfd = new JCheckBox();
		JLabel maiqatfd = new JLabel(">");
		JLabel menqatfd = new JLabel("<");
		
		JLabel andfeat = new JLabel("AND");
		JLabel orfeat = new JLabel("OR");
		JCheckBox andfeatcheck= new JCheckBox();
		JCheckBox orfeatcheck = new JCheckBox();
		
		JCheckBox laaCheck = new JCheckBox();
		JCheckBox maiorlaa = new JCheckBox();
		JCheckBox menorlaa = new JCheckBox();
		JLabel maiqlaa = new JLabel(">");
		JLabel menqlaa = new JLabel("<");
		
		double [] initial = new double [4];
		initial[0]= Double.parseDouble(loc.getText());
		initial[1]= Double.parseDouble(cyclo.getText());
		initial[2]= Double.parseDouble(atfd.getText());
		initial[3]= Double.parseDouble(laa.getText());

		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(2, 0));
		
		myPanel.add(new JLabel("Long method"));
		myPanel.add(locCheck);
		myPanel.add(new JLabel("LOC:"));
		loc.setVisible(false);;
		myPanel.add(maiorloc);
		maiorloc.setVisible(false);
		myPanel.add(maiqloc);
		maiqloc.setVisible(false);
		myPanel.add(menorloc);
		menorloc.setVisible(false);
		myPanel.add(menqloc);
		menqloc.setVisible(false);
		myPanel.add(loc);
		
		myPanel.add(andlongcheck);
		andlongcheck.setVisible(false);
		myPanel.add(andlong);
		andlong.setVisible(false);
		myPanel.add(orlongcheck);
		orlongcheck.setVisible(false);
		myPanel.add(orlong);
		orlong.setVisible(false);
		
		
		myPanel.add(cycloCheck);
		myPanel.add(new JLabel("CYCLO:"));
		cyclo.setVisible(false);
		myPanel.add(maiorcyclo);
		maiorcyclo.setVisible(false);
		myPanel.add(maiqcyclo);
		maiqcyclo.setVisible(false);
		myPanel.add(menorcyclo);
		menorcyclo.setVisible(false);
		myPanel.add(menqcyclo);
		menqcyclo.setVisible(false);
		myPanel.add(cyclo);
		
		myPanel.add(new JLabel("Feature envy"));
		myPanel.add(atfdCheck);
		myPanel.add(new JLabel("ATFD:"));
		atfd.setVisible(false);
		myPanel.add(maioratfd);
		maioratfd.setVisible(false);
		myPanel.add(maiqatfd);
		maiqatfd.setVisible(false);
		myPanel.add(menoratfd);
		menoratfd.setVisible(false);
		myPanel.add(menqatfd);
		menqatfd.setVisible(false);
		myPanel.add(atfd);
		
		myPanel.add(andfeatcheck);
		andfeatcheck.setVisible(false);
		myPanel.add(andfeat);
		andfeat.setVisible(false);
		myPanel.add(orfeatcheck);
		orfeatcheck.setVisible(false);
		myPanel.add(orfeat);
		orfeat.setVisible(false);
		
		myPanel.add(laaCheck);
		myPanel.add(new JLabel("LAA:"));
		laa.setVisible(false);;
		myPanel.add(maiorlaa);
		maiorlaa.setVisible(false);
		myPanel.add(maiqlaa);
		maiqlaa.setVisible(false);
		myPanel.add(menorlaa);
		menorlaa.setVisible(false);
		myPanel.add(menqlaa);
		menqlaa.setVisible(false);
		myPanel.add(laa);
		
		
		
		//checkboxes do loc
		
		
		locCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!maiorloc.isVisible() && !menorloc.isVisible()) {
					maiorloc.setVisible(true);
					maiqloc.setVisible(true);
					menorloc.setVisible(true);
					menqloc.setVisible(true);
				}
				if (maiorloc.isSelected() || (!maiorloc.isSelected() && menorloc.isSelected())) {
					loc.setVisible(false);
					maiorloc.setVisible(false);
					maiqloc.setVisible(false);
				} 
				if (menorloc.isSelected() || (!menorloc.isSelected() && maiorloc.isSelected())) {
					loc.setVisible(false);
					menorloc.setVisible(false);
					menqloc.setVisible(false);
				} 
				if (maiorloc.isSelected() && locCheck.isSelected()) {
					loc.setVisible(true);
					maiorloc.setVisible(true);
					maiqloc.setVisible(true);
				}
				if (menorloc.isSelected() && locCheck.isSelected()) {
					loc.setVisible(true);
					menorloc.setVisible(true);
					menqloc.setVisible(true);
				}
				if (!menorloc.isSelected() && !maiorloc.isSelected() && !locCheck.isSelected()) {
					loc.setVisible(false);
					maiorloc.setVisible(false);
					maiqloc.setVisible(false);
					menorloc.setVisible(false);
					menqloc.setVisible(false);
				}
				if (!locCheck.isSelected() || !cycloCheck.isSelected()) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((locCheck.isSelected() && cycloCheck.isSelected()) && ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() ))) {
					if (!andlongcheck.isSelected() && !orlongcheck.isSelected()) {
						orlongcheck.setVisible(true);
						orlong.setVisible(true);
						andlongcheck.setVisible(true);
						andlong.setVisible(true);
					}
					if(andlongcheck.isSelected() && !orlongcheck.isSelected()) {
						andlongcheck.setVisible(true);
						andlong.setVisible(true);
					}
					if(orlongcheck.isSelected() && !andlongcheck.isSelected()) {
						orlongcheck.setVisible(true);
						orlong.setVisible(true);
					}
					
				}
				
				
			}
		});
		
		maiorloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setSymbol1(Symbol.MAIOR);
				if (!menorloc.isSelected() && maiorloc.isSelected()) {
					loc.setVisible(true);
					menorloc.setVisible(!menorloc.isVisible());
					menqloc.setVisible(!menqloc.isVisible());
				} 
				if (!menorloc.isSelected() && !maiorloc.isSelected()) {
					loc.setVisible(false);
					menorloc.setVisible(!menorloc.isVisible());
					menqloc.setVisible(!menqloc.isVisible());
				} 
				if (!maiorloc.isSelected() || !maiorcyclo.isSelected() || !menorloc.isSelected() || !menorcyclo.isSelected() && (!cycloCheck.isSelected() || !locCheck.isSelected())) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() )) {
					andlongcheck.setVisible(true);
					andlong.setVisible(true);
					orlongcheck.setVisible(true);
					orlong.setVisible(true);
				}
			}
		});
		
		menorloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setSymbol1(Symbol.MENOR);
				if (!maiorloc.isSelected() && menorloc.isSelected()) {
					loc.setVisible(true);
					maiorloc.setVisible(!maiorloc.isVisible());
					maiqloc.setVisible(!maiqloc.isVisible());
				} 
				if (!maiorloc.isSelected() && !menorloc.isSelected()) {
					loc.setVisible(false);
					maiorloc.setVisible(!maiorloc.isVisible());
					maiqloc.setVisible(!maiqloc.isVisible());
				} 
				if (!maiorloc.isSelected() || !maiorcyclo.isSelected() || !menorloc.isSelected() || !menorcyclo.isSelected() && (!cycloCheck.isSelected() || !locCheck.isSelected())) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() )) {
					andlongcheck.setVisible(true);
					andlong.setVisible(true);
					orlongcheck.setVisible(true);
					orlong.setVisible(true);
				}
			}
		});
		
		
		//checkboxes do cyclo
		
		
		cycloCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!maiorcyclo.isVisible() && !menorcyclo.isVisible()) {
					maiorcyclo.setVisible(true);
					maiqcyclo.setVisible(true);
					menorcyclo.setVisible(true);
					menqcyclo.setVisible(true);
				}
				if (maiorcyclo.isSelected() || (!maiorcyclo.isSelected() && menorcyclo.isSelected())) {
					cyclo.setVisible(false);
					maiorcyclo.setVisible(false);
					maiqcyclo.setVisible(false);
				} 
				if (menorcyclo.isSelected() || (!menorcyclo.isSelected() && maiorcyclo.isSelected())) {
					cyclo.setVisible(false);
					menorcyclo.setVisible(false);
					menqcyclo.setVisible(false);
				} 
				if (maiorcyclo.isSelected() && cycloCheck.isSelected()) {
					cyclo.setVisible(true);
					maiorcyclo.setVisible(true);
					maiqcyclo.setVisible(true);
				}
				if (menorcyclo.isSelected() && cycloCheck.isSelected()) {
					cyclo.setVisible(true);
					menorcyclo.setVisible(true);
					menqcyclo.setVisible(true);
				}
				if (!menorcyclo.isSelected() && !maiorcyclo.isSelected() && !cycloCheck.isSelected()) {
					cyclo.setVisible(false);
					maiorcyclo.setVisible(false);
					maiqcyclo.setVisible(false);
					menorcyclo.setVisible(false);
					menqcyclo.setVisible(false);
				}
				if (!locCheck.isSelected() || !cycloCheck.isSelected()) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((locCheck.isSelected() && cycloCheck.isSelected()) && ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() ))) {
					if (!andlongcheck.isSelected() && !orlongcheck.isSelected()) {
						orlongcheck.setVisible(true);
						orlong.setVisible(true);
						andlongcheck.setVisible(true);
						andlong.setVisible(true);
					}
					if(andlongcheck.isSelected() && !orlongcheck.isSelected()) {
						andlongcheck.setVisible(true);
						andlong.setVisible(true);
					}
					if(orlongcheck.isSelected() && !andlongcheck.isSelected()) {
						orlongcheck.setVisible(true);
						orlong.setVisible(true);
					}
					
				}
			}
		});
		
		maiorcyclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setSymbol2(Symbol.MAIOR);
				if (!menorcyclo.isSelected() && maiorcyclo.isSelected()) {
					cyclo.setVisible(true);
					menorcyclo.setVisible(!menorcyclo.isVisible());
					menqcyclo.setVisible(!menqcyclo.isVisible());
				} 
				if (!menorcyclo.isSelected() && !maiorcyclo.isSelected()) {
					cyclo.setVisible(false);
					menorcyclo.setVisible(!menorcyclo.isVisible());
					menqcyclo.setVisible(!menqcyclo.isVisible());
				} 
				if (!maiorloc.isSelected() || !maiorcyclo.isSelected() || !menorloc.isSelected() || !menorcyclo.isSelected() && (!cycloCheck.isSelected() || !locCheck.isSelected())) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() )) {
					andlongcheck.setVisible(true);
					andlong.setVisible(true);
					orlongcheck.setVisible(true);
					orlong.setVisible(true);
				}
			}
		});
		
		menorcyclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setSymbol2(Symbol.MENOR);
				if (!maiorcyclo.isSelected() && menorcyclo.isSelected()) {
					cyclo.setVisible(true);
					maiorcyclo.setVisible(!maiorcyclo.isVisible());
					maiqcyclo.setVisible(!maiqcyclo.isVisible());
				} 
				if (!maiorcyclo.isSelected() && !menorcyclo.isSelected()) {
					cyclo.setVisible(false);
					maiorcyclo.setVisible(!maiorcyclo.isVisible());
					maiqcyclo.setVisible(!maiqcyclo.isVisible());
				} 
				if (!maiorloc.isSelected() || !maiorcyclo.isSelected() || !menorloc.isSelected() || !menorcyclo.isSelected() && (!cycloCheck.isSelected() || !locCheck.isSelected())) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
				}
				if ((maiorcyclo.isSelected() && maiorloc.isSelected()) || (maiorcyclo.isSelected() && menorloc.isSelected()) || (menorcyclo.isSelected() && maiorloc.isSelected()) || (menorcyclo.isSelected() && menorloc.isSelected())  && (locCheck.isSelected() && cycloCheck.isSelected() )) {
					andlongcheck.setVisible(true);
					andlong.setVisible(true);
					orlongcheck.setVisible(true);
					orlong.setVisible(true);
				}
			}
		});
		
		
		//checkboxes do AND e OR do longmethod
		
		andlongcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setCondition(Condition.AND);
				if (andlongcheck.isSelected()) {
					orlongcheck.setVisible(false);
					orlong.setVisible(false);
					}
				if (!andlongcheck.isSelected()) {
					orlongcheck.setVisible(true);
					orlong.setVisible(true);
					}
			}
		});
		orlongcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLmRule().setCondition(Condition.OR);
				if (orlongcheck.isSelected()) {
					andlongcheck.setVisible(false);
					andlong.setVisible(false);
					}
				if (!orlongcheck.isSelected()) {
					andlongcheck.setVisible(true);
					andlong.setVisible(true);
					}
			}
		});
			
		//checkboxes do AND e OR do featureenvy
		
		andfeatcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setCondition(Condition.AND);
				if (andfeatcheck.isSelected()) {
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if (!andfeatcheck.isSelected()) {
					orfeatcheck.setVisible(true);
					orfeat.setVisible(true);
				}
			}
		});
		orfeatcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setCondition(Condition.OR);
				if (orfeatcheck.isSelected()) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
				}
				if (!orfeatcheck.isSelected()) {
					andfeatcheck.setVisible(true);
					andfeat.setVisible(true);
				}
			}
		});
		
		//checboxes do atfd
		
		
		atfdCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!maioratfd.isVisible() && !menoratfd.isVisible()) {
					maioratfd.setVisible(true);
					maiqatfd.setVisible(true);
					menoratfd.setVisible(true);
					menqatfd.setVisible(true);
				}
				if (maioratfd.isSelected() || (!maioratfd.isSelected() && menoratfd.isSelected())) {
					atfd.setVisible(false);
					maioratfd.setVisible(false);
					maiqatfd.setVisible(false);
				} 
				if (menoratfd.isSelected() || (!menoratfd.isSelected() && maioratfd.isSelected())) {
					atfd.setVisible(false);
					menoratfd.setVisible(false);
					menqatfd.setVisible(false);
				} 
				if (maioratfd.isSelected() && atfdCheck.isSelected()) {
					atfd.setVisible(true);
					maioratfd.setVisible(true);
					maiqatfd.setVisible(true);
				}
				if (menoratfd.isSelected() && atfdCheck.isSelected()) {
					atfd.setVisible(true);
					menoratfd.setVisible(true);
					menqatfd.setVisible(true);
				}
				if (!menoratfd.isSelected() && !maioratfd.isSelected() && !atfdCheck.isSelected()) {
					atfd.setVisible(false);
					maioratfd.setVisible(false);
					maiqatfd.setVisible(false);
					menoratfd.setVisible(false);
					menqatfd.setVisible(false);
				}
				if (!atfdCheck.isSelected() || !laaCheck.isSelected()) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((laaCheck.isSelected() && atfdCheck.isSelected()) && ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() ))) {
					if (!andfeatcheck.isSelected() && !orfeatcheck.isSelected()) {
						orfeatcheck.setVisible(true);
						orfeat.setVisible(true);
						andfeatcheck.setVisible(true);
						andfeat.setVisible(true);
					}
					if(andfeatcheck.isSelected() && !orfeatcheck.isSelected()) {
						andfeatcheck.setVisible(true);
						andfeat.setVisible(true);
					}
					if(orfeatcheck.isSelected() && !andfeatcheck.isSelected()) {
						orfeatcheck.setVisible(true);
						orfeat.setVisible(true);
					}
				}
			}
		});
		
		maioratfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setSymbol1(Symbol.MAIOR);
				if (!menoratfd.isSelected() && maioratfd.isSelected()) {
					atfd.setVisible(true);
					menoratfd.setVisible(!menoratfd.isVisible());
					menqatfd.setVisible(!menqatfd.isVisible());
				} 
				if (!menoratfd.isSelected() && !maioratfd.isSelected()) {
					atfd.setVisible(false);
					menoratfd.setVisible(!menoratfd.isVisible());
					menqatfd.setVisible(!menqatfd.isVisible());
				} 
				if (!maiorlaa.isSelected() || !maioratfd.isSelected() || !menorlaa.isSelected() || !menoratfd.isSelected() && (!atfdCheck.isSelected() || !laaCheck.isSelected())) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() )) {
					andfeatcheck.setVisible(true);
					andfeat.setVisible(true);
					orfeatcheck.setVisible(true);
					orfeat.setVisible(true);
				}
			}
		});
		
		menoratfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setSymbol1(Symbol.MENOR);
				if (!maioratfd.isSelected() && menoratfd.isSelected()) {
					atfd.setVisible(true);
					maioratfd.setVisible(!maioratfd.isVisible());
					maiqatfd.setVisible(!maiqatfd.isVisible());
				} 
				if (!maioratfd.isSelected() && !menoratfd.isSelected()) {
					atfd.setVisible(false);
					maioratfd.setVisible(!maioratfd.isVisible());
					maiqatfd.setVisible(!maiqatfd.isVisible());
				} 
				if (!maiorlaa.isSelected() || !maioratfd.isSelected() || !menorlaa.isSelected() || !menoratfd.isSelected() && (!atfdCheck.isSelected() || !laaCheck.isSelected())) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() )) {
					andfeatcheck.setVisible(true);
					andfeat.setVisible(true);
					orfeatcheck.setVisible(true);
					orfeat.setVisible(true);
				}
			}
		});
		
		//checkboxes do laa
		
		laaCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!maiorlaa.isVisible() && !menorlaa.isVisible()) {
					maiorlaa.setVisible(true);
					maiqlaa.setVisible(true);
					menorlaa.setVisible(true);
					menqlaa.setVisible(true);
				}
				if (maiorlaa.isSelected() || (!maiorlaa.isSelected() && menorlaa.isSelected())) {
					laa.setVisible(false);
					maiorlaa.setVisible(false);
					maiqlaa.setVisible(false);
				} 
				if (menorlaa.isSelected() || (!menorlaa.isSelected() && maiorlaa.isSelected())) {
					laa.setVisible(false);
					menorlaa.setVisible(false);
					menqlaa.setVisible(false);
				} 
				if (maiorlaa.isSelected() && laaCheck.isSelected()) {
					laa.setVisible(true);
					maiorlaa.setVisible(true);
					maiqlaa.setVisible(true);
				}
				if (menorlaa.isSelected() && laaCheck.isSelected()) {
					laa.setVisible(true);
					menorlaa.setVisible(true);
					menqlaa.setVisible(true);
				}
				if (!menorlaa.isSelected() && !maiorlaa.isSelected() && !laaCheck.isSelected()) {
					laa.setVisible(false);
					maiorlaa.setVisible(false);
					maiqlaa.setVisible(false);
					menorlaa.setVisible(false);
					menqlaa.setVisible(false);
				}
				if (!atfdCheck.isSelected() || !laaCheck.isSelected()) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((laaCheck.isSelected() && atfdCheck.isSelected()) && ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() ))) {
					if (!andfeatcheck.isSelected() && !orfeatcheck.isSelected()) {
						orfeatcheck.setVisible(true);
						orfeat.setVisible(true);
						andfeatcheck.setVisible(true);
						andfeat.setVisible(true);
					}
					if(andfeatcheck.isSelected() && !orfeatcheck.isSelected()) {
						andfeatcheck.setVisible(true);
						andfeat.setVisible(true);
					}
					if(orfeatcheck.isSelected() && !andfeatcheck.isSelected()) {
						orfeatcheck.setVisible(true);
						orfeat.setVisible(true);
					}
				}
			}
		});
		
		maiorlaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setSymbol2(Symbol.MAIOR);
				if (!menorlaa.isSelected() && maiorlaa.isSelected()) {
					laa.setVisible(true);
					menorlaa.setVisible(!menorlaa.isVisible());
					menqlaa.setVisible(!menqlaa.isVisible());
				} 
				if (!menorlaa.isSelected() && !maiorlaa.isSelected()) {
					laa.setVisible(false);
					menorlaa.setVisible(!menorlaa.isVisible());
					menqlaa.setVisible(!menqlaa.isVisible());
				} 
				if (!maiorlaa.isSelected() || !maioratfd.isSelected() || !menorlaa.isSelected() || !menoratfd.isSelected() && (!atfdCheck.isSelected() || !laaCheck.isSelected())) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() )) {
					andfeatcheck.setVisible(true);
					andfeat.setVisible(true);
					orfeatcheck.setVisible(true);
					orfeat.setVisible(true);
				}
			}
		});
		
		menorlaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFeRule().setSymbol2(Symbol.MENOR);
				if (!maiorlaa.isSelected() && menorlaa.isSelected()) {
					laa.setVisible(true);
					maiorlaa.setVisible(!maiorlaa.isVisible());
					maiqlaa.setVisible(!maiqlaa.isVisible());
				} 
				if (!maiorlaa.isSelected() && !menorlaa.isSelected()) {
					laa.setVisible(false);
					maiorlaa.setVisible(!maiorlaa.isVisible());
					maiqlaa.setVisible(!maiqlaa.isVisible());
				} 
				if (!maiorlaa.isSelected() || !maioratfd.isSelected() || !menorlaa.isSelected() || !menoratfd.isSelected() && (!atfdCheck.isSelected() || !laaCheck.isSelected())) {
					andfeatcheck.setVisible(false);
					andfeat.setVisible(false);
					orfeatcheck.setVisible(false);
					orfeat.setVisible(false);
				}
				if ((maioratfd.isSelected() && maiorlaa.isSelected()) || (maioratfd.isSelected() && menorlaa.isSelected()) || (menoratfd.isSelected() && maiorlaa.isSelected()) || (menoratfd.isSelected() && menorlaa.isSelected())  && (laaCheck.isSelected() && atfdCheck.isSelected() )) {
					andfeatcheck.setVisible(true);
					andfeat.setVisible(true);
					orfeatcheck.setVisible(true);
					orfeat.setVisible(true);
				}
			}
		});
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
			loc.setText(String.valueOf(initial[0]));
			cyclo.setText(String.valueOf(initial[1]));
			atfd.setText(String.valueOf(initial[2]));
			laa.setText(String.valueOf(initial[3]));
		}
		if(result ==JOptionPane.OK_OPTION) {
			for (int i =0; i<getMethodTable().getColumnCount();i++) {
		         getTable().setDefaultRenderer(getTable().getColumnClass(i), new MethodCellRenderer());
		    }
			getMethodTable().fireTableDataChanged();	
			
			
			try {
				getLmRule().setVar1(Double.valueOf(loc.getText()));
				getLmRule().setVar2(Double.valueOf(cyclo.getText()));
				getFeRule().setVar1(Double.valueOf(atfd.getText()));
				getFeRule().setVar2(Double.valueOf(laa.getText()));
			} catch ( java.lang.NumberFormatException e) {
				
			}
	
			
			is_L.setText(getLmRule().toString());
			is_F.setText(getFeRule().toString());
			
		}
		

	}

	public GUI() {
		setTitle("Avaliação da qualidade de deteção de defeitos de desenho em projetos de software");
		setLayout(new GridLayout(4,0));
	    create_GUI();
	}
	


	public void open() {
		setSize(1200, 800);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		GUI f=new GUI();
		f.open();
	}



	public JTable getTable() {
		return table;
	}

	public MethodTable getMethodTable() {
		return methodTable;
	}
	public DetailTable getDetailTable() {
		return detailTable;
	}

	public LMRule getLmRule() {
		return lmRule;
	}

	public FERule getFeRule() {
		return feRule;
	}
}




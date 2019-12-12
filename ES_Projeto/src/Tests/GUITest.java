package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.DetailTable;
import es.ExcelReader;
import es.FERule;
import es.GUI;
import es.LMRule;
import es.MethodTable;
import es.ResultPanel;

class GUITest {

	static GUI g;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		g = new GUI();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreate_GUI() {
		MethodTable mtable= g.getMethodTable();
		ResultPanel resultp = g.getResultp();
		DetailTable dtable = g.getDetailTable();
		LMRule rule = g.getLmRule();
		FERule ferule = g.getFeRule();
		
		JTable table1 = g.getTable();
		JTable table2 = g.getDtable();

		assertEquals(table1.getPreferredScrollableViewportSize(),(new Dimension(450, 400)));
		assertEquals(table1.getFillsViewportHeight(), true);
		
		JScrollPane pane1 = new JScrollPane(table1);
		JScrollPane pane2 = new JScrollPane(table2);
		
		Component[] c = g.getContentPane().getComponents();
		JPanel pane1Components = (JPanel)c[0];
		Component [] cComponents = pane1Components.getComponents();
		for (int i = 0; i < cComponents.length; i++) {
			if (cComponents[i] instanceof JScrollPane) {
				assertTrue(pane1.toString().equals(cComponents[1].toString()));
			}
		}
		//PAINEIS TOTAIS
		int countPanels = 0;
		JPanel [] panels = new JPanel [4];
		int j = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof JPanel) {
				panels[j] = (JPanel)c[i];
				countPanels++;
				j++;
			}
		}
		assertEquals(countPanels,4);
		
		//PAINEL1
		countPanels = 0;
		j=0;
		Component [] cPanel1 = ((JPanel)cComponents[0]).getComponents();
		JButton [] subpanel1 = new JButton [2];
		
		for (int i = 0; i < cPanel1.length; i++) {
				subpanel1[j] = (JButton)cPanel1[i];
				countPanels++;
				j++;
			
		
		}
		
		assertEquals(countPanels,2);

		JButton file = (JButton)subpanel1[0];
		assertTrue(((ExcelReader)file.getActionListeners()[0]).getLmRule().equals(new ExcelReader(mtable, dtable, resultp,rule).getLmRule()));
		
		JButton clear = (JButton)subpanel1[1];
		assertTrue(!clear.getActionListeners()[0].equals(null));
		
		//PAINEL2
		
		assertEquals(((GridLayout)panels[1].getLayout()).getColumns(),new GridLayout(2,0).getColumns());
		assertEquals(((GridLayout)panels[1].getLayout()).getHgap(),new GridLayout(2,0).getHgap());
		assertEquals(((GridLayout)panels[1].getLayout()).getVgap(),new GridLayout(2,0).getVgap());
		assertEquals(((GridLayout)panels[1].getLayout()).getRows(),new GridLayout(2,0).getRows());
		
		
		//assertTrue(panels[1].getBorder().equals(BorderFactory.createEmptyBorder(0,40,10,40))); da nulo pq nao foi definida nenhuma border
		
		JPanel rulesp = (JPanel)panels[1].getComponents()[0];
		assertEquals(((GridLayout)rulesp.getLayout()).getColumns(),new GridLayout(0,2).getColumns());
		assertEquals(((GridLayout)rulesp.getLayout()).getHgap(),new GridLayout(0,2).getHgap());
		assertEquals(((GridLayout)rulesp.getLayout()).getVgap(),new GridLayout(0,2).getVgap());
		assertEquals(((GridLayout)rulesp.getLayout()).getRows(),new GridLayout(0,2).getRows());
		
		JTextField isL= new JTextField(rule.toString());
		isL.setEditable(false);
		assertEquals(rulesp.getComponents()[0].toString(),isL.toString());
		
		JTextField isF= new JTextField(ferule.toString());
		isF.setEditable(false);
		assertEquals(rulesp.getComponents()[1].toString(),isF.toString());
		
		
		JPanel bp = (JPanel)panels[1].getComponents()[1];
		JButton tresh = (JButton)bp.getComponents()[0];
		assertTrue(!tresh.getActionListeners()[0].equals(null));
		
		
		//PAINEL3
		
		assertTrue(((JLabel)((JPanel)panels[2].getComponents()[0]).getComponent(0)).getText().equals(new JLabel("RESULTS").getText()));
		assertTrue(panels[2].getComponents()[1].toString().equals(pane2.toString()));
		
		
		//PAINEL4
		
		assertEquals(panels[3],(resultp));
	}

}


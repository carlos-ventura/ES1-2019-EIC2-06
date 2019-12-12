package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.Condition;
import es.DetailTable;
import es.ExcelReader;
import es.LMRule;
import es.MethodTable;
import es.ResultPanel;
import es.ResultPanel.Line;
import es.ResultPanel.ResultTable;
import es.Symbol;

class ResultPanelTest {

	static ResultPanel rp;
	static MethodTable mt;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		rp = new ResultPanel();
		mt = new MethodTable();
		LMRule r = new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
		DetailTable dt = new DetailTable();
		ExcelReader ex = new ExcelReader(mt, dt, rp, r);
		ex.setKeep("Long-Method.xlsx");
		ex.readExcel();
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
	void testCheckErrors() {
		
		LMRule rule = new LMRule(Symbol.MAIOR,80,Condition.AND,Symbol.MAIOR,10);
		rp.checkErrors(mt, rule);
		ResultTable table = rp.getResult();
		
		assertEquals(table.getRowCount(), 3);
		
		Line l1 = (Line)table.getValueAtRow(0);
		assertEquals(l1.getLineADCI(),280);
		assertEquals(l1.getLineADII(),0);
		assertEquals(l1.getLineDCI(),140);
		assertEquals(l1.getLineDII(),0);
		
		Line l2 = (Line)table.getValueAtRow(1);
		assertEquals(l2.getLineADCI(),262);
		assertEquals(l2.getLineADII(),18);
		assertEquals(l2.getLineDCI(),140);
		assertEquals(l2.getLineDII(),0);
		
		Line l3 = (Line)table.getValueAtRow(2);
		assertEquals(l3.getLineADCI(),280);
		assertEquals(l3.getLineADII(),3);
		assertEquals(l3.getLineDCI(),137);
		assertEquals(l3.getLineDII(),0);
	}

	@Test
	void testClear() {
		
		ResultTable table = rp.getResult();
		rp.clear();
	
		assertEquals(table.getRowCount(), 0);
	}

}

package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.Condition;
import es.DetailTable;
import es.FERule;
import es.LMRule;
import es.Method;
import es.Symbol;

class DetailTableTest {

	static DetailTable dt;
	static Method m1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		dt = new DetailTable();
		m1 = new Method(8,"es.project.","GrammerException" , "GrammerException(int,String)", 3,1,0,0,false,false,false,false);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dt.clear();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testClear() {
		assertEquals(dt.getRowCount(), 0);
	}

	@Test
	void testAddMethod() {
		dt.addMethod(m1);
		assertEquals(dt.getRowCount(),1);
	}

	@Test
	void testGetRowCount() {
		dt.addMethod(m1);
		assertEquals(dt.getRowCount(),1);
	}

	@Test
	void testGetColumnCount() {
		dt.addMethod(m1);
		assertEquals(dt.getColumnCount(),14);
	}

	@Test
	void testGetColumnNameInt() {
		assertEquals(dt.getColumnName(2), "class");
	}

	@Test
	void testGetColumnClassInt() {
		assertTrue(dt.getColumnClass(2).equals(String.class));
	}

	@Test
	void testGetValueAtRow() {
		dt.addMethod(m1);
		assertEquals(dt.getValueAtRow(0),m1);
	}

	@Test
	void testGetValueAt() {
		dt.addMethod(m1);
		assertEquals(dt.getValueAt(0,11),false);
	}

	@Test
	void getLMRule() {
		assertEquals(dt.getLMRule().getVar2(), 10);
	}

	
	@Test
	void testSetRMRule() {
		dt.setRMRule(new LMRule(Symbol.MAIOR, 90, Condition.AND, Symbol.MAIOR, 15));
		assertEquals(dt.getLMRule().getVar1(), 90);
	}

	@Test
	void getFERule() {
		assertEquals(dt.getFERule().getVar2(), 0.42);
	}
	
	
	@Test
	void testSetFERule() {
		dt.setFERule(new FERule(Symbol.MAIOR, 2, Condition.AND, Symbol.MAIOR, 0.9));
		assertEquals(dt.getFERule().getVar1(), 2);
	}

	@Test
	void testGetColumnNames() {

		String[] s = {"MethodID", "package","class", "method", "LOC", "CYCLO", "ATFD", "LAA", "is_long_method", "New_is_long_method", "iPlasma", "PMD", 
				"is_feature_envy", "New_is_feature_envy"};
		for (int i=0; i<dt.getColumnNames().length;i++) {
			assertEquals(dt.getColumnNames()[i],s[i]);
			}
		
	}

	@Test
	void testGetColumnClasses() {
	
		Class[] s = {Integer.class, String.class, String.class, String.class , Integer.class, Integer.class, Integer.class,
				Double.class,Boolean.class,Boolean.class, Boolean.class, Boolean.class};
		for (int i=0; i<dt.getColumnClasses().length;i++) {
			assertEquals(dt.getColumnClasses()[i],s[i]);
			}
	
	}

	@Test
	void testGetData() {
		dt.addMethod(m1);
		Vector v = new Vector();
		v.add(m1);
		assertTrue(dt.getData().equals(v));
	}

}

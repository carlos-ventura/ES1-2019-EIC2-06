package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.Method;
import es.MethodTable;

class MethodTableTest {

	static MethodTable m;
	static Method m1;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		m = new MethodTable();
		m1 = new Method(8,"es.project.","GrammerException" , "GrammerException(int,String)", 3,1,0,0,false,false,false,false);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		m.clear();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testClear() {
		assertEquals(m.getRowCount(), 0);
	}

	@Test
	void testAddMethod() {
		m.addMethod(m1);
		
		assertEquals(m.getRowCount(),1);
	}

	@Test
	void testGetRowCount() {
		m.addMethod(m1);
		assertEquals(m.getRowCount(),1);
	}

	@Test
	void testGetColumnCount() {
		m.addMethod(m1);
		assertEquals(m.getColumnCount(),12);
	}

	@Test
	void testGetColumnNameInt() {
		assertEquals(m.getColumnName(2), "class");
	}

	@Test
	void testGetColumnClassInt() {
		assertTrue(m.getColumnClass(2).equals(String.class));
	}

	@Test
	void testGetValueAtRow() {
		m.addMethod(m1);
		assertEquals(m.getValueAtRow(0),m1);
	}

	@Test
	void testGetValueAt() {
		m.addMethod(m1);
		assertEquals(m.getValueAt(0,11),false);
	}

}

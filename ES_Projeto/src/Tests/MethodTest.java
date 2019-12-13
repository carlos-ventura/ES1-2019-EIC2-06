package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.Condition;
import es.Method;
import es.Symbol;

class MethodTest {

	static Method m;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		m = new Method(7,"es.project.","Reference" , "result()", 29,5,5,0.28,false,false,true,true);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIs_long_method() {
		
		assertEquals(m.is_long_method(10, 4, Symbol.MAIOR, Symbol.MAIOR, Condition.AND), true);
		assertEquals(m.is_long_method(30, 3, Symbol.MAIOR, Symbol.MAIOR, Condition.AND), false);
		assertEquals(m.is_long_method(2,6, Symbol.MAIOR, Symbol.MAIOR, Condition.OR), true);
		
		assertEquals(m.is_long_method(2,6, Symbol.MAIOR, Symbol.MENOR, Condition.AND), true);
		assertEquals(m.is_long_method(2,4, Symbol.MAIOR, Symbol.MENOR, Condition.AND), false);
		assertEquals(m.is_long_method(2, 7, Symbol.MAIOR, Symbol.MENOR, Condition.OR), true);
		assertEquals(m.is_long_method(30, 4, Symbol.MAIOR, Symbol.MENOR, Condition.OR), true);
		
		assertEquals(m.is_long_method(30,2, Symbol.MENOR, Symbol.MAIOR, Condition.AND), true);
		assertEquals(m.is_long_method(7,2, Symbol.MENOR, Symbol.MAIOR, Condition.AND), false);
		assertEquals(m.is_long_method(31,2, Symbol.MENOR, Symbol.MAIOR, Condition.OR), true);
		
		assertEquals(m.is_long_method(31, 41, Symbol.MENOR, Symbol.MENOR, Condition.AND), true);
		assertEquals(m.is_long_method(10, 41, Symbol.MENOR, Symbol.MENOR, Condition.AND), false);
		assertEquals(m.is_long_method(30, 1, Symbol.MENOR, Symbol.MENOR, Condition.OR), true);
		assertEquals(m.is_long_method(1, 30, Symbol.MENOR, Symbol.MENOR, Condition.OR), true);
		assertEquals(m.is_long_method(1, 2, Symbol.MENOR, Symbol.MENOR, Condition.OR), true);
		
		
		Method m1 = new Method(8,"es.project.","GrammerException" , "GrammerException(int,String)", 95,21,0,0,false,false,false,false);
		assertEquals(m1.is_long_method(80,10, Symbol.MAIOR, Symbol.MAIOR, Condition.AND), true);
		
	}

	@Test
	void testIsF_e_rule() {
		
		assertEquals(m.isF_e_rule(4,0.4, Symbol.MAIOR, Symbol.MENOR, Condition.AND), true);
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MAIOR , Symbol.MAIOR,	Condition.AND),false);
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MAIOR , Symbol.MAIOR, Condition.OR),false);
		
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MAIOR , Symbol.MENOR, Condition.AND),false);
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MAIOR , Symbol.MENOR, Condition.OR),false);
		
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MENOR , Symbol.MAIOR, Condition.AND),false);
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MENOR , Symbol.MAIOR, Condition.OR),false);
		
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MENOR , Symbol.MENOR, Condition.AND),false);
		assertEquals(m.isF_e_rule(m.getAtfd(), m.getLaa(), Symbol.MENOR , Symbol.MENOR, Condition.OR),false);

		
	
		
		
	}

	@Test
	void testGetId() {
		
		assertEquals(m.getId(), 7);
	}

	@Test
	void testSetId() {
		m.setId(7);
		assertEquals(m.getId(), 7);
	}

	@Test
	void testGetPpackage() {
		assertEquals(m.getPpackage(), "es.project.");
	}

	@Test
	void testSetPpackage() {
		m.setPpackage("es.change.");
		assertEquals(m.getPpackage(), "es.change.");
	}

	@Test
	void testGetCclass() {
	
		assertEquals(m.getCclass(), "Reference");
	}

	@Test
	void testSetCclass() {
		m.setCclass("DocumentParseFixture");
		assertEquals(m.getCclass(), "DocumentParseFixture");
	}

	@Test
	void testGetName() {
		assertEquals(m.getName(), "result()");
	}

	@Test
	void testSetName() {
		m.setName("DocumentDocumentation");
		assertEquals(m.getName(), "DocumentDocumentation");
	}

	@Test
	void testGetLoc() {
		assertEquals(m.getLoc(), 29);
	}

	@Test
	void testSetLoc() {
		m.setLoc(4);
		assertEquals(m.getLoc(), 4);
	}

	@Test
	void testGetCyclo() {
		assertEquals(m.getCyclo(), 5);
	}

	@Test
	void testSetCyclo() {
		m.setCyclo(4);
		assertEquals(m.getCyclo(), 4);
	}

	@Test
	void testGetAtfd() {
		assertEquals(m.getAtfd(),5);
	}

	@Test
	void testSetAtfd() {
		m.setAtfd(4);
		assertEquals(m.getAtfd(), 4);
	}

	@Test
	void testGetLaa() {
		assertEquals(m.getLaa(),0.28);
	}

	@Test
	void testSetLaaString() {
		m.setLaa("4.3");
		assertEquals(m.getLaa(), 4.3);
	}

	@Test
	void testSetLaaDouble() {
		m.setLaa(4.5);
		assertEquals(m.getLaa(), 4.5);
	}

	@Test
	void testIsL_m() {
		assertEquals(m.isL_m(),false);
	}

	@Test
	void testSetL_m() {
		m.setL_m(true);
		assertEquals(m.isL_m(), true);
	}

	@Test
	void testIsiPlasma() {
		assertEquals(m.isiPlasma(),false);
	}

	@Test
	void testSetiPlasma() {
		m.setiPlasma(true);
		assertEquals(m.isiPlasma(), true);
	}

	@Test
	void testIsPmd() {
		assertEquals(m.isPmd(),true);
	}

	@Test
	void testSetPmd() {
		m.setPmd(true);
		assertEquals(m.isPmd(), true);
	}

	@Test
	void testIsF_e() {
		assertEquals(m.isF_e(),true);
	}

	@Test
	void testSetF_e() {
		m.setF_e(true);
		assertEquals(m.isF_e(), true);
	}

}


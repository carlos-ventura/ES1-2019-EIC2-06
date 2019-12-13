package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.Condition;
import es.FERule;
import es.LMRule;
import es.Rule;
import es.Symbol;

class FERuleTest {

	static Rule r;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		r = new FERule(Symbol.MAIOR, 3, Condition.AND, Symbol.MAIOR, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testToString() {
		assertEquals(r.toString(),(Rule.SPACE+FERule.RULENAME+": ATFD>3.0 and LAA>2.0"));
	}

	@Test
	void testGetVar1() {
		assertEquals(r.getVar1(),3);
	}

	@Test
	void testSetVar1() {
		r.setVar1(5);
		assertEquals(r.getVar1(),5);
	}

	@Test
	void testGetSymbol1() {
		assertTrue(r.getSymbol1().equals(Symbol.MAIOR));
	}

	@Test
	void testSetSymbol1() {
		r.setSymbol1(Symbol.MENOR);
		assertTrue(r.getSymbol1().equals(Symbol.MENOR));
	}

	@Test
	void testGetVar2() {
		assertEquals(r.getVar2(),2);
	}

	@Test
	void testSetVar2() {
		r.setVar2(3);
		assertEquals(r.getVar2(),3);
	}

	@Test
	void testGetSymbol2() {
		assertTrue(r.getSymbol2().equals(Symbol.MAIOR));
	}

	@Test
	void testSetSymbol2() {
		r.setSymbol2(Symbol.MENOR);
		assertTrue(r.getSymbol2().equals(Symbol.MENOR));
	}

	@Test
	void testGetCondition() {
		assertEquals(r.getCondition(), Condition.AND);
	}

	@Test
	void testSetCondition() {
		r.setCondition(Condition.OR);
		assertEquals(r.getCondition(), Condition.OR);
	}

}


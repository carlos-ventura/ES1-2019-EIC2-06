package Tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({DetailTableTest.class,FERuleTest.class,GUITest.class,
	LMRuleTest.class, MethodTableTest.class,MethodTest.class,ResultPanelTest.class})
public class AllTests {


}

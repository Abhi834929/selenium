package org.selenium.base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.selenium.setup.DriverType;
import org.selenium.setup.SeleniumDriverUtil;

public abstract class AbstractBaseTest{
	protected static WebDriver webDriver;
	private AbstractDriver pageDriver;
	
	/**
	 * - note: this makes the concurrent execution of tests impossible (for now); when that is a goal - move this around as a parameter
	 */
	public static AbstractBaseTest currentTest;
	public static DriverType driverType;
	
	public AbstractBaseTest(){
		super();
	}
	
	// fixtures
	
	@Before
	public void before(){
		currentTest = this;
	}
	
	@BeforeClass
	public static void beforeClass(){
		if( driverType == null ){
			driverType = DriverType.HTMLUNIT;
		}
		webDriver = new SeleniumDriverUtil().getDriver( driverType );
	}
	@AfterClass
	public static void afterClass(){
		webDriver.quit();
	}
	
	//
	
	public final < D extends AbstractDriver >D getDriver(){
		return (D) this.pageDriver;
	}
	
	public final WebDriver getWebDriver(){
		return webDriver;
	}
	
	public final void setPageDriver( final AbstractDriver pageDriverToSet ){
		this.pageDriver = pageDriverToSet;
	}
	
}

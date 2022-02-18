package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Try implements Action {
  private static final Logger LOG = Logger.getLogger(Try.class.getName());
  private static long UNIQUE_ID = 0;

  private final long uid = ++UNIQUE_ID;

  public Action tryit;
  public Action except;

  public WebDriver execute (WebDriver driver, String[] args) {
    LOG.info("Try execute, uid: " + uid);
    try {
      tryit.execute(driver, args);
    } catch (Exception e) {
      except.execute(driver, args);
    }
    LOG.info("Try END " + uid);
    return driver;
  }
}

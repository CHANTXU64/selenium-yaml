package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Try extends ArrayList<Action> implements Action{
  private static final Logger LOG = Logger.getLogger(Try.class.getName());
  private static long UNIQUE_ID = 0;

  private long uid = ++UNIQUE_ID;

  public WebDriver dwork (WebDriver driver) {
    LOG.info("Try dwork " + uid + ", count: " + size());
    try {
      for (int i = 0; i < size(); ++i) {
        Action step = get(i);
        driver = step.dwork(driver);
      }
    } catch (Exception e) {
    }
    LOG.info("Try END " + uid);
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    LOG.info("Try dwork " + uid + ", count: " + size());
    try {
      for (int i = 0; i < size(); ++i) {
        Action step = get(i);
        driver = step.dwork(driver, args);
      }
    } catch (Exception e) {
    }
    LOG.info("Try END " + uid);
    return driver;
  }
}

package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

public class Steps extends ArrayList<Action> implements Action {
  private static final Logger LOG = Logger.getLogger(Steps.class.getName());
  private static long UNIQUE_ID = 0;

  private final long uid = ++UNIQUE_ID;

  public WebDriver execute (WebDriver driver, Map<String, String> args) {
    LOG.info("Steps execute, uid: " + uid + ", count: " + size());
    for (int i = 0; i < size(); ++i) {
      Action step = get(i);
      driver = step.execute(driver, args);
    }
    LOG.info("Steps END " + uid);
    return driver;
  }
}

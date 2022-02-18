package net.chantx.selenium;

import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DoubleClick implements Action {
  private static final Logger LOG =
    Logger.getLogger(DoubleClick.class.getName());

  public Elem elem;

  public WebDriver execute (WebDriver driver, Map<String, String> args) {
    LOG.info("DoubleClick elem: " + elem.getQuery(args));
    Actions action = new Actions(driver);
    action.doubleClick(elem.get(driver, args)).perform();
    return driver;
  }
}

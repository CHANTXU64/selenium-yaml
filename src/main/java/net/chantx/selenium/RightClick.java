package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RightClick implements Action {
  private static final Logger LOG =
    Logger.getLogger(RightClick.class.getName());

  public Elem elem;

  public WebDriver dwork (WebDriver driver, String[] args) {
    LOG.info("RightClick elem: " + elem.by + " - " + elem.getQuery(args));
    Actions action = new Actions(driver);
    action.contextClick(elem.get(driver, args)).perform();
    return driver;
  }
}

package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class PressKeys implements Action {
  private static final Logger LOG = Logger.getLogger(PressKeys.class.getName());

  public Elem elem;
  public CharSequence[] keys;

  public WebDriver dwork (WebDriver driver) {
    String s = keys[0].toString();
    for (int i = 1; i < keys.length; ++i) {
      s += "," + keys[i];
    }
    LOG.info("PressKeys elem: " + elem.by + " - " + elem.query + "\nkeys: " + s);
    elem.get(driver).sendKeys(keys);
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    return dwork(driver);
  }
}

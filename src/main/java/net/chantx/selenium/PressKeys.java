package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class PressKeys implements Action {
  private static final Logger LOG = Logger.getLogger(PressKeys.class.getName());

  public Elem elem;
  public CharSequence[] keys;

  public WebDriver dwork (WebDriver driver, String[] args) {
    log(args);
    elem.get(driver, args).sendKeys(keys);
    return driver;
  }

  private void log (String[] args) {
    String s = keys[0].toString();
    for (int i = 1; i < keys.length; ++i) {
      s += "," + keys[i];
    }
    LOG.info("PressKeys elem: " + elem.by + " - "
        + elem.getQuery(args) + "\nkeys: " + s);
  }
}

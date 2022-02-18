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
    StringBuffer buf = new StringBuffer();
    buf.append(keys[0]);
    for (int i = 1; i < keys.length; ++i) {
      buf.append(",").append(keys[i]);
    }
    LOG.info("PressKeys elem: " + elem.getQuery(args) + "\nkeys: " + buf);
  }
}

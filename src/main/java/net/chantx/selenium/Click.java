package net.chantx.selenium;

import java.util.Map;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class Click implements Action {
  private static final Logger LOG = Logger.getLogger(Click.class.getName());

  public Elem elem;

  public WebDriver execute (WebDriver driver, Map<String, String> args) {
    assert elem != null;
    LOG.info("Click elem: " + elem.getQuery(args));
    elem.get(driver, args).click();
    return driver;
  }
}

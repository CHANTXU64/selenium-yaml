package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.logging.Logger;

public class Send implements Action {
  private static final Logger LOG = Logger.getLogger(Send.class.getName());

  public Elem elem;
  public String text;

  public WebDriver execute (WebDriver driver, Map<String, String> args) {
    assert elem != null;
    assert text != null;
    String s = Args.replace(text, args);
    LOG.info("Send elem: " + elem.getQuery(args) + " - " + s);
    elem.get(driver, args).sendKeys(s);
    return driver;
  }
}

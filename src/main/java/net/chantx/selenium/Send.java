package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Send implements Action {
  private static final Logger LOG = Logger.getLogger(Send.class.getName());

  public Elem elem;
  public String text;

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = Args.replace(text, args);
    LOG.info("Send elem: " + elem.getQuery(args) + " - " + s);
    elem.get(driver, args).sendKeys(s);
    return driver;
  }
}

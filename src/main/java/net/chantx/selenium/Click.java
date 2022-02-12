package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class Click implements Action {
  private static final Logger LOG = Logger.getLogger(Click.class.getName());

  public Elem elem;

  public WebDriver dwork (WebDriver driver) {
    LOG.info("Click elem: " + elem.by + " - " + elem.query);
    elem.get(driver).click();
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    return dwork(driver);
  }
}

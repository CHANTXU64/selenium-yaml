package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class GotoUrl implements Action {
  private static final Logger LOG = Logger.getLogger(GotoUrl.class.getName());

  public String url;

  public WebDriver dwork (WebDriver driver) {
    LOG.info("Goto url: " + url);
    driver.get(url);
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    return dwork(driver);
  }
}

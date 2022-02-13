package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Log implements Action {
  private static final Logger LOG = Logger.getLogger(Log.class.getName());

  public String info;

  public WebDriver dwork (WebDriver driver) {
    LOG.info("Log: " + info);
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    return dwork(driver);
  }
}

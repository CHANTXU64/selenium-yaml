package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

//TODO add replace arg in url

public class GotoUrl implements Action {
  private static final Logger LOG = Logger.getLogger(GotoUrl.class.getName());

  public String url;

  public WebDriver dwork (WebDriver driver, String[] args) {
    LOG.info("Goto url: " + url);
    driver.get(url);
    return driver;
  }
}

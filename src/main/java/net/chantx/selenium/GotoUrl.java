package net.chantx.selenium;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class GotoUrl implements Action {
  private static final Logger LOG = Logger.getLogger(GotoUrl.class.getName());

  public String url;

  public WebDriver execute (WebDriver driver, String[] args) {
    LOG.info("Goto url: " + Args.replace(url, args));
    driver.get(url);
    return driver;
  }
}

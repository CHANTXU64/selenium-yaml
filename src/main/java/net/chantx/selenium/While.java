package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class While implements Action {
  private static final Logger LOG = Logger.getLogger(While.class.getName());

  public Condition condition;
  public Action continues;

  public WebDriver execute (WebDriver driver, String[] args) {
    LOG.info("While elem " + condition.log(args));
    if (condition.isTrue(driver, args)) {
      if (continues != null) {
        continues.execute(driver, args);
      }
    }
    return driver;
  }
}

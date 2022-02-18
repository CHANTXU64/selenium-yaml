package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class If implements Action {
  private static final Logger LOG = Logger.getLogger(If.class.getName());

  public Condition condition;
  public Action then;
  public Action el;

  public WebDriver dwork (WebDriver driver, String[] args) {
    LOG.info("If elem " + condition.log(args));
    if (condition.isTrue(driver, args)) {
      if (then != null) {
        then.dwork(driver, args);
      }
    } else {
      if (el != null) {
        el.dwork(driver, args);
      }
    }
    return driver;
  }
}

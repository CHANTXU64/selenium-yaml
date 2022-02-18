package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class If implements Action {
  private static final Logger LOG = Logger.getLogger(If.class.getName());

  public Elem elem;
  public Condition.Operator operator;
  public String arg;
  public Action then;
  public Action el;

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = Args.replace(arg, args);
    LOG.info("If elem " + elem.getQuery(args) + " " + operator + " " + s + ".");
    if (Condition.isTrue(driver, elem, operator, s, args)) {
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

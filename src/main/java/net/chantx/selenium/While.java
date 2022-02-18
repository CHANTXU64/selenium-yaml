package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class While implements Action {
  private static final Logger LOG = Logger.getLogger(While.class.getName());

  public Elem elem;
  public Condition.Operator operator;
  public String arg;
  public Action continues;

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = Args.replace(arg, args);
    LOG.info("While elem " + elem.getQuery(args)
        + " " + operator + " " + s + ".");
    if (Condition.isTrue(driver, elem, operator, s, args)) {
      if (continues != null) {
        continues.dwork(driver, args);
      }
    }
    return driver;
  }
}

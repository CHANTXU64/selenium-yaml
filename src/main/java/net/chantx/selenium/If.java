package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class If implements Action {
  private static final Logger LOG = Logger.getLogger(If.class.getName());

  public Elem elem;
  public Operator operator;
  public String arg;
  public Action then;
  public Action el;

  public enum Operator {
    valueEqual, valueNotEqual, valueLessThan, valueLessThanOrEqual,
    valueGreaterThan, valueGreaterThanOrEqual, textEqual, textNotEqual,
    textLessThan, textLessThanOrEqual, textGreaterThan, textGreaterThanOrEqual,
    elemVisible, elemNotVisible, elemExist, elemNotExist
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = Args.replace(arg, args);
    LOG.info("If elem " + elem.query + " " + operator + " " + s + ".");
    if (judge(driver, s, args)) {
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

  private boolean judge (WebDriver driver, String param, String[] args) {
    String value = "";
    if (operator == Operator.valueEqual
        || operator == Operator.valueNotEqual
        || operator == Operator.valueLessThanOrEqual
        || operator == Operator.valueGreaterThan
        || operator == Operator.valueGreaterThanOrEqual) {
      value = elem.get(driver, args).getAttribute("value");
    } else if (operator == Operator.textEqual
        || operator == Operator.textNotEqual
        || operator == Operator.textLessThan
        || operator == Operator.textLessThanOrEqual
        || operator == Operator.textGreaterThan
        || operator == Operator.textGreaterThanOrEqual) {
      value = elem.get(driver, args).getText();
    }
    return switch (operator) {
      case valueEqual -> value == param;
      case valueNotEqual -> value != param;
      case valueLessThan -> parseFloat(value) < parseFloat(param);
      case valueLessThanOrEqual -> parseFloat(value) <= parseFloat(param);
      case valueGreaterThan -> parseFloat(value) > parseFloat(param);
      case valueGreaterThanOrEqual -> parseFloat(value) >= parseFloat(param);
      case textEqual -> value == param;
      case textNotEqual -> value != param;
      case textLessThan -> parseFloat(value) < parseFloat(param);
      case textLessThanOrEqual -> parseFloat(value) <= parseFloat(param);
      case textGreaterThan -> parseFloat(value) > parseFloat(param);
      case textGreaterThanOrEqual -> parseFloat(value) >= parseFloat(param);
      case elemVisible -> elemVisible(driver, args);
      case elemNotVisible -> !elemVisible(driver, args);
      case elemExist -> elemExist(driver, args);
      case elemNotExist -> !elemExist(driver, args);
    };
  }

  private float parseFloat (String s) {
    return Float.parseFloat(s);
  }

  private boolean elemVisible (WebDriver driver, String[] args) {
    if (elemExist(driver, args)) {
      return elem.get(driver, args).isDisplayed();
    } else {
      return false;
    }
  }

  private boolean elemExist (WebDriver driver, String[] args) {
    if (elem.parent == null) {
      return !driver.findElements(elem.getBy(args)).isEmpty();
    } else {
      return !elem.parent.get(driver, args)
        .findElements(elem.getBy(args)).isEmpty();
    }
  }
}

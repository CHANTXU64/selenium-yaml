package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

  public WebDriver dwork (WebDriver driver) {
    LOG.info("If elem " + elem.query + " " + operator + " " + arg + ".");
    if (judge(driver, arg)) {
      if (then != null) {
        then.dwork(driver);
      }
    } else {
      if (el != null) {
        el.dwork(driver);
      }
    }
    return driver;
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = arg;
    if (isMatchARG(arg)) {
      s = args[getARGNumber(arg)];
    }
    LOG.info("If elem " + elem.query + " " + operator + " " + s + ".");
    if (judge(driver, s)) {
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

  private boolean judge (WebDriver driver, String param) {
    String value = "";
    if (operator == Operator.valueEqual
        || operator == Operator.valueNotEqual
        || operator == Operator.valueLessThanOrEqual
        || operator == Operator.valueGreaterThan
        || operator == Operator.valueGreaterThanOrEqual) {
      value = elem.get(driver).getAttribute("value");
    } else if (operator == Operator.textEqual
        || operator == Operator.textNotEqual
        || operator == Operator.textLessThan
        || operator == Operator.textLessThanOrEqual
        || operator == Operator.textGreaterThan
        || operator == Operator.textGreaterThanOrEqual) {
      value = elem.get(driver).getText();
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
      case elemVisible -> elemVisible(driver);
      case elemNotVisible -> !elemVisible(driver);
      case elemExist -> elemExist(driver);
      case elemNotExist -> !elemExist(driver);
    };
  }

  private float parseFloat (String s) {
    return Float.parseFloat(s);
  }

  private boolean elemVisible (WebDriver driver) {
    if (elemExist(driver)) {
      return elem.get(driver).isDisplayed();
    } else {
      return false;
    }
  }

  private boolean elemExist (WebDriver driver) {
    if (elem.parent == null) {
      return !driver.findElements(elem.getBy()).isEmpty();
    } else {
      return !elem.parent.get(driver).findElements(elem.getBy()).isEmpty();
    }
  }

  private boolean isMatchARG (String input) {
    boolean isMatch = Pattern.matches("___ARG_\\d+___", input);
    return isMatch;
  }

  private int getARGNumber (String input) {
    String pattern = "(?<=___ARG_)\\d+(?=___)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(input);
    if (m.find()) {
      return Integer.parseInt(m.group(0));
    } else {
      return -1;
    }
  }
}

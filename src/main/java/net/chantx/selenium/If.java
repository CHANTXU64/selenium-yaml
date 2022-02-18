package net.chantx.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    elemVisible, elemNotVisible, elemExist, elemNotExist,
    elemClickable, elemNotClickable
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    String s = Args.replace(arg, args);
    LOG.info("If elem " + elem.getQuery(args) + " " + operator + " " + s + ".");
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
      case valueEqual, textEqual -> value.equals(param);
      case valueNotEqual, textNotEqual -> !value.equals(param);
      case valueLessThan, textLessThan ->
        parseFloat(value) < parseFloat(param);
      case valueLessThanOrEqual, textLessThanOrEqual ->
        parseFloat(value) <= parseFloat(param);
      case valueGreaterThan, textGreaterThan ->
        parseFloat(value) > parseFloat(param);
      case valueGreaterThanOrEqual, textGreaterThanOrEqual ->
        parseFloat(value) >= parseFloat(param);
      case elemVisible -> elemVisible(driver, args);
      case elemNotVisible -> !elemVisible(driver, args);
      case elemExist -> elemExist(driver, args);
      case elemNotExist -> !elemExist(driver, args);
      case elemClickable -> elemClickable(driver, args);
      case elemNotClickable -> !elemClickable(driver, args);
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
    return !driver.findElements(elem.getBy(args)).isEmpty();
  }

  private boolean elemClickable (WebDriver driver, String[] args) {
    try {
      WebElement element = elem.get(driver, args);
      new WebDriverWait(driver, Duration.ofMillis(100))
        .until(ExpectedConditions.elementToBeClickable(element));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

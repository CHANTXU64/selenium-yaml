package net.chantx.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class Condition {
  public static enum Operator {
    valueEqual, valueNotEqual, valueLessThan, valueLessThanOrEqual,
    valueGreaterThan, valueGreaterThanOrEqual, textEqual, textNotEqual,
    textLessThan, textLessThanOrEqual, textGreaterThan, textGreaterThanOrEqual,
    elemVisible, elemNotVisible, elemExist, elemNotExist,
    elemClickable, elemNotClickable
  }

  public static boolean isTrue (WebDriver driver, Elem elem, Operator operator,
      String param, String[] args) {
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
      case elemVisible -> elemVisible(driver, elem, args);
      case elemNotVisible -> !elemVisible(driver, elem, args);
      case elemExist -> elemExist(driver, elem, args);
      case elemNotExist -> !elemExist(driver, elem, args);
      case elemClickable -> elemClickable(driver, elem, args);
      case elemNotClickable -> !elemClickable(driver, elem, args);
    };
  }

  private static float parseFloat (String s) {
    return Float.parseFloat(s);
  }

  private static boolean elemVisible (WebDriver driver,
      Elem elem, String[] args) {
    if (elemExist(driver, elem, args)) {
      return elem.get(driver, args).isDisplayed();
    } else {
      return false;
    }
  }

  private static boolean elemExist (WebDriver driver,
      Elem elem, String[] args) {
    return !driver.findElements(elem.getBy(args)).isEmpty();
  }

  private static boolean elemClickable (WebDriver driver,
      Elem elem, String[] args) {
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

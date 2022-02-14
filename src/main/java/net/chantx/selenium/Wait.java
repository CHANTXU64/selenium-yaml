package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Wait implements Action {
  private static final Logger LOG = Logger.getLogger(Wait.class.getName());

  public Elem elem;
  public UntilType until;
  public long timeout;

  public enum UntilType {
    visible, exist, notVisible, notExist
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    LOG.info("Wait elem: " + elem.by + " - "
        + elem.getQuery(args) + " - " + until + " - " + timeout);
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
      .until(getUntil(driver, args));
    return driver;
  }

  private ExpectedCondition<Boolean> getUntil (WebDriver driver, String[] args) {
    if (elem.parent == null) {
      By by = elem.getBy(args);
      return switch (until) {
        case visible -> ExpectedConditions
          .and(ExpectedConditions.visibilityOfElementLocated(by));
        case notVisible -> ExpectedConditions
          .not(ExpectedConditions.visibilityOfElementLocated(by));
        case exist -> ExpectedConditions
          .and(ExpectedConditions.presenceOfElementLocated(by));
        case notExist -> ExpectedConditions
          .not(ExpectedConditions.presenceOfElementLocated(by));
      };
    } else {
      WebElement parent = elem.parent.get(driver, args);
      By by = elem.getBy(args);
      return switch (until) {
        case visible -> ExpectedConditions.and(ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(parent, by));
        case notVisible -> ExpectedConditions.not(ExpectedConditions
            .visibilityOfNestedElementsLocatedBy(parent, by));
        case exist -> ExpectedConditions.and(ExpectedConditions
            .presenceOfNestedElementLocatedBy(parent, by));
        case notExist -> ExpectedConditions.not(ExpectedConditions
            .presenceOfNestedElementLocatedBy(parent, by));
      };
    }
  }
}

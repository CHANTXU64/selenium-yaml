package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Logger;

public class Wait implements Action {
  private static final Logger LOG = Logger.getLogger(Wait.class.getName());

  public Elem elem;
  public UntilType until;
  public long timeout;

  public enum UntilType {
    visible, exist, notVisible, notExist, clickable, notClickable
  }

  public WebDriver execute (WebDriver driver, Map<String, String> args) {
    LOG.info("Wait elem: " + elem.getQuery(args)
        + " - " + until + " - " + timeout);
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
      .until(getUntil(args));
    return driver;
  }

  private ExpectedCondition<Boolean> getUntil (Map<String, String> args) {
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
      case clickable -> ExpectedConditions
        .and(ExpectedConditions.elementToBeClickable(by));
      case notClickable -> ExpectedConditions
        .not(ExpectedConditions.elementToBeClickable(by));
    };
  }
}

package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elem {
  public Elem parent;
  public ByType by;
  public String query;

  public enum ByType {
    id, xpath, className, cssSelector, linkText, name, partialLinkText, tagName
  }

  public WebElement get (WebDriver driver, String[] args) {
    if (parent == null) {
      return driver.findElement(getBy(args));
    } else {
      return parent.get(driver, args).findElement(getBy(args));
    }
  }

  public String getQuery (String[] args) {
    return Args.replace(query, args);
  }

  public By getBy (String[] args) {
    String q = getQuery(args);
    return switch (by) {
      case id -> By.id(q);
      case xpath -> By.xpath(q);
      case className -> By.className(q);
      case cssSelector -> By.cssSelector(q);
      case linkText -> By.linkText(q);
      case name -> By.name(q);
      case partialLinkText -> By.partialLinkText(q);
      case tagName -> By.tagName(q);
    };
  }
}

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

  public WebElement get (WebDriver driver) {
    if (parent == null) {
      return driver.findElement(getBy());
    } else {
      return parent.get(driver).findElement(getBy());
    }
  }

  public By getBy() {
    return switch (by) {
      case id -> By.id(query);
      case xpath -> By.xpath(query);
      case className -> By.className(query);
      case cssSelector -> By.cssSelector(query);
      case linkText -> By.linkText(query);
      case name -> By.name(query);
      case partialLinkText -> By.partialLinkText(query);
      case tagName -> By.tagName(query);
    };
  }
}

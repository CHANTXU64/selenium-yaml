package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Elems {
  public Elem parent;
  public Elem.ByType by;
  public String query;

  public List<WebElement> get (WebDriver driver) {
    if (parent == null) {
      return driver.findElements(getBy());
    } else {
      return parent.get(driver).findElements(getBy());
    }
  }

  public By getBy () {
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

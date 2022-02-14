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

  public List<WebElement> get (WebDriver driver, String[] args) {
    if (parent == null) {
      return driver.findElements(getBy(args));
    } else {
      return parent.get(driver, args).findElements(getBy(args));
    }
  }

  public By getBy () {
    return getBy_main(query);
  }

  public By getBy (String[] args) {
    return getBy_main(Args.replace(query, args));
  }

  private By getBy_main (String q) {
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

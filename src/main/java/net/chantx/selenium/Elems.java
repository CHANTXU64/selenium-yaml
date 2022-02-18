package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Elems {
  public Elem parent;
  public String query;

  public List<WebElement> get (WebDriver driver, String[] args) {
    return driver.findElements(getBy(args));
  }

  public String getQuery (String[] args) {
    if (parent == null) {
      return Args.replace(query, args);
    } else {
      return parent.getQuery(args) +
        Args.replace(query, args).replaceAll("^\\.", "");
    }
  }

  public By getBy (String[] args) {
    String q = getQuery(args);
    return By.xpath(q);
  }
}

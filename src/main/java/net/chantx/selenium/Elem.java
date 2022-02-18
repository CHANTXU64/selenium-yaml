package net.chantx.selenium;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elem {
  public Elem parent;
  public String query;

  public WebElement get (WebDriver driver, Map<String, String> args) {
    return driver.findElement(getBy(args));
  }

  public String getQuery (Map<String, String> args) {
    if (parent == null) {
      return Args.replace(query, args);
    } else {
      return parent.getQuery(args) +
        Args.replace(query, args).replaceAll("^\\.", "");
    }
  }

  public By getBy (Map<String, String> args) {
    String q = getQuery(args);
    return By.xpath(q);
  }
}

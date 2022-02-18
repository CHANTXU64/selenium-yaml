package net.chantx.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elem {
  public Elem parent;
  public String query;

  public WebElement get (WebDriver driver, String[] args) {
    return driver.findElement(getBy(args));
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

package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

public interface Action {
  public WebDriver dwork (WebDriver driver);
  public WebDriver dwork (WebDriver driver, String[] args);
}


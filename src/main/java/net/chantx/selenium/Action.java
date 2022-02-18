package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

public interface Action {
  WebDriver execute (WebDriver driver, String[] args);
}


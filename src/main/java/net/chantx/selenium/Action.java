package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface Action {
  WebDriver execute (WebDriver driver, Map<String, String> args);
}


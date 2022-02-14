package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Send implements Action {
  private static final Logger LOG = Logger.getLogger(Send.class.getName());

  public Elem elem;
  public String[] text;

  public WebDriver dwork (WebDriver driver) {
    return main(driver, text);
  }

  public WebDriver dwork (WebDriver driver, String[] args) {
    String[] s = new String[text.length];
    for (int i = 0; i < text.length; ++i) {
      s[i] = Args.replace(text[i], args);
    }
    return main(driver, s);
  }

  private WebDriver main (WebDriver driver, String[] input) {
    String s = input[0];
    for (int i = 1; i < input.length; ++i) {
      s += "," + input[i];
    }
    LOG.info("Send elem: " + elem.by + " - " + elem.query + "\ntext: " + s);
    elem.get(driver).sendKeys(input);
    return driver;
  }
}

package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class Send implements Action {
  private static final Logger LOG = Logger.getLogger(Send.class.getName());

  public Elem elem;
  public String[] text;

  public WebDriver dwork (WebDriver driver, String[] args) {
    assert text.length != 0;
    String[] s = new String[text.length];
    for (int i = 0; i < text.length; ++i) {
      s[i] = Args.replace(text[i], args);
    }
    log(s, args);
    elem.get(driver, args).sendKeys(s);
    return driver;
  }

  private void log (String[] str, String[] args) {
    StringBuffer buf = new StringBuffer();
    buf.append(str[0]);
    for (int i = 1; i < str.length; ++i) {
      buf.append(",").append(str[i]);
    }
    LOG.info("Send elem: " + elem.by + " - "
        + elem.getQuery(args) + "\ntext: " + buf);
  }
}

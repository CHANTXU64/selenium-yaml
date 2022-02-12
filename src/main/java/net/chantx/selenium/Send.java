package net.chantx.selenium;

import org.openqa.selenium.WebDriver;

import java.lang.String;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      String t = text[i];
      if (isMatchARG(t)) {
        s[i] = args[getARGNumber(t)];
      } else {
        s[i] = t;
      }
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

  private boolean isMatchARG (String input) {
    boolean isMatch = Pattern.matches("___ARG_\\d+___", input);
    return isMatch;
  }

  private int getARGNumber (String input) {
    String pattern = "(?<=___ARG_)\\d+(?=___)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(input);
    if (m.find()) {
      return Integer.parseInt(m.group(0));
    } else {
      return -1;
    }
  }
}

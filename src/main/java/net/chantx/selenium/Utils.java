package net.chantx.selenium;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.Map;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;

public class Utils {
  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  public static Map<String, Object> parse (String fileName) {
    return parse(fileName, new LoaderOptions());
  }

  public static Map<String, Object> parse (String fileName,
      LoaderOptions options) {
    Yaml p = new Yaml(options);
    Map<String, Object> m = null;
    try {
      m = p.load(new FileInputStream(fileName));
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert m != null;
    return m;
  }

  public static Map<String, Object> parse (InputStream input) {
    return parse(input, new LoaderOptions());
  }

  public static Map<String, Object> parse (InputStream input,
      LoaderOptions options) {
    Yaml p = new Yaml(options);
    Map<String, Object> m = p.load(input);
    assert m != null;
    return m;
  }

  public static void chromeVerboseLogging () {
    File tempFile = null;
    try {
      tempFile = File.createTempFile("chromedriver_log", "");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert tempFile != null;
    String tempFileName = tempFile.getPath();
    System.setProperty("webdriver.chrome.logfile", tempFileName);
    System.setProperty("webdriver.chrome.verboseLogging", "true");
    LOG.info("ChromeDriver Log Path: " + tempFileName);
  }

  public static WebDriver createWebDriver (boolean imagesEnabled) {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
    chromeOptions.setLogLevel(ChromeDriverLogLevel.INFO);
    if (!imagesEnabled) {
      chromeOptions.addArguments("blink-settings=imagesEnabled=false");
    }
    return new ChromeDriver(chromeOptions);
  }

  public static WebDriver execute (Map<String, Object> m,
      WebDriver driver, String actionName) {
    return execute(m, driver, actionName, map());
  }

  public static WebDriver execute (Map<String, Object> m,
      WebDriver driver, String actionName, Map<String, String> args) {
    assert m.get(actionName) instanceof Action;
    Action s = (Action) m.get(actionName);
    s.execute(driver, args);
    return driver;
  }

  public static WebElement getElem (Map<String, Object> m,
      WebDriver driver, String elemName) {
    return getElem(m, driver, elemName, map());
  }

  public static WebElement getElem (Map<String, Object> m,
      WebDriver driver, String elemName, Map<String, String> args) {
    assert m.get(elemName) instanceof Elem;
    Elem elem = (Elem) m.get(elemName);
    return elem.get(driver, args);
  }

  public static List<WebElement> getElems (Map<String, Object> m,
      WebDriver driver, String elemsName) {
    return getElems(m, driver, elemsName, map());
  }

  public static List<WebElement> getElems (Map<String, Object> m,
      WebDriver driver, String elemsName, Map<String, String> args) {
    assert m.get(elemsName) instanceof Elems;
    Elems elems = (Elems) m.get(elemsName);
    return elems.get(driver, args);
  }

  public static Map<String, String> map () {
    return new HashMap<>();
  }

  public static Map<String, String> map (String key, String value) {
    Map<String, String> m = new HashMap<>();
    m.put(key, value);
    return m;
  }
}

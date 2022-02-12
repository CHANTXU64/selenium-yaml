package net.chantx.selenium;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

public class Utils {
  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  public static Map<String, Object> parse (String fileName) {
    Yaml p = new Yaml();
    Map<String, Object> m = null;
    try {
      m = p.load(new FileInputStream(new File(fileName)));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
    WebDriver driver = new ChromeDriver(chromeOptions);
    return driver;
  }
}

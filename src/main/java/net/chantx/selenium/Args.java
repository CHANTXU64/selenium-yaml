package net.chantx.selenium;

import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Args {
  private static final Logger LOG = Logger.getLogger(Args.class.getName());

  public static String replace (String str, Map<String, String> args) {
    while (isMatch(str)) {
      str = replaceFirstMatch(str, args);
    }
    return str;
  }

  protected static boolean isMatch (String str) {
    Pattern r = Pattern.compile("___ARG_[a-z,A-Z,0-9]+___");
    Matcher m = r.matcher(str);
    return m.find();
  }

  protected static String replaceFirstMatch (String str,
      Map<String, String> args) {
    String key = getArgKey(str);
    String match = "___ARG_" + key + "___";
    if (args.get(key) == null) {
      LOG.warning("\"" + str + "\" key not exist, args: " + args.toString());
      return str.replace(match, "");
    }
    return str.replace(match, args.get(key));
  }

  protected static String getArgKey (String str) {
    String pattern = "(?<=___ARG_)[a-z,A-Z,0-9]+(?=___)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(str);
    if (m.find()) {
      return m.group(0);
    } else {
      LOG.warning("\"" + str + "\" get key error.");
      return "";
    }
  }
}

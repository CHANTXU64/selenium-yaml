package net.chantx.selenium;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Args {
  private static final Logger LOG = Logger.getLogger(Args.class.getName());

  public static String replace (String str, String[] args) {
    while (isMatch(str)) {
      str = replaceFirstMatch(str, args);
    }
    return str;
  }

  protected static boolean isMatch (String str) {
    Pattern r = Pattern.compile("___ARG_\\d+___");
    Matcher m = r.matcher(str);
    return m.find();
  }

  protected static String replaceFirstMatch (String str, String[] args) {
    int index = getArgIndexNumber(str);
    String match = "___ARG_" + index + "___";
    if (args.length - 1 < index) {
      LOG.warning("\"" + str + "\" index out of bounds, args: "
          + Arrays.toString(args));
      return str.replace(match, "");
    }
    return str.replace(match, args[index]);
  }

  protected static int getArgIndexNumber (String str) {
    String pattern = "(?<=___ARG_)\\d+(?=___)";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(str);
    if (m.find()) {
      return Integer.parseInt(m.group(0));
    } else {
      LOG.warning("\"" + str + "\" get index error.");
      return -1;
    }
  }
}

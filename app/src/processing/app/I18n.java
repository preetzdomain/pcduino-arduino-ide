/*
 * by Shigeru KANEMOTO at SWITCHSCIENCE.
 *
 * Extract strings to be translated by:
 *   % xgettext -L Java --from-code=utf-8 -k_ -d Resources_ja *.java
 * Extract and merge by:
 *   % xgettext -j -L Java --from-code=utf-8 -k_ -d Resources_ja *.java
 *
 * Edit "Resources_ja.po".
 * Convert to the properties file format by:
 *   % msgcat -p Resources_ja.po > Resources_ja.properties
 */

package processing.app;

import java.util.*;
import java.text.MessageFormat;

public class I18n {
  // start using current locale but still allow using the dropdown list later
  private static ResourceBundle i18n;

  // prompt text stuff

  static String PROMPT_YES;
  static String PROMPT_NO;
  static String PROMPT_CANCEL;
  static String PROMPT_OK;
  static String PROMPT_BROWSE;

  static protected void init (String language) {
    // there might be a null pointer exception ... most likely will never happen but the jvm gets mad
    try {
      if (language != null && language.trim().length() > 0) {
        Locale.setDefault(new Locale(language));
      }

      try {
        i18n = ResourceBundle.getBundle("processing.app.i18n.Resources", Locale.getDefault());
      }
      catch ( MissingResourceException e) {
        // add by liaods, bug fix for MissingResourceException
        Locale.setDefault(new Locale(Locale.getDefault().toString().toLowerCase()));
        i18n = ResourceBundle.getBundle("processing.app.i18n.Resources", Locale.getDefault());
      }

      PROMPT_YES = _("Yes");
      PROMPT_NO = _("No");
      PROMPT_CANCEL = _("Cancel");
      PROMPT_OK = _("OK");
      PROMPT_BROWSE = _("Browse");
    } catch (java.lang.NullPointerException e) {
    }
  }

  public static String _(String s) {
    try {
      return i18n.getString(s);
    }
    catch (MissingResourceException e) {
      return s;
    }
  }

  public static String format(String fmt, Object ... args) {
    return MessageFormat.format(fmt, args);
  }
  
  /**
   * Does nothing.
   * 
   * This method is an hack to extract words with gettext tool.
   */
  protected static void unusedStrings() {
    // These phrases are defined in the "platform.txt".
    _("Arduino AVR Boards");
    _("Arduino ARM (32-bits) Boards");

    // This word is defined in the "boards.txt".
    _("Processor");
  }
}

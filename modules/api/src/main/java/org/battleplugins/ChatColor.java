package org.battleplugins;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A color utility mainly used in chat.
 */
public enum ChatColor {

    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    MAGIC('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r'),
    NONE(Character.MIN_VALUE);

    public static final char COLOR_CHAR = '\u00A7';
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + COLOR_CHAR + "[0-9A-FK-OR]");

    private final char code;
    private final boolean isFormat;
    private final String toString;
    private static final Map<Character, ChatColor> BY_CHAR = new HashMap<>();

    static {
        for (ChatColor color : values()) {
            BY_CHAR.put(color.code, color);
        }
    }

    ChatColor(char code) {
        this(code, false);
    }

    ChatColor(char code, boolean isFormat) {
        this.code = code;
        this.isFormat = isFormat;
        this.toString = new String(new char[] {COLOR_CHAR, code});
    }

    /**
     * The color character
     *
     * @return the color character
     */
    public char getChar() {
        return code;
    }

    /**
     * If the ChatColor is a format
     *
     * @return if the ChatColor is a format
     */
    public boolean isFormat() {
        return isFormat;
    }

    /**
     * If the ChatColor is a color
     *
     * @return the ChatColor is a format
     */
    public boolean isColor() {
        return !isFormat && (this != RESET && this != NONE);
    }

    /**
     * Gets the ChatColor from the given character
     *
     * @param character the given character
     * @return the ChatColor from the given character
     */
    public static ChatColor getByChar(char character) {
        return getByChar(String.valueOf(character));
    }

    /**
     * Gets the ChatColor from the given String
     *
     * @param str the given String
     * @return the ChatColor from the given String
     */
    public static ChatColor getByChar(String str) {
        return BY_CHAR.getOrDefault(str.charAt(0), ChatColor.NONE);
    }

    /**
     * Strips the given string from all colors and formats
     *
     * @param input the string to strip
     * @return the given string without colors and formats
     */
    public static String stripColor(String input) {
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    /**
     * Does a string replacement replacing the color character
     * with the given character
     *
     * @param altColorChar the alternative color code symbol
     * @param textToTranslate the text to do a replacement on
     * @return the string with the color and formatting replacements
     */
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    /**
     * Gets the last colors in a string
     *
     * @param input the string to get the last colors of
     * @return the last colors in a string
     */
    public static String getLastColors(String input) {
        StringBuilder result = new StringBuilder();
        int length = input.length();

        // Search backwards from the end as it is faster
        for (int index = length - 1; index > -1; index--) {
            char section = input.charAt(index);
            if (section == COLOR_CHAR && index < length - 1) {
                char c = input.charAt(index + 1);
                ChatColor color = getByChar(c);

                if (color != null && color != ChatColor.NONE) {
                    result.insert(0, color.toString());

                    // Once we find a color or reset we can stop searching
                    if (color.isColor() || color.equals(RESET)) {
                        break;
                    }
                }
            }
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return toString;
    }
}
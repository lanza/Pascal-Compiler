package njl.frontend.pascal;

import java.util.Hashtable;
import java.util.HashSet;

import njl.frontend.TokenType;

public enum PascalTokenType implements TokenType {
    AND, ARRAY, BEGIN, CASE, CONST, DIV,
    DO, DOWNTO, ELSE, END, FILE, FOR,
    FUNCTION, GOTO, IF, IN, LABEL, MOD,
    NIL, NOT, OF, OR, PACKED, PROCEDURE,
    PROGRAM, RECORD, REPEAT, SET, THEN,
    TO, TYPE, UNTIL, VAR, WHILE, WITH,

    PLUS("+"), MINUS("-"), STAR("*"),
    SLASH("/"), COLON_EQUALS(":="),
    DOT("."), COMMA(","), SEMICOLON(";"),
    COLON(":"), QUOTE("'"), EQUALS("="),
    NOT_EQUALS("<>"), LESS_THAN("<"),
    LESS_EQUALS("<="),
    GREATER_EQUALS(">="), GREATER_THAN(">"),
    LEFT_PAREN("("), RIGHT_PAREN(")"),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"),
    LEFT_BRACE("{"), RIGHT_BRACE("}"),
    UP_ARROW("^"), DOT_DOT(".."),

    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = AND.ordinal();
    private static final int LAST_RESERVED_INDEX = WITH.ordinal();

    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int LAST_SPECIAL_INDEX = DOT_DOT.ordinal();

    private String text;

    PascalTokenType() {
        this.text = this.toString().toLowerCase();
    }

    PascalTokenType(String text) {
        this.text = text;
    }

    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        PascalTokenType values[] = PascalTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].text.toLowerCase());
        }
    }

    public static Hashtable<String, PascalTokenType> SPECIAL_SYMBOLS = new Hashtable<String, PascalTokenType>();
    static {
        PascalTokenType values[] = PascalTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].text, values[i]);
        }
    }
}

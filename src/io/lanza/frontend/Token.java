package io.lanza.frontend;

public class Token {
    public TokenType type;
    public String text;
    public Object value;
    public Source source;
    public int lineNum;
    public int position;

    public Token(Source source) throws Exception {
        this.source = source;
        this.lineNum = source.lineNum;
        this.position = source.currentPos;

        extract();
    }

    public int getLineNum() {
        return lineNum;
    }

    protected void extract() throws Exception {
        text = Character.toString(currentChar());
        value = null;
        nextChar();
    }
    protected char currentChar() throws Exception {
        return source.currentChar();
    }
    protected char nextChar() throws Exception {
        return source.nextChar();
    }
    protected char peekChar() throws Exception {
        return source.peekChar();
    }
}

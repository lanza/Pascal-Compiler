package njl.frontend.pascal;

import njl.frontend.*;
import njl.message.Message;
import njl.intermediate.SymTabEntry;

import njl.message.MessageType;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    protected static PascalErrorHandler errorHandler = new PascalErrorHandler();

    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();

        try {
            while (!((token = nextToken()) instanceof EofToken)) {
                TokenType tokenType = token.type;

                if (tokenType == PascalTokenType.IDENTIFIER) {
                    String name = token.text.toLowerCase();

                    SymTabEntry entry = symTabStack.lookup(name);
                    if (entry == null) {
                        entry = symTabStack.enterLocal(name);
                    }

                    entry.appendLineNumber(token.getLineNum());
                } else if (tokenType == PascalTokenType.ERROR) {
                    errorHandler.flag(token, (PascalErrorCode) token.value, this);
                }
            }

            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{token.lineNum, getErrorCount(), elapsedTime}));
        } catch (java.io.IOException ex) {
            errorHandler.abortTranslation(PascalErrorCode.IO_ERROR, this);
        }
    }

    public int getErrorCount() {

        return errorHandler.errorCount;
    }
}

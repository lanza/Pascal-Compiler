package io.lanza.frontend.pascal;

import io.lanza.frontend.*;
import io.lanza.message.Message;
import io.lanza.message.MessageType;

import static io.lanza.message.MessageType.PARSER_SUMMARY;

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
                if (tokenType != ERROR) {
                    sendMessage(new Message(MessageType.TOKEN, new Object[]{token.lineNum, token.position, tokenType, token.text, token.value}));
                } else {
                    errorHandler.flag(token, (PascalErrorCode) token.value, this);
                }
            }
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(MessageType.PARSER_SUMMARY, new Number[]{token.lineNum, getErrorCount(), elapsedTime}));
        } catch (java.io.IOException ex) {
            errorHandler.abortTranslation(IO_ERROR, this);
        }
    }

    public int getErrorCount() {

        return errorHandler.getErrorCount();
    }
}

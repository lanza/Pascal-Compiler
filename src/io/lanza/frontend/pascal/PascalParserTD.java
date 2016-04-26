package io.lanza.frontend.pascal;

import io.lanza.frontend.*;
import io.lanza.message.Message;

import static io.lanza.message.MessageType.PARSER_SUMMARY;

public class PascalParserTD extends Parser {
    public PascalParserTD(Scanner scanner) {
        super(scanner);
    }

    public void parse() throws Exception {
        Token token;
        long startTime = System.currentTimeMillis();

        while (!((token = nextToken()) instanceof EofToken)) {}

        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
        sendMessage(new Message(PARSER_SUMMARY, new Number[] {token.getLineNum(), getErrorCount(), elapsedTime}));
    }
    public int getErrorCount() {
        return 0;
    }

}

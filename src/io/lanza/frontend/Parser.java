package io.lanza.frontend;

import io.lanza.intermediate.ICode;
import io.lanza.intermediate.SymTab;
import io.lanza.message.Message;
import io.lanza.message.MessageListener;
import io.lanza.message.MessageHandler;
import io.lanza.message.MessageProducer;

public abstract class Parser implements MessageProducer {

    public static SymTab symTab;
    public ICode iCode;

    public static MessageHandler messageHandler;

    static  {
        symTab = null;
        messageHandler = new MessageHandler();
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    protected Scanner scanner;


    protected Parser(Scanner scanner) {
        this.scanner = scanner;
        this.iCode = null;
    }

    public abstract void parse() throws Exception;
    public abstract int getErrorCount();
    public Token currentToken() {
        return scanner.currentToken();
    }
    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }
}


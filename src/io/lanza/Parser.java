package io.lanza;


class SymTab {

}

class ICode {

}

public abstract class Parser implements MessageProducer {

    protected static SymTab symTab;
    protected static MessageHandler messageHandler;

    static  {
        symTab = null;
        messageHandler = new MessageHandler();
    }

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

    protected Scanner scanner;
    protected ICode iCode;

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


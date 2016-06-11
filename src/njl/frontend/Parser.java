package njl.frontend;

import njl.intermediate.SymTabFactory;
import njl.intermediate.SymTabStack;
import njl.message.MessageHandler;
import njl.message.MessageProducer;
import njl.intermediate.ICode;
import njl.intermediate.SymTab;

public abstract class Parser implements MessageProducer {

    public static SymTabStack symTabStack;
    public static SymTab symTab;
    public ICode iCode;

    public static MessageHandler messageHandler;

    static  {
        symTab = null;
        symTabStack = SymTabFactory.createSymTabStack();
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


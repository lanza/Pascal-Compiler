package njl.backend;

import njl.intermediate.ICode;
import njl.intermediate.SymTabStack;
import njl.message.MessageHandler;
import njl.message.MessageProducer;

public abstract class Backend implements MessageProducer {

    protected static SymTabStack symTabStack;
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    protected ICode iCode;

    public ICode getICode() {
        return iCode;
    }

    public SymTabStack getSymTabStack() {
        return symTabStack;
    }

    public abstract void process(ICode iCode, SymTabStack symTabStack) throws Exception;

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }
}


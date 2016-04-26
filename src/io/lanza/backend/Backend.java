package io.lanza.backend;

import io.lanza.frontend.ICode;
import io.lanza.frontend.Scanner;
import io.lanza.frontend.SymTab;
import io.lanza.frontend.Token;
import io.lanza.message.Message;
import io.lanza.message.MessageHandler;
import io.lanza.message.MessageListener;
import io.lanza.message.MessageProducer;

/**
 * Created by Nathan on 4/26/16.
 */
public abstract class Backend implements MessageProducer {

    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    protected SymTab symTab;
    protected ICode iCode;

    public abstract void process(ICode iCode, SymTab symTab) throws Exception;

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }
}


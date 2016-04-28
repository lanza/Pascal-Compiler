package io.lanza.message;

import io.lanza.message.Message;
import io.lanza.message.MessageHandler;

public interface MessageProducer {

    public MessageHandler getMessageHandler();

    default public void addMessageListener(MessageListener listener) {
        getMessageHandler().addListener(listener);
    }
    default public void removeMessageListener(MessageListener listener) {
        getMessageHandler().removeListener(listener);
    }
    default public void sendMessage(Message message) {
        getMessageHandler().sendMessage(message);
    }
}

package io.lanza.message;

import io.lanza.message.Message;

public interface MessageProducer {
    public void addMessageListener(MessageListener listener);
    public void removeMessageListener(MessageListener listener);
    public void sendMessage(Message message);
}

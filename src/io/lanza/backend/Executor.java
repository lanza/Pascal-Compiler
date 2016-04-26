package io.lanza.backend;

import io.lanza.frontend.SymTab;
import io.lanza.frontend.ICode;

import io.lanza.message.Message;
import io.lanza.message.MessageType;

public class Executor extends Backend {
    public void process(ICode icode, SymTab symTab) throws  Exception {
        long startTime = System.currentTimeMillis();
        float elapsedTime = (System.currentTimeMillis() - startTime)/1000f;
        int executionCount = 0;
        int runtimeErrors = 0;

        sendMessage(new Message(MessageType.INTERPRETER_SUMMARY, new Number[] {executionCount, runtimeErrors, elapsedTime}));
    }
}

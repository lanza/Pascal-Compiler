package io.lanza.backend.interpreter;

import io.lanza.backend.Backend;
import io.lanza.intermediate.SymTab;
import io.lanza.intermediate.ICode;

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

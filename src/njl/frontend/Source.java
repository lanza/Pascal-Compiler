package njl.frontend;

import njl.message.Message;
import njl.message.MessageType;
import njl.message.MessageHandler;
import njl.message.MessageProducer;

import java.io.BufferedReader;
import java.io.IOException;

public class Source implements MessageProducer {
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    private BufferedReader reader;
    private String line;
    public int lineNum;
    public int currentPos;

    public Source(BufferedReader reader) throws Exception {
        this.lineNum = 0;
        this.currentPos = -2;
        this.reader = reader;
    }

    public char currentChar() throws Exception {
        if (currentPos == -2) {
            readLine();
            return nextChar();
        } else if (line == null) {
            return EOF;
        } else if ((currentPos == -1) || (currentPos == line.length())) {
            return EOL;
        } else if (currentPos > line.length()) {
            readLine();
            return nextChar();
        } else {
            return line.charAt(currentPos);
        }
    }

    public char nextChar() throws Exception {
        ++currentPos;
        return currentChar();
    }

    public char peekChar() throws Exception {
        currentChar();
        if (line == null) {
            return EOF;
        }
        int nextPos = currentPos + 1;
        return nextPos < line.length() ? line.charAt(nextPos) : EOL;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    private void readLine() throws IOException {
        line = reader.readLine();
        currentPos = -1;

        if (line != null) {
            ++lineNum;
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[] { lineNum, line}));
        }
    }

    public void close() throws  Exception {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
}

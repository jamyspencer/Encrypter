/**
 * Created by jamy on 10/24/16.
 */

public class Message {
    protected String original;
    protected String encrypted;
    protected WordList words;
    Cypher key;

    Message() { }

    public String getEncrypted() {
        return encrypted;
    }

    public String getOriginal() {
        return original;
    }
}
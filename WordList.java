/**
 * Created by jamy on 10/25/16.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordList {
    Word root;

    WordList(StringBuilder sb, String regEx){
        parseWordsToList(sb, regEx);
    }
    class Word{
        StringBuilder w;
        Word next = null;

        Word(String s){
            w = new StringBuilder(s);
        }
    }
    public void parseWordsToList(StringBuilder sb, String regEx){
        Word thisWord = null;
        Pattern pat = Pattern.compile(regEx);
        Matcher match = pat.matcher(sb);
        boolean initial = true;
        if(root != null){
            root = null;
        }
        while(match.find()){
            if(initial){
                root = new Word(sb.substring(match.start(), match.end()));
                thisWord = root;
                initial =false;
            }
            else {
                while (thisWord.next != null) {
                    thisWord = thisWord.next;
                }
                thisWord.next = new Word(sb.substring(match.start(), match.end()));
            }
        }
    }
    protected  String copyBack(){
        StringBuilder sb = new StringBuilder();
        Word traverse = root;
        while(traverse != null){
            sb.append(traverse.w.toString()).append(' ');
            traverse= traverse.next;
        }
        return sb.toString();
    }
}
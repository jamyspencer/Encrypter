import java.util.regex.Pattern;

/**
 * Created by jamy on 10/25/16.
 */
public class Decrypter extends Message {

    Decrypter(StringBuilder sb) throws Cypher.errCorruptCypherException{
        String pCypher = sb.substring(0, 9);
        sb.delete(0,9);
        try{key = new Cypher(pCypher);}
        catch(Cypher.errCorruptCypherException e){
            throw new Cypher.errCorruptCypherException(pCypher);
        }
        encrypted = sb.toString().trim();
        words = new WordList(sb, "[\\S]+");

        WordList.Word traverse = words.root;
        while(traverse != null){
            for(int i = (traverse.w.length() - 1) - ((traverse.w.length()-1) % key.getZ()); i >= 0; i -= key.getZ()){
                traverse.w.deleteCharAt(i);
            }
            traverse.w.trimToSize();
            if(traverse.w.toString().matches("[\\S]+\\p{Punct}" + (key.getGrp2()) )){
                traverse.w.delete(traverse.w.length()-3, traverse.w.length());
            }
            traverse.w.delete(traverse.w.length()-2,traverse.w.length());
            traverse.w.insert(0,traverse.w.charAt(traverse.w.length()-1));
            traverse.w.delete(traverse.w.length()-1,traverse.w.length());
            traverse.w.trimToSize();
            traverse = traverse.next;
        }
        original = words.copyBack();
    }
}
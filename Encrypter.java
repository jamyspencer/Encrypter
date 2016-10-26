/**
 * Created by jamy on 10/25/16.
 */
public class Encrypter extends Message {

    Encrypter(String s){
        original = s.trim();
        key = new Cypher();
        Encrypt();
    }

    protected void Encrypt(){
        String s = original;
        StringBuilder sb;
        char check = 'a';

        //delete punctuation

        sb = new StringBuilder(original);

        for(int i = 0; i < sb.length(); i++){
            check = (sb.charAt(i));
            //lowercase all letters
            if(Character.isUpperCase(sb.charAt(i))){
                sb.setCharAt(i, Character.toLowerCase(check));
            }
            else if(i < sb.length()-1){
                if(sb.substring(i,i+1).matches("[\\p{Punct}]")){
                    sb.deleteCharAt(i);
                }
            }
        }
        words = new WordList(sb, "\\w+(\\p{Punct})|\\w+");
        WordList.Word traverse = words.root;
        int len;
        while(traverse != null){
            //move first to last letter
            len = traverse.w.length();
            traverse.w.append(traverse.w.charAt(0));
            traverse.w.deleteCharAt(0);
            //append x1 and x2
            traverse.w.append(key.getX1());
            traverse.w.append(key.getX2());
            //if first letter of word is a vowel append y1x3x4
            if(Character.toString(traverse.w.charAt(len-1)).matches("[aeiou]")){
                traverse.w.append(key.getY1());
                traverse.w.append(key.getX3());
                traverse.w.append(key.getX4());
            }
            for(int i = 0; i < traverse.w.length(); i += key.getZ()){
                traverse.w.insert(i, key.getY2());
            }
            //next list item
            traverse = traverse.next;
        }

        encrypted = words.copyBack();
    }
}
/**
 * Created by jamy on 10/24/16.
 */
import java.util.Random;

public class Cypher {

    private char    x1,
                    x2,
                    x3,
                    x4,
                    y1,
                    y2;
    private int     z;
    private StringBuilder fullCypher;

    static class errCorruptCypherException extends Exception{
        String s;

        public errCorruptCypherException(String sin){
            this.s = sin;
        }
    }

    public Cypher(){
        x1 = Rando(0);
        x2 = Rando(0);
        x3 = Rando(0);
        x4 = Rando(0);
        y1 = Rando(1);
        y2 = Rando(1);
        Rando(2);
        fullCypher =  new StringBuilder();
        fullCypher.append(x1).append(x2).append("-").append(y1);
        fullCypher.append(x3).append(x4).append("-").append(z).append(y2);
    }

    public Cypher(String s) throws errCorruptCypherException {
        if(!s.matches("[a-zA-Z]{2}[-]\\p{Punct}[a-zA-Z]{2}[-][0-9]\\p{Punct}")){
            throw new errCorruptCypherException(s);
        }
        else{
            fullCypher = new StringBuilder(s);
            parseCypher();
        }
    }

    private void parseCypher(){
        x1 = fullCypher.charAt(0);
        x2 = fullCypher.charAt(1);
        x3 = fullCypher.charAt(4);
        x4 = fullCypher.charAt(5);
        y1 = fullCypher.charAt(3);
        y2 = fullCypher.charAt(8);
        z = Character.getNumericValue(fullCypher.charAt(7));
    }

    private char Rando(int x) {
        boolean alpha = false,
                specialChar = false;
        char val = 'a';
        Random rand = new Random();
        if (x == 0) {
            while (!alpha) {
                val = ((char) (rand.nextInt(58) + 65));
                if (Character.isLetter(val)) {alpha = true;}
                else {alpha = false;}
            }
        }
        else if(x == 1){
            while(!specialChar){
                val = ((char) (rand.nextInt(32) + 33));
                if(Character.isDigit(val)){specialChar = false;}
                else {specialChar = true;}
            }
        }
        else if(x == 2){
            z = (rand.nextInt(4) + 2);
        }
    return val;
    }
    public String getGrp1(){
        StringBuilder t = new StringBuilder().append(x1).append(x2);
        return t.toString();
    }

    public String getGrp2(){
        StringBuilder t = new StringBuilder().append(x3).append(x4);
        return t.toString();
    }

    public char getX1() {
        return x1;
    }

    public char getX2() {return x2;}

    public char getX3() {return x3;}

    public char getX4() {return x4;}

    public char getY1() { return y1; }

    public char getY2() {return y2;}

    public int getZ() {return (z);}

    public String getFullCypher() {return fullCypher.toString();}
}
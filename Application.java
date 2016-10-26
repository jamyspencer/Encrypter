/**
 * Created by jamy on 10/24/16.
 */
import java.util.Scanner;
public class Application{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder pass = new StringBuilder();

        System.out.printf("Enter a message to encrypt: \n");
        Encrypter m = new Encrypter(in.nextLine());

        pass.append(m.key.getFullCypher());
        pass.append(" ").append(m.getEncrypted());

        System.out.printf("%s\n", pass);
        try{
            Decrypter read = new Decrypter(pass);
            System.out.printf("%s\n", read.getOriginal());
        }
        catch (Cypher.errCorruptCypherException e){
            System.out.println("Sorry, " + e.s +" is a corrupt key");
        }
    }
}
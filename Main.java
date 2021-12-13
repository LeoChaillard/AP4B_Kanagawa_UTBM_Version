import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  public static void main(String [] args) throws IOException {
    int card_ID = 2;
    int attribute = 7;

    String[] data = Files.readAllLines(Paths.get("cards_format.txt")).get(card_ID).split("\t");


    if (attribute == 7) {
      String[] d = data[attribute].split("\\*");
      System.out.println ("number: " + d[0] + "\nattribute: " + d[1]);
    } else {
      System.out.println (data[attribute]);
    }
	}
}

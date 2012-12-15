import java.io.*;
import java.util.*;

class GroupedWordCheckerTest{
  public static void main(String args[]){
    System.out.print("Enter a String of words separated by spaces\n : ");

    Scanner kbInput = new Scanner(System.in);
    String inputLine = kbInput.nextLine();

    String words[] = inputLine.split(" ");

    GroupedWordChecker checkInput = new GroupedWordChecker();
    System.out.println(checkInput.testWords(words) + " words are grouped");
  }
}

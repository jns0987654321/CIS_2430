import java.io.*;
import java.util.*;

class GroupedWordChecker{
  public int testWords(String words[]){
    int count = 0;

    for(int i=0; i < words.length; i++)
      if(isGrouped(words[i]))
        count++;

    return count;
  }

  public boolean isGrouped(String word){
    for(int i = 0; i < word.length() - 1; i++){
      if(word.charAt(i) != word.charAt(i+1)){
        if(word.indexOf(word.charAt(i), i+1) >= 0)
          return false;
      }
    }

    return true;
  }
}

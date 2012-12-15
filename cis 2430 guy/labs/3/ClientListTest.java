import java.util.*;

public class ClientListTest {
  private static ArrayList<Client> clientList = new ArrayList<Client>();

  private static boolean isDuplicate(Client client){
    for(int i = 0 ; i < clientList.size(); i++){
      if(client.equals(clientList.get(i)))
        return true;
    }
    return false;
  }
  
  public static void main(String[] args) {
    Scanner kbInput = new Scanner(System.in);
    
    System.out.println("Enter Names: (Blank line to exit)");
    String input;

    do{
      System.out.print(" : ");
      input = kbInput.nextLine();
      if(input.length() == 0)
        break;

      Client client = new Client(input);
      if(!clientList.contains(client))
        clientList.add(client);
      
    }while(true);
    
    System.out.println("Printing All Clients After Removing Duplicates: ");
    for(int i = 0 ; i < clientList.size(); i++){
      System.out.println(clientList.get(i));
    }
  }
}

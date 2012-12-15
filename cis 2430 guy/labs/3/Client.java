public class Client {
  String firstName, lastName;

  public Client(String name){
    String names[] = name.split(",");
    if(names.length == 2){
      lastName = names[0].trim();
      firstName = names[1].trim();
    } else {
      names = name.split(" ");
      firstName = names[0].trim();
      lastName = names[1].trim();
    }
  }

  public String toString(){
    return firstName + " " + lastName;
  }

  public boolean equals(Object otherObject){
    if(otherObject == null)
      return false;
    else if(otherObject.getClass() != this.getClass())
      return false;

    Client otherClient = (Client)otherObject;
    if(firstName.equals(otherClient.firstName) && lastName.equals(otherClient.lastName))
      return true;

    return false;
  }
}

package org.example;

public class Main {
    public static void main(String[] args) {
       System.out.println("================   Task1   ================");
       Task1.createUserPOST();
       Task1.updateUserPUT(10);
       Task1.deleteUser(10);
       Task1.getUserID(10);
       Task1.getUserUsername("Moriah.Stanton");
       Task1.getUserAll();
       System.out.println();
       System.out.println("================   Task2   ================");
       Task2.getComments(10);
       System.out.println();
       System.out.println("================   Task3   ================");
       int userID = 5;
       System.out.println("List tasks for userID " + userID +":" + Task3.getListTask(userID));
    }

}
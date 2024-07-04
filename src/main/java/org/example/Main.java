package org.example;

public class Main {
    public static void main(String[] args) {
       Task1 task1 = new Task1();
       System.out.println("================   Task1   ================");
       task1.createUserPOST();
       task1.updateUserPUT(10);
       task1.deleteUser(10);
       task1.getUserID(10);
       task1.getUserUsername("Moriah.Stanton");
       task1.getUserAll();
       System.out.println();
       System.out.println("================   Task2   ================");
       Task2 task2 = new Task2();
       task2.getComments(10);
       System.out.println();
       System.out.println("================   Task3   ================");
       Task3 task3 = new Task3();
       int userID = 5;
       System.out.println("List tasks for userID " + userID +":" + task3.getListTask(userID));
    }

}
package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList myList = new UsersArrayList();
        User user1 = new User("Alice", 1500);
        User user2 = new User("Eve", 800);
        User user3 = new User("Chuck", 300);
        User user4 = new User("Dave", 1200);
        User user5 = new User("Frank", 2000);
        User user6 = new User("Grace", 250);
        User user7 = new User("Hank", 600);
        User user8 = new User("Ivy", 1700);
        User user9 = new User("Jack", 1000);
        User user10 = new User("Kelly", 1800);
        User user11 = new User("Liam", 900);
        User user12 = new User("Mia", 1600);
        User user13 = new User("Noah", 700);
        User user14 = new User("Olivia", 1400);
        User user15 = new User("Peter", 1100);
        myList.addUser(user1);
        myList.addUser(user2);
        myList.addUser(user3);
        myList.addUser(user4);
        myList.addUser(user5);
        myList.addUser(user6);
        myList.addUser(user7);
        myList.addUser(user8);
        myList.addUser(user9);
        myList.addUser(user10);
        myList.addUser(user11);
        myList.addUser(user12);
        myList.addUser(user13);
        myList.addUser(user14);
        myList.addUser(user15);
        System.out.println("Size of list: " + myList.getSize());
        System.out.println("myList.RetrieveNumberUsers() = " + myList.RetrieveNumberUsers());
        try {
            System.out.println(myList.RetrieveUserId(5).getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(myList.RetrieveUserIndex(4).getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

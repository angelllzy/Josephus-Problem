import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode front;      // a PersonNode pointer that tracks first node in the list
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
         PersonNode tail = null; // last node added so far
         
         // read file
         while (file.hasNextLine()) {
            String name = file.nextLine().trim();
            if (name.length() > 0) {
               PersonNode node = new PersonNode(name);
               if (front == null) {
                  front = node;     // first person
               } else {
               tail.next = node;    // last person will get attached by the node
            }
            tail = node;            // new node is the last person
            size++;
         }
         
         // make the ring circular by attaching last node's next to front
         tail.next = front;
         
         // generate, print, and save the random elimination count
         Random rand = new Random();
         eliminationCount = rand.nextInt(Math.max(1, size / 2)) + 1;
         System.out.println("=== Elimination count is " + eliminationCount + " ===");
         

      } catch (FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   // optional helper method for constructing the circle
   private void add(String val) {
   }
   
   public void eliminate() {
      // count to the elimination count
      PersonNode prev = front;
      for (int i = 1; i < size; i++) {
         prev = prev.next;
      }
      
      // counting starts from the first person
      PersonNode curr = front;
      
      // move forward, keeping prev one node behind curr
      for (int i = 1; i < eliminationCount; i++) {
         prev = curr;            // prev becomes curr
         curr = curr.next;       // curr becomes the next node in front
      }
      
      // print who will be eliminated
      System.out.println(curr.name + " eliminated!");
      
      // eliminate the person and update "front" of the circle and size
      prev.next = curr.next;     // skip the eliminated person
      front = curr.next;         // next round starts with the next person after them
      size--;                    // size decreases
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      if (size == 1) {
         return true;
      } else {
         return false;
      }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      
      // if many, print survivors (watch out for infinite loop since list is circular)

      return "";
   }

}
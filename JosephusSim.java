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
    }  
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      if (isOver()) {
         return front.name + " is the last survivor!";
      }
      
      // if many, print survivors (watch out for infinite loop since list is circular)
      String result = "";
      PersonNode curr = front;
      int num = 1;
      do {
         if (num > 1) {
            result += ", ";
         }
         result += num + "-" + curr.name;
         curr = curr.next;
         num++;
      } while (curr != front);
      return result;
   }
}

/* Output:
 ----jGRASP exec: java JosephusDriver
=== Elimination count is 6 ===
1-Ziru, 2-Marshall, 3-Sophie, 4-Mohamed, 5-Unumergen, 6-Anthony, 7-Angel, 8-Lukas, 9-Phoung, 10-Maharshi, 11-Mya, 12-Yunfeng

Continue elimination? <press enter>

Anthony eliminated!
1-Angel, 2-Lukas, 3-Phoung, 4-Maharshi, 5-Mya, 6-Yunfeng, 7-Ziru, 8-Marshall, 9-Sophie, 10-Mohamed, 11-Unumergen

Continue elimination? <press enter>

Yunfeng eliminated!
1-Ziru, 2-Marshall, 3-Sophie, 4-Mohamed, 5-Unumergen, 6-Angel, 7-Lukas, 8-Phoung, 9-Maharshi, 10-Mya

Continue elimination? <press enter>

Angel eliminated!
1-Lukas, 2-Phoung, 3-Maharshi, 4-Mya, 5-Ziru, 6-Marshall, 7-Sophie, 8-Mohamed, 9-Unumergen

Continue elimination? <press enter>

Marshall eliminated!
1-Sophie, 2-Mohamed, 3-Unumergen, 4-Lukas, 5-Phoung, 6-Maharshi, 7-Mya, 8-Ziru

Continue elimination? <press enter>

Maharshi eliminated!
1-Mya, 2-Ziru, 3-Sophie, 4-Mohamed, 5-Unumergen, 6-Lukas, 7-Phoung

Continue elimination? <press enter>

Lukas eliminated!
1-Phoung, 2-Mya, 3-Ziru, 4-Sophie, 5-Mohamed, 6-Unumergen

Continue elimination? <press enter>

Unumergen eliminated!
1-Phoung, 2-Mya, 3-Ziru, 4-Sophie, 5-Mohamed

Continue elimination? <press enter>

Phoung eliminated!
1-Mya, 2-Ziru, 3-Sophie, 4-Mohamed

Continue elimination? <press enter>

Ziru eliminated!
1-Sophie, 2-Mohamed, 3-Mya

Continue elimination? <press enter>

Mya eliminated!
1-Sophie, 2-Mohamed

Continue elimination? <press enter>

Mohamed eliminated!
Sophie is the last survivor!

 ----jGRASP: Operation complete. */
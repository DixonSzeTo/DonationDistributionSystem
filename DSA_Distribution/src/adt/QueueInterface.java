 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Asus
 */
public interface QueueInterface<T> {
  public Iterator<T> getIterator();

    
  public void enqueue(T newDonation);
  //Add donation to queue
  
  public T dequeue();
  //Remove first in queue
  
  public T getFront();
  //Get First in queue
  
  public boolean isEmpty();
  //Check Queue

  public void clear();
  //Clear queue
  
  
  public int size();
  
  public T get(int lastindex);
  
}

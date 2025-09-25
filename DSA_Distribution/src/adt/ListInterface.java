package adt;

/**
 *
 * @author youch
 */
public interface ListInterface<T> {

    public void add(T newEntry);

    public T remove(int givenPosition);

    public boolean update(int givenPosition, T newEntry);

    public T getDetails(int givenPosition);

    public void display();

    public T filter(T criteria);

    public boolean contains(T criteria);

    public String generateReport();

    public void clear();

    public boolean isEmpty();

    public boolean isFull();

    public int getCount();
    
}

package ua.nure.danylenko.task2;

public interface Iterator<E> extends java.util.Iterator<E> {
    // returns true if the iteration has more elements
    boolean hasNext();
    // returns the next element in the iteration
    E next();
    // removes the last element returned by this iterator
    // this method can be called only once per call to next()
    // method throws IllegalStateException if:
    // (1) the next method has not yet been called, or
    // (2) the remove method has already been called
    // after the last call to the next method
    void remove();;

}

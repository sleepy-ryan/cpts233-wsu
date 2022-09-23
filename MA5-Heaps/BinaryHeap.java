/*
 *  Assignment: Implementing Percolates
 *
 *  [this file]: Main Heap class
 *   Heaps Microassignment
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
    private ArrayList<AnyType> data = new ArrayList<>();
    private Integer currentSize = 0;
    private static final int DEFAULT_CAPACITY = 10;

    // Default constructor reserves 10 items worth of space
    public BinaryHeap () {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap (int reserve_size) {
        ensureHeapSize(reserve_size);
        currentSize = 0;
    }

    // Array initializer-based constructor
    //  Added every item in items to heap
    public BinaryHeap( AnyType [ ] items ) {
        this();             // Call default constructor
        for (AnyType item : items) {
            insert(item);
        }
    }

    // Quick hack of a print out of the heap's data
    public void dump() {
        for (AnyType val : data) {
            System.out.print(val + ", ");
        }
    }

    // Returns a SHALLOW COPY of the data arraylist
    public ArrayList<AnyType> getData() {
        ArrayList<AnyType> newData = new ArrayList<>();
        for (AnyType item : data) {
            if( item != null) {
                newData.add(item);
            }
        }
        return newData;
    }

    // Expands the heap's internal sized ArrayList
    public void ensureHeapSize(int newSize) {
        data.ensureCapacity(newSize);    // Prevent excessive copying while we're adding
        while (data.size() < newSize) {
            data.add(null);
        }
    }

    public AnyType findMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }
        return data.get(0);
    }

    public boolean isEmpty() {
        return (currentSize <= 0);
    }

    public void makeEmpty() {
        while( !isEmpty() ) {
            deleteMin();
        }
    }

    public int size() {
        return currentSize;
    }

    public AnyType deleteMin() {
        if( isEmpty( ) ) {
            throw new IndexOutOfBoundsException( );
        }

        AnyType minItem = findMin( );
        data.set(0, data.get(--currentSize));
        data.set(currentSize, null);
        percolateDown( 0 );

        return minItem;
    }

    // ********************************************************************* //
    //  Microassignment Section: Implement percolations
    // ********************************************************************* //
// method to insert x into the heap
    public void insert(AnyType x) {

        if(currentSize == data.size()) // heap is full, expand the heap by twice its original capacity
            ensureHeapSize(currentSize*2);

        data.set(currentSize, x); // insert x at the end of list
        percolateUp(currentSize); // call percolate up to move x up the heap to its correct position
        currentSize++; // increment currentSize by 1
    }

    // helper method to percolate the item at index hole down the heap to maintain a min heap
    private void percolateDown( int hole )
    {
        // hole is a valid index of list
        if(hole < currentSize)
        {
            // obtain the index of the node's left and right children at the hole
            int minimunIndex = hole;
            int left = 2*hole+1;
            int right = 2*hole+2;

            // to the left Update minimunIndex to the left if a child exists and is less than the element at minimunIndex.
            if(left < currentSize && data.get(left).compareTo(data.get(minimunIndex)) < 0)
                minimunIndex = left;

           // If the right child exists and is smaller than the element at minimunIndex, the minimunIndex should be updated to the right.
            if(right < currentSize && data.get(right).compareTo(data.get(minimunIndex)) < 0)
                minimunIndex = right;

            // If the element at hole isn't in the right place, exchange the elements at hole and minimunIndex, then call pecolateDown with the new minimunIndex.
            if(minimunIndex != hole)
            {
                AnyType temp = data.get(minimunIndex);
                data.set(minimunIndex, data.get(hole));
                data.set(hole, temp);
                percolateDown(minimunIndex);
            }
        }
    }

    // Percolate the item at index hole up the heap to keep the heap at a minimum size.
    private void percolateUp(int hole)
    {
        // The currently selected element is not the root element.
        if(hole > 0)
        {
            int parent = (hole-1)/2;

           // The parent element is bigger than the element in the hole.
            if(data.get(parent).compareTo(data.get(hole)) > 0)
            {
               // Switch the elements at parent and hole, then call percolateUp with the parent index.
                AnyType temp = data.get(parent);
                data.set(parent, data.get(hole));
                data.set(hole, temp);

                percolateUp(parent);
            }
        }
    }

}

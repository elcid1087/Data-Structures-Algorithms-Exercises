// partition.java
// demonstrates partitioning an array
// to run this program: C>java PartitionApp
////////////////////////////////////////////////////////////////
class Partition
{
    private long[] theArray;          // ref to array theArray

    private int nElems;               // number of data items
    //--------------------------------------------------------------
    public Partition(int max)          // constructor
    {
        theArray = new long[max];      // create the array
        nElems = 0;                    // no items yet
    }
    //--------------------------------------------------------------
    public void insert(long value)    // put element into array
    {
        theArray[nElems] = value;      // insert it
        nElems++;                      // increment size
    }

    public long getElement(int index) {
        return theArray[index];
    }
    //--------------------------------------------------------------
    public int size()                 // return number of items
    { return nElems; }
    //--------------------------------------------------------------
    public void display()             // displays array contents
    {
        System.out.print("A=");
        for(int j=0; j<nElems; j++)    // for each element,
            System.out.print(theArray[j] + " ");  // display it
        System.out.println("");
    }
    //--------------------------------------------------------------

    public long findKthSmallest(int leftIndex, int rightIndex, int kthIndex) {
        // Adjust the offset, e.g. 7 smallest element is at index 7 - 1. Because the array starts with 0.
        kthIndex = kthIndex - 1;
        return recurseFindMedian(leftIndex, rightIndex, kthIndex);

    }

    private long recurseFindMedian(int leftIndex, int rightIndex, int kthIndex) {
        int pivotIndex = partitionIt(leftIndex, rightIndex);
        // base case
        if(pivotIndex == kthIndex) {
            return theArray[pivotIndex];
        } else if(pivotIndex > kthIndex) {
            return recurseFindMedian(leftIndex, pivotIndex - 1, kthIndex);
        } else {
            return recurseFindMedian(pivotIndex + 1, rightIndex, kthIndex);
        }

    }

    public int partitionIt(int left, int right)
    {
        int leftPtr = left - 1;           // right of first elem
        int rightPtr = right;         // left of pivot
        while(true)
        {
            while(leftPtr < right &&       // find bigger item
                    theArray[++leftPtr] < theArray[right])
                ;  // (nop)

            while(rightPtr > left &&       // find smaller item
                    theArray[--rightPtr] > theArray[right])
                ;  // (nop)
            if(leftPtr >= rightPtr)        // if pointers cross,
                break;                      //    partition done
            else                           // not crossed, so
                swap(leftPtr, rightPtr);    //    swap elements
        }  // end while(true)
        swap(leftPtr, right);
        return leftPtr;                   // return partition
    }  // end partitionIt()
    //--------------------------------------------------------------
    public void swap(int dex1, int dex2)  // swap two elements
    {
        long temp;
        temp = theArray[dex1];             // A into temp
        theArray[dex1] = theArray[dex2];   // B into A
        theArray[dex2] = temp;             // temp into B
    }  // end swap()
//--------------------------------------------------------------
}  // end class Partition
////////////////////////////////////////////////////////////////
class PartitionApp
{
    public static void main(String[] args)
    {
        int maxSize = 9;             // array size
        Partition arr;                 // reference to array
        arr = new Partition(maxSize);  // create the array

        arr.insert(49);
        arr.insert(3);
        arr.insert(12);
        arr.insert(1);
        arr.insert(27);
        arr.insert(15);
        arr.insert(9);
        arr.insert(10);
        arr.insert(11);
        arr.display();                // display unsorted array

        System.out.println("KthSmallest is : " + arr.findKthSmallest(0, arr.size() - 1, 5));
    }  // end main()
}  // end class PartitionApp
////////////////////////////////////////////////////////////////
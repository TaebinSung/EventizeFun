package model;

import java.util.*;

public class Test {

    List<Integer> numberList1 = new ArrayList<>();
    List<Integer> numberList2 = new ArrayList<>();


    // public Iterator<Integer> getIterator() {
    //     return new
    // }

    class getListIterator implements Iterator<Integer> {
        Iterator<Integer> list1 = numberList1.iterator();
        Iterator<Integer> list2 = numberList2.iterator();

        public void function() {

        }

        @Override
        public boolean hasNext() {
            return list1.hasNext() || list2.hasNext();
        }

        @Override
        public Integer next() {

            while (hasNext()) {

            }
            return null;
        }
    }
}

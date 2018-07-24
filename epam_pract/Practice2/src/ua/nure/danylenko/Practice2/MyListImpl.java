package ua.nure.danylenko.Practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

    private Object[] mas;
    private int count;

    private Object get(int index) {
        if (index >= count || index < 0) {
            return null;
        } else {
            if (mas[index] != null) {
                return mas[index];
            } else {
                return null;
            }
        }
    }

    private int indexOf(Object el) {
        if (el != null) {
            for (int i = 0; i < count; i++) {
                if (el.equals(get(i))) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (el == get(i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(Object e) {
        if (count == 0) {
            mas = new Object[1];
            mas[0] = e;
            count++;
        } else {
            Object[] tempMas = new Object[count + 1];
            System.arraycopy(mas, 0, tempMas, 0, count);
            tempMas[count] = e;
            mas = tempMas;
            count++;
        }
    }

    public void clear() {
        mas = new Object[0];
        count = 0;
    }

    public Object[] toArray() {
        Object[] copyOb = new Object[mas.length];
        System.arraycopy(mas, 0, copyOb, 0, count);
        return copyOb;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            Object[] tempMas = new Object[count - 1];
            System.arraycopy(mas, 0, tempMas, 0, index);
            System.arraycopy(mas, index + 1, tempMas, index, count - index - 1);
            mas = tempMas;
            count--;
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return count;
    }

    public boolean contains(Object o) {
        for (Object ob : mas) {
            if (ob != null) {
                if (ob.equals(o)) {
                    return true;
                }
            } else {
                if (o == null && ob == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsAll(MyList c) {
        Object[] ob = c.toArray();
        for (int i = 0; i < ob.length; i++) {
            if (!this.contains(ob[i])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String res = "[";
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                if (mas[i] != null) {
                    res += mas[i].toString() + ", ";
                } else {
                    res += "null, ";
                }
            } else {
                if (mas[i] == null) {
                    res += "null";
                } else {
                    res += mas[i].toString();
                }
            }
        }
        res += "]";
        return res;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;
        private boolean isNext = false;
        //private boolean isRemove = false;

        // returns true if the iteration has more elements
        public boolean hasNext() {
            return currentIndex < count;
        }

        // returns the next element in the iteration
        public Object next() {
            isNext = true;
            return mas[currentIndex++];
        }

        // removes from the underlying collection the last element
        // returned by this iterator
        public void remove() {
            if (isNext == false) {
                throw new IllegalStateException();
            } else {
                currentIndex--;
                MyListImpl.this.remove(get(currentIndex));
                isNext = false;
            }
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        private boolean isPrevious = false;

        // returns true if this list iterator has more elements when traversing
        // the list in the reverse direction
        public boolean hasPrevious() {
            if (super.currentIndex > 0) {
                return true;
            } else {
                return false;
            }
        }

        // returns the previous element in the list and moves the cursor
        // position backwards
        public Object previous() {
            isPrevious = true;
            return mas[--super.currentIndex];
        }


        // replaces the last element returned by next or previous with
        // the specified element
        public void set(Object e) {
            if (super.isNext == false && isPrevious == false) {
                throw new IllegalStateException();
            } else {
                if (isPrevious == true) {
                    MyListImpl.this.mas[super.currentIndex + 1] = e;
                } else {
                    MyListImpl.this.mas[super.currentIndex - 1] = e;
                }
                isPrevious = false;
                super.isNext = false;
            }
        }

        // replaces the last element returned by next or previous with
        // the specified element
        public void remove() {
            if (super.isNext == false && isPrevious == false) {
                throw new IllegalStateException();
            } else {
                if (isPrevious == true) {
                    MyListImpl.this.remove(get(++super.currentIndex));
                } else {
                    MyListImpl.this.remove(get(--super.currentIndex));
                }
                isPrevious = false;
                super.isNext = false;
            }
        }
    }
}

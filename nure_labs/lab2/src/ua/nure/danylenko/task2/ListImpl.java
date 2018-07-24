package ua.nure.danylenko.task2;

public class ListImpl<E> implements List<E> {

    private Object[] mas;
    private int count;

    public void add(E e) {
        if (count == 0) {
            mas = new Object[1];
            mas[0] = e;
            ++count;
        } else {
            Object[] tempMas = new Object[count + 1];
            System.arraycopy(mas, 0, tempMas, 0, count);
            tempMas[count] = e;
            mas = tempMas;
            count++;
        }
    }

    public void addAll(List<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    public void clear() {
        mas = new Object[0];
        count = 0;
    }

    public E get(int index) {
        if (index >= count || index < 0) {
            System.err.println("Внимание: объект с таким порядковым номером не существует" +
                    "будет возвращено значение null");
            return null;
        } else {
            if (mas[index] != null) {
                return (E) mas[index];
            } else {
                return null;
            }
        }
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

    public int indexOf(Object el) {
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

    public int size() {
        return count;
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

    public Object remove(int index) throws IndexOutOfBoundsException {
        if (index >= count || index < 0) {
            System.err.println("Ошибка! Указанного элемента нет в Списке!");
            throw new IndexOutOfBoundsException();
        } else {
            Object[] tempMas = new Object[count - 1];
            Object res = get(index);
            System.arraycopy(mas, 0, tempMas, 0, index);
            System.arraycopy(mas, index + 1, tempMas, index, count - index - 1);
            mas = tempMas;
            count--;
            return res;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                if (mas[i] != null) {
                    res.append(mas[i].toString()).append(", ");
                } else {
                    res.append("null, ");
                }
            } else {
                if (mas[i] == null) {
                    res.append("null");
                } else {
                    res.append(mas[i].toString());
                }
            }
        }
        res.append("]");
        return res.toString();
    }

    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<E> {
        private int currentIndex = 0;
        private boolean isNext = false;
        //private boolean isRemove = false;

        // returns true if the iteration has more elements
        public boolean hasNext() {
            return currentIndex < count;
        }

        // returns the next element in the iteration
        public E next() {
            isNext = true;
            return (E) mas[currentIndex++];
        }

        // removes from the underlying collection the last element
        // returned by this iterator
        public void remove() {
            if (isNext == false) {
                throw new IllegalStateException();
            } else {
                currentIndex--;
                ListImpl.this.remove(get(currentIndex));
                isNext = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("~~~ list A B C");
        System.out.println("~~~ Result: [A, B, C]");
        List<String> list = new ListImpl<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list);

        System.out.println("~~~ list2: D E F");
        System.out.println("~~~ Result: [D, E, F]");
        List<String> list2 = new ListImpl<>();
        list2.add("D");
        list2.add("E");
        list2.add("F");
        System.out.println(list2);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [A, B, C, D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ list.add(C)");
        System.out.println("~~~ Result: [A, B, C, D, E, F, C]");
        list.add("C");
        System.out.println(list);

        System.out.println("~~~ list.clear()");
        System.out.println("~~~ Result: []");
        list.clear();
        System.out.println(list);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ list.contains(E)");
        System.out.println("~~~ Result: true");
        System.out.println(list.contains("E"));

        System.out.println("~~~ list.contains(С)");
        System.out.println("~~~ Result: false");
        System.out.println(list.contains("C"));

        System.out.println("~~~ list.indexOf(D)");
        System.out.println("~~~ Result: 0");
        System.out.println(list.indexOf("D"));

        System.out.println("~~~ list.get(2)");
        System.out.println("~~~ Result: F");
        System.out.println(list.get(2));

        System.out.println("~~~ list.indexOf(F)");
        System.out.println("~~~ Result: 2");
        System.out.println(list.indexOf("F"));

        System.out.println("~~~ list.size()");
        System.out.println("~~~ Result: 3");
        System.out.println(list.size());

        System.out.println("~~~ list");
        System.out.println("~~~ Result: [D, E, F]");
        System.out.println(list);

        System.out.println("~~~ list.remove(1)");
        System.out.println("~~~ Result: [D, F]");
        list.remove(1);
        System.out.println(list);

        System.out.println("~~~ list.remove(F)");
        System.out.println("~~~ Result: [D]");
        list.remove("F");
        System.out.println(list);

        System.out.println("~~~ list.size()");
        System.out.println("~~~ Result: 1");
        System.out.println(list.size());

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ foreach list");
        System.out.println("~~~ Result: D D E F");
        for (String el : list) {
            System.out.print(el + " ");
        }
        System.out.println();

        System.out.println("~~~ Iterator it = list.iterator()");
        Iterator<String> it = list.iterator();

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: D");
        System.out.println(it.next());

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: D");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D, E, F]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: E");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D, F]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ it.next()");
        System.out.println("~~~ Result: F");
        System.out.println(it.next());

        System.out.println("~~~ it.remove()");
        System.out.println("~~~ Result: [D]");
        it.remove();
        System.out.println(list);

        System.out.println("~~~ list.remove(D)");
        System.out.println("~~~ Result: []");
        list.remove("D");
        System.out.println(list);

        System.out.println("~~~ list.addAll(list2)");
        System.out.println("~~~ Result: [D, E, F]");
        list.addAll(list2);
        System.out.println(list);

        System.out.println("~~~ foreach list");
        System.out.println("~~~ Result: D E F ");
        for (String el : list) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
}

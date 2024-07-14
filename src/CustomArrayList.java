import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.RandomAccess;

public class CustomArrayList<E> extends AbstractList<E>
        implements RandomAccess, Cloneable, Serializable {

    private E[] array;
    private final int INITIAL_CAPACITY = 10;
    private final Class<String> DEFAULT_TYPE = java.lang.String.class;

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        array = (E[]) Array.newInstance(DEFAULT_TYPE, INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(E defaultValue, int size) {
        array = (E[]) Array.newInstance(defaultValue.getClass(), size);
        Arrays.fill(array, defaultValue);
    }

    public void setArray(E[] array) {
        this.array = array;
    }

    public int size() {
        return array.length;
    }

    public void add(int index, E element) {
        try {
            array[index] = element;
        } catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean add(E element) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                array[index] = element;
                return true;
            }
        }
        array = Arrays.copyOf(array, (array.length + (array.length / 2 + 2)));
        add(element);
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            add(e);
            modified = true;
        }
        return modified;
    }

    public void clear() {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = null;
        }
    }

    public E get(int index) {
        return array[index];
    }

    public boolean isEmpty() {
        for (E e : array) {
            if (e != null) {
                return false;
            }
        }
        return true;
    }

    public boolean removeIndex(int index) {
        if (index < array.length && index >= 0) {
            array[index] = null;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public E removeWithReturn(int index) {
        if (index < array.length && index >= 0) {
            E dummy = array[index];
            array[index] = null;
            return dummy;
        }
        return (E) new Object();
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        try {
            if (index < array.length && index >= 0)
                array[index] = null;
                return true;
        } catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int indexOf(Object o) {
        ListIterator<E> it = listIterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return it.previousIndex();
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return it.previousIndex();
        }
        return -1;
    }

    public void sort(Comparator<? super E> c) {
        if (this.isEmpty())
            return;

        int leftIndex = 0;
        int rightIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                rightIndex += 1;
            }
        }
        quickSort(array, leftIndex, rightIndex, c);
    }

    public void quickSort(E[] array, int leftIndex, int rightIndex, Comparator<? super E> c) {
        if (leftIndex > rightIndex)
            return;
        int i = leftIndex;
        int j = rightIndex;
        E pivot = array[(leftIndex + rightIndex) / 2];

        while (i <= j) {
            while (c.compare(array[i], pivot) < 0)
                i++;
            while (c.compare(array[j], pivot) > 0)
                j--;
            if (i <= j) {
                E tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
            quickSort(array, leftIndex, j, c);
            quickSort(array, i, rightIndex, c);
        }
    }
}

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

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public CustomArrayList(E defaultValue, int size) {
        array = (E[]) Array.newInstance(defaultValue.getClass(), size);
        Arrays.fill(array, defaultValue);
    }

    public int size() {
        int count = array.length;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null)
                break;
            if (array[i] == null) {
                count--;
            }
        }
        return count;
    }

    public int getCapacity() {
        return array.length;
    }

    private void checkArrayLength() {
        if (size() == array.length) {
            array = Arrays.copyOf(array, (array.length + (array.length >> 1 + 1)));
        }
    }

    public void add(int index, E element) {
        int arraySize = size();
        int arrayLength = array.length;

        try {
            if (index >= arrayLength) {
                // If size != length there will not be increasing of capacity
                checkArrayLength();
                // Throws outofboundsexception when array is not filled
                array[index] = element;
            } else if (index == arrayLength - 1) {
                if (array[index] == null)
                    array[index] = element;
                else {
                    checkArrayLength();
                    array[index] = element;
                }
            } else if (index <= arraySize && arraySize <= arrayLength) {
                checkArrayLength();
                arrayLength = array.length;
                for (int i = arrayLength - 1; i > index; i--) {
                    E dummy = array[i - 1];
                    array[i] = dummy;
                }
                array[index] = element;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean add(E element) {
        checkArrayLength();
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                array[index] = element;
                return true;
            }
        }
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

    public boolean isEmpty() {
        for (E e : array) {
            if (e != null) {
                return false;
            }
        }
        return true;
    }

    public E get(int index) {
        return array[index];
    }

    private void shiftForRemove(int index) {
        if (index == array.length - 1)
            array[index] = null;
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
            if ((i + 1) == array.length - 1) {
                array[i + 1] = null;
            }
        }
    }

    public boolean removeByIndex(int index) {
        if (index < array.length && index >= 0) {
            shiftForRemove(index);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public E removeWithReturn(int index) throws Exception {
        boolean fl = false;
        E dummy = (E) new Object();

        if (index < array.length && index >= 0) {
            dummy = (E) array[index];
            shiftForRemove(index);
            fl = true;
        }

        if (fl == false) {
            System.out.println("No such index");
            throw new Exception("No such index");
        }
        return dummy;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < array.length && index >= 0) {
            shiftForRemove(index);
            return true;
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

    public void deleteNulls() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == null && i != array.length - 1) {
                int j = i + 1;
                while (j < array.length - 1) {
                    if (array[j] != null) {
                        array[i] = array[j];
                        array[j] = null;
                        i++;
                        j++;
                    } else
                        j++;
                }
            }
        }
    }

    public void sort(Comparator<? super E> c) {
        if (this.isEmpty())
            return;
        this.deleteNulls();

        int leftIndex = 0;
        int rightIndex = size() - 1;
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

    @Override
    public String toString() {
        return "CustomArrayList [array=" + Arrays.toString(array) + "\nnumber of elements. incl. inner nulls: " + size()
                + "\ncapacity: " + array.length + "]";
    }
}

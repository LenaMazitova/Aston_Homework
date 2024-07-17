import java.util.Comparator;

public class App {

    public static void main(String[] args) throws Exception {
        CustomArrayList<String> strArr = new CustomArrayList<>();
        System.out.println();
        System.out.println(strArr);

        strArr.add("a");
        strArr.add("b");
        strArr.add("c");
        strArr.add(1, "d");
        System.out.println(strArr);

        strArr.removeByIndex(0);
        System.out.println("After removing by index 0: " + strArr);

        String str = strArr.removeWithReturn(2);
        System.out.println("Remove witn return by index 2: " + str);
        System.out.println();

        CustomArrayList<String> strArr2 = new CustomArrayList<>();
        strArr2.add("A");
        strArr2.add("B");
        strArr2.add(null);
        strArr.addAll(strArr2);
        System.out.println("After adding all of items of another collection: " + strArr);

        String strGet = strArr.get(2);
        System.out.println(strGet);
        System.out.println(strArr);
        strArr.clear();
        System.out.println(strArr.isEmpty() + " " + strArr);

        CustomArrayList<Integer> intArr = new CustomArrayList<>(0, 5);
        System.out.println();
        System.out.println(intArr);

        intArr.add(10);
        intArr.add(8);
        intArr.add(1);
        intArr.add(3);
        intArr.add(4);
        intArr.add(2);
        intArr.add(5);
        System.out.println(intArr);

        intArr.add(3, 100);
        System.out.println(intArr);

        boolean fl = intArr.remove(Integer.valueOf(100));
        System.out.println(fl + " " + intArr);

        intArr.add(3, null);
        System.out.println(intArr);

        intArr.add(13, 101);
        System.out.println(intArr);

        intArr.add(14, 3);
        Integer integer = intArr.get(14);
        System.out.println("Index 14: " + integer + " Massiv: " + intArr);

        intArr.add(15, null);
        System.out.println(intArr);

        intArr.deleteNulls();
        System.out.println("After deleting nulls: " + intArr);

        intArr.add(5, null);
        System.out.println(intArr);

        intArr.sort(Comparator.reverseOrder());
        System.out.println(intArr);
    }
}

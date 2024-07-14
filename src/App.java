import java.util.Comparator;


public class App {

    public static void main(String[] args) throws Exception {
        CustomArrayList<String> strArr = new CustomArrayList<>();
        System.out.println();

        strArr.add("a");
        strArr.add("b");
        strArr.add("c");
        System.out.println(strArr);

        System.out.println(strArr.size());

        String str = strArr.removeWithReturn(0);
        System.out.println(str);
        System.out.println();

        CustomArrayList<String> ca2 = new CustomArrayList<>();
        ca2.add("A");
        ca2.add("B");
        strArr.addAll(ca2);
        System.out.println(strArr);

        String str2 = strArr.get(2);
        System.out.println(str2);
        strArr.clear();
        System.out.println(strArr.isEmpty());

        CustomArrayList<Integer> intArr = new CustomArrayList<>(0, 5);
        System.out.println();
        System.out.println(intArr);

        intArr.add(10);
        System.out.println(intArr);

        Integer[] arr = { 2, 9, 5 };
        intArr.setArray(arr);
        System.out.println(intArr);

        intArr.add(8);
        intArr.add(1);
        intArr.add(3);
        intArr.add(4);
        intArr.add(8);
        intArr.add(1);
        intArr.add(3);
        System.out.println(intArr);

        intArr.sort(Comparator.reverseOrder());
        System.out.println(intArr);
    }
}

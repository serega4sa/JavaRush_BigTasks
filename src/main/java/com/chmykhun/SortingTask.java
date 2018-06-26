package com.chmykhun;

import java.util.ArrayList;
import java.util.List;

public class SortingTask {
    private static ArrayList<Integer> list1 = new ArrayList<>();
    private static ArrayList<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        initArrays();

        printArray(list1);
        printArray(list2);
        printArray(getSortedArray(list1, list2));
    }

    private static void initArrays() {
        initArray(list1);
        initArray(list2);
    }

    private static void initArray(List list) {
        int arraySize = (int) (Math.random() * 10) + 10;
        for (int i = 0; i < arraySize; i++) {
            int randomElement = (int) (Math.random() * 100);
            list.add(randomElement);
        }
    }

    private static void printArray(List list) {
        StringBuilder strBld = new StringBuilder();
        for (Object element : list) {
            strBld.append(element).append(",");
        }
        strBld.deleteCharAt(strBld.length()-1);
        System.out.println("Resulting list: [" + strBld.toString() + "]");
    }

    private static List getSortedArray(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        List<Integer> resultingList = new ArrayList<>(list1);
        for (Integer elementFrom1 : list1) {
            for (Integer elementFrom2 : list2) {
                if (elementFrom1 > elementFrom2) {
                    int maxElementIndex = getIndexOfMaxElementBefore(elementFrom2, resultingList);
                    resultingList.add(maxElementIndex == -1 ? 0 : maxElementIndex + 1, elementFrom2);
                }
            }
        }

        return resultingList;
    }

    private static int getIndexOfMaxElementBefore(int element, List inList) {
        for (int i = 0; i < inList.size(); i++) {
            if (element < ((int) inList.get(i))) {
                return i;
            }
        }
        return -1;
    }
}

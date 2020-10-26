package com.company;

import java.util.ArrayList;

public class Sort {

    public static Counter insertSort(ArrayList<Integer> data) {
        Counter counter = new Counter(0, 0 );
        int key;
        for (int i = 1; i < data.size(); i++)
        {
            key = data.get(i);
            int j = i-1;
            while ((j >= 0)&&(data.get(j) - key) > 0)
            {
                counter.incCountComp();
                data.set(j+1, data.get(j));
                j--;
                counter.incCountSwap();
            }
            data.set(j+1, key);
            counter.incCountSwap();
        }
        if (checkSort(data))
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return counter;
    }

    public static Counter binaryInsertSort(ArrayList<Integer> data){
        Counter counter = new Counter(0,0);

        int key;
        int left, right, mid;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i-1) > data.get(i)) {
                counter.incCountComp();
                key = data.get(i);
                left = 0;
                right = i - 1;
                while (left < right) {
                    mid =(left+right)/2;
                    if (key < data.get(mid))
                        right = mid;
                    else left = mid + 1;
                    counter.incCountComp();
                }
                for (int j = i; j > left; j--) {
                    data.set(j, data.get(j - 1));
                    counter.incCountSwap();
                }
                data.set(left, key);
                counter.incCountSwap();
            }
        }

        if (checkSort(data))
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return counter;
    }

    private static boolean checkSort(ArrayList<Integer> data){
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i) < data.get(i - 1)) {
                return true;
            }
        }
        return false;
    }
}
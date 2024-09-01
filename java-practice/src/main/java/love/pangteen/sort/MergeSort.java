package love.pangteen.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/1 22:20
 **/
public class MergeSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        Arrays.setAll(f, i -> in.nextInt());
        mergeSort(f);
        for(int i = 0; i < n; ++ i){
            System.out.print(f[i] + (i == n - 1 ? "\n" : " "));
        }
    }

    public static void mergeSort(int[] f){
        mergeSort(f, 0, f.length - 1);
    }

    private static void mergeSort(int[] f, int l, int r){
        if(l < r){
            int mid = l + r >> 1;
            mergeSort(f, l, mid);
            mergeSort(f, mid + 1, r);
            merge(f, l, mid, r);
        }
    }

    private static void merge(int[] f, int l, int mid, int r){
        int len = r - l + 1;
        int[] g = new int[len];
        int lPos = l, rPos = mid + 1;
        int pos = 0;
        while(lPos <= mid && rPos <= r){
            if(f[lPos] <= f[rPos]){
                g[pos ++] = f[lPos ++];
            } else {
                g[pos ++] = f[rPos ++];
            }
        }
        while(lPos <= mid){
            g[pos ++] = f[lPos ++];
        }
        while(rPos <= r){
            g[pos ++] = f[rPos ++];
        }
        for(int i = l; i <= r; ++ i) f[i] = g[i - l];
    }
}

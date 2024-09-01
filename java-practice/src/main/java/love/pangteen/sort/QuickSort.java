package love.pangteen.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/1 21:45
 **/
public class QuickSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        Arrays.setAll(f, i -> in.nextInt());
        quickSort(f);
        for(int i = 0; i < n; ++ i){
            System.out.print(f[i] + (i == n - 1 ? "\n" : " "));
        }
    }

    public static void quickSort(int[] f){
        Random random = new Random(System.currentTimeMillis());
        quickSort(f, random, 0, f.length - 1);
    }

    private static void quickSort(int[] f, Random random, int l, int r){
        if(r - l + 1 <= 1) return;
        int sentinel = random.nextInt(r - l + 1) + l;
//        System.out.println(l + " " + r + " -> " + sentinel);
        int lPos = l, rPos = r;
        while(lPos < rPos){
            while(lPos < sentinel && f[lPos] <= f[sentinel]){
                ++ lPos;
            }
            if(lPos < sentinel){
                swap(f, lPos, sentinel);
                sentinel = lPos;
            }
            while(sentinel < rPos && f[rPos] >= f[sentinel]){
                -- rPos;
            }
            if(sentinel < rPos){
                swap(f, rPos, sentinel);
                sentinel = rPos;
            }
        }
        quickSort(f, random, l, sentinel - 1);
        quickSort(f, random, sentinel + 1, r);
    }

    private static void swap(int[] f, int x, int y){
        int tmp = f[x];
        f[x] = f[y];
        f[y] = tmp;
    }
}

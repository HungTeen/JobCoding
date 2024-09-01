package love.pangteen.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/9/1 22:42
 **/
public class HeapSort {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] f = new int[n];
        Arrays.setAll(f, i -> in.nextInt());
        heapSort(f);
        for(int i = 0; i < n; ++ i){
            System.out.print(f[i] + (i == n - 1 ? "\n" : " "));
        }
    }

    public static void heapSort(int[] f){
        int n = f.length;
        int[] h = new int[n];
        for(int i = 0; i < n; ++ i){
            insert(h, i, f[i]);
        }
//        for(int j = 0; j < n; ++ j){
//            System.out.print(h[j] + (j == n - 1 ? "\n" : " "));
//        }
        for(int i = 0; i < n; ++ i){
            f[i] = pop(h, n - i - 1);
//            for(int j = 0; j < n; ++ j){
//                System.out.print(h[j] + (j == n - 1 ? "\n" : " "));
//            }
        }
    }

    private static void insert(int[] h, int pos, int val){
        h[pos] = val;
        pushUp(h, pos);
    }

    private static int pop(int[] h, int last){
        int res = h[0];
        swap(h, 0, last);
        pushDown(h, 0, last);
        return res;
    }

    private static void pushUp(int[] h, int pos){
        while(pos != 0){
            int fa = (pos - 1) >> 1;
            if(h[fa] > h[pos]){
                swap(h, fa, pos);
                pos = fa;
            } else break;
        }
    }

    private static void pushDown(int[] h, int now, int last){
        while(now < last){
            int l = now * 2 + 1;
            int r = now * 2 + 2;
            if(r < last && h[r] < h[l] && h[now] > h[r]){
                swap(h, r, now);
                now = r;
            } else if(l < last && h[now] > h[l]){
                swap(h, now, l);
                now = l;
            } else break;
        }
    }

    private static void swap(int[] f, int x, int y){
        int tmp = f[x];
        f[x] = f[y];
        f[y] = tmp;
    }

}

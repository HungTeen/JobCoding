package love.pangteen.problems.huawei.internship_240522;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/6/23 10:33
 **/
public class CommonLinkList {

    /**
     * 数据太小，直接暴力。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        System.out.println(line1 + ", " + line2);
        List<Integer> list1 = Arrays.stream(line1.split(" ")).map(Integer::valueOf).toList();
        List<Integer> list2 = Arrays.stream(line2.split(" ")).map(Integer::valueOf).toList();
        for(int len = list1.size(); len >= 1; -- len){
            for(int i = 0; i + len - 1 < list1.size(); ++ i) {
                for(int k = 0; k + len - 1 < list2.size(); ++ k){
                    boolean same = true;
                    for(int l = 0; l < len; ++ l){
                        if(! list1.get(i + l).equals(list2.get(k + l))){
                            same = false; break;
                        }
                    }
                    if(same){
                        for(int j = 0; j < len; ++ j){
                            System.out.print(list1.get(j + i) + (j == len - 1 ? "\n" : " "));
                        }
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }

}

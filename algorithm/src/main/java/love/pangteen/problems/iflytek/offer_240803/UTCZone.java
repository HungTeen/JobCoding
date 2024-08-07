package love.pangteen.problems.iflytek.offer_240803;

import java.util.Scanner;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/8/7 23:07
 **/
public class UTCZone {

    /**
     * 签到题。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n -- > 0){
            String time = in.next();
            String[] ss = time.split(":");
            int hour = Integer.valueOf(ss[0]);
            System.out.println(String.format("%02d", (hour + 16) % 24) + ":" + ss[1]);
        }
    }
}

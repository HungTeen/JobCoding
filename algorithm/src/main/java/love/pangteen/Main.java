package love.pangteen;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 10; ++ i)
        map.put(i, i * i);
        System.out.println(map.hashCode());
        for(int j = 0; j < 10; ++ j)
        map.put(-j, j * 10);
        System.out.println(map.hashCode());
    }

}
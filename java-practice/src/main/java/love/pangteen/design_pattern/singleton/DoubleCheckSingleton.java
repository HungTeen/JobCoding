package love.pangteen.design_pattern.singleton;

/**
 * 双重校验锁实现单例模式。
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/25 14:59
 **/
public class DoubleCheckSingleton {

    private static volatile DoubleCheckSingleton instance;

    public DoubleCheckSingleton getInstance() {
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}

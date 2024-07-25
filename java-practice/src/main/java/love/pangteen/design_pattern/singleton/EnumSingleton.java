package love.pangteen.design_pattern.singleton;

/**
 * @program: JobCoding
 * @author: PangTeen
 * @create: 2024/7/25 14:58
 **/
public enum EnumSingleton {

    INSTANCE

    ;


    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}

package Utils;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
public class Time {

    public static final long SECOND = 1000000000l; // количество наносекунд

    public static long get(){
        return System.nanoTime();
    }

}

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {


        List<Integer> nums = Arrays.asList(2,45,6,6,9,4,8,7,3,7,1);


        //@FunctionalInterface = lambda function to define the abstract methode inside it
        Predicate<Integer> predicateVal = (Integer N)-> N % 2 == 0;



        //streams chaining
        nums.stream().filter(predicateVal).sorted().forEach( n -> System.out.println("pair :"+n));
    }
}
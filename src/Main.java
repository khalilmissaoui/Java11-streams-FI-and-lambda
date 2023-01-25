import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


        List<Integer> nums = Arrays.asList(2,45,6,6,9,4,8,7,3,7,1);


        //@FunctionalInterface = lambda function to define the abstract methode inside it
        Predicate<Integer> predicateVal = (Integer N)-> N % 2 == 0;



        //streams chaining
        nums.stream().filter(predicateVal).sorted().forEach( n -> System.out.println("pair :"+n));

        separate_section();

        String ERROR = "ERROR";
        String REGEX ="   :";

        //String methodes
        String logs = "03/22 08:51:01 INFO   :..settcpimage: Associate with TCP/IP image name = TCPCS\n" +
                "03/22 08:51:02 INFO   :..reg_process: registering process with the system\n" +
                "03/22 08:51:02 ERROR   :..reg_process: attempt OS/390 for registration did fail due to lose connection\n" +
                "03/22 08:51:02 INFO   :..reg_process: return from registration rc=0\n";


        System.out.println(logs);

        Predicate<String> errorChecker = (String line) ->
              !line.isBlank() && line.contains(ERROR) ;



        List<ErrorLogMessage> errorList = logs.lines().filter(errorChecker)
                .map(line -> line.split(REGEX))
                .map(errorMessage -> builder_ErrorLogMessage(errorMessage) )
                .collect(Collectors.toList());

        errorList.forEach( errorLogMessage -> System.out.println("error date"+errorList.get(0).getDate() +errorList.get(0).getMessage()));

        separate_section();
        // list to array

        ErrorLogMessage[] errorLogsArray = errorList.toArray(ErrorLogMessage[]::new);
        System.out.println(errorLogsArray[0].getDate());
    }


     static void separate_section() {
        System.out.println("----------------------------------------------------------------------");
    }

    static ErrorLogMessage builder_ErrorLogMessage (String[] errorMessage) {
       // String REGEX = "^\\d{02}/\\d{02} \\d{02}:\\d{02}:\\d{02}";
        // Matcher m = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE).matcher(errorMessage[0]);
        //  System.out.println(m.group(0));
        ErrorLogMessage errorlog =  new ErrorLogMessage() ;
        errorlog.setDate(errorMessage[0]);
        errorlog.setMessage(errorMessage[1]);
        return errorlog;

    }
}
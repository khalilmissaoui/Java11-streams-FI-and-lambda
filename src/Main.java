import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        List<Integer> nums = Arrays.asList(2,45,6,6,9,4,8,7,3,7,1);


        //@FunctionalInterface = lambda function to define the abstract methode inside it
        Predicate<Integer> predicateVal = (Integer N)-> N % 2 == 0;



        //streams chaining
        nums.stream().filter(predicateVal).sorted().forEach( n -> System.out.println("pair :"+n));




        //String methodes
        separate_section();

        String ERROR = "ERROR";
        String REGEX ="  :";

        //dump logs data

            String logs = Files.readString(Paths.get("C:\\Users\\mi.khalil\\IdeaProjects\\Java11 practice\\src\\logs.txt"));

        Predicate<String> errorChecker = (String line) ->
              !line.isBlank() && line.contains(ERROR) ;


        //processing errors
        List<ErrorLogMessage> errorList = logs.lines()
                .filter(errorChecker)
                .map(line -> line.split(REGEX))
                .map((String[] errorMessage) -> log_ErrorLogMessage_builder(errorMessage) )
                .collect(Collectors.toList());


        // list to array
        separate_section();


        ErrorLogMessage[] errorLogsArray = errorList.toArray(ErrorLogMessage[]::new);
        Arrays.stream(errorLogsArray).forEach(
                logMessage ->  System.out.println("date :" + logMessage.getDate() + "logs message :"  +logMessage.getMessage())
        );

        //http Client use
        separate_section();

        String HTML ="<html";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.google.com"))
                .build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("http google response contains html body : "+httpResponse.body().toString().contains(HTML));
    }


     static void separate_section() {
        System.out.println("----------------------------------------------------------------------");
    }

    static ErrorLogMessage log_ErrorLogMessage_builder(String[] errorMessage) {

        ErrorLogMessage errorlog =  new ErrorLogMessage() ;
        errorlog.setDate(log_errorDate_parser(errorMessage[0]));
        errorlog.setMessage(errorMessage[1]);
        return errorlog;

    }

    static LocalDateTime log_errorDate_parser(String dirtyDate){
        //default current time if no param is passed
        String DATE_REGEX ="yyyy/MM/d HH:mm:ss";
        String EXTRACT_DATE_REGEX ="(\\d{2}[\\/]\\d{2} \\d{2}:\\d{2}:\\d{2})";
        //extract data that does match the REGEX pattern
        String matchedDate = Pattern.compile(EXTRACT_DATE_REGEX)
                .matcher(dirtyDate)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new)[0];

        //hacky way to add year to the beginning of my string => avoid error w/ year
        String dateToConvert = Calendar.getInstance().get(Calendar.YEAR) + "/" + matchedDate;
        LocalDateTime logDate = LocalDateTime.parse(dateToConvert,
                DateTimeFormatter.ofPattern(DATE_REGEX));

        return logDate;
    }
}
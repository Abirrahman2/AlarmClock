import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        //first i need to format the input, so i format it hour minute and second.
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Enter your time for alarm (HH:MM:SS): ");
        try {
            String myTime=scanner.nextLine();
            LocalTime time=LocalTime.parse(myTime,formatter);
            String path="my alarm.wav";
            Alarm clock=new Alarm(time,path);
            Thread thread=new Thread(clock);
            thread.start();
            System.out.println(time);

        }
        catch (DateTimeException e)
        {
            System.out.println("Invalid format");
        }
        catch (Exception e)
        {
            System.out.println("Something went wrong");
        }
        scanner.close();
    }
}
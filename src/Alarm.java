import com.sun.source.doctree.EscapeTree;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

public class Alarm implements  Runnable{
    private final LocalTime myAlarmTime;
    private final String path;
    public Alarm(LocalTime myAlarmTime,String path)
    {
        this.myAlarmTime=myAlarmTime;
        this.path=path;
    }
    @Override
    public void run()
    {

            LocalTime now=LocalTime.now();
            //now we should check is our given time is already lower then current time;
            //we can do that in many ways.
            //first way is find out the duration between now and myAlarmTime;
            //second way is just use a function called isBefore();
          /*  Duration duration=Duration.between(now,myAlarmTime);
            long milis=duration.toMillis();
            if(milis<0)//this if check, we should do when user insert the input.
            {           //right now we will not consider this features.

                System.out.println("The time you provide is already passed.");

            }*/


            while (LocalTime.now().isBefore(myAlarmTime))
            {
                try {
                    Thread.sleep(1000);
                    System.out.printf("\r%02d:%02d:%02d",LocalTime.now().getHour(),LocalTime.now().getMinute(),LocalTime.now().getSecond() );

                }
                catch (InterruptedException e)
                {
                    System.out.println("Thread interrupted");
                }

            }

            playAlarm(path);



    }
    private void playAlarm(String path)
    {
     File audio=new File(path);
        AudioInputStream audiStream;
        try
        {
            audiStream=AudioSystem.getAudioInputStream(audio);
            Clip clip=AudioSystem.getClip();
            clip.open(audiStream);
            clip.start();
            Thread.sleep(5000);

        }
        catch (Exception e)
        {
            System.out.println("Something went wrong");
        }


    }
}

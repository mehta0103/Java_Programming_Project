package DAO_impl;
import ConnectionPackage.Connection_Class;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class AudioPlayer {
    Scanner sc=new Scanner(System.in);
    Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    static String path;



    public AudioPlayer(String filePath)throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        path=filePath;
        audioInputStream= AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void PlaySong() {
        Scanner sc = new Scanner(System.in);
        try{
            play();

            while (true)
            {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. exit");
                int c = sc.nextInt();
                gotoChoice(c);
                if (c == 4)break;
            }
          }
        catch (Exception ex)
        {
            System.out.println("Song is ended " +ex);
        }
    }
    private void gotoChoice(int selection)throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (selection)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
        }
    }
    public void play()
    {
        clip.start();
        status = "played";
    }
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
       // this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
    public void resumeAudio() throws UnsupportedAudioFileException,IOException,LineUnavailableException
    {
        if (status.equals("played"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();

        this.play();
    }
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        this.play();
    }
    public void stop()
    {
       clip.stop();
        clip.close();
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



}

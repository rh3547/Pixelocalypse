package com.thale.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

import com.thale.main.ScreenPixelocalypse;

public class AudioHandler
{
	Port lineOut;
	FloatControl volCtrl;
	
	public static synchronized void playSound(final String url) 
	{
		new Thread(new Runnable() 
		{
			public void run() 
		    {
				try 
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							ScreenPixelocalypse.class.getResourceAsStream("/" + url));
					clip.open(inputStream);
					clip.start(); 
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	public static synchronized void stopSound(final String url) 
	{
		new Thread(new Runnable() 
		{
			public void run() 
		    {
				try 
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							ScreenPixelocalypse.class.getResourceAsStream("/" + url));
					clip.open(inputStream);
					clip.stop(); 
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	public static synchronized void loopSound(final String url, final int count) 
	{
		new Thread(new Runnable() 
		{
			public void run() 
		    {
				try 
				{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							ScreenPixelocalypse.class.getResourceAsStream("/" + url));
					clip.open(inputStream);
					clip.loop(count);
				} 
				catch (Exception e) 
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	public void changeVolume(Float volume)
	{
		try 
		{
			Mixer mixer = AudioSystem.getMixer(null);
			lineOut = (Port)mixer.getLine(Port.Info.SPEAKER);
			lineOut.open();
			volCtrl = (FloatControl) lineOut.getControl(FloatControl.Type.VOLUME);
			
			volCtrl.setValue(volume);

			// Assuming getControl call succeeds, 
			// we now have our LINE_IN VOLUME control.
		} 
		catch (Exception e) 
		{
			System.out.println("Failed trying to find"
			+ " VOLUME control: exception = " + e);
		}
	}
}

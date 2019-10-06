package engine.audio;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;

public class AudioPlayer {

	private static AudioPlayer instance = null;
	
	public static AudioPlayer get() {
		if(instance == null) {
			instance = new AudioPlayer();
		}
		return instance;
	}
	
	private class PlayerThread implements Runnable {
	
		private void closeStream(AudioInputStream stream) {
			try {
				stream.close();
			} catch (IOException e) {
				System.err.println("Error closing audio stream: " + e.getMessage());
			}		
		}
		
		private HashMap<File, Clip> clipCache = new HashMap<>();
		
		private void playFile(File f) {
			AudioInputStream ss = null;
			
			try {
				Clip audio;
				if(!clipCache.containsKey(f)) {
					final AudioInputStream stream = AudioSystem.getAudioInputStream(f);
					ss = stream;

					audio = AudioSystem.getClip();
					audio.open(stream);
					audio.addLineListener(new LineListener() {
						@Override
						public void update(LineEvent event) {
							Type eventType = event.getType();
							if (eventType == Type.STOP || eventType == Type.CLOSE) {
								audio.close();
								closeStream(stream);
							}
						}
					});
					clipCache.put(f, audio);
				}
				audio.start();
			
			} catch(IOException ioex) {
				System.err.println("Input/Output exception reading file " + f.getPath());
				closeStream(ss);
			} catch (UnsupportedAudioFileException uafe) {
				System.err.println("Audio file format not supported");
				uafe.printStackTrace();
				closeStream(ss);
			} catch (LineUnavailableException lue) {
				System.err.println("Line unavailable; audio disabled");
				lue.printStackTrace();
				closeStream(ss);
			}
		}
		
		@Override
		public void run() {

			do {
				if(!requests.isEmpty()) {
					playFile(requests.remove());
				}
				
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					System.err.println("Audio player thread interrupted");
				}
			
			} while(true);
		}
	}
	
	private Thread player = null;
	private volatile Queue<File> requests = new LinkedList<>();
		
	private AudioPlayer() {
		player = new Thread(new PlayerThread());
		player.start();
	}
	
	public void play(String file) {
		requests.add(new File(file));
	}
}

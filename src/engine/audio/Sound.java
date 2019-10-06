package engine.audio;

/**
 * Quick-and-dirty sound interface for playing single sound effects. 
 */
public class Sound {

	private String file;
	
	public Sound(String file) {
		this.file = file;
	}
	
	public void play() {
		AudioPlayer.get().play(file);
	}	
	
}

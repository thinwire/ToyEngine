# Toy Engine
Simple Java 2D game engine


## Introduction

This project was created out of a need for a minimalist framework for teaching the fundamentals of game development based on discussion in [The Benny Discord](https://discord.gg/aMqvk3E).

As such it only contains the base features required in order to make something simple, like Pong or Invaders, but does contain enough hooks so that it can be extended to accommodate all medium-difficulty projects (complexity level of basically anything from the 8 bit NES era).

This code is written against the Java 1.8 specification. It does not use all Java 8 features (nor does it aim to).

## Getting started

### Step 0: install a JDK, Eclipse and Git

Refer to [this guide here](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html) for instructions on how to install a JDK and the Eclipse integrated development environment (IDE) on your platform of choice.

Follow [this guide here](https://www.atlassian.com/git/tutorials/install-git) for instructions on how to install Git on your platform of choice.

### Step 1: clone the Git repository

Note: it is recommended to save the ToyEngine project under your Eclipse workspace. Don't worry if it's not there already, you can just move the downloaded directory.

Use your GUI of choice to clone the repository (e.g. [like this](https://confluence.atlassian.com/sourcetreekb/clone-a-repository-into-sourcetree-780870050.html) in Atlassian SourceTree) or use a console command:

`git clone https://github.com/thinwire/ToyEngine.git`

### Step 1b (optional): clone the Awesome Space Game

The Awesome Space Game is a demonstration project for how to use the Toy Engine to create something that resembles a game. As of now it is rather pointless, but it demonstrates the basics of working with the graphics and input subsystems.

Yes, the name is ironic.

### Step 2: Import the ToyEngine project into Eclipse

Follow [this guide to import the project](https://www.360logica.com/blog/how-to-import-a-java-project-into-eclipse-ide/) into your Eclipse workspace. If your JDK and Eclipse installation are set up properly, the project should compile just fine.

### Step 2b (optional): import the Awesome Space Game project

The Awesome Space Game can be a good reference for working on your own game. Import it in the same way as ToyEngine. If `ToyEngine` was cloned with that exact name and placed into the Eclipse Workspace directory (and Awesome Space Game was placed there as well), Awesome Space Game should import without errors. If it did not, follow the steps for how to set up your own project so it works with the Toy Engine.

### Step 3: Create your own project and set up project linkage

Create a new Eclipse Project by [following this guide](https://www.tutorialspoint.com/eclipse/eclipse_create_java_project.htm). Make sure to set the execution environment JRE to JavaSE-1.8 (or equivalent) in order to avoid issues. Higher may work. 1.7 and below are not guaranteed to work, 1.6 and below _will not_ work.

Since we're using Eclipse, we'll use the project linkage feature it provides to let us to link together two otherwise unrelated projects. This allows us to keep the engine and the game separate (and lets you, for example, have multiple games sharing the same engine code base).

Right click on your project in the `Project Explorer` or `Package Explorer` view (they do almost the same thing) and select `Properties` - it is the last option in the drop down menu. From the left-hand list box, select `Project References`, and in the view that opens to the right, select the `ToyEngine` project. This lets Eclipse know that your project is related to ToyEngine in some fashion.

Without closing the window, select `Java Build Path` from the left-hand list box and then select the `Projects` tab in the view to the right. Click the `Add` button and select `ToyEngine`, the click `OK`. ToyEngine should be listed under `Required projects on the build path`. Click `Apply and Close` to close the Properties window.
Adding the ToyEngine project to the build path lets you access ToyEngine classes as if they existed directly in your game project.

Linking projects in Eclipse instead of [packaging the engine into a .jar file](https://www.javahotchocolate.com/tutorials/jar-libraries.html) and adding that to your project lets you modify the ToyEngine project while making the changes available to your own projects immediately. If you went the Jar route instead, you'd need to build a new Jar for every change you make to the engine, and manually copy it to the projects that use it. 

### Step 4: Add a main class

Your main class is the main entry point for the program, i.e. where Java starts looking for instructions to run when your program is started.
To make it easy to use the Toy Engine, the engine provides its own `Application` base class which your code should extend. `Application` provides access to the `Screen` (i.e. the window that graphics get drawn to) as well as `Input` (which allows you to read keyboard state). It also provides an absolute basic level of graphics object control by allowing you to add and remove objects, such as sprites, that are to be drawn to screen each frame.
Most importantly, `Application` handles the main loop. The main loop will keep running until the `Application` is told to exit, by calling the `exit()` method of `Application`.

The simplest main class is as follows (replace `MyGame` with your actual class name: `Main` is a good one ;) )

~~~
import engine.Application;

public class MyGame extends Application {
	MyGame() {
		super(800, 600);
	}

	@Override
	public void update(double delta) {
	}

	public static void main(String[] args) {
		new MyGame().run();
		System.exit(0);
	}
}
~~~

The above code, with comments explaining what's going on:

~~~
import engine.Application;

public class MyGame extends Application {

	MyGame() {
		// Set up the engine with an 800 by 600 pixel window
		super(800, 600);
	}

	@Override
	public void update(double delta) {
		// This code gets run once per frame, by default at
		// a rate of 60 frames per second. Do not add an
		// infinite loop here.
		//
		// The 'delta' parameter is the number of seconds
		// that have elapsed between the start of this frame
		// and the start of the previous frame.
	}

	// Main program entry point, aka. where Java starts running code
	// from. Create an instance of our class and call the run() 
	// method it inherits from Application to start our game. The run()
	// method will not exit until the game is closed, either by closing
	// the window or by calling Application.exit() in code.
	public static void main(String[] args) {
		new MyGame().run();
		
		// Explicitly exit Java so that any and all dangling background
		// threads are properly killed off...
		System.exit(0);
	}

}
~~~

### Step 5: Start the game

From the Eclipse window menu, select `Run` and then `Run`, or press the green button with the same icon in the main Eclipse toolbar to run the game. Make sure the `Project` and `Main Class` fields refer to your game and main class, then select the `Run` button in the lower right of the window. The game should start and you should see a window of the size you specified in the main class pop up, with the title "Untitled Game".  

### Step 5b (optional): run the Awesome Space Game

Open up the file `src/spacegame/SpaceGame.java` in the Awesome Space Game project and follow the instructions above. Awesome Space Game should start up and let you play it.


## License

Source code is free to use and modify for any purpose, and is licenced under the [WTFPL](http://www.wtfpl.net/about/).


## Authors

Code by Thinwire, on Github.

# GAME-project-
JAVA class lab work project
# my-game-project
JAVA class lab work
# My Game Project

This is a 2D game built in Java using Swing for rendering and a custom game engine architecture.

## Features
- Dynamic sprite movements with animations
- Physics engine
- Game over and retry functionality]
- Player movement and collision detection.
- Modular engines for rendering, physics, and game logic.

## How to Run
1. Clone this repository.
2. Open the project in your Java IDE (e.g., IntelliJ IDEA, Eclipse, or VSCode).(git clone https://github.com/jawadnaim-code/GAME-project.git
cd GAME-project)
3.javac -d bin -sourcepath src src/Main.java
4.java -cp bin Main
5. Ensure the `img` and `data` directories are in the root of the project.
6. Run the `Main` class.

## Dependencies
- Java 8 or higher
Java 2D Game Project
A simple 2D game built with Java Swing and AWT, featuring sprite movement, collision detection, and game states like "Game Over" and retry functionality.

## Game Mechanics
Lives: Player loses 1 life upon stepping on traps. Game Over after 3 lives.
Invincibility: Temporary invincibility after hitting a trap.
Levels: Dynamically loaded from .txt files with symbols:
T = Tree, R = Rock, J = Trap, = Grass.

Run the Game
Use arrow keys to navigate the hero and avoid traps. Retry from the "Game Over" screen if lives are lost.


# CITS2005 Project – Java OOP Grid Game

This project was completed for **CITS2005: Object-Oriented Programming and Software Engineering** at The University of Western Australia (Semester 1, 2025). The assignment focused on interface-driven design, modular programming, and test coverage using Java.

## Project Description

The game is a two-player turn-based path-building game played on a square grid. Players alternate placing black or white pieces with the goal of creating a 4-connected path from either:

- Top to bottom, or  
- Left to right  

Only cardinal direction movement (up, down, left, right) is allowed.

Students were provided with partial code, and tasked with implementing and testing the core game logic.

## Core Tasks Completed

- **`MoveImpl.java`**: Implements the `Move` interface, including constructors and `toString()` override.
- **`GridImpl.java`**: Implements the `Grid` interface to manage the game board and move placements.
- **`GameImpl.java`**: Implements the `Game` interface with full win/draw logic using the provided `PathFinder` class.
- **`GameTest.java`**: A thorough custom test suite to validate `GameImpl` functionality against correct and faulty implementations.

## Constraints and Requirements

- No AI-generated code was allowed; all logic was implemented manually.
- No extra constructors or third-party libraries permitted.
- Submission format required a strict `/src` folder structure with only `.java` files.
- Code had to compile and run cleanly using `javac`.

## Running the Game

Once compiled, the `PlayVsAI.java` file allows players to play against a terminal-based AI:
- The AI plays as white and always moves first.
- The player plays as black, taking alternating turns until the game ends.

## Author

**Taleena Watts**  
Second-Year Computer Science Student  
The University of Western Australia  
[LinkedIn](www.linkedin.com/in/taleena-watts-781421201)

# Go_Playoffs
AI Platform for the Go game


## Purpose

It's a platform for competing one AI algorithm against another, playing the game of Go

## Usage

- Currently the algorithm files are hardcoded in the main file.
- The program runs the script/binaries which enter an infinite loop.
- Each turn the program sends the board state as 20 lines consisting of 20 integers 
seprataded by " " to the stdin of the player algorithm.
- Each turn the player algorithm must print a single line to the stdout containing either
"x y" coordeinates of the next move or "pass" in case it wants to skip a move.
- If a move is invalid or if the output of the algorithm 
doesn't complay with the rule above, the game ends and the other player wins.
- If the algorithm takes to long to output the next move (currntly more than 1s),
the platform stops it and returns a time out error. The other player wins.

## To Be Continued...

Stay tuned for future development and improvements.

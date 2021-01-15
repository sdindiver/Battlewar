# BattleShip Game [![Build Status](https://travis-ci.org/sdindiver/Battlewar.svg?branch=main)](https://travis-ci.com/github/sdindiver/Battlewar) 

BattleShip is a turn-based two-player game that simulates a war between ships on an ocean.
Each player gets their own battle area with a certain number of ships placed in non-overlapping positions. The ships might be of different sizes. Note, players cannot see each others ship's location.

There are two types of ships - type P and type Q. Type P ships can be destroyed by a single hit in each of their cells and type Q ships require 2 hits in each of their cells.
A ship is considered destroyed when all of its cells are destroyed.
The player who destroys all the ships of the other player first wins the game. The game ends in a draw if none of the players can destroy all of the other’s ships using a finite number of missiles.

#### Example:

Consider a case where each player gets 3 ships of sizes 1x3, 2x2 and 1x4. The players arrange their ships in their battle areas as shown in the following figure.
Each ship can span multiple cells as shown in the figure. For example, the second ship of Player 1 covers cells D1, D2, E1 and E2.

![alt text](https://user-images.githubusercontent.com/9547856/104731269-787f8780-5761-11eb-8158-f80797e37a02.jpg)

Once the players set up the ships in their battle areas, the battle begins.
The players take turns to fire their missiles at the other player’s ships. Firing is done by targeting a particular cell given by its coordinates in the opponent’s battle area. For example, if Player-1 targets position E1 in the
Player-2’s battle area and fires a missile, the player will have successfully hit a ship. On each turn, the player who received the missile should communicate to the other whether it was a hit or a miss.
If a player gets a hit in a turn, they get another turn. If it’s a miss, the other player gets their turn.

#### Input:


The first line of the input contains the width and height of the battle area respectively.
 
-	The second line of the input contains the number of battleships that each player gets (N).

-	Read N lines where each line contains the type of the battleship, its dimensions (width and height) and coordinates of ship for
Player-1 and Player-2.

-	The second last line contains the sequence of the target locations of missiles fired by Player-1.

-	The last line contains the sequence of the target locations of missiles fired by Player-2.


#### Sample Input:
5 E
2
Q 1 1 A1 B2 P 2 1 D4 C3 A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5


#### Constraints:

1 <= Width of Battle area (M’) <= 9, A <= Height of Battle area (N’) <= Z
1 <= Number of battleships <= M’ * N’ Type of ship = {‘P’, ‘Q’}
1 <= Width of battleship <= M’ A <= Height of battleship <= N’ 1 <= X coordinate of ship <= M’ A <= Y coordinate of ship <= N’




<pre>Player 1 Battle Area:	                                                        Player 2 Battle Area</pre> 

![alt text](https://user-images.githubusercontent.com/9547856/104731274-7a494b00-5761-11eb-8eac-982a5def86ea.jpg)

 
#### Sample Output:

Player-1 fires a missile with target A1 which got miss Player-2 fires a missile with target A1 which got hit Player-2 fires a missile with target B2 which got miss Player-1 fires a missile with target B2 which got hit Player-1 fires a missile with target B2 which got hit Player-1 fires a missile with target B3 which got miss Player-2 fires a missile with target B3 which got miss Player-1 has no more missiles left to launch
Player-2 fires a missile with target A1 which got hit Player-2 fires a missile with target D1 which got miss Player-1 has no more missiles left to launch
Player-2 fires a missile with target E1 which got miss Player-1 has no more missiles left to launch
Player-2 fires a missile with target D4 which got hit Player-2 fires a missile with target D4 which got miss Player-1 has no more missiles left to launch
Player-2 fires a missile with target D5 which got hit Player-2 won the battle

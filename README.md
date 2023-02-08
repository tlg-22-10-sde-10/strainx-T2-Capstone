StrainX

mavern project

1. team members:
Cameron Davis;
Ethan Wang;
Chongwei Ma;

2. Game story line:
Military conducted experiments to create a “super-soldier” - experiments resulted in uncontrollable disfigured non-human creatures (“zombies”).
Player is part of a small strike team sent to obtain a blood sample from patient zero for analysis to determine what went wrong (by any means necessary), or reinstate containment measures of lab before the monsters escape.
Due to unforeseen setbacks encountered during insertion, player is separated from rest of strike team. Player has choice to either attempt to rendezvous with squad members or attempt to execute mission on their own.

3. Game Objective (Player has choice on how to meet the objective(s)):
traverse the map, manage resourse and find the key item(blood sample).

Attempt to neutralize patient zero with lethal force then obtain the sample (option 1) or 
Find the tranquilizer gun and appropriate ammo to sedate and take sample (option 2) or
Attempt to contain and securely extract patient zero via labs failed containment zone (option 3)

If option 1, player must:
rendezvous with at least 1 other teammate 
obtain a particular lost firearm/ammo/bodyarmor
obtain sample collection kit

If option 2, player must:
Obtain tranquilizer gun and it’s appropriate high-power tranquilizer darts
Obtain sample collection kit

If option 3, player must:
Rendezvous with at least 2 other teammates
Obtain containment field flux capacitor
Reset containment field by placing flux capacitor in power core

4. List of Features
(1). rogue-like game with a lot random features;
(2). matrix map (using an array to represent the map) with random content within each map block;
(3). combat between main charactor team and random spawned enemy team;
(4). non-linear game progress (player will not be able to do the exact same thing over and over to accomplish the game);
(5). ***multi ending*** 


Game Modules:
(A). ui
1, enter game user interface (display start screen/exit screen -> ui)
*. Start screen: 
***. New game;
***. Load game;
***. exit game;

*. Exit Screen:
***. >? if defeated: Game Over;                        exit_code: 2 
***. >? if exit from the game: exit game;              exit_code: 0 
***. >? if pass the game: mission accomplished.        exit_code: 1 	

2, main game user interface (display the main screen when in game -> ui)
*. Display the general information:
***. Display resources on hand: food, fuel, ammo…
***. Display map/directions to go.
***. Display options to interact with game (give player to choose their actions, for example, search area, move to next area, engage with enemy)

3, combat user interface (display combat information -> ui)
4, map user interface (display map -> ui)
5, items user interface (display items -> ui)
6, team/squad user interface (display squad members status -> ui)

(B). Game Engines
1, map engine (generate map and export as certain data structure-> Engine)
***should be able to draw a map with all the revealed information marked***

2, squad engine (generate a squad and export as certain data structure-> engine)
***main charactor's squad should be an arraylist***
 
3, combat engine (handling combat and return combat result/modify data within -> engine)
***combat should be able to handle complex combat situations and have player choose strategy***

4, dialog engine (handling conversations -> engine)
***dialogs should be a stored in a map data structure***

//below are all contents of a map
5, enemy engine (similar to squad engine, generate a squad of enemies instead and export as certain data structure -> engine)
***enemy squad should be an arraylist***

6, event engine (generate events and trigger requirements, export as certain data structure -> engine)
***events should be randomly loaded from an arraylist depending on the situation*** 

7, items engine (generate item lists and export as certain data structure -> engine)
***items should be stored in an arraylist***

( C). Game Data
1, conversation text -> data
2, items information/introduction -> data
3, soldier information -> data
4, enemy information -> data
5, event information -> data
6, map information -> data

//below are not directly game play related data
1, special thanks
2, music
3, sound effects
4, visual effects


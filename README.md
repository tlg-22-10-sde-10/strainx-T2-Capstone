![Strain X Title](/src/main/resources/images/strainX-title.jpg)

# TLG Team 2 Capstone Project

## Objective

1. Review another team's pre-capstone project
   - Team 4's Strain X console text-based zombie survival rogue-like
     - Consisted of randomly generated stats, enemies, map layout, and items
     - Random turn-based combat
     - Objective is to search areas, fight zombies, find items to find a passcode to a lab, and get a sample from Patient Zero (by killing it)
   - Code was highly modular and implemented great OOP concepts
2. Use their existing code to create a graphical user interface (GUI) version of the game using [Java Swing](https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/package-summary.html)

## Team Members

- Lorenzo Ortega | [GitHub](https://github.com/antoni909) | [LinkedIn](https://www.linkedin.com/in/lorenzo-ortega-antoni/)
- Lok Tamang | [GitHub](https://github.com/loktama21) | [LinkedIn](https://www.linkedin.com/in/loktam/)
- Joseph Racke | [GitHub](https://github.com/JMRacke) | [LinkedIn](https://www.linkedin.com/in/joe-racke/)

## Design Philosophy (Team Tenets)

- Keep as much of the original code as possible
- Only change code if a bug existed that affects the game in some way or the performance of the game
- Implement Java Swing components individually to keep code modular
- Make tickets for each item worked on (even if you started working on it without an existing ticket in place)
- Working With Protected Branches, PR Review before merging to Dev, Naming Branches and Locally associating commits with TicketItem 
- Small commits often!
- Dive deep into how Java Swing actually works

## Install and Play StrainX

1. Ensure your have JDK v11 or higher
1. For Windows users, git bash command tool is highly recommended
1. For Mac users, terminal will work
1. Go to the GitHub Repository and download the latest version of [StrainX](https://github.com/tlg-22-10-sde-10/strainx-T2-Capstone/releases)
1. Open your teminal/command line and navigate to StrainX jar file
1. Type `java -jar <strainx-*.*-all.jar>` to start the game
1. Enjoy the game!
 
## Project Timeline
 
- Sprint 0 (1 Day)
  - Major Challenges/Milestones as a Team
    - Vote on a game that all were interested in
    - Learning Curve to learn how to play the game
    - Determine the difficulty of adapting to GUI
  - As A team voted for StrainX
    -[ ] Played the game: 
      - to understand the intent/goal, 
      - player experience, 
      - difficulties, wins/loss scenarios
    -[ ] Review the Backlog
      - verifying own access
      - begin managing the backlog
    -[ ] Review Existing Code Base
      - project directory/folder structure
      - learning to read/interpret their code
    -[ ] Research Swing as UI Library
      - understand how to create basic GUI
      - hierarchy of Java Swing Classes
      - commonly used Classes (JFrame,JPanel,JLabel,JButton,JDialogue..)
      - commonly used methods (add,setSize,setLayout,setListener...)

- Sprint 1 (3 Days)
    - Major Challenges/Milestones
      - Becoming familiar with the code base
      - Concurrently learning the basics of Swing and trying to implement the desired outcome
    -[ ] Implement Major GUI features
      - Title Screen
      - Status Panel
      - Game Map 
        - Grid Size reflects Game Difficulty
      - Sound
    - [ ] Continue to Understand the Code
      - Use Intellij Debugger Tool
        - step through complex/monolithic code
        
- Sprint 2 (3 Days)
    - Major Challenges/Milestones
      - moving beyond basic Swing GUI
        - repainting components as needed
    -[ ] Implement Major Player Interactivity Features
      - Player can click a button 
        - help
        - adjust settings
        - see inventory list
        - looting and adding items to inventory
        - enter combat
      - Player can hover over a button and receive game information 
      - Player can view own/team stats (health,attack)
      - Using Icons/Images to enhance Visuals of the game
      
- Sprint 3 (3 Days)
  - Major Challenges/Milestones
    - enhancing game visuals/experience
      - adding sound,images
    - finding bugs/potential problems
    - finalizing/stabilizing project

## Technologies / Skills Utilized

- Java
- Java Swing
- Maven
- IntelliJ
- JUnit 4
- Git
- Production and Development protected branches (using unique branches per tickets)
- Pull request peer code reviews
- GitHub
- Azure DevOps Kanban Boards
- Agile development philosophy
- Three-day Sprints
- Daily Stand-ups with other teams and between each other
- Peer-programming

## Asset Credit

- Special thanks to the following for sound effects:
- Running Sound Effect by <a href="https://pixabay.com/users/km007_-23606303/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=9109">KM007_</a> from <a href="https://pixabay.com//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=9109">Pixabay</a>
- Title Music by <a href="https://pixabay.com/users/geoffreyburch-5739114/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=22045">GeoffreyBurch</a> from <a href="https://pixabay.com//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=22045">Pixabay</a>
- Zombie Bite Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=44538">Pixabay</a>
- AR-15 Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=14443">Pixabay</a>
- Game Over Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=38511">Pixabay</a>
- Bare Hands Sound Effect by <a href="https://pixabay.com/users/universfield-28281460/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=140236">Universfield</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=140236">Pixabay</a>
- Victory Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=14800">Pixabay</a>
- M249 Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=39814">Pixabay</a>
- Tire Iron Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=96095">Pixabay</a>
- Axe Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=106748">Pixabay</a>
- Handgun Sound Effect from <a href="https://pixabay.com/sound-effects/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=106671">Pixabay</a>
- Big Bertha Sound Effect from <a href="https://pixabay.com/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=96223">Pixabay</a>
- Crystal Femur Sound Effect by <a href="https://pixabay.com/users/sergequadrado-24990007/?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=21464">SergeQuadrado</a> from <a href="https://pixabay.com/sound-effects//?utm_source=link-attribution&amp;utm_medium=referral&amp;utm_campaign=music&amp;utm_content=21464">Pixabay</a>


***

# Team 4 Pre-Capstone README.md
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
Obtain tranquilizer gun, and it’s appropriate high-power tranquilizer darts
Obtain sample collection kit

If option 3, player must:
Rendezvous with at least 2 other teammates
Obtain containment field flux capacitor
Reset containment field by placing flux capacitor in power core

4. List of Features
(1). rogue-like game with a lot of random features;
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
# JavaFxTileGame

A pipe game can be played on PC.  
<img src="/GamePNGs/BallPiping.png" width="250" height="250" alt= "Starter Tile">

#### Tiles

-**Starter**    
  This type of tile is where the ball starts game. It is static which means this tile does not have capability of moving.  
  <img src="/GamePNGs/startVertical.png" width="50" height="50" alt= "Starter Tile">    <img src="/GamePNGs/startHorizontal.png" width="50" height="50" alt= "Starter Tile">

 
-**End**  
  This type of tile is where the ball should be end of game. It is static which means this tile does not have capability of moving.  
    <img src="/GamePNGs/endVertical.png" width="50" height="50" alt= "End Tile">    <img src="/GamePNGs/endHorizontal.png" width="50" height="50" alt= "End Tile">

-**Pipe** 
  This type of tile is just a normal pipe can be declared as horizontal, vertical, 00, 01, 10 and 00. These pipes are not static so they can move around.  
    <img src="/GamePNGs/pipeHorizontal.png" width="50" height="50" alt= "Pipe Horizontal"> <img src="/GamePNGs/pipeVertical.png" width="50" height="50" alt= "Pipe Vetical "> <img src="/GamePNGs/curvedPipe00.png" width="50" height="50" alt= "Curved Pipe 00 Tile"> <img src="/GamePNGs/curvedPipe01.png" width="50" height="50" alt= "Curved Pipe 01 Tile"> <img src="/GamePNGs/curvedPipe10.png" width="50" height="50" alt= "Curved Pipe 10 Tile"> <img src="/GamePNGs/curvedPipe11.png" width="50" height="50" alt= "Curved Pipe 11 Tile">  
-**StaticPipe**  
  This type of tile is just a normal pipe which is static. They are not allowed to move.  
    <img src="/GamePNGs/pipeStaticHorizontal.png" width="50" height="50" alt= "PipeStatic Horizontal">  <img src="/GamePNGs/pipeStaticVertical.png" width="50" height="50" alt= "PipeStatic Vertical">  
-**Empty**  
  This type of tile is a tile has ability to move but there is no pipe inside of it.  
    <img src="/GamePNGs/empty.png" width="50" height="50" alt= "Empty Tile">  
 -**EmptyFree**  
  This type of tile is like just a space which all the tiles, can move, to move.  
    <img src="/GamePNGs/emptyFree.png" width="50" height="50" alt= "Empty Free Tile">
    
    
## Level System and Adding New Levels

Ball Piping has a special level mechanic which reads .txt files and creates levels. That is mean adding new levels to Ball Piping is not hard.  
For example, this is the contents of level 1.
```
1,Starter,Vertical
2,Empty,none
3,Empty,none
4,Empty,none
5,Pipe,Vertical
6,Empty,none
7,Empty,none
8,Empty,none
9,Pipe,Vertical
10,Pipe,Horizontal
11,Empty,none
12,Empty,none
13,Pipe,01
14,Empty,Free
15,PipeStatic,Horizontal
16,End,Horizontal
```

This is simple example of how to create a level txt file.  
**Note that**: Level files should be named as "level[a number].txt" such as level1.txt, level15.txt  
**Also shoul be noted**: Level files values should be continuously. For instance when a new level adding, the name of the text file should be **level17.txt** if the last level created is **level16.txt**  
**Another important note**: There should be just one of Starter and End tile. If not the game will crash. 


    
  

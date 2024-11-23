# Introduction

Fully functional chess game written in Java with Spring/AWT GUI & official rule implementations.

![Chess_Screenshot](https://github.com/BeraSenol/Chess-Game-Java-2.0/blob/main/res/readme/chess-board-pieces.png)
<br/>
<br/>


# Features [(Screenshots)](https://github.com/BeraSenol/Chess-Game-Java-2.0b/wiki/Screenshots#features)

- [x] Game Panel
- [x] Game Loop
- [x] Chess Board
- [x] File/Rank Labels
- [x] Chess Pieces
- [x] Legal Move Validation
- [x] Legal Move Indicators
- [x] Player vs Player
- [x] Playable as Black
- [x] Turn Based Movement
- [x] Capture Validation
- [x] Capture Highlight
- [x] Castling Mechanic
- [x] En Passant Mechanic
- [x] Promotion Panel
- [ ] Pinned Piece Detection
- [ ] Check Detection
- [ ] Checkmate Detection
- [ ] Stalemate Detection
- [ ] User Settings
- [ ] Undo/Redo
- [ ] PGN Notation
- [ ] Database Logging
<br/>
<br/>

# Requirements

- Latest Java version.
- Swing & AWT Library
  <br/>
  <br/>

# Installation

Clone and run:

```
git clone https://github.com/BeraSenol/Chess-Game-Java-2.0.git
```

Build and run:

```
javac -cp src/ Main.java
java -cp src/ Main
```

<br/>
<br/>

# Usage

Clicking on piece will reveal it's legal moves, click tile to move or re-select another piece to reveal it's legal moves, white always begins.

Options in a menu bar will be implemented to easily select player color etc (For now change `playerColor` in `ln30:GameWindow.java` to `PlayerColor.Black` to play as black).

Default:
`private static PlayerColor playerColor = PlayerColor.WHITE;`
<br/>
<br/>

# Code Overview

`/board`

- `Board.Java` Class that creates tile objects, draws chess board, creates and places pieces.
- `Tile.Java` Class that define tile objects and hold a piece.
- `TileColor.Java` Enum holding possible tile color values.

`/main`

- `App.java` Main Class that serves `GameWindow.java` 
- `GameWindow.java` Main file that runs the game, update() and repaint() are the main methods which loop the game.
- `Mouse.java` Class that detects mouse events.

`/piece`

- `/pieces`
- - `Bishop.java` Class with movement & capture logic.
- - `King.java` Class with movement, capture & castling logic.
- - `Knight.java` Class with movement & capture logic.
- - `Pawn.java:` Class with movement, capture & en passant logic.
- - `Queen.java` Class with movement & capture logic.
- - `Rook.java` Class with movement & capture logic.
- `/promotion`
- - `PromotionButtonAction.java` Class with JButton ActionListener.
- - `PromotionPannel.java` Class with JPanel holding JButtons.
- `Moveable.java` Interface holding move and capture method.
- `Piece.java` Class holding piece general and common variables/values.
- `PieceColor.java` Enum holding possible piece colors.
- `PieceType.java` Enum holding possible piece types.
- `PlayerColor.java` Enum holding possible player colors.
  <br/>
  <br/>

# Acknowledgments

This project was originally inspired by a Youtube Channel's video [How to Code Chess in Java](https://www.youtube.com/watch?v=jzCxywhTAUI&t=6148s) and a sequel to [Chess-Game-Java](https://github.com/BeraSenol/Chess-Game-Java).

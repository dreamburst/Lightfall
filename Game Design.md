# Game Design

## Summary

The hero is a descendent of the Aetherian guardian, protector of the Aethosphere, a vessel which gives light to the world.  The villain tampers with the Aethosphere, causing it to shatter, thus sending the world into darkness.  The hero must collect the light shards, the fragments of the Aethosphere that were cast around the world, and restore the Aethosphere, as well as defeat the villain and put a stop to his reign of darkness.

## Gameplay

The goal of the game is to restore peace and light to the world.  The obstacles are puzzles the hero will encounter on his quest; the varying types of enemies with unique features; the minibosses, each with unique abilities the hero can yield and use to solve previously hindering puzzles and defeat the main boss who guards the light shard of that dungeon.

## Mindset

The hero is silent but valiant, and will not cower at the sight of enemies.  He will always sacrifice himself for anyone in danger without a second thought.  The hero is someone with a strong responsibility to those he seeks to protect, that almost anyone will look up to.  These aspects of the hero are shown throughout the game, and used to provide the player with a sense of justice and heroism.

# Technical

## Screens

### Title Screen
A screen with a minimalistic user interface. The only text visible is "Press Any Button", which will start a new game or continue a previously played game. There is no "How To Play" or "Instructions". The game aims to teach the player by letting them play and learn by doing so. The player can access the options menu from this screen.

### Pause Screen
A simple screen which pauses the game and adds an overlay screen containing three options:
  - Game Options
    * Video Options
    * Audio Options
    * Erase Save Data
  - Exit Game to Title Screen
  - Exit Game to Desktop

### Game Screen
The Game Screen contains the playable area, a toggleable inventory overlay and a player status overlay.

## Controls

This assumes the Controller used is an Xbox 360 Controller

| Control Type                  | Keyboard / Mouse    | Controller   |
|-------------------------------|---------------------|--------------|
| Movement                      | WASD                | Left Analog  |
| Aim (Precision Item Usage)    | Mouse               | Right Analog |
| Attack (Hold to focus stance) | Mouse - Left Click  | RT           |
| Interact                      | E                   | A            |
| Use Item                      | Mouse - Right Click | RB           |
| Inventory                     | Q                   | Y            |
| Sprint                        | Space               | B            |
| Map                           | C                   | Back         |
| Pause                         | Escape              | Start        |

## Mechanics

### NATIVE PLAYER MECHANICS

#### Sprinting
Sprinting acts similarly to the mountain bike in Pokémon, whereby the hero gains speed but loses control when coming to a stop. Unless mastered, this can cause the hero to take damage by falling off the edge of a cliff.

#### Swordsmanship
The hero wields a blade which can be used to quickly slice at enemies, or, if in focus stance, deal increased damage to all enemies within reach. Additional swordsman skills can be learnt.

#### Basic Environment Interaction
The hero is able to press basic buttons and switches, talk to NPCs, and open doors and chests.

### ACQUIRED PLAYER MECHANICS

#### Lightstep
Lightstep allows the hero to teleport a short distance, over small holes or through void walls.

#### Hookchain
The Hookchain allows the hero to pull towards anything the chain is attached to. If attached to another entity, depending on its' size, it will be brought towards the player.

### GAME MECHANICS

#### Moving Platforms
Automatic and switch-activated platforms.

#### Twilit Gateways
Doors that are opened by striking the keyhole with a Twilit Keyblade, the boss key of a dungeon.

#### Void Walls
Void Walls are walls of darkness that cannot be passed by matter. They can be teleported through using Lightstep.

# Art

Art for Lightfall is represented in a 2D pixel style with a hint of ambience. Typically, default sprites have a size of 32x64 (for individual sprites, not for sprite sheets.) This is not limited for every sprite in the game.

## Tile Sheets
Tiles have a size of 16x16. Tiles can contain transparent pixels. Tiles in TileSheets do not need an order, but it would help to group relating tiles with each other.

Tile Sheets should be kept within a folder named "tilesets" and given the name of the area of the world or dungeon they represent.

## Sprites

All Entities have a Direction and a State. The Direction dictates which way the entity is facing. The list of Directions:

* UP
* DOWN
* LEFT
* RIGHT

Sprites should be made using this order, whereby each frame of the sprite is followed horizontally by the next frame, and each direction of the sprite is followed vertically by the next direction.

The State of the Entity determines which animation should be used while the Entity is in that State. The list of States:

* NONE
* IDLE
* MOVING
* ATTACKING
* TAKING_DAMAGE
* FALLING
* LANDING
* DYING
* DEAD

Individual sprite sheets should be created for each state. Each sprite sheet created for one Entity should be packaged into its own folder. File and folder names should all be lowercase. Example:

```
/player
    idle.png
    moving.png
    attacking.png
    taking_damage.png
    falling.png
    landing.png
    dying.png
    dead.png
```

File names should represent the entity state. Entities without a state should be contained within a folder named `stateless`, and given their own unique name.

All files are packed and processed into one single file with a corresponding atlas file which is read by libGDX's TextureAtlas class.
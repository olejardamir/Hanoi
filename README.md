# Hanoi Engine as REST API

## Rules of the Game
This code is a simple representation of the towers of Hanoi game.
The game setup consists of having rods and pegs (or discs) that fit on the rod. 
The goal of the game is to move the stack of pegs (or discs) from initial to a final rod while having the same sorting of the pegs as the result.

The rules of the game are:
- only one peg (or disc) moved at a time
- only the upper-most peg (or disc) is moved
- no peg (or disc) to be placed on top of a smaller peg (or disc)

## Technology stack
The programing language of choice is Java. The framework of choice is called Ninja Framework. Ninja was used for its simplicity, representing a Swiss-army knife of the RESTful microservices. Simplicity is its power, and it was chosen instead of the popular frameworks such as Spring-boot (although the logic is the same). Furthermore, there was no need to use any databaess, and all POJOs are loosely coupled, existing only for the session. The connection management and everything else is done by Ninja Framework. No filters or session data is used. Server is Jetty, dependency injection and singletons are Google classes, while JUnit was used for the test-cases. All requests are POST requests to HTTP at localhost:8080. For manual testing, you may use CURL or POSTMAN.

## Installing and Running
For the purpose of this document, a new and a fresh Linux has been placed into a Virtual Machine. The version is Linux Mint linuxmint-20-cinnamon-64bit.

sudo apt-get update

sudo apt-get install openjdk-11-jdk -y

sudo apt-get install maven -y

sudo apt-get install git -y

git clone https://github.com/olejardamir/Hanoi.git

cd Hanoi/

mvn clean install

### Testing

mvn test

You should see "Tests run: 8, Failures: 0, Errors: 0, Skipped: 0"

### Manual Running or Testing

mvn ninja:run

Open a new console tab and execute, for example:

curl -X POST http://localhost:8080/newGame/1/2/

Result will be:
```JSON
{
  "message": "New game started",
  "currentState": {
    "rods": [
      {
        "totalPegs": 1,
        "rodNumber": 0,
        "stack": [
          0
        ],
        "finalRod": false
      },
      {
        "totalPegs": 1,
        "rodNumber": 1,
        "stack": [],
        "finalRod": true
      }
    ],
    "finalRod": 0
  }
}
```

curl -X POST http://localhost:8080/playGame/0/1/
```JSON
{
  "message": "VICTORY!",
  "currentState": {
    "rods": [
      {
        "totalPegs": 1,
        "rodNumber": 0,
        "stack": [],
        "finalRod": false
      },
      {
        "totalPegs": 1,
        "rodNumber": 1,
        "stack": [
          0
        ],
        "finalRod": true
      }
    ],
    "finalRod": 0
  }
}
```

## Engine design
### Logic
Although we were told that the game engine should support 4 pegs, it does not say that it should not support any other number of rods or pegs. Therefore, the universal Henoi tower engine was made. Having the universal engine implies several constraints. 
- We cannot have empty rods. 
- The minimum number of pegs is 1. 
- We can have two or more rods. 
- If we have two rods, then the number of pegs must be 1, otherwise, it can be any number.

The pegs are represented by a number, starting from 0. Higher the number, larger the peg. While moving the upermost peg from one rod to another (one by one), the rules of the game become:
- We cannot remove from an empty rod.
- We cannot place on top of the smaller peg.

### Data Structures
For this game, we only need one data structure. Stack is the data structure that functions according to a FIFO (First-in, First-out) principle. The stack functions that we need are peek (for verifying the move), push (for placing a peg on the rod), and pop (for removing a peg). However, we have also used a Queue (as a cloned Stack, since code is cleaner) to confirm the victory, that is, if all pegs are sorted.

### Design Patterns
We have used strictly the MVC pattern offered by the framework, while re-routing the view into a JSON output. Furthermore, we have used a Singleton for the game object and Dependency Injection to loosely couple the game and make it user-connection specific. The framework patterns were strictly followed, without complicating things further. Therefore, we are using the Controller and Service for executing the game and control the Models, with JSON as the view.

### Code Architecture



# Hanoi Engine as REST API

## Rules
This code is a simple representation of the towers of Hanoi game.
The game setup consists of having rods and pegs (or discs) that fit on the rod. 
The goal of the game is to move the stack of pegs (or discs) from initial to a final rod while having the same sorting of the pegs as the result.

The rules of the game are:
- only one peg moved at a time
- only the upper-most peg is moved
- no peg to be placed on top of a smaller peg

## Technology stack
The programing language of choice is Java. The framework of choice is called Ninja Framework. Ninja was used for its simplicity, representing a Swiss-army knife of the RESTful microservices. Simplicity is its power, and it was chosen instead of the popular frameworks such as Spring-boot (although the logic is the same). Furthermore, there was no need to use any databaess, and all POJOs are loosely coupled, existing only for the session. The connection management and everything else is done by Ninja Framework. No filters or session data is used. Server is Jetty, dependency injection and singletons are Google classes, while JUnit was used for the test-cases. All requests are POST requests to HTTP at localhost:8080. For manual testing, you may use CURL or POSTMAN.

## Installing and Running
For the purpose of this document, a new and a fresh Linux has been placed into a Virtual Machine. The version is Linux Mint linuxmint-20-cinnamon-64bit.


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



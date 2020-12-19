# Hanoi Engine as REST API

## Rules
This code is a simple representation of the towers of Hanoi game.
The game setup consists of having rods and pegs that fit on the rod, thus forming a stack. 
The goal of the game is to move the stack of pegs from initial to a final rod while having the same sorting of the pegs as the result.

The rules of the game are:
- only one disk moved at a time
- only the upper-most disk is moved
- no disc to be placed on top of a smaller disc

## Technology stack
The programing language of choice is Java. The framework of choice is called Ninja Framework. Ninja was used for its simplicity, representing a Swiss-army knife of the RESTful microservices. Simplicity is its power, and it was chosen instead of the popular frameworks such as Spring-boot (although the logic is the same). Furthermore, there was no need to use any databaess, and all POJOs are loosely coupled and existing for the session. Within the Ninja Framework, the server is Jetty, dependency injection and singletons are Google classes, while JUnit was used for the test-cases. The connection management and everything else is done by Ninja Framework. All requests are POST requests to HTTP at localhost:8080. For manual testing, you may use CURL or POSTMAN.

## Installing
For the purpose of this document, a new and a fresh Linux has been placed into a Virtual Machine. The version is Linux Mint linuxmint-20-cinnamon-64bit.


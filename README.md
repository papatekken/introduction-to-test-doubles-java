# Introduction to Test Doubles - Java Track
Tech Returners Exercise
## Introduction

This repo is designed to enable you to explore the different kinds of Test Doubles used in the context of testing.

## Welcome to the Simplified Bubble Tea Order Service System! 🧋
You will be exploring a simplified version of a Bubble Tea Order Service System.

## Key Features 🧋

🧋 A Bubble Tea Order Service to manage the creation of a `BubbleTeaOrderRequest` based on receiving a `BubbleTeaRequest`

🧋 A Bubble Tea Messenger to send `BubbleTeaOrderRequest`s over email

🧋 A Bubble Tea Roulette Service to let the customer choose a random type of Bubble Tea

## Pre-Requisites

- Java SE Development Kit 15 (The Maven pom.xml specifies version 15, but the code should work against version 11 upwards).
- Maven

## How to Get Started

1. Fork this repo to your Github and then clone the forked version of this repo
2. Work through the activities in the activities folder starting with [Activity 1](./activities/activity_1.md).

### Running the Unit Tests

You can run the unit tests in IntelliJ, or you can go to your terminal and inside the root of this directory, run:

`mvn test`

## What's Next?
Feel free to head back to review the concepts again.
We encourage you to do some of your own research and experimentation in your own time to explore these concepts further.

-----
### Allan Yip

## Improvements

test function shouldGenerateBubbleTeaTypePeachIceTeaWhenGetRandomBubbleTeaTypeIsCalled() is added in test class BubbleTeaRouletteServiceTest

new class file BubbleTeaOrderServiceParameterizedTest is added for perform parameterized test

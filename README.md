![Image of Snackpass](https://www.snackpass.co/static/media/logo_round_2.d74f1dd2.png)

# Snackpass Full Stack Code Challenge
Welcome to the Snackpass Challenge. We really appreciate your time to participate. 

## The Challenge

Assume the customers around a campus order 5000 orders a day from 50 local restaurants. Each order contains one or multiple products. For eg, `2 burritos, a soda, and a side of chips`.

1. Design a full stack application which returns an infinite-scrolling list of trending products to the user.
2. A product can be qualified as trending if it is purchased at least once in last 48 hours
3. Each product should be tagged with two tags:
    * a recent purchase tag: `5 purchased recently`
    * a time tag `ordered 3 min ago`
4. Use a heuristic to determine which trending products gets returned higher. Base heuristic on both recency and number of recent purchases.

## Requirements
1. Implement a Full stack solution including web server, persistent storage and associated code
2. Please allocate 2-4 hours to design and implement. If you cannot finish it, you can use pseudocode for parts of web application. For eg, `assume this service has the following API`.
3. Please submit with in 72 hours from the time you accept invitation. 

## Practices
### Quality of code 
 Please use best practices for writing code and publish to this repo. 
### Q & A
 Please create an issue and tag @shrimuthu, @aduca98, @nprbst or @seankwalker for questions or review.
### Data
For sample data, use [Sample Orders](https://docs.google.com/spreadsheets/d/1xfAjSlBflehOYj4O7I2YkfcBB1b9VgSHg9X-SmRWmsE/edit#gid=280279953)
 
## Solution
Your solution content goes here... Please provide an explaination on 

<img src='https://imgur.com/XvRD4Sd.gif' title='App Screenshot' width='400px' />

### Functionality 

#### Required
[X] Design a full stack application which returns an infinite-scrolling list of trending products to the user.
[X] A product can be qualified as trending if it is purchased at least once in last 48 hours
[X] Each product should be tagged with two tags:
    * a recent purchase tag: `5 purchased recently`
    * a time tag `ordered 3 min ago`
[X] Use a heuristic to determine which trending products gets returned higher. Base heuristic on both recency and number of recent purchases.

#### Additions
[X] Randomized food icon and trending icon with purchase price 

## How you solved the problem
   - This is an android studio app coded in Java with Firebase Database using provided sample data. 
   - Pagination, 35 items loaded at a time, as items are loaded their counts are stored into a HashMap to return "recent purchases"
   - 
   

## How to setup, how to run it
   - Download Android Studio
   - Connect to internet and to database --> Tools --> Firebase --> Realtime Database (should already be connected)
   - Run Android app emulator (any model) 
   

You could be as verbose as you would like for providing details on approach, setup etc.


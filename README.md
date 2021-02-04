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

<img src='https://imgur.com/XvRD4Sd.gif' title='App Screenshot' width='400px' />

### Functionality 

#### Required
* [X] Design a full stack application which returns an infinite-scrolling list of trending products to the user.
* [X] A product can be qualified as trending if it is purchased at least once in last 48 hours
* [X] Each product should be tagged with two tags:
    * a recent purchase tag: `5 purchased recently`
    * a time tag `ordered 3 min ago`
* [X] Use a heuristic to determine which trending products gets returned higher. Base heuristic on both recency and number of recent purchases.

#### Additions
* [X] Randomized food icon and trending icon with purchase price 

### How you solved the problem
   - This is an android studio app coded in Java with Firebase Database using provided sample data. 
   - Converted SampleData into json format and uploaded to Firebase
   - Here's an image of the data in Firebase: 
   - <img src='https://imgur.com/XeZoYpl.png' title='App Screenshot' width='200px' />

### Important Files:
#### MainActivity_d.java
   - Infinite Scroll, Pagination with RecycleView, 35 items loaded at a time
   - currentTime is set to "03/08/2019 21:58", this is the most recent date; using today's date would result in no trending items 
   - As items are loaded their counts are stored into a HashMap "recent_purchases" to return "recent purchases", this counts all of "item_name" that is loaded
   - helper functions "addDic" and "getRecentPurchases" help us count and get correct values for RecyclerView 
   - "RecentPurchase_and_Time implements Comparator<Orders>" is used to help us sort "Orders" so we can determine which trending products are higher, see class for math calculation 
   - Use FirebaseSQL (very limited)
   - "OnCreate", "getOrders", "getLastKeyfromFirebase" implement infinite scroll, there are no repeating items and all items have been purchased at least once in last 48 hrs
   
#### MyAdapter_d.java
   - "addAll" Orders from MainActivity_d, change text in RecyclerView, randomize food images 

#### Orders.java
   - implement new class Orders, calculate time date differences with DateTimeFormatter

#### activity_main.xml
   - <img src='https://imgur.com/YFQOEkB.png' title='App Screenshot' width='200px' />
   
#### row_layout.xml
   - <img src='https://imgur.com/tFCMU3G.png' title='App Screenshot' width='200px' />
   

### How to setup, how to run it
   - Download Android Studio
   - Connect to internet and to database --> Tools --> Firebase --> Realtime Database (should already be connected)
   - Run Android app emulator (any model) 
   

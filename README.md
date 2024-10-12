# Multi online ticketing system
This software serves as an event management and ticketing platform, 
exclusively catering to authorized event organizers. <br> 
It enables these verified hosts to create, promote, and sell tickets for their events. <br> 
The system provides a comprehensive online ticketing solution that caters to both event hosts and customers.<br> 
Event organizers can easily input authenticated event information, 
while users can access event listings and make ticket purchases as well as choose seats (where applicable) 
once events are live.

***

## User Story
- As a host, I want to add events to host for other users.
- As a customer, I want to view the hosted events.
- As a host, I want to cancel hosted events.
- As a customer, I want to add events to shopping cart.
- As a Host and/ or customer, I want to view account details
#### !new!
- As a Host, I want to retrieve customer information
#### -- Phase 2 --
- As a user, when I select the quit option from the application menu, I want to be reminded to save my shopping cart to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my shopping cart item list from file. 

#### *User stories to Implement later:*
- As a user (customer), I want to purchase tickets
- As a user (customer), I want to cancel tickets
- As a user (host), I want to set the price of the event tickets.
- As a user (customer), I want to get tickets from Events to my collection
- 
- As a user, I want to be able to load bought tickets and other purchased tickets from file (manula retrieval)


# Phase 4: Task 2
Fri Dec 01 16:22:54 PST 2023--> Organizer: Taebin Created <br>
Fri Dec 01 16:22:59 PST 2023--> Taebin added House Party to Event List <br>
Fri Dec 01 16:23:06 PST 2023--> Taebin added Halloween Party to Event List<br>
Fri Dec 01 16:23:13 PST 2023--> Taebin added Christmas Party to Event List<br>
Fri Dec 01 16:23:31 PST 2023--> Taebin added Birthday Party to Event List<br>
Fri Dec 01 16:25:17 PST 2023--> Tae bin removed Halloween Party to Event List<br>
Fri Dec 01 16:25:29 PST 2023--> Customer: Rita Created<br>
Fri Dec 01 16:25:32 PST 2023--> Added House Party to Cart<br>
Fri Dec 01 16:25:33 PST 2023--> Added Christmas Party to Cart<br>
Fri Dec 01 16:25:34 PST 2023--> Added Birthday Party to Cart<br>
Fri Dec 01 16:25:35 PST 2023--> Added House Party to Cart<br>
Fri Dec 01 16:25:39 PST 2023--> Removed House Party from Cart<br>
Fri Dec 01 16:25:42 PST 2023--> Saved Rita's ShoppingCart<br>
Fri Dec 01 16:25:44 PST 2023--> Added Christmas Party to Cart<br>
Fri Dec 01 16:25:44 PST 2023--> Added Birthday Party to Cart<br>
Fri Dec 01 16:25:44 PST 2023--> Added House Party to Cart<br>
Fri Dec 01 16:25:44 PST 2023--> Loaded Rita's ShoppingCart


# Reflection
If I had more time to work on this project (which I will continuously),
First of all i want to refactor codes so that the Customer model does not contain the save to Json functionality.
I will extract codes and make a new class to represent a customer being able to save to Json.

Also, I want to have unnecessary relations among classes. I will be able to use singleton method to simplify and share 
organizer and customer objects among the panel classes.
# Computer Networking Fall 2022 
**CS401Assignments** <br />
Assignment01: TCP Muli-User Socket Programming <br /><br /><br />
Assignment02: Exploring how symmetric encryption works. You will use Caesar Cipher substitution
technique to encode and decode your data transmitted over a network. Socket programming in Java should be
used for implementation. <br /><br /><br />

Final Assignment: Design a simple application that meets the following requirements: (60 points)

Your application should have at least two separate servers programs (server1, server2) and a client program.
The client should communicate with server1 using TCP and with server2 using UDP or vice versa.
At least one of the servers should be multi-threaded
Should involve some sort of database component -- you can use a file for this part
At some point, server1 and server2 should interact with each other
For example, consider a simple shopping website with two servers. 

Server1 is the main server that the client interacts with. Server1 first asks the user for credentials and verifies them by accessing its login.txt file which has registered username and password details.
For a valid user, Server1 then provides a list of items to the client. The user, through the client program, then chooses from the list. Server1 then generates a receipt and returns it to the client. 
The client will then send the user's credit card details in the encrypted format to server1. Server1 forwards it to server2.
Server2 validates the credit card info. and notifies server1 of payment, and server1 confirms it to the client. <br />

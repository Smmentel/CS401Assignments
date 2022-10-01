To run this code in the proper manner, you will need eclispe or at least some java language complier. 
(Also preferably two or more devices to better exemplify what is occuring.) 
1. Begin with the server side on device 1, 
the code to compile is in the folder "Server side" which should contain the following files: 
	Dictionary.java
	Dictionary.txt
	DictionaryDefinition.java
	HandleClient.java
	Server.java

2. On a separate device, device 2, compile only the following file: client.java

3.BEFORE COMPILING client.java: if you are using two separate devices, before compiling client.java please ensure that the IP Address is correct!
	The IP Address should be device 1's IP address, otherwise known as the Server's side IP address. 
	To find it simply enter "ipconfig" into device 1's terminal, 
	under "Wireless LAN adapter Wi-Fi" there is a section called "IPv4 Address" 
	the corresponding number given is what you enter in the client side code at the point:
	"Socket socket = new Socket("localHost",8000);" 
	(replace localHost with the given IP address, unless performing code on same device)


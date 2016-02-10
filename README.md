# Rover-GPS-Tracker

License: GNU GPL v3

This project is a modification of the Minigeo Library(https://code.google.com/archive/p/minigeo/) to suit the needs of tracking a vehicle from a base station.

Major modifications include:
* Adding a side panel to add/remove points to the map panel dynamically as well as get additional information of the points
* Requiring a base point location initially
* The map panel has been modified to initialize with a 2km x 2km working area with the base point at the center
* Addition of a special rover point the location of which is updated on the map panel in real time as per the GPS coordinates received from the rover
* Adding the direction pointer to indicate the direction the rover is facing
* Some visual tweaks to the map panel

# Installation
Just compile the java files and run

# Usage
Provide the GPS coordinates of the base station in the dialogue box that appears right after lauching the application.
New points can be added using the side panel that apears in the dialogue box after entering the base station GPS coordinates.
Connect to the server by specifying the server IP address and port number in the text boxes at the bottom right corner and clicking connect.
The server should send GPS information string in the following format:

GPS x y z
where x, y and z are the latitude, longitude and the heading information of the rover.

As soon as rover information is received from the server, the rover point will be plotted in the map.

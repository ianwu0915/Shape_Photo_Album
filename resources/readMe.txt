// CS5004 
// HW9
// Hsinyen Wu

# PHOTO ALBUM APPLICATION

This is a photo album application that applies a Model-View-Controller design.
The application reads from a text file that gives a list of sequential commands for the Model to create shapes and take snapshots.
These snapshots are then visualized by either the interactive graphical view or web view as an html file.


## TABLE OF CONTENTS

- Model
- Controller
- View


## MODEL

The design of model is generally based on HW8 but with some changes for better combining with Controller and View.


The MODEL component of the photo album application is designed around three interface classes: IPhotoAlbum, ISnapShot, and IShape, which respectively represent the photo album, snapshots, and geometric shapes. For each of these components, several concrete classes are defined, following a top-down design approach:

### PhotoAlbumModel:

IPhotoAlbumModel: an interface defining a photo album that holds shapes and snapshots of those shapes. It includes methods for creating, manipulating, and deleting shapes, taking snapshots, and resetting the album.

PhotoAlbumModel: a concrete implementation of IPhotoAlbum, with attributes for lists of shapes and snapshots.

#### Changes: The previous createSnapshots() function always use the same shape list of the PhotoAlbumModel to create new snapshots, resulting in all snapshots sharing the exact same shape list.
Therefore, I modified the function that creates a deep copy of the shape list for each snapshot to have the right shape list at the moment. 			


### Snapshot

ISnapShot: an interface defining methods for representing a snapshot of a shape's state at a specific time. It includes methods for getting information about the snapshot (id, timestamp, description, and information about the shape it contains).

Snapshot: a concrete implementation of ISnapShot, with attributes for the snapshot's id, timestamp (using the exact time the snapshot is taken), description, and a list of shapes it contains.


### Shapes

IShape: an interface representing geometric shapes, with common attributes such as name, color, position, and size. It defines methods for resizing, moving, and changing the color of a shape.

AbstractShape: an abstract class capturing the common attributes of different shapes (name, type, color).

Rectangle: a subclass of AbstractShape, representing a rectangle with attributes for its top-left corner position, width, and height.

Oval: another subclass of AbstractShape, representing an oval with attributes for its center position, x and y radius.

Point: a class representing a 2D point (x, y) used for positioning shapes.
 
Color: a class representing a color in RGB format, with three int values (r, g, b) representing its red, green, and blue components.


#### Changes: The RGB values of the Color class is set from double to integer for Graphical View to visualize using the built-in Java Color class. 
	       The width and height are also set from double to integer for Graphical View to create images.

## CONTROLLER 

The Controller of this application serve as the intermediary between the user input, Model and View.

It parses the commands in the input file for Model to create shapes and snapshots. Then, it provides the View the data Model process for visualization.
It has two constructor that takes in Model, input text file, and choice of either graphical or web View class dependent on the user requirement.

The read() method reads commands from the input and performs actions in the photo album model. The supported commands include creating shapes, moving shapes, resizing shapes, changing shape colors, creating snapshots, and removing shapes.
After performing the actions in the photo album model, the read() method passes the information stored in the model to either the graphical view or web view for display. If the graphical view is used, the method creates an image for each snapshot and adds it to the image list of the view. If the web view is used, the method calls the convertToHtml() method of the view to generate an HTML file to display the snapshots.


## VIEW

### Graphical View

This Java Swing Class extends JFrame represents the user interface for an interactive Graphical view. 
After the controller parses the commands and have model executes the commands, the snapshot list that holds the information of all snapshots will be shared to View.
The application will then use the createGraph() in Graphical View to draw those snapshots and stored in its list of snapshot image for visualization.

There are three major panels on the interface, top, middle, and bottom panel.

The top panel shows the information of the snapshot displayed using JTextArea that 
The middle panel shows the snapshot.
The bottom panel has four buttons for user to use.

User can view and interact the snapshots on the interface by clicking these following buttons:
"prev": display the previous snapshot
"select": choose the snapshot to display
"next": display the next snapshot
"quit": quit the program

The functions of each buttons are implemented with the actionPerfromed(). 


### Web View

This class represents a view of photo album application in HTML format.
It provides a method, convertToHtml to convert the data processed in Model to an HTML file.
It then display all the snapshots using SVG objects. 

### Main

This main class of the photo album application parse command line arguments and initializes the application based on the user's input.
The command line must specify "-in [input-file-name] and -view "type-of-view" for application to execute.
The type of view includes "graphical" or "web".

For web view, the commands should also specify "-out [output-file-name]". User can also specify the width and height of the view window.














JavaFX
================

Java FX Exercises

JavaFX is a cross platform GUI toolkit for Java, and is the successor to the Java Swing libraries.

#### Installation

If you already develop applications with Java, you probably don't need to download anything at all: JavaFX has been included with the standard JDK (Java Development Kit) bundle since JDK version 7u6 (August 2012).  If you haven't updated your Java installation in a while, head to the Java download website for the latest version.

### Basic Framework Classes

Creating a JavaFX program begins with the Application class, from which all JavaFX applications are extended.  Your main class should call the launch() method, which will then call the init() method and then the start() method, wait for the application to finish, and then call the stop() method.  Of these methods, only the start() method is abstract and must be overridden.

The Stage class is the top level JavaFX container. When an Application is launched, an initial Stage is created and passed to the Application's start method. Stages control basic window properties such as title, icon, visibility, resizability, fullscreen mode, and decorations; the latter is configured using StageStyle. Additional Stages may be constructed as necessary. After a Stage is configured and the content is added, the show() method is called.

Knowing all this, we can write a minimal example that launches a window in JavaFX:

```
import javafx.application.Application;
import javafx.stage.Stage;
 
public class Example1 extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
        theStage.setTitle("Hello, World!");
        theStage.show();
    }
}
```

### Structuring Content

Content in JavaFX (such as text, images, and UI controls) is organized using a tree-like data structure known as a scene graph, which groups and arranges the elements of a graphical scene. 

A general element of a scene graph in JavaFX is called a Node. Every Node in a tree has a single "parent" node, with the exception of a special Node designated as the "root". A Group is a Node which can have many "child" Node elements. Graphical transformations (translation, rotation, and scale) and effects applied to a Group also apply to its children. Nodes can be styled using JavaFX Cascading Style Sheets (CSS), quite similar to the CSS used to format HTML documents.

The Scene class contains all content for a scene graph, and requires a root Node to be set (in practice, this is often a Group). You can set the size of a Scene specifically; otherwise, the size of a Scene will be automatically calculated based on its content. A Scene object must be passed to the Stage (by the setScene() method) in order to be displayed.

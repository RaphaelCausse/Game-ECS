# PROJECT UNKNOWN

# Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
- [Execution](#execution)
- [Game Manual](#game-manual)
- [Author](#author)

## REQUIREMENTS

- Java JDK (version >= 18)
- JavaFX SDK (version >= 17.0.6)

https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html

https://gluonhq.com/products/javafx/

## INSTALLATION

Install Java JDK and JavaFX SDK according to your OS.

Locate the `lib` folder of JavaFX SDK in your installation, it will be necessary for the next step.

Open a termial and move to the root of project folder.

```shell
cd <your_path_to_project_folder>
```

## EXECUTION

Open a termial, move to the root of project folder.

Run the executable JAR File using the following command, and make sure to write your path to the `lib` folder as previously mentionned :

```shell
java --module-path "your_path_to_javafx_lib_folder" --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.media -jar Runnable-Project-Unknown.jar
```

## GAME MANUAL

You are free to explore the game world. You are a wanderer that helps people during his journey.

### Game controls

**Move** the character with `W`, `A`, `S` and `D` in AZERTY keyboard layout.

Perform some **attacks** with `SPACE`.

In the inventory, use the arrow keys to :
* **Navigate** with `LEFT` and `RIGHT`
* **Use** an item with `UP`
* **Drop** an item with `DOWN`

## AUTHOR

Raphael CAUSSE.

05/2023, Developped on Windows 10, Eclipse IDE.
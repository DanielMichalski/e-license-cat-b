# e-Licence Category B

This is a Java Swing application that allows candidates to prepare for the Driving License test category B. \
It contains lessons and exams that end with a PDF report.

[![e-Licence Category B](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/gifs/application.gif)](https://www.youtube.com/watch?v=Mx8VI7CxQxY "e-Licence Category B")

## Table of Contents

* [Prerequisites](#prerequisites)
* [Project contains](#project-contains)
* [Running the application](#running-the-application)
    * [On Windows](#on-windows)
    * [On MacOS/ Linux](#on-macos-linux)
* [Video presentation](#video-presentation)
* [Screenshots](#screenshots)

## Prerequisites

- [Java JDK](https://www.oracle.com/pl/java/technologies/javase-downloads.html) version 17+
                        
## Project contains
- Swing application
- Displaying videos and images
- Encryption
- Reading encrypted files from csv
- Converting XLS to CSV
- Generating PDF files
- Multithreading

## Running the application

#### On Windows

```bash
## Build application using Maven Wrapper
mvnw.cmd clean install

## Run Java application using Maven Wrapper or simply run ELicenseApplication class
mvnw.cmd exec:java -Dexec.mainClass="com.danielmichalski.elicense.ELicenseApplication"
```

#### On MacOS/ Linux

```bash
## Build application using Maven Wrapper
./mvnw clean install

## Run Java application using Maven Wrapper or simply run ELicenseApplication class
./mvnw exec:java -Dexec.mainClass="com.danielmichalski.elicense.ELicenseApplication"
```
## Video presentation

[![See on YouTube](http://img.youtube.com/vi/Mx8VI7CxQxY/0.jpg)](https://www.youtube.com/watch?v=Mx8VI7CxQxY)

## Screenshots

![Screen 1](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/images/1.jpeg "Screen 1")

![Screen 2](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/images/2.jpeg "Screen 2")

![Screen 3](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/images/3.jpeg "Screen 3")

![Screen 4](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/images/4.jpeg "Screen 4")

![Screen 5](https://github.com/DanielMichalski/e-license-cat-b/blob/master/.github/images/5.jpeg "Screen 5")

# GTFSParser
============
The goal of this project is to build a parser in Java which reads GTFS files and creates an engineered suitable POJO structure filled with the data.

Result: Automatic population of a mysql database with GTFS files, creation of suitable pojo structure filled with the data.

MySql is used to create a database and populate it with the pieces of information extracted from the GTFS.

ECLIPSE Neon IDE has been used to implement this project.

=============================================================================================================================

PROJECT LAYOUT:
===============
The original repository  contains 4 package.

1) The package "engine" contains engineered_GTFS.java, the main controller class which contains main() 
for execution of this project.

2) The package "model" contains *_pojo.java files.
This files defines defines various pojo classes for storing the data objects from the GTFS files.

3) The package "MySqlDB_management" contains:
"UnzipTool.java" static class used to extract the "raw_data/GTFS.zip" file into "raw_data/GTFS".

"ProcessingValue.java" static class used to process data in mysql database. 

"MySQLConnection.java" java class used to get connection to MYSQL database.

The data from database could be used for suitable objects creation in "object" package.

4) The package "object" contains Obt_*.java files.
Classes in this package get data from MYSQL database and create objects of respective classes of "model" package.


Folder "raw_data" has been used to put GTFS.zip file inside it for processing. It must be noted that GTFS.zip file must contain ".txt" files as per the GTFS specification.

=============================================================================================================================

PROJECT SETUP AND RUNNING THE PROJECT:
======================================
Some initial setup is required to run this project.

Make sure that Java 8 and MySql database has been properly installed onto your system. 
Make sure of granting the right permission to the user on the database.
The credential of the user for the database are:	userName = "gtfs_user";	password = "waynaut".

Put the GTFS.zip file inside the "raw_data" folder.

Run engineered_GTFS.main() to view the output, the structured content of the GTFS files.

=============================================================================================================================

Name: Junior Samaroo
Student I.D. # 0663108

CIS 2430 Assignment #2

Task: 
Re-design and extend assignment #1 which was a simple "LibrarySearch" program that records in an array of strings and performs "add" and search functions for the records. The program should use object-oriented concepts in general and some specific features of the Java language, such as multiple classes and "Arraylists".

Problem: 
Various problems had to be solved. The first was how the user inputs data for the library e.g. all the book info in one line or separate info such as title, call number etc. on separate prompts to the user on different lines. Also searching both book and journal array lists had to be performed. Lastly the year range to be searched for which was my most time consuming part of the assignment.

Assumptions and Limitations: 
It is assumed that only one "Library" is being worked with i.e. not multiple libraries having multiple records of books and journals. Only major limitation found is that data is not stored permanently such that all data is lost when the program closes.

Build/Test: 
The program consists of four classes:
			1) Library (main class)
			2) Book
			3) Journal
			4) LibrarySearch
To run the program, user must compile "Library.java" by typing "javac Library.java" from a terminal within its directory. After compilation the user must run the compiled code by typing "java Library" within the terminal. One can test the program by entering various record information and then trying to search for stored information.

Examples of Test Cases:

Book:
CallNumber: QA76.73.J38S265
Authors: Walter Savitch,
Title: Absolute Java
Publisher: Addision-Wesley
Year: 2009

Journal:
CallNumber: P98.C6116
Title: Kenrich Mock Computational Linguistics
Organization: Association for Computational Linguistics
Year: 2008

A search for year range '2000-2010' would result in both records being found.
A search for 'title' Mock only would result in only the journal being found.

Improvements: 
Program can be improved if less code can be reused and if more search specific fields such as 'Authors' can be entered. Also only searching the book and journal sections can also be implemented.


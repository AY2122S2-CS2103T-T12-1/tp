---
layout: page title: User Guide
---

**Ultimate DivocTracker _(UDT)_** is a desktop app for managing COVID-19 contacts in school administration, optimized for use via interacting with the application through easy-to-use commands on a user-centric interface. 
Ultimate Divoc Tracker can get your contact-tracing tasks done faster than traditional GUI apps. 

School administrators _(like teachers)_ in charge of managing COVID-19 can use UDT to easily track COVID-19 cases amongst the student population with ease and concentrate on what matters most, the education of the students.

Through this user guide, you will learn how to use UDT effectively and efficiently, to manage COVID-19 cases in your schools.
Features below are accompanied by instructions, figures and examples to help you understand how to use them.
A glossary is included at the end to clarify any technical or vague terms used.

# Content Page

<div markdown="block" class="alert alert-info">

- [Quick start](#quick-start)
- [About UDT](#about-udt)
- [Features](#features)
  - [Add a student](#add-a-student-add): `add`
  - [List all students](#list-all-students-list): `list`
  - [Find student by name](#find-student-by-name-find): `find`
  - [Find student by status](#find-student-by-status-findstatus):  `findstatus`
  - [Find student by class](#find-student-by-class-findclasscode): `findclasscode`
  - [Find student by activity](#find-student-by-activity-findactivity): `findactivity`
  - [Edit student’s personal details](#edit-student-details-edit): `edit`
  - [Delete a student](#delete-a-student-delete): `delete`
  - [Exit the application](#exit-the-application-exit): `exit`
- [Saving the data](#saving-the-data)
- [Editing the data file](#editing-the-data-file)
- [Frequently Asked Questions](#faq)
- [Command Summary](#command-summary)
- [Glossary](#glossary)

</div>

# Quick start
1. Ensure you have **Java 11** or above installed in your Computer.
   - You can download **Java 11** from [this link](https://www.oracle.com/java/technologies/downloads/#java11).
   - To check which version of Java you have installed:
     1. Type "Command Prompt" into the search bar next to your Start menu, and click on it when it appears in the search results.
     2. Type "java -version" into the Command Prompt, then press Enter on your keyboard.
2. Download the latest **ultimatedivoctracker.jar** from [our GitHub repository](https://github.com/AY2122S2-CS2103T-T12-1/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Ultimate DivocTracker application.
4. Double-click the file to start the app. The GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.
5. Refer to the Features below for details of each command.

<div markdown="block" class="alert alert-info">

> :information_source: **Installation notes:**  
> - Application save data will be stored in the same folder containing the **ultimatedivoctracker.jar** application.  
> - Currently officially supported for **Windows**, **Mac** and **Linux** platforms.

</div>

## About UDT
Before we get into the details of what UDT can do, let's first bring you through what the application will look like.

|![UDTGUI](images/UdtGuiWithLabels.png)|
|:--:|
|*Figure 1 - GUI*|

As seen in Figure 1 above, the application contains 3 main segments.

Firstly, the Command Line (area to input commands) is at the top of the application and can be easily seen by the blinking cursor/insertion point.

Secondly, the box beneath the Command Line is where the application will produce any text outputs or errors if the command provided requires so.

Lastly, each information card contains the following details of the student and are presented in order:
1. Name
2. Activity/Activities
3. Phone Number
4. Address
5. Email Address
6. Class
7. COVID-19 Status

With UDT, you can update and track COVID-19 Cases in your school, keep track of Close-Contacts, to perform timely updates to parents and Next-of-Kin.
Filter through the endless list of students with a simple command to extract details on the cases by class, or by activities (CCAs etc.).

# Features
Below are a set of commands that can be used in the **_UDT_**. Their formats and examples are provided along with each feature.

<div markdown="block" class="alert alert-info">

> :information_source: **Formatting notes:**  
> - Words in `UPPER_CASE` are the user inputs to be supplied.  
> - Items in square brackets `[]` are optional.

</div>

## Add a student: `add`
Adds a student to the tracking list
- Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​ cc/CLASS s/STATUS [act/ACTIVITY] [act/MORE ACTIVITIES]`
  - `NAME`, `ADDRESS`, `CLASS`, `ACTIVITY` takes in text
  - `EMAIL` follows the standard email formatting 
    - eg. `johntan@example.com`
  - `PHONE_NUMBER` takes a sequence of numbers
  - `STATUS` takes either of these texts `Positive`, `Negative`, `Close-Contact`
    - `STATUS` is case-sensitive and should strictly follow the texts stated above
- Examples:
  - `add n/John Doe p/98765432 e/johnd@example.com a/John Street, Block 123 #01-01 cc/5A s/Negative`
  - `add n/Betsy Crowe p/99999999 e/betsycrowe@example.com a/Woodlands Street, Block 69 cc/2B s/Positive act/choir`

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tips:**  
> - Multiple activity tags can be added to a single student by using multiple `/act` prefixes  
>   - Eg. `act/choir act/dance`  
> - A student can also have no activity tags  
> - User inputs can be in any order

</div>

<div markdown="span" class="alert alert-info" role="alert">

> :information_source: **Note:** Capitalization of text will be reflected in the User Interface

</div>

## List all students: `list`
Shows a list of all students in the application.
- Format: `list`
  - Any user input after `list` is ignored
    - `list 12345 john` is the same as `list`

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tip:** For a filtered list, use the __*find*__ commands

</div>

|![list command](images/user-guide/list.png)|
|:--:|
|*Figure 2 - `list` Command*|

## Find student by name: `find`
Find an existing student in the application by their name
- Format: `find NAME`
  - Returns a list of students with the specified `NAME`
  - `NAME` is case-insensitive
  - Order of words in `NAME` is irrelevant
    - `find yeoh alex` can find student _"Alex Yeoh"_
  - Searching for name returns a list of names contains the provided name
    - `find john` can find students _"John Tan"_ and _"John Lee"_
- Example:
  - `find bernice` will find student _"Bernice Yu"_
  
|![find command](images/user-guide/find.png)|
|:--:|
|*Figure 3 - `find` Command*|

## Find student by status: `findstatus`
Find an existing student in the application by their Covid-19 Status
- Format: `findstatus STATUS`
  - Returns a list of students with the specified `STATUS`
  - `STATUS` is either `positive`, `negative` or `close contact`
  - `STATUS` is case-insensitive
- Examples:
  - `findstatus positive` finds all students that are labelled COVID positive
  - `findstatus negative` finds all students that are labelled COVID negative
  
|![find status command](images/user-guide/findstatus.png)|
|:--:|
|*Figure 4 - `findstatus` Command*|

## Find student by class: `findclasscode`
Finds an existing student in the application by their class
- Format: `findclasscode CLASS`
  - Returns a list of students with the specified `CLASS`
  - `CLASS` is case-insensitive
- Example:
  - `findclasscode 4A` finds all students in the class 4A

<div markdown="span" class="alert alert-primary" role="alert">

</div>

|![find classcode command](images/user-guide/findclasscode.png)|
|:--:|
|*Figure 5 - `findclasscode` Command*|

## Find student by activity: `findactivity`
Finds an existing student in the application by the activities they are participating in
- Format: `findactivity ACTIVITIY [MORE ACTIVITIES]`
  - Returns a list of students with the specified `ACTIVITY`
    - Matches based on students that have specified `ACTIVITY` in their list of `ACTIVITIES`
  - If more than 1 activity is specified, command returns a list of student that participated in **ANY** of the activities specified
  - `ACTIVITY` is case-insensitive
- Example:
  - `findactivity badminton` finds all students that have the activity _"Badminton"_
  - `findactivity badminton choir` finds all students that have the activity _"Badminton"_, _"choir"_ or **both**

|![find activity command](images/user-guide/findactivity.png)|
|:--:|
|*Figure 6 - `findactivity` Command*|

## Edit student details: `edit`
Edits an existing student's details in the list Index provided and the parts that you want to edit
- Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [cc/CLASS] [s/STATUS] [act/ACTIVITY] [act/MORE ACTIVITIES]`
  - Edits the student at the specified `INDEX`
  - `INDEX` denotes the list index of the student in the displayed list
  - `INDEX` must be a _positive integer (1, 2, 3...)_
  - Only listed fields will be changed
- Examples:
  - `edit 1 s/Positive` edits 1st student to be _COVID-19 Positive_
  - `edit 5 p/98641865 e/maryjane@yahoo.com` edits 5th student to a new phone number and email address

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tips:**  
> - Filter the student list via __*find*__ commands to make finding the index easier  
> - Omitting parts of the student details from the command will leave them unedited

</div>

|![edit command](images/user-guide/edit.png)|
|:--:|
|*Figure 7 - `edit` Command*|

## Delete a student: `delete`
Deletes the specified person from the application.
- Format: `delete INDEX`
  - Deletes the student at the specified `INDEX`
  - `INDEX` denotes the list index of the student in the displayed list
  - `INDEX` must be a _positive integer (1, 2, 3...)_ 
- Examples:
  - `list` followed by `delete 2` deletes the 2nd person in the student list
  - `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tip:** filter the student list via __*find*__ commands to make finding the index easier

</div>

|![delete command](images/user-guide/delete.png)|
|:--:|
|*Figure 8 - `delete` Command*|

## Exit the application: `exit`
Exits the program.
Format: `exit`

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tip:** You can also close the application directly

</div>

## Saving the data
UDT data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
- Data is saved to the same location as the application executable

## Editing the data file
UDT data are saved as a JSON file `[JAR file location]/data/addressbook.json`. 
- Data is saved to the same location as the application executable

<div markdown="span" class="alert alert-primary" role="alert">

> :bulb: **Tip:** Advanced users are welcome to update data directly by editing that data file

</div>

<div markdown="span" class="alert alert-warning" role="alert">

> :warning: **Warning:** Editing the data file erroneously may result in the entire data file becoming unreadable by UDT

</div>

----------------
# FAQ

Q: How do I transfer my data to another Computer?  
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous _**UDT**_.

Q: I edited my data file directly and now the application does not work properly!  
A: Delete the data folder to allow _**UDT**_ to create a new data file. Your previous data has unfortunately been lost to time.

Q: Manual insertion of students takes time, is there a faster way to do it?  
A: We are working on a feature to allow importing of **.csv** files into _**UDT**_!

Q: What if I key in the wrong command?  
A: An error message colored in red will appear stating that you have typed an unknown command.
![invalid command](images/user-guide/invalidcommand.png)


----------------
## Command Summary

| Action                                                             | Format                                                                                                                    | Example                                                                                                         |
|--------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [Add a student](#add-a-student-add)                                | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS cc/CLASS s/STATUS [act/ACTIVITIES] [act/MORE ACTIVITIES]`                    | `add n/John Doe p/98765432 e/johnd@example.com a/John Street, Block 123, #01-01 cc/5A s/Negative act/badminton` |
| [List all students](#list-all-students-list)                       | `list`                                                                                                                    | `list`                                                                                                          |
| [Find student by name](#find-student-by-name-find)                 | `find NAME [MORE_NAME]`                                                                                                   | `find James Jake`                                                                                               |
| [Find student by status](#find-student-by-status-findstatus)       | `findstatus STATUS`                                                                                                       | `findstatus positive`                                                                                           |
| [Find student by class](#find-student-by-class-findclasscode)      | `findclasscode CLASS`                                                                                                     | `findclasscode 4A`                                                                                              |
| [Find student by activity](#find-student-by-activity-findactivity) | `findactivity ACTIVITY`                                                                                                   | `findactivity choir`                                                                                            |
| [Edit student details](#edit-student-details-edit)                 | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [cc/CLASS] [s/STATUS] [act/ACTIVITIES] [act/MORE ACTIVITIES]` | `edit 2 n/James Lee e/jameslee@example.com`                                                                     |
| [Delete a student](#delete-a-student-delete)                       | `delete INDEX`                                                                                                            | `delete 3`                                                                                                      |
| [Exit the application](#exit-the-application-exit)                 | `exit`                                                                                                                    | `exit`                                                                                                          |

----------------

## Glossary
| Term      | Meaning                                 |
|-----------|-----------------------------------------|
| Parameter | Input supplied after the command        |
| JSON      | A file type that UDT uses to store data |

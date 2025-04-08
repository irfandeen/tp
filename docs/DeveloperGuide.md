---
layout: page
title: Developer Guide
---

# LogJob Developer Guide

* Table of Contents
* [LogJob Developer Guide](#logjob-developer-guide)
  * [**Acknowledgements**](#Acknowledgements) 
  * [**Setting up, getting started**](#setting-up-getting-started)
  * [**Design**](#design)
    * [Architecture](#architecture)
    * [UI component](#ui-component)
    * [Logic component](#logic-component)
    * [Model component](#model-component)
    * [Storage component](#storage-component)
  * [**Implementation**](#implementation)
    * [Add a new internship application](#add-a-new-internship-application)
    * [Edit an internship application](#edit-an-internship-application)
    * [Delete an internship application](#delete-an-internship-application)
    * [List all internship applications](#list-all-internship-applications)
    * [Sort internship applications by field](#sort-internship-applications-by-field)
    * [Find an internship application](#find-an-internship-application)
    * [Help command](#help-command)
    * [Exit the application](#exit-the-application)
* [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
  * [**Appendix: Requirements**](#appendix-requirements)
    * [Product scope](#product-scope)
    * [User stories](#user-stories)
    * [Use cases](#use-cases)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)
  * [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
    * [Launch](#launch)
    * [Getting Help](#getting-help)
    * [Adding an internship application](#adding-an-internship-application)
    * [Editing an internship application](#editing-an-internship-application)
    * [Deleting an internship application](#deleting-an-internship-application)
    * [Listing applications](#listing-applications)
    * [Exit](#exit)
--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- This Developer Guide structure draws inspiration from [AB-3](https://se-education.org/addressbook-level3/DeveloperGuide.html)
- It uses JUnit5 for testing software

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

> 💡 **_NOTE:_** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

### Architecture

<img src="diagrams/architecture-diagrams/ArchitectureDiagram.png" alt="Architecture Diagram" width = 300 />

The ***Architecture Diagram*** given above shows the overall architecture of the LogJob application software. A quick overview of each of the components has been given below.

**Main components of the architecture**

**`Main`** (consisting of [`LogJob`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) class) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of LogJob. Responsible for all user interactions between the user and the application.
* [**`Logic`**](#logic-component): The command parser/executor. Parses, validates, and executes the given command.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

`Util`: Provides classes for use across multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="diagrams/architecture-diagrams/ArchitectureSequenceDiagram.png" alt="Architecture Sequence Diagram" width="750" />

> **NOTE**: The sequence diagram above shows a simplified version of the `delete` command execution for clarity purposes

For the two main components [`Model`](...) and [`Storage`](...) dealing with application state,

* The main components `Logic`, `Model` and `Storage` are defined in interfaces. This allows the implemenetation details of each component to be decoupled from the implementation details.
* The main componetents `Logic`, `Model` and `Storage` are implemented in `LogicManager`, `ApplictationManager`, and `StorageManager` respectively.

> **EXAMPLE:** The [`Storage`](...) component defines its API in the [`Storage.java`](...) interface. It implements this interface via the [`StorageManager.java`](...) class. Other components interact through this interface rather than a concrete calss to prevent other components being coupled to the implementation. 

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`UiMain.java`](https://github.com/AY2425S2-CS2113-T11a-2/tp/blob/master/src/main/java/seedu/logjob/ui/UiMain.java)

Here is a class diagram of the UI component, not all methods and attributes are included to reduce the complexity.
![UML Diagram](diagrams/class-diagrams/UIClassDiagram.png)

The UI consists of a `UiMain` that handles the direct interactions between the user and the program logic.

The `UI` component is called by the ApplicationManager,
* Receive command from the user on the CLI.
* Outputs the list of applications on the CLI.
* Outputs the responses of the program to the user.
* Outputs the error message that is thrown by the program.

The Class UiMain is a Singleton class, and only one instance exists system wide. It is passed to the necessary components as an instance when needed.

The UiMain also calls the methods in the utility class UiTable to generate the table for the list of application.
The getTable() method will return the full string of the application table to the UiMain.

The UiMain also access constants specified in the UiConstants class.

### Logic component

**API** : [`Logic.java`]

Here's a (partial) class diagram of the `Logic` component (Note that individual parser classes are omitted for clarity and represented by the overall `Parser`package):

<img src="diagrams/class-diagrams/LogicClassDiagram.png" width="405"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `ApplicationParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. After each execution, the `LogicManager` saves the `Model` state by executing `Storage` methods

Here are the other classes in `Parser` (omitted from the class diagram above) that are used for parsing a user command:

<img src="diagrams/class-diagrams/ParserClassesDiagram.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ApplicationParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) 
* `XYZCommandParser` uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which is returned back as a `Command` object.
* All `XYZCommand` objects inherit from the abstract `Command` class that defines common methods such as `execute`, `equals`, and `isRunning`.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) implement the `Parser` interface so that they can be treated similarly where possible e.g, during testing.
* The `ParserUtils` class depends on the `Validator` package to validate each specific field. This ensures user inputs for job title, company name, application status, etc. meet domain constraints before conversion.  

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="diagrams/class-diagrams/ModelClassDiagram.png" width="800" />


The `Model` component

* Is in charge of storing the working copy of the current Internship Applications in an ArrayList.
- Implements the `InternshipApplication` class which stores relevant data of an application such as its Company Name, Job Title, Status, and Date.
  - Status is implemented using an enumeration `ApplicationStatus` which helps restrict statuses to a few valid values.
* Handles the actual manipulation of data through the `Application Manager` class.
** It recieves the appropriate command and data from the command classes, and executes the relevant methods. 
* Does not depend on any of the other structures of the program, as it's designed to be a black box to manipulate, store and handle data.


### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2113-T11a-2/tp/blob/master/src/main/java/seedu/logjob/storage/Storage.java)


![Class Diagram of Storage](diagrams/class-diagrams/StorageClassDiagram.png)

The `Storage` component,
* Is implemented by the StorageManager, which reads and writes from the `data.txt` file stored in disk
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)
* It depends on `HashUtil` to generate and verify hashes
* It depends on `ApplicationSerializer` to serialize and deserialize applications into a string format.


--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.
> ❗ **_NOTE:_** The lifeline for the obejcts instantiated should end at the destroy marker (X) but due to the limitation of PlantUML, the lifeline reaches the end of diagram.

### Add a new internship application
The diagram below illustrates the classes involved and the sequence of method calls involved in processing an `add` command:

![Sequence diagram of add command](diagrams/sequence-diagrams/add-sequence.png)

The `AddCommand` handles the creation of new internship applications from user input. The input string is first passed to `LogicManager`, which delegates parsing responsibility to `ApplicationParser`. The parser recognizes the command type and forwards the input to `AddCommandParser`. This parser extracts the required fields—such as company name, role, status and date—and validates them. If validation succeeds, it passes these fields to `AddCommand` which constructs an `InternshipApplication` with the values.

Next the `AddCommand` interacts with the `Model` to add a new `InternshipApplication` class. If there is an existing application already in the model, the new class is a duplicate and an error is thrown. Otherwise, it is added tp the `Model`. A `CommandResult` that encapsulates command output is returned `LogicManager`, which then uses the `UI` component to output this result to the user.   


### Edit an internship application
The diagram below illustrates the classes involved and the sequence of method calls involved in processing an `edit` command:
![Sequence diagram of edit command](diagrams/sequence-diagrams/edit-sequence.png)

The `EditCommand` is responsible for modifying an existing internship application in the application list. After parsing is completed and the command is instantiated, `EditCommand` proceeds to execute by retrieving the application at the specified index.
During execution, it checks:
* Whether the `index` is valid (within bounds of the application list)
* Whether at least one editable field (e.g., company name, job title, status, date) has been provided

If no field is provided for update, or the provided field is same as the existing, an error is thrown to indicate a no-op command.

To apply the edit, a new `InternshipApplication` object is constructed using the updated values, and this new instance replaces the original application in the model. Before replacement, the model performs a duplicate check—if the new application already exists in the list (based on key fields), the command is rejected as a duplicate.


### Delete an internship application
The diagram below illustrates the classes involved and the sequence of method calls involved in processing an `delete` command:
![Sequence diagram of delete command](diagrams/sequence-diagrams/delete-sequence.png)
### List all internship applications
![Sequence diagram of list command](diagrams/sequence-diagrams/list-sequence.png)

After the input is read and parsed by the parser, the main program calls execute which will then be identified to call the listApplication() method.
The listApplication method calls the printApplications() of the UiMain class, passing in the list of application. UI will do the job of printing the table of applications onto the CLI.

### Sort internship applications by field
![Sequence diagram of sort command](diagrams/sequence-diagrams/sort-sequence.png)

Very similar to the list command, sort command reads and parses the input from the user and execute the sorting on the application list 
and finally automatically calls a printApplications() to print the table of applications (now sorted) onto the CLI.

### Find an internship application

![Sequence diagram of find command](diagrams/sequence-diagrams/find-sequence.png)

### Help command

![Sequence diagram of help command](diagrams/sequence-diagrams/help-sequence.png)

### Exit the application
![Sequence diagram of exit command](diagrams/sequence-diagrams/exit-sequence.png)


--------------------------------------------------------------------------------------------------------------------

## **Design considerations:**
1. **Clear Separation of Concerns:** When deciding the architecture, we wanted to ensure that the different components of the application are clearly separated. This allows for easier maintenance and development of the application as the team could work on different components simultaneously. For example, the `Logic` component is responsible for parsing and executing commands, while the `Model` component is responsible for managing the data.
2. **Intuitive User Experience:** To make the application user-friendly, we tried to make the commands as intuitive as possible, and even developed a help command to assist users in understanding how to use the application. The commands are designed to be similar to common CLI commands, making it easier for users who are familiar with command line interfaces.
3. **Extensibility:** The architecture is designed to be extensible, allowing for future features to be added without significant changes to the existing codebase. For example, the `Logic` component can easily accommodate new commands by adding new command classes and parsers.

### \[Proposed\] Data archiving

Our code is set up so that data persistence is handled by our Storage component. The plan for data archiving is to integrate an extension of this component that allows older data to be serialized and moved into a separate archive file. Essentially, when the application manager updates or cleans up the current data store, the archiving feature would trigger the StorageManager to back up the relevant data (for example, in JSON format) and move it to an archive file. This modular approach ensures that data archiving is added without major changes to the existing persistence logic.


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of internship applications
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage internship applications faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​    | I want to …​                                                                                                | So that I can…​              |
| -------- |------------|-------------------------------------------------------------------------------------------------------------|------------------------------|
| `* * *`  | Job Seeker | Keep track of all the applications I made                                                                   | Easily view their statuses in one place |
| `* * *`  | Job Seeker | Add new job applications to a list with data such as company name, job title, applied date and application status | Track my application history |
| `* * *`  | Job Seeker | Delete applications if deemed redundant                                                                     | Keep my current applications list clean |
| `* * *`  | Job Seeker | Have persistence throughout different sessions                                                              | Work on my applications over time|
| `* *`    | Job Seeker | Sort a list                                                                                                 | See my application list by name or application date |
| `*`      | Job Seeker | Find applications by their name or job type                                                                 | Quickly filter a list from my application pool |



### Use cases

(For all use cases below, the **System** is `LogJob` and the **Actor** is the `User`, unless specified otherwise)

### Use Case: UC01 - Add Internship Application

**Main Success Scenario (MSS):**

1. User enters command to add a new internship application with required details.
2. LogJob parses the input and validates the provided fields.
3. LogJob adds the internship application to the list.
4. Use case ends.

**Extensions:**

- **2a.** User input is missing required fields or contains invalid formats.
    - 2a1. LogJob shows an error message and prompts for correct input.
    - 2a2. User re-enters command with the corrected input.
    - Use case resumes from Step 2.

- **3a.** Duplicate internship application detected (same name, job title, status, and date).
    - 3a1. LogJob rejects the application and notifies the user.
    - Use case ends.


### Use Case: UC02 - List Internship Applications

**MSS:**

1. User requests to list all internship applications.
2. LogJob displays the full list of stored applications.
3. Use case ends.


### Use Case: UC03 - Delete Internship Application

**MSS:**

1. User enters command to delete a specific application by index.
2. LogJob verifies that the index is valid.
3. LogJob deletes the internship application at the given index.
4. Use case ends.

**Extensions:**

- **1a.** The index provided is invalid or out of bounds.
    - 1a1. LogJob informs the user of the invalid index.
    - 1a2. User provides a valid index.
    - Use case resumes from Step 3.


### Use Case: UC04 - Edit Internship Application

**MSS:**

1. User enters command to edit an internship application with a valid index and at least one editable field.
2. LogJob validates the new input fields and index.
3. LogJob replaces the old application with the updated one.
4. Use case ends.

**Extensions:**

- **1a.** Index provided does not correspond to any stored application.
    - 1a1. LogJob displays an error message.
    - 1a2. User re-enters command with a valid index.
    - Use case resumes from Step 2.

- **1b.** No editable fields are provided.
    - 1b1. LogJob informs user that at least one field must be specified.
    - Use case ends.

- **2a.** Updated application duplicates an existing one.
    - 2a1. LogJob prevents the update and notifies the user.
    - Use case ends.

---

### Use Case: UC05 - Find Internship Applications by Company Name or Job Title

**Main Success Scenario (MSS):**

1. User enters a keyword to search for internship applications.
2. LogJob displays applications whose company name or job title contains the keyword.
3. Use case ends.

**Extensions:**

- **1a.** Keyword is missing.
    - 1a1. LogJob prompts user to enter keyword.
    - 1a2. User re-enters keyword.
    - Use case resumes from Step 2.

- **2a.** No matching applications found.
    - 2a1. LogJob notifies the user that no matches were found.
    - 2a2. User may retry with a different keyword.
    - Use case resumes from Step 2.


### Use Case: UC06 - Sort Internship Applications by Application Date

**Main Success Scenario (MSS):**

1. User requests to sort the internship applications by date.
2. LogJob sorts the stored applications in ascending order of date.
3. Sorted list is displayed to the user.
4. Use case ends.

**Extensions:**

- **1a.** Sort command is missing required date flag.
    - 1a1. LogJob shows an error message requesting a flag input.
    - 1a2. User re-enters the command correctly.
    - Use case resumes from Step 2.

- **2a.** No applications are present to be sorted.
    - 2a1. LogJob displays a message stating that no applications exist.
    - Use case ends.


### Non-Functional Requirements

1.  Should be portable to any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 applications without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system is designed for local use and therefore for 1 local user. 
5.  A complete user guide will be available for job seekers, detailing every command and covering common troubleshooting methods.  
6.  The system should handle 100% of invalid inputs (invalid company names, application indexes) without crashing and provide useful error messages for the user to correct their input.
7.  Automated unit and integration testing should be supported for continuous integration and development, with unit tests should cover at least 70% of the codebase. This ensures code quality and robustness and future updates to be tested with less manual intervention. 

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

### Launch

1. Initial launch
   1. Download the jar file and copy into an empty folder
   2. Open the terminal and navigate to the folder.
   3. Type `java -jar LogJob.jar` and press enter.
   4. Maximise the terminal size to prevent the text from wrapping.
   5. Test the commands below to ensure the application is working as expected.


### Getting Help

1. Typing help command

    1. Test case: `help`<br>
       **Expected:** A table listing all commands and their usage appears.


### Adding an Internship Application

1. Adding a new internship entry

    1. Test case: `add -n Goggle -j SWE -s APPLIED -d 2025-01-01`<br>
       **Expected:** A message `Application: Goggle SWE APPLIED Added Successfully` is shown.

    1. Test case: `add -j PM -n Amazon`<br>
       **Expected:** Defaults are applied (`APPLIED`, today's date). A message `Application: Amazon PM APPLIED Added Successfully` is shown.

    1. Test case: `add -n Google -j SWE -d 2025-01-99`<br>
       **Expected:** Invalid date. Error message shown.

    1. Test case: `add -n Google -d 2025-01-01 -j SWE -s APPLIED` (duplicate)<br>
       **Expected:** Error message indicating duplicate application.


### Editing an internship application
1. Editing fields of an existing application

    1. Prerequisite: At least one application exists.

    1. Test case: `edit 1 -n Goggle -j HWE -s INTERVIEW -d 2025-02-02`<br>
       **Expected:** The application is updated. A success message `Application: Goggle HWE INTERVIEW Edited Successfully` is shown.

    1. Test case: `edit 1`<br>
       **Expected:** Error message indicating that at least one field must be edited.

    1. Test case: `edit 100 -n Test` (index out of range)<br>
       **Expected:** Error message for invalid index.

### Listing applications
1. Input: `list`
2. **Expected:** Table with all current internship applications.<br>
   - The table should be formatted with the following columns:
     - Index
     - Company Name
     - Job Title
     - Application Status
     - Date of Application


### Sort the internship applications
1. Sorting by name or date

    1. Test case: `sort -n`<br>
       **Expected:** The list is sorted alphabetically by company name.

    1. Test case: `sort -d`<br>
       **Expected:** The list is sorted by application date in ascending order.

    1. Test case: `sort -x`<br>
       **Expected:** Error message shown for invalid sort key.

### Find an internship application
1. Searching by keyword

    1. Prerequisite: Multiple applications with varying fields. No field contains the string `"NotFound"`.

    1. Test case: `find SWE`<br>
       **Expected:** Applications with "SWE" in company name, job title, status or date are shown.

    1. Test case: `find NotFound`<br>
       **Expected:** No results. Message shown to indicate no matches.

    1. Test case: `find`<br>
       **Expected:** Error shown for missing keyword.
   

### Deleting an internship application

1. Deleting a valid application from the list.

   **Prerequisite**: At least 2 applications exist. Use `list` to verify.

    - Test case: `delete 1`  
      **Expected**: First application is removed.  
      Success message: `ID: 1 Deleted Successfully`  
      Use `list` to confirm it's removed.

2. Attempting to delete with an invalid index.

    - Test case: `delete 99` (index out of bounds)  
      **Expected**: No deletion occurs.  
      Error message: `Invalid index. Please enter a valid index in the list.`


### Exit
1. Input: `exit`<br>
2. **Expected** Goodbye message is displayed. The application closes.<br>

### Data Persistence
1. Input: Exit the program and restart the program. <br>
2. Expected: The data from the previous session is retained. Use `list` command to verify.

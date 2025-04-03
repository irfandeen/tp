# User Guide

## Introduction

LogJob (LJ) is a desktop app for managing job applications, optimized for use via a Command Line Interface (CLI). If you can type fast, LJ can get your job application management tasks done faster than traditional GUI apps.

---
## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Down the latest version of `LogJob` from [here](https://github.com/AY2425S2-CS2113-T11a-2/tp/releases).
3. On the same directory, using a terminal, run java -jar LogJob.jar
---
## Features

### Listing the application: `list`
List the job applications to the command line interface.

Format: `list`

* no other argument is expected

Example of usage:

`list`

### Sort the application: `sort`
Sort the job applications and output the sorted list onto the command line interface.

Format: `list -n/-d`

* `-d` Sort the list based on the date of application
* `-n` Sort the list based on the name of company

Example of usage:

`list -n`

`list -d`

### Delete an application
Delete applications by index.
 * No flags taken
 * Index is provided as preamble (as shown below)

Example of usage:

`delete 0`

`delete 3`

### Find an application by keyword
Finds applications with the given keyword as a substring.

* No flags given
* Non-empty preamble required with search term (shown below)

Example of usage:

`find engineer`

`find applied`

### Exit from the application
Exits from LogJob.

* No flags given
* No preamble given

Example usage:

`exit`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can simply move over data.txt to the same directory as LogJob.jar anywhere, and LogJob will be synchronised!

**Q**: How do I save my data?

**A**: No need to manually save your data! LogJob will automatically save your data to your computer whenever you exit the application.

## Command Summary

{Give a 'cheat sheet' of commands here}

* List applications `list`
* Sort applications `sort -n/-d`
* Delete applications `delete <index>`
* Find applications `find <keyword>`

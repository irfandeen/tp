# Ethan Chan - Project Portfolio Page 🚀

## Overview
LogJob (LJ) is a desktop application for managing job applications, designed for efficient use through a Command Line Interface (CLI). It simplifies the process of tracking and organizing applications with robust functionality and consistent performance.

## Summary of Contributions

### Core Components
- **Application Manager 🔧:**  
  Developed and maintained the [`ApplicationManager`](src/main/java/seedu/logjob/model/ApplicationManager.java) for handling the storage and manipulation of internship applications.
- **Command Classes ⚙️:**  
  Implemented multiple command classes (e.g., [`AddCommand`](src/main/java/seedu/logjob/logic/commands/AddCommand.java), [`DeleteCommand`](src/main/java/seedu/logjob/logic/commands/DeleteCommand.java), `Exit Command`, among others) to interpret user inputs and execute corresponding actions.
- **Internship Application & Application Status 📄:**  
  Enhanced the data model by developing the [`InternshipApplication`](src/main/java/seedu/logjob/model/InternshipApplication.java) and [`ApplicationStatus`](src/main/java/seedu/logjob/model/ApplicationStatus.java) classes, ensuring a clear and accurate representation of application data and statuses.
- **Main Application Integration 🔗:**  
  Contributed to the integration of major components by supporting the main class, [`LogJob`](src/main/java/seedu/logjob/LogJob.java), which orchestrates the application lifecycle and module interactions.
- **Basic Integration 🛠:**  
  Assisted in establishing seamless interoperability between the UI, logic, and model components.
- **Date Time Integration 📅:**  
  Incorporated Java's DateTime API into the model and parser modules to manage date functionalities effectively, improving the handling of application dates.
- **Comprehensive Testing ✅:**  
  Added JUnit tests for the command classes and model components to ensure reliable performance and maintain high code quality.

### Developer Guide Contributions
- **Model Documentation 📚:**  
  Documented the technical structure of the model component, providing detailed class diagrams and explanations to facilitate developer understanding.
- **Edit Function Sequence Diagram 🔄:**  
  Created sequence diagrams illustrating the logical flow for add, delete, and edit commands, ensuring a clear depiction of the processes. Also contributed to the manual testing instructions for thorough developer testing.

### User Guide Contributions
- **User Instructions 📖:**  
  Produced clear, step-by-step guides for adding new internship applications, ensuring that users can set up and operate the application with ease.
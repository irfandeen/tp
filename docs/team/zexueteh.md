# Teh Ze Xue - Project Portfolio Page

## 📌 Overview
LogJob (LJ) is a desktop app for managing internship applications, optimized for fast and efficient use via a Command Line Interface (CLI). It helps students conveniently track applications, edit them, and sort/filter/search through various job-related data.

---

## 🚀 Summary of Contributions

📌 **Code Contributions:**  
[Project Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=zexueteh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=zexueteh&tabRepo=AY2425S2-CS2113-T11a-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### 👨‍💻 Core Features Developed

- 🔧 **Logic Architecture**  
  Designed and implemented the core `Logic` framework used to process, validate and execute user commands. Introduced `ArgumentTokenizer`, `ArgumentMap`, and `Flag` abstractions for robust flag-based parsing. 

- ✍️ **Add and Edit Feature**  
  Implemented the `add` and `edit` command end-to-end, including parsing, validation, model updates, error handling for duplicate/no-op edits, and comprehensive test coverage.

- 🧪 **Testing**  
  Wrote extensive JUnit tests for:
    - `Parser` and parts of the `Command` package.
    - Covered edge cases for malformed input, invalid flags, and duplicate detection.

- 📋 **Manual Testing Instructions**  
  Contributed detailed test cases for manual verification of core commands (edit, help, delete) in Developer Guide.

---

## 📘 Contribution to User Guide

- Documented usage instructions and examples for:
    - `edit` command (handling indexes, optional flags, invalid input cases)
    - `help` command (behavior and expected format)

---

## 📗 Contributions to Developer Guide

- Authored and diagrammed key parts of the **Logic** and **Parser** components:
    - Class diagrams showing `LogicManager`, `ApplicationParser`, `Parser` interface, command-parsing classes
    - Sequence diagram for the `add` and `edit` command flow
    - Explained architecture and command execution pipeline

- Drafted **manual testing appendix** for commands such as:
    - `edit`, `delete`, `find`, `help`, `add`

- Contributed to parsing logic explanation and validation utility design in the DG.

---

## 🛠️ Notable Design Decisions

- Enforced **duplicate command protection** for `edit` and `add`, ensuring data integrity.
- Centralized validation logic via `Validator` interfaces to allow modular and consistent input checking across parsers.
- Supported **flexible CLI syntax** with custom error messages for invalid or missing flags.

---

## ✅ Summary

Through this project, I led the design and implementation of the parsing layer and the parsing and implementation of the  `add` and `edit` functionality, ensuring robustness in user input handling. I also contributed significantly to documentation, developer onboarding, and rigorous testing infrastructure.

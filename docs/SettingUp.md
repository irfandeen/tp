# Setting Up the Project on Your Computer

If you plan to use IntelliJ IDEA (highly recommended), follow these steps:

---

## 1. Configure the JDK

> Ensure IntelliJ IDEA is configured to use JDK 17.

Follow this guide:  
➡️ [Configuring the JDK — se-edu/guides](https://se-education.org/guides/tutorials/intellijJdk.html)

---

## 2. Import the Project as a Gradle Project

> Import the project properly to leverage Gradle's features.

Guide:  
➡️ [Importing a Gradle Project — se-edu/guides](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)

---

## 3. Verify Your Setup

Make sure everything works by:

- Running `seedu.logjob.LogJob` and trying a few commands.
- Running the test suite and ensuring all tests pass.

---

# Before Writing Code

## 4. Configure the Coding Style

> Standardize your code format to match the team's conventions.

Guide:  
➡️ [Configuring the Code Style — se-edu/guides](https://se-education.org/guides/tutorials/intellijCodeStyle.html)

---

## 5. (Optional) Use Checkstyle

> Checkstyle helps catch style violations automatically.

Guide:  
➡️ [Using Checkstyle — se-edu/guides](https://se-education.org/guides/tutorials/checkstyle.html)

:bulb: Tip: You can configure Checkstyle in IntelliJ IDEA to display style issues while you code.

---

# Continuous Integration (CI)

CI is already set up for this project using GitHub Actions.

> GitHub will automatically run the CI pipeline for:
- Every push to the `master` branch.
- Any pull request (PR).

Configuration files are located in: `.github/workflows/`

No additional setup is required.

---

# Learn the Project Design

Before diving into coding, it is highly recommended to get familiar with the overall architecture and design.

➡️ Refer to: `LogJob's Developer Guide`

---

# Summary of Recommended Resources

| Step                          | Guide Link                                                                 |
|--------------------------------|----------------------------------------------------------------------------|
| Configure JDK                 | [IntelliJ IDEA: Configure JDK](https://se-education.org/guides/tutorials/intellijJdk.html) |
| Import Gradle Project         | [IntelliJ IDEA: Import Gradle Project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html) |
| Configure Code Style          | [IntelliJ IDEA: Code Style Guide](https://se-education.org/guides/tutorials/intellijCodeStyle.html) |
| Use Checkstyle (Optional)     | [Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html) |

---

Happy Coding! 🚀

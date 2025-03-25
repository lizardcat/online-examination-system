# Online Examination System

## Project Goals

- Allow administrators to manage students, exams, and results
- Support multiple-choice exam questions only
- Restrict students from switching applications during exams
- Automatically display results upon exam completion
- Auto-submit exams when time expires

## Technologies Used

- Java 21
- JavaFX for desktop client UI
- Spring Boot for backend services
- SQLite for database storage
- Gradle for build management

## Project Structure

- `resources/xss.it` - JavaFX front-end module
- `xss.it/backend` - Spring Boot back-end module

## Build Instructions

1. Clone the project repository
2. Build using Gradle: `./gradlew build`
3. Run the backend: `./gradlew :server:bootRun`
4. Run the JavaFX client from your IDE or command line

## Notes

- Ensure the SQLite database is correctly configured before starting the server
- All student interactions are limited to the exam window to prevent cheating
- Results are auto-calculated and displayed instantly after submission or timeout


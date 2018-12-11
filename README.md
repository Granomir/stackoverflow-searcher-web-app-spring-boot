# stackoverflow-searcher-web-app-spring-boot

Used technologies - Spring Boot, Thymeleaf, HTML, CSS, Maven

Second version.
Simple Java web application with a form that allows a user to enter a search string, queries Stack Exchange API to find questions 
with titles containing that string, and displays the results in a tabular format showing the date of the question, its title and 
who posted it. Application uses a diferent colors to differentiate answered questions from unanswered.

There are 2 ways to start the application - from IDEA or from JAR.

First way:
To start the application I use Intellij IDEA Community Edition (https://www.jetbrains.com/idea/download/#section=windows). 
The following "Run" configuration must be configured in IDEA:

1. "Edit configurations..." -> Alt + Insert -> "Application".
2. Set "StackoverflowSearcherWebAppSpringBootApplication.java" as @Main class" -> OK
Start program by pushing "Run" button in IDEA. It will be running on http://localhost:8080/

Second way:
To package the application into JAR I use Intellij IDEA Community Edition (https://www.jetbrains.com/idea/download/#section=windows): 

1. Open "Maven" tab on the right panel -> "Lifecycle" -> Click "package" -> "Run Maven Build".
2. Start program by running JAR in following path - *your apth*/stackoverflow-searcher-web-app-spring-boot\target/target/stackoverflow-searcher-web-app-spring-boot-0.0.1-SNAPSHOT.jar. It will be running on http://localhost:8080/

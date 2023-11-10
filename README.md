# Automation_heroku_immense_hollows

This project contains a Selenium test suite for [Herokuapp](http://immense-hollows-74271.herokuapp.com/)

A SpringBoot application to trigger the tests through endpoints, and a docker-compose to run everything remote.

There are 3 ways to run the test suite:
- **Manually**: running the Test classes as JUnit tests.
- **Local endpoint**: Running the SpringBoot application and triggering the tests with a GET request on localhost.
- **Docker**: With "docker compose up" to create containers with Selenium Grid, a Chrome pod, and the SpringBoot application to trigger the execution.



### Requirements:
- IDE with JDK 21 installed to run/edit Java: IntelliJ.
- Maven to be able to run the SpringBoot application locally.
- Docker Desktop to run everything remote.


# How to run the tests manually:
* Clone the project from the public repository: [Github](https://github.com/TomasMontivero/Automation_heroku_immense_hollows)
* In IntelliJ go to the Test class: ```src/main/java/com/selenium/test/HerokuTest.java```
* If your OS is not Windows, then download de Chromedriver version for the correct OS and replace the one in the directory: ```src/main/resources/chromedriver/chromedriver.exe```
* Open the "Run/Debug Configurations" (Hotkey: Alt+Shift+F10, then O)
* In the "Run/Debug Configurations" window, add the "VM Option" ```-Dwebdriver_size=desktop``` or ```-Dwebdriver_size=mobile``` to set the Chromedriver window size.
* Run the HerokuTest java file (Hotkey: Shift+F10)
* This should open and close Chromedriver windows to execute every test.

# How to run the tests through the SpringBoot application:
* Clone the project from the public repository: [Github](https://github.com/TomasMontivero/Automation_heroku_immense_hollows)
* If your OS is not Windows, then download de Chromedriver version for the correct OS and replace the one in the directory: ```src/main/resources/chromedriver/chromedriver.exe```
* Go to the Main class: ```src/main/java/com/springboot/Main.java```
* Run the Main java file, or open a new terminal in the project root folder and run ```mvn spring-boot:run```
* In the terminal will appear the Spring ascii logo, and the initializing logs
* To test if the application is running correctly, in Postman (or any web browser) do a GET request to ```http://localhost:8080/automation/health``` . It should respond with ```Test endpoint: OK```
* Now you can trigger the mobile or desktop test suite with GET requests on the endpoints: 
* http://localhost:8080/automation/smoke/desktop 
* http://localhost:8080/automation/smoke/mobile
* This should open and close Chromedriver windows to execute every test.
* The results will be shown as responses in those endpoints.

# How to run the tests with Docker:
* Clone the project from the public repository: [Github](https://github.com/TomasMontivero/Automation_heroku_immense_hollows)
* Open the Docker Desktop application.
* Open a terminal in the project root folder 
* First create a docker image with the command: ```docker build -t automation_heroku_immense_hollows .```
* Then create and deploy the docker containers with the command:  ```docker compose up```
* The Docker Desktop application will show ```automation_heroku_immense_hollows``` with 3 new containers ```selenium-hub``` , ```chrome-1``` and ```junit-1```
* Now you can trigger the mobile or desktop test suite with GET requests on the endpoints:
* http://localhost:8080/automation/smoke/desktop
* http://localhost:8080/automation/smoke/mobile
* This action will not open Chromedriver windows, it all runs inside the docker containers.
* The results will be shown as responses in those endpoints.
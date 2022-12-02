# Weatherman
### This is weatherman-backend repository
## About this project
This project is the technical task and consists of two repositories:

[weatherman-frontend](https://github.com/sndvsk/weatherman-frontend) <br/>
[weatherman-backend](https://github.com/sndvsk/weatherman-backend)

## Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
  ```sh
  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
  ```

* [Maven](https://maven.apache.org/download.cgi)
  ```sh
  https://maven.apache.org/download.cgi
  ```
  
* WeatherApi Api key
  
   Go to [WeatherApi](https://www.weatherapi.com/), login/sign up and get your api key.

## Build

1. Unzip weatherman-backend-master.zip
2. Run __cmd__ or __terminal__
3. Go to weatherman-backend-master folder <br/>
   ```
   cd .../weatherman-backend-master
   ```
4. Build the project
   ```sh
   mvn clean package
   ```
5. Run the project <br/>
   
   Put your [WeatherApi](https://www.weatherapi.com/) Api key instead of your_api_key 

   ```sh
   java -Dweatherman.weatherapi.service.secret_key=your_api_key -jar ./target/weatherman-1.0-SNAPSHOT.jar
   ```
6.  Run the [weatherman-frontend](https://github.com/sndvsk/weatherman-frontend) <br/>
    
    Check how to run it: [weatherman-frontend](https://github.com/sndvsk/weatherman-frontend)

7. Now you can use the project!

## Robot
1.  To start project in schedule robot mode:

    ```sh
    java -Dweatherman.weatherapi.service.secret_key=your_api_key -Dweatherman.robot.cron_interval='0 0 * ? * *' -jar ./target/weatherman-1.0-SNAPSHOT.jar 
    ```
    
    This cron_interval means robot does the scheduled task once an hour

    ```sh
    .cron_interval='0 0 * ? * *'
    ```
    
    If you want to test whether the robot is working, use:
    ```sh
    .cron_interval='0/10 * * ? * *'
    ```
    It will make the robot to do the task every 10 seconds. <br/>
    
    For more details about cron visit: [Crontab generator](https://codebeautify.org/crontab-format)

Credentials for the database can be changed in __src/main/resources/application.properties__.
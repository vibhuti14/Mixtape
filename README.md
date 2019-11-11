# Mixtape
Need java 8 to run this.

Clone the project from github into your local machine.

Navigate to the project on terminal and run(Execute both the steps below incase anything, including the jsons are edited):

    mvn clean install
  
    mvn exec:java -Dexec.mainClass="com.Main" -Dexec.args="mixtape.json changes.json output.json"
  
The files mixtape.json and changes.json are present under the resources folder in src. They can be edited to change the output.json

Output.json is located in the main project(It is created the first time you run the project).

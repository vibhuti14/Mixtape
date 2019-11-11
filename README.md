# Mixtape
Need java 8 to run this.

Clone the project from github into your local machine.

Navigate to the project on terminal and run(Execute both the steps below incase anything, including the jsons are edited):

    mvn clean install
  
    mvn exec:java -Dexec.mainClass="com.Main" -Dexec.args="mixtape.json changes.json output.json"
  
The files mixtape.json and changes.json are present under the resources folder in src. They can be edited to change the output.json

Output.json is located in the main project(It is created the first time you run the project).

Scaling the application:
We can scale this application vertically or Horizontally, while Vertical scaling would mean adding more CPUs or memory to the
existing system, it is only useful if the number of users for the application are less. Adding more memory can lead to slower
application response time and scaling up a single server can create a bottleneck in terms of availability of the application
if the server was to go down.
Horizontal scaling would mean adding more servers/nodes to the system. This helps in getting rid of the bottleneck and distributes
the load across multiple servers. We can also implement multithreading in case of horizontal scaling.

If a playlist/song/user information is accessed frequently, it is a good idea to cache these values, this would save the number of
database hits for operations performed more frequently and save time while performing those.
In terms of code, if we have to read or write large JSON file, we can use Jackson's SequenceWriter and piped streams.
If we make the method @Async(Spring), it wouldn't block the execution.
Assuming the data will not fit in the memory, we will have to do everything in batches and incremental writes.

I haven't worked with Akka but I am little familiar with the concept and that it helps in scaling applications.

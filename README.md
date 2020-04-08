## Social and Professional Networking Web Application

This is a simple Web Application that consists of features of both Social and Professional Web Application.

#### MySQL Server 
1. Refer to the MySQL file to setup the MySQL server. You can copy and paste the queries mentioned in this file

#### MongoDB Server
1. To start MongoDB server first run `mongod.exe` file in C folder and then run `mongo.exe`. MongoDB server is now up and running.
2. Create a database with the name `Applications`
3. Create two collections `myApplications` and `myReviews`
4. Job applications can be viewed using the command `db.myApplications.find().pretty()` and reviews can be viewed using the command `db.myReviews.find().pretty()`.

#### Python files setup
1. Create a twitter account and get the `CONSUMER_KEY`, `CONSUMER_SECRET`, `ACCESS_TOKEN_KEY` and `ACCESS_TOKEN_SECRET`. 
2. Add these credential in the files `TwitterFollowingList.py` and `TrendingTweets.py`. 
2. Launch jupyter notebook and run the file `JobRecommender.py`.

#### Project Configuration Steps

1. Copy *project* folder to the tomcat webapp folder.
2. Set the class path `C:\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\webapps\project\WEB-INF\classes` .
3. Run the java classes using the command `javac *.java`
4. Start the tomcat server.
5. To start the application open the browser and type `http://localhost/project/Home`.



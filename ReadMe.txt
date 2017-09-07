To test the API follow the below steps.

1.Use jdk8 version.
2.Download the war file and deploy it in corresponding server.

For my development i used ApacheTomcat8.0.45.


To run the api

http://<host>:<port>/GitIdFollowers/rest/getFollowers/:gituserID

Example : http://localhost:8080/GitIdFollowers/rest/getFollowers/prudhvi1990


JSON  Output explaination:

Please find the example

	{  
      "userId":"prudhvi1990",  //userid of the follower
      "follow":"chinababuGIT", //userid of the follwed  person
      "level":3                   //Follower level of the given userid
   }
   
   
   
   


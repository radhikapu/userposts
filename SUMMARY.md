As part of this exercise I was able to 
1. Create postuser, fetch users, update user
2. Create posts and associate them to the user, update post user, fetch all posts, 
fetch post by postid, fetch all posts by user id
3. Used in-mem h2 database for this exercise
4. Added junits around post functionality at controller level

Taking more time, I would have
1. Added more logging and exceptional handling
2. Covered the junits with better code coverage
3. Load sample data on application startup
4. explored authentication using OAuth2
5. Work on deploy script to deploy to aws

I used intelliJ as the editor

To run the application, simply run mvn spring-boot:run (if mvn is setup) or run in 
intelliJ
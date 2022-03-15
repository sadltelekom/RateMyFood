# RateMyFood

Team

Sebastian - Head of destruction
Henry - good looking Guy
Mathias - the bad and the ugly


1. Repository
   https://github.com/sadltelekom/RateMyFood

2. Springboot add  (Sebastian)

3. Database Plan

4. Frontend - new Repository
5. Models in Java
6. DB in Java
7. Controller in Java
8. New SQL File Database with first Dataset
9. Website raw design 
10. Added Base Structure for Java Backend Classes
11. Implemented all model Classes and started First DB Class as template.




COMMENTS Controller

get/comments/ 		-- get all comments
get/comments/{id}	-- get comment by id
get/comments/recipe/{id} -- getcomments by recipeId
get/comments/user/{id}	-- get comments by userId

post/comment		-- create a new comment
	{
    "recipeId": [recipeID],
    "userId": [userID],
    "comment": "[user Input]"
	}

update/comment		-- update a comment
	{
    "commentId": [commentID],
    "comment": "[new user Input]"
	}

delete/comment/{id}	-- delete a comment

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


</br>
</br>

**COMMENTS Controller**

API | Data
--- | ---
get/comments/|get all comments
get/comments/{id}|get comment by id
get/comments/recipe/{id}|get comments by recipeId
get/comments/user/{id}|get comments by userId

returns: `List<Comments>`

</br>

API | Data
--- | ---  
post/comment/|create a new comment  
return: `List<Comments>` with new comment
```
{
"recipeId": [recipeID],
"userId": [userID],
"comment": "[user Input]"
}
```
</br>

API | Data
--- | ---  
update/comment/|update a comment
return: `List<Comments>` with updated comment
```
{
"commentId": [commentID],
"comment": "[new user Input]"
}
```
</br>

API | Data
--- | ---  
delete/comment/{id}|delete a comment
return: `boolean`


</br>

**RATINGS Controller**

API | Data
--- | ---  
get/ratings/|get all ratings
get/ratings/{id}|get rating by id
get/ratings/recipe/{id}|get ratings by recipe id
get/ratings/user/{id}|get ratings by user id
return: `List<Rating>`

</br>

API | Data
--- | ---  
get/ratings/recipeaverage/{id}|get average of ratings for recipe id
get/ratings/useraverage/{id}|get average of ratings given by user id
return: `Long`

</br>

API | Data
--- | ---  
post/rating/|create a new rating
return: `List<Rating>` with new rating
```
{
"recipeId": [recipeID],
"userId": [userID],
"rating": [user rating]
}
```

</br>

API | Data
--- | --- 
update/rating/|update a rating
return: `List<Rating>` with updated rating
```
{
"ratingId": [recipeID],
"rating": [new user rating]
}
```

</br>

API | Data
--- | --- 
delete/ratings/{id}|delete a rating
return: `boolean`


</br>

**PICTURE Controller**

API | Data
--- | ---  
get/pictures/|get all picture entries
get/pictures/{id}|get picture entry by ID
get/pictures/recipe/{id}|get picture entries by recipe ID
get/pictures/user/{id}|get picture entries by user ID
return: `List<Picture>`

</br>

API | Data
--- | --- 
get/pictures/uploadname/|get name for picture upload
return: `String`

</br>

API | Data
--- | --- 
post/pictures/|create a new picture entry
return: `List<Picture>` with new picture entry
```
{
    "recipeId": [recipeID],
    "userId": [userID],
    "link": "[link to picture file storage]"
}
```

</br>

API | Data
--- | --- 
delete/pictures/{id}|delete picture entry
return: `boolean`


</br>

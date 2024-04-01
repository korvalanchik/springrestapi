# Note
## For authenticated users

### GET 
all user's notes
#### /api/notes
Token in header



### POST
Add a new note
### /api/notes  
Token in header  
Request Body  
>{  
"title": "...",  
"content": "..."  
}


### PUT
Update an existing note
### /api/notes
Token in header  
Request Body
>{  
"title": "...",  
"content": "..."  
}


### GET
Finds note by Id
### /api/notes/{noteId}
Token in header  



### DELETE
Deletes a note
### /api/notes/{noteId}
Token in header  


# Registration
## For all users

### POST
Add a new user
### /register
Request Body
>{  
"username": "...",  
"password": "..."  
}


# User
## For all users
### POST
User's authentication
### /api/users
Request Body
>{  
"username": "...",  
"password": "..."  
}

Default  
>{  
"username": "User",  
"password": "User"  
}


### GET
#### For authenticated users
View all users
#### /api/users


### GET
#### For authenticated users
Finds user by Id
### /api/users/{Id}


### PUT
#### For authenticated users
Update an existing user
### /api/users/{Id}
Request Body
>{  
"username": "...",  
"password": "..."  
}


### DELETE
#### For authenticated users
Deletes a user
### /api/users/{Id}


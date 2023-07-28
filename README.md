# Blogging-Platform-API


## Language and Framwork 

```bash
 Java , SpringBoot , JPA , MYSQL database , Swagger UI
```

## Data Flow

 1. Controller 

```bash


#Apis for User (UserController)
  signUpUser()
  deleteUser()

#APIs for Post
  createPost()
  getPost()
  deletePost()
  updatePost()

#Apis for Follow
  knowFollowers()
  knowFollowing()
  follow()
  unfollow()

#Apis for comment
  getComment()
  postComment()
  deleteComment()


```
2. Service 

```bash
 

#Services
  signUpUser()
  createPost()
  deletePost()
  deleteUser()
  updatePost()
  follow()
  unfollow()
  comment()
  deleteComment()
  

```

3. Model

```bash
 
 User()
 SignUpOutput()
 SignInInput()
 Comment()
 Follow()
 post()
 Gender()
```
4. Repository

```bash

 IPostRepo()
 ICommentRepo()
 IFollowRepo()
 IUserRepo()
```

## Data Structure Used

```bash
 AWS MYSQL server as database 
```

## Project Summary

Blogging Platform Api Backend is a Project in which  User can create account , APis , SignIn and SignUP and can  createPost and get post by id in springboot.
 
  #### Features for User

- Create /SignUp user
- Delete user (Delete user)
- Create Post (Create the Post using email and pass and post link)
- Comment Post (comment on the Post using email and token and post id)
- Follow a user (by user id and email)





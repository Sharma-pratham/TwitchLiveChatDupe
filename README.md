# TwitchLiveChatDupe
 
## Hey all

## This is the project repo that is based of the backend assignment. 

### I have chosen to implement this project using Spring boot 3.3.0 on Java 17 and maven. I used spring intializr website to create the inital project on the said settings.
### I created the postgres db on postgres sql 16 on using pgadmin 4 v8.

## Project specifics

### I was unable to put in the oauth2 part but apart from that all the services and fonctionnalities have been implmented.

### Push notification and email send outs are just written as sysouts as building a whole email service required me to setup firebase free cloud service that ended up giving me issues hence I did not go through with it.

### I have written javadocs in all the files that require except for Model files that describes all the fonctionnalities. Addtionally I have setup swagger for the restcontrollers.

## Please find below the additonal DB tables I have created for the project apart from the profiles table metioned in the backend task repo

CREATE TABLE followers (
    profile_id UUID REFERENCES profiles(id) ON DELETE CASCADE,
    follower_id UUID REFERENCES profiles(id) ON DELETE CASCADE,
    PRIMARY KEY (profile_id, follower_id)
);

CREATE TABLE channels (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) UNIQUE NOT NULL,
    owner_id UUID REFERENCES profiles(id) ON DELETE CASCADE
);

CREATE TABLE chat_messages (
    id UUID PRIMARY KEY,
    from_user VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    channel_id VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    mute BOOLEAN DEFAULT FALSE,
    ban BOOLEAN DEFAULT FALSE
);

## To test the api's I have used postman json, the service runs by default on port 8080 via tomcat.
## Below are the sample requests for all th endpoints that I have created.

### ProfileController

POST /api/profiles/signup
GET /api/profiles/{id}
PUT /api/profiles/{id}
POST /api/profiles/{id}/follow
POST /api/profiles/{id}/unfollow
NotificationController

POST /api/notifications/sendNotification
POST /api/notifications/sendEmail
StreamController

POST /api/streams/start
CommandController

POST /api/commands/setAdmin
POST /api/commands/unsetAdmin
POST /api/commands/mute
POST /api/commands/unmute
POST /api/commands/ban
POST /api/commands/unban
POST /api/commands/suspend
POST /api/commands/setTitle
POST /api/commands/setDescription

### POSTMAN json bodies and urls


### ProfileController

### Signup

URL: http://localhost:8080/api/profiles/signup
Method: POST
JSON Body:

{
  "fullName": "John Doe",
  "username": "johndoe",
  "email": "johndoe@example.com",
  "password": "password123",
  "avatar": "avatarUrl"
}

### Get Profile

URL: http://localhost:8080/api/profiles/{id}
Method: GET
Update Profile

URL: http://localhost:8080/api/profiles/{id}
Method: PUT
JSON Body:

{
  "fullName": "John Doe Updated",
  "username": "johnupdated",
  "email": "johnupdated@example.com",
  "password": "newpassword123",
  "avatar": "newAvatarUrl"
}

### Follow Profile

URL: http://localhost:8080/api/profiles/{id}/follow
Method: POST
JSON Body:

{
  "followerId": "11111111-1111-1111-1111-111111111111"
}

### Unfollow Profile

URL: http://localhost:8080/api/profiles/{id}/unfollow
Method: POST
JSON Body:
{
  "followerId": "11111111-1111-1111-1111-111111111111"
}

### NotificationController
### Send Notification

URL: http://localhost:8080/api/notifications/sendNotification
Method: POST
JSON Body:

{
  "userId": "11111111-1111-1111-1111-111111111111",
  "message": "This is a notification message"
}

### Send Email

URL: http://localhost:8080/api/notifications/sendEmail
Method: POST
JSON Body:

{
  "userId": "11111111-1111-1111-1111-111111111111",
  "subject": "Email Subject",
  "body": "Email Body"
}

### StreamController
### Start Stream

URL: http://localhost:8080/api/streams/start
Method: POST
JSON Body:
{
  "userId": "11111111-1111-1111-1111-111111111111"
}

### CommandController
### Set Admin

URL: http://localhost:8080/api/commands/setAdmin
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/set admin",
  "targetUser": "targetUser"
}

### Unset Admin

URL: http://localhost:8080/api/commands/unsetAdmin
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/unset admin",
  "targetUser": "targetUser"
}

### Mute

URL: http://localhost:8080/api/commands/mute
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/mute",
  "targetUser": "targetUser"
}

### Unmute

URL: http://localhost:8080/api/commands/unmute
Method: POST
JSON Body:
{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/unmute",
  "targetUser": "targetUser"
}

### Ban

URL: http://localhost:8080/api/commands/ban
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/ban",
  "targetUser": "targetUser"
}

### Unban

URL: http://localhost:8080/api/commands/unban
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/unban",
  "targetUser": "targetUser"
}

### Suspend

URL: http://localhost:8080/api/commands/suspend
Method: POST
JSON Body:

{
  "from": "superAdminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/suspend"
}

### Set Title

URL: http://localhost:8080/api/commands/setTitle
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/set title",
  "additionalData": "New Channel Title"
}

### Set Description

URL: http://localhost:8080/api/commands/setDescription
Method: POST
JSON Body:

{
  "from": "adminUser",
  "channelId": "22222222-2222-2222-2222-222222222222",
  "command": "/set description",
  "additionalData": "New Channel Description"
}



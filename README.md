# userlogin
#docker-build
docker build --tag=userlogin:latest .
#docker-run
docker run -p8080:8080 userlogin:latest

RestAPI-Test
POST - localhost:8080/user/register
{
    "userName":"testUser",
    "email":"testUser@gg.com",
    "password":"12345"
}

POST - localhost:8080/user/login
{
    "userName":"testUser",
    "password":"12345"
}

# service-prototype
a microservices prototype for the initial purpose of having models of skeleton services composed to model reawl world solutions in different topologies. many technologies can be 
employed and evaluated for example status and health monitoring services, service discovery services,  containers and immutable server settings using such a model.

there will be two services, or lets say many clones of two kinds of services
sync and async. port number and number of thread in the thread pool will be parsed through command line when start the servers

api:
GET localhost:80XX/getData, params: action(type string), address(type string)

docker build:
docker build -t async-py .

docker run:
docker run -p 8080:8080 -t async-py python3 service-py/server_asyncio.py

call the api by:
http://192.168.59.103:8080/getData

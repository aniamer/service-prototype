# service-prototype

there will be two services, or lets say many clones of two kinds of services
sync and async. port number and number of thread in the thread pool will be parsed through command line when start the servers

api:
GET localhost:80XX/getData, params: action(type string), address(type string)

docker build:
docker build -t async-py .

docker run:
docker run -p 8080:8080 -t async-py python3 service-py/server_asyncio.py
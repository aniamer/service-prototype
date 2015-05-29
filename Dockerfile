FROM debian:jessie

# Install required packages
RUN apt-get update
RUN apt-get -y install python3 python3-pip
RUN pip3 install tornado

# Add python code to the image
RUN mkdir -p /service-py
ADD . /service-py
WORKDIR /service-py

# Set the default command to execute
#  CMD ["python3", "server_asyncio.py"]

CMD pwd

EXPOSE 8080
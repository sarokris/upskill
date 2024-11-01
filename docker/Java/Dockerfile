# using lightweight base image
FROM amazoncorretto:11-alpine

#setting working directory in the container
WORKDIR /app

#Copying the Greeter.java to working directory
COPY Greeter.java .

#Compiling Java file to create executable
RUN javac Greeter.java

#Starting point where the container execution
ENTRYPOINT ["java","Greeter"]

#default arguments passed to the container if not provided
CMD ["Good morning","Java lovers"]









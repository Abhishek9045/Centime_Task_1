# Centime_Task_1
Goal: Create a three-service microservice project to demonstrate orchestration, rest api invocation, error handling, tracing of logs in javaEE or Node.js Services should communicate with aws hosted URLs


1st Service) Expose two HTTP methods, one get and one post.
From the get method return “Up” if service is up. The post method should return the concatenated responses of the Get call of Service 2 and the Post call of Service 3 using the same payload({The json})

2nd Service) It contains one get method which is called by the first service to fetch a string” Hello” wrapped with a spring response entity.

3rd Service) This exposes one post method which is called by first service to print/log the passed json and return the concatenated name elements as a string (example - “John Doe”) 

Print logs before each method call with a traceID to trace the call flow. 

The json.
{
“Name”: “John”,
“Surname”:”Doe”
} 

Concatenated Response :-
{
“Hello John Doe”
}

Extra: Handle exception when passed Json in post calls is not valid


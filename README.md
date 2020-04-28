# step-restassured-sample-project

## Description
This repository contains a sample project, showcasing integration between STEP (Java) and REST Assured.
Test-Scenario is based on the [httpbin.org](http://httpbin.org) endpoints and can be used as an initialization project to get started.

## Test-Scenario

Step | Method | URI Path             | Validate                      | Return 
---- | ------ | -------------------- | ----------------------------- | ----------------------
1    | GET    | /json                | title is "Sample Slide Show"  | all slide titles
2    | GET    | /get?param=x&param=y | params size is 2              | origin ip address
3    | GET    | /uuid                | UUID length is 36             | UUID
4    | POST   | /anything            | quote of the day is correct   | slide title/uuid pairs


Step 4 sample request:
 
```json
{
    "title": "Sample Slide Show",
    "quote of the day": "Apples fall. Birds fly. We live. We die.",
    "slides": [
        {
            "title": "Wake up to WonderWidgets!",
            "uuid": "0a7caee7-77e0-4461-9747-1b44e8f34f30"
        },
        {
            "title": "Overview",
            "uuid": "0a7caee7-77e0-4461-9747-1b44e8f34f31"
        }
    ]
}
```

## References:
* [STEP - Test Execution Platform](https://step.exense.ch/)
* [REST Assured - Testing and validating REST services](http://rest-assured.io/)
* [httpbin.org - A simple HTTP Request & Response Service](http://httpbin.org)

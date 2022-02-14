# Lotto winnings 
A web application to calculate the historical winning amount for same numbers.

## Technologies used
- Spring boot
- JUnit Test
- Vue3
- Github Actions
- AWS

## Components
- [x] REST Api Developed using Spring Boot, Java. Data set is bundled within code base.
- [x] Web application is built using Vue 3 framework. Web is integrated with the REST APi using Axios.
- [x] CI/CD : Github Actions used to implement CI/CD pipeline for both REST API and Web application. For every push to main branch, Github Actions gets triggered and deploys the component to AWS based on the changes. It involves 3 steps;
- Unit test execution using JUnit
- Build
- Deploy to AWS : Elastic beanstalk is used to deploy REST APi and AWS S3 is used to deploy Web.
- [x] Test cases : JUnit test cases has been added for unit testing.

## Application URL
Url to access the application : http://lottowinnings-ui-client.s3-website-us-east-1.amazonaws.com/

### Endpoints
REST API endpoint to calculate the winnings;<br/>
GET http://lottowinningsapideploy-env.eba-zby67cpm.us-east-1.elasticbeanstalk.com/api/v1/lottoResults?n1={first number}&n2={second number}&n3={third number}&n4={fourth number}&n5={fifth number}&n6={sixth number}





# wso2-app
SchoolFinder-Service is MicroService developed using MSFJ

which fetch the school from xml (school.xml)

If you want to deploy this application compile and copy the jar into wso2\msf4j\deployment\microservices folder

After starting the wso2 MSFJ service.

you call this service metioned below

To list all the schools (GET)
curl -v http://localhost:9090/schoolfinder/all

To get school by city (GET) -- Coventry is path param
curl -v http://localhost:9090/schoolfinder/ByCity/{Coventry}

To get school by postcode (GET)
curl -v http://localhost:9090/schoolfinder/ByPostCode/{CV4 7PS}

To add achool (POST)
Create a JSON file names request.json with the following request payload.
{
"name": "Acorns1",
"address": "Long Compton Shipston On Stour",
"postCode": "CV36 5LA",
"city": "Warwickshire",
"email": "admin2639@welearn365.com",
"telePhone": "016 0868 4654"
}
		
curl -v -X POST --data @request.json http://localhost:9090/schoolfinder/ --header "Content-Type:application/json"

If you want to Test this application wso2 EI
Deploy SchoolFinderAppCompositeApplication_1.0.0.car or you create car by downloading all the file from the repository. 

To fetch all the school 
curl -v  http://localhost:8280/schoolfinder/allschools

To fetch school by city -- -- Warwickshire is path param
curl -v  http://localhost:8280/schoolfinderbycity/bycity/{Warwickshire}

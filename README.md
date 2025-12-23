# AWS Serverless User API — Test Automation

Short Description

This directory contains API tests, local mock data (db.json), and a Postman collection.
Tests are written with RestAssured + JUnit5 and run using Maven.

Prerequisites
	•	Java 17 (project source/target 17). Recommended via Homebrew/Temurin:

  export JAVA_HOME=$(/usr/libexec/java_home -v17)

  	•	Maven (3.9+)
	•	Node.js + npm (for json-server)
	•	(Optional) Newman (to run the Postman collection from CLI)

Files / Locations
	•	src/test/java/api — JUnit tests (GetUserTest, AddUserTest, UpdateUserTest, DeleteUserTest)
	•	db.json — sample data for json-server
	•	postman_collection.json — Postman import file
	•	.gitignore — recommended ignore list

Local Execution (Recommended Flow)
	1.Go to the automation directory:

  cd /Users/gulcancelik/Desktop/awsProject/aws-serverless-book-api-qa/automation 

  	2.	Start the mock API with json-server (in a separate terminal):

    npx json-server --watch db.json --port 3000

    Or if installed globally:

    npm install -g json-server
json-server --watch db.json --port 3000

	3.	Run tests (in another terminal):

  mvn clean test -Dapi.baseUrl=http://localhost:3000

  
Environment Variables / Parameters

The base URL is read in the following order:
	1.	Maven system property: -Dapi.baseUrl=...
	2.	Environment variable: API_BASE_URL
	3.	Default: http://localhost:3000

Troubleshooting
	•	UnknownHost: Provide a real endpoint or start the local mock.
	•	If port 3000 is in use, find and stop the process:

  lsof -iTCP:3000 -sTCP:LISTEN -n -P
pkill -f json-server || true

	•	If IDs in json-server /users responses are strings, make them numeric in db.json (e.g., 1).

Postman
	•	Import the collection: postman_collection.json
	•	Set the collection variable baseUrl to http://localhost:3000.

Git / GitHub
	•	Repo: https://github.com/gulcannce/my-aws-user-api-qa￼
	•	Commit & push:

  git add .
git commit -m "Add tests, db.json and Postman collection"
git push -u origin main

Notes
	•	Remove log() statements added for debugging (removed in GetUserTest).
	•	In CI, if using a real endpoint, provide the correct URL with -Dapi.baseUrl or environment variable.


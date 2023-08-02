# Your CLI Tool

Your CLI Tool is a command-line utility that allows you to perform various tasks, including fetching data from websites, performing live queries, and showing the history of fetched data.

## Getting Started

### Prerequisites

To build and run the CLI tool, make sure you have the following installed on your system:

- Java 17
- Maven
- Docker (if you want to run the tool using Docker)

### Building the Tool

To build the tool, open a terminal or command prompt, navigate to the root directory of the project (where the `pom.xml` file is located), and run the following command:

```sh
mvn clean package
```

The above command will compile the source code, run tests, and package the application into an executable JAR file named your-cli-tool-1.0.0.jar in the target directory.


### Running the Tool
To run the CLI tool, use the following command:

```sh
java -jar target/your-cli-tool-1.0.0.jar [command] [options] [URLs]
```

### Available Commands

Show commands helper
```sh
java -jar target/your-cli-tool-1.0.0.jar
```

--fetch: Fetch data from the specified URLs.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --fetch https://www.example.com
```
```sh
java -jar target/your-cli-tool-1.0.0.jar --fetch https://www.example.com https://www.google.com/test 
```

--live: Perform a live query with a default interval of 5 seconds.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --live https://www.example.com
```

--live -#seconds: Perform a live query with a custom interval in seconds.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --live -10 https://www.example.com
```

--live --output: Perform a live query with output and a default interval of 10 seconds.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --live --output https://www.example.com
```

--history: Show the history of fetched data.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --history
```

--backup: Create a backup of the current datastore and save it to a CSV file.

Example:

```sh
java -jar target/your-cli-tool-1.0.0.jar --backup
```

## Running the Tool with Docker
To run the CLI tool using Docker, follow these steps:

### Build the Docker Image:

Open a terminal or command prompt, navigate to the root directory of the project (where the Dockerfile is located), and run the following command:
```sh
docker build -t your-cli-tool:1.0.0 .
```

### Run the Docker Container:

After the Docker image is built, you can run the CLI tool inside a Docker container using the following command:
```sh
docker run your-cli-tool:1.0.0 [command] [options] [URLs]
```
PS: If you use this command without arguments the tool will show usage helper.

Example:
```sh
docker run your-cli-tool:1.0.0 --fetch https://www.example.com
```
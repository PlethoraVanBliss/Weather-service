## Weather forecast app.

##### Installation
To execute you must first have docker installed.

    docker run r --name weather-forecast-service

If you don't have docker installed, please referer to the link: https://docs.docker.com/install/

##### New Features!

  - Get the daily, nightly weather forecast average for the following 3 days. As an example, get Berlin temperature averages,

        curl -i http://localhost:9000/data/v1/forecast/Berlin

  - Find api documentation at link http://localhost:9000/swagger-ui.html.




### Installation

The Weather forecast app requires [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) to run.

### Development
#### Building for source
For local build:
```sh
$ mvn clean install
```


### Docker
The weather forecast app is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 9000, so change this within the Dockerfile if necessary. When ready, simply use the Dockerfile to build the image.



```sh
$ cd weather-forecast
$ docker build -t development/weatherforecast:${package.json.version} .
```
This will create the weatherforecast image and pull in the necessary dependencies. Be sure to swap out `${package.json.version}` with the actual version of weatherforecast.
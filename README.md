#spring-boot + docker demo

## Getting Started

##### 1. Clone the repository:
    git clone https://github.com/438926995/spring-boot-demo.git
##### 2. package and build docker
    mvn package docker:build
##### 3. docker run    
    docker run -p 8080:8080 huwenwen/spring-boot-demo

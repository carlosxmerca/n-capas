# HelloWorld

## Controller

```java
package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/greeting")
	public String getGreet() {
		return "greeting";
	}
	
	@GetMapping("/say-hello")
	public String getHello(@RequestParam(name = "name", defaultValue = "no one") String name, Model model) {
		model.addAttribute("someone", name);
		
		return "say-hello";
	}

	
}
```

## Template

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello page</title>
    <style>
        h1 {
            color: darkslateblue;
        }
    </style>
</head>
<body>
    <h1 th:text="'Hello  ' + ${ someone } + '!'" />
</body>
</html>
```

### build.gradle
```
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.0.4'
	id 'io.spring.dependency-management' version '1.1.0'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```
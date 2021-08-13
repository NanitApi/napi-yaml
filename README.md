# Yaml configuration API

This API based on [this](https://mvnrepository.com/artifact/org.spongepowered/configurate-yaml/3.7.2) library. 
There is only convenient configuration builder and wrapper with some utils.

## Usage

Add library to project via JitPack

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.NanitApi:napi-yaml:main-SNAPSHOT'
}
```

```java
Configuration conf = Configuration.builder()
        .source(ConfigSources.resource("/test.yml", this)
            .copyTo("E:\\"))
        .build();

conf.reload();

String name = conf.getNode("name").getString();
System.out.println("Name " + name);
```

```java
Language lang = Language.builder()
        .source(ConfigSources.resource("/test.yml", this))
        .build();

lang.reload();
        
System.out.println(lang.of("my.lang.key"));
```
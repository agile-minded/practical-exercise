Implementation
==============

The API server is available at http://localhost:8080.

I used the API-first approach and OpenApi maven plugin to generate the code, and H2 in-memory database to store the user data.
I used the delegation pattern to decouple the API implementation from the generated code so that API code can be regenerated independently.

The example string in openapi-spec.yml was changed from JSON to YAML to fix the code generation by the plugin.


Practical Exercise
==================

Please build a RESTful API server that fulfils the OpenApi Spec ([`openapi-spec.yml`](openapi-spec.yml)) using the
following technologies.

1. [Maven](https://maven.apache.org/)
1. [Spring Boot](https://spring.io/projects/spring-boot)

Feel free to use any Maven plugins or code generation you wish. It is also perfectly fine to use an embedded DB for
persistence like [H2](https://www.h2database.com/html/main.html).

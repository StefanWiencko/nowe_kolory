## Run the app

    ./mvnw spring-boot:run

#### App runs on port 8080
#### Make sure to add your api key to application.properties in project root

## Run the tests

    ./mvnw test

# REST API

## Add new book to favourites

### Request

`POST /movies`

    curl -i -X POST -H 'Accept: application/json' http://localhost:8080/movies?imdbID=VALID_IMDBID&&name=USERNAME

### Response

    HTTP/1.1 201 
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Mon, 08 Jan 2024 17:53:32 GMT


    {
    "imdbID":"tt1285016",
    "title":"The Social Network",
    "plot":"As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea and by the co-founder who was later squeezed out of the business.",
    "genre":"Biography, Drama",
    "director":"David Fincher",
    "poster":"https://m.media-amazon.com/images/M/MV5BOGUyZDUxZjEtMmIzMC00MzlmLTg4MGItZWJmMzBhZjE0Mjc1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
    } 

### If the movie was already in database

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Mon, 08 Jan 2024 17:53:32 GMT


    {
    "imdbID":"tt1285016",
    "title":"The Social Network",
    "plot":"As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea and by the co-founder who was later squeezed out of the business.",
    "genre":"Biography, Drama",
    "director":"David Fincher",
    "poster":"https://m.media-amazon.com/images/M/MV5BOGUyZDUxZjEtMmIzMC00MzlmLTg4MGItZWJmMzBhZjE0Mjc1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
    }

## Get a specific movie

### Request

`GET /movies?imdbID=VALID_IMDB`

    curl -i -H 'Accept: application/json' http://localhost:8080/movies?imdbID=VALID_IMDBID

### Response

    HTTP/1.1 200 
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Mon, 08 Jan 2024 18:08:01 GMT


    {
    "imdbID":"tt1285016",
    "title":"The Social Network",
    "plot":"As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea and by the co-founder who was later squeezed out of the business.",
    "genre":"Biography, Drama",
    "director":"David Fincher",
    "poster":"https://m.media-amazon.com/images/M/MV5BOGUyZDUxZjEtMmIzMC00MzlmLTg4MGItZWJmMzBhZjE0Mjc1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
    }

## Get list of all favourite books

### Request

`GET /movies?name=USERNAME`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/

### Response

    HTTP/1.1 200 
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Mon, 08 Jan 2024 18:33:13 GMT


    [{
    "imdbID":"tt1285016",
    "title":"The Social Network","plot":"As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea and by the co-founder who was later squeezed out of the business.","genre":"Biography, Drama",
    "director":"David Fincher",
    "poster":"https://m.media-amazon.com/images/M/MV5BOGUyZDUxZjEtMmIzMC00MzlmLTg4MGItZWJmMzBhZjE0Mjc1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg"
    }]    

### When no user in database

    HTTP/1.1 204
    Content-Type: application/json
    Date: Mon, 08 Jan 2024 18:15:00 GMT

    []

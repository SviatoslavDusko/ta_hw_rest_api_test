package services;

import Const.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Author;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import serializer.Serializer;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AuthorService {
    public Response getAllAuthors() {
        return get(Constants.API_URL + Constants.ALL_AUTHORS_URL);
    }

    public Response getAuthorById(Long id) {
        return get(Constants.API_URL + Constants.GET_AUTHOR_URL + id);
    }

    public Response postAuthor(Author author) throws JsonProcessingException {
        String string = new Serializer().fromObjectTotString(author);
        return
                given().
                        baseUri(Constants.BASE_URL).
                        basePath(Constants.API_URL + Constants.POST_AUTHOR_URL).
                        contentType(ContentType.JSON).
                        body(string).
                        post();
    }

    public Response deleteAuthorById(Long id) {
        return
                given().
                        baseUri(Constants.BASE_URL).
                        basePath(Constants.API_URL + Constants.GET_AUTHOR_URL + id + Constants.FORCIBLY_FALSE).
                        delete();
    }

    public Response putAuthor(Author author) throws JsonProcessingException {
        return
                given().
                        baseUri(Constants.BASE_URL).
                        basePath(Constants.API_URL + Constants.POST_AUTHOR_URL).
                        contentType(ContentType.JSON).
                        body(new Serializer().fromObjectTotString(author)).
                        put();
    }
}

package services;

import constants.Consts;
import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Author;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import serializer.Serializer;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AuthorService {
    public Response getAllAuthors() {
        return get(Consts.API_URL + Consts.ALL_AUTHORS_URL);
    }

    public Response getAuthorById(Long id) {
        return get(Consts.API_URL + Consts.GET_AUTHOR_URL + id);
    }

    public Response postAuthor(Author author) throws JsonProcessingException {
        String string = new Serializer().fromObjectTotString(author);
        return
                given().
                        baseUri(Consts.BASE_URL).
                        basePath(Consts.API_URL + Consts.POST_AUTHOR_URL).
                        contentType(ContentType.JSON).
                        body(string).
                        post();
    }

    public Response deleteAuthorById(Long id) {
        return
                given().
                        baseUri(Consts.BASE_URL).
                        basePath(Consts.API_URL + Consts.GET_AUTHOR_URL + id + Consts.FORCIBLY_FALSE).
                        delete();
    }

    public Response putAuthor(Author author) throws JsonProcessingException {
        return
                given().
                        baseUri(Consts.BASE_URL).
                        basePath(Consts.API_URL + Consts.POST_AUTHOR_URL).
                        contentType(ContentType.JSON).
                        body(new Serializer().fromObjectTotString(author)).
                        put();
    }
}

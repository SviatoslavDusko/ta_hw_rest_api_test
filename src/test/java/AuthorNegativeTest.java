import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Author;
import entity.nested.Birth;
import entity.nested.Name;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.AuthorService;

import java.util.Random;

public class AuthorNegativeTest {
    private final AuthorService authorService = new AuthorService();

    @Test
    public void deleteDefunctAuthor() {
        Response response;
        Long authorId;
        do {
            authorId = new Random().nextLong();
            System.out.println(authorId);
            response = authorService.getAuthorById(authorId);
        } while (response.getStatusCode() != 404);

        Assert.assertEquals(authorService.deleteAuthorById(authorId).getStatusCode(), 404);
    }

    @Test
    public void postAuthorWithOccupiedData() throws JsonProcessingException {
        Response response;
        Long freeAuthorId;
        do {
            freeAuthorId = (long) (Math.random() * 10000);
            response = authorService.getAuthorById(freeAuthorId);
        } while (response.getStatusCode() != 404);

        Author author = new Author(
                freeAuthorId,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );
        authorService.postAuthor(author);
        Assert.assertEquals(authorService.postAuthor(author).getStatusCode(), 409);
    }

    @Test
    public void postWithWrongData() throws JsonProcessingException {
        Author author = new Author(
                1L,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997", "Ukraine", "Lviv"),
                "description"
        );
        Assert.assertEquals(authorService.postAuthor(author).getStatusCode(), 400);
    }
}

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Author;
import entity.nested.Birth;
import entity.nested.Name;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.AuthorService;


public class AuthorPositiveTest {
    private final AuthorService authorService = new AuthorService();

    @Test
    public void getAllAuthorTest() {
        Assert.assertEquals(authorService.getAllAuthors().getStatusCode(), 200);
    }

    @Test
    public void getAuthor() {
        Assert.assertEquals(authorService.getAuthorById(1L).getStatusCode(), 200);
    }

    @Test
    public void postAuthor() throws JsonProcessingException {
        Author author = new Author(
                4L,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );
        Assert.assertEquals(authorService.postAuthor(author).getStatusCode(), 201);
    }

    @Test
    public void deleteAuthor() {
        Assert.assertEquals(authorService.deleteAuthorById(3L).getStatusCode(), 204);
    }

    @Test
    public void putAuthor() throws JsonProcessingException {
        Author author = new Author(
                1L,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );
        Assert.assertEquals(authorService.putAuthor(author).getStatusCode(), 200);
    }

    @Test
    public void postAndDeleteAuthor() throws JsonProcessingException {
        Author author = new Author(
                13L,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );
        Assert.assertEquals(authorService.postAuthor(author).getStatusCode(), 201);
        Assert.assertEquals(authorService.deleteAuthorById(author.getAuthorId()).getStatusCode(), 204);
    }

    @Test
    public void postPutAndDeleteAuthor() throws JsonProcessingException {
        Response response;
        Long freeAuthorId;
        do {
            freeAuthorId = (long) (Math.random() * 10000);
            response = authorService.getAuthorById(freeAuthorId);
        } while (response.getStatusCode() != 404);

        Author postAuthor = new Author(
                freeAuthorId,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );

        Author putAuthor = new Author(
                freeAuthorId,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );

        Assert.assertEquals(authorService.postAuthor(postAuthor).getStatusCode(), 201);

        Assert.assertEquals(authorService.putAuthor(putAuthor).getStatusCode(), 200);

        Assert.assertEquals(authorService.deleteAuthorById(postAuthor.getAuthorId()).getStatusCode(), 204);
    }

    @Test
    public void postGetAndDeleteAuthor() throws JsonProcessingException {
        Response response;
        Long freeAuthorId;
        do {
            freeAuthorId = (long) (Math.random() * 10000);
            response = authorService.getAuthorById(freeAuthorId);
        } while (response.getStatusCode() != 404);

        Author postAuthor = new Author(
                freeAuthorId,
                new Name("first", "Second"),
                "nationality",
                new Birth("1997-06-18", "Ukraine", "Lviv"),
                "description"
        );

        Assert.assertEquals(authorService.postAuthor(postAuthor).getStatusCode(), 201);

        Assert.assertEquals(authorService.getAuthorById(freeAuthorId).getStatusCode(), 200);

        Assert.assertEquals(authorService.deleteAuthorById(freeAuthorId).getStatusCode(), 204);
    }
}

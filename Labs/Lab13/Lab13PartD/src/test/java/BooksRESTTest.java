import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8081);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testAddBook(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");

        given()
        .contentType(ContentType.JSON)
        .body(book)
        .when()
        .post("/books")
        .then()
        .statusCode(200);

        given()
        .when()
        .get("books/878")
        .then()
        .contentType(ContentType.JSON)
        .and()
        .body("isbn",equalTo("878"))
        .body("title",equalTo("Book 123"))
        .body("price",equalTo(18.95f))
        .body("author",equalTo("Joe Smith"));

        given()
        .when()
        .delete("books/878");

    }

    @Test
    public void testDeleteBook(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");

        given()
        .contentType(ContentType.JSON)
        .body(book)
        .when()
        .post("/books")
        .then()
        .statusCode(200);

        given()
        .when()
        .delete("books/878");

        given()
        .when()
        .get("books/878")
        .then()
        .statusCode(404)
        .and()
        .body("errorMessage",equalTo("Book with isbn= 878 is not available"));

    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");

        given()
        .contentType(ContentType.JSON)
        .body(book)
        .when()
        .post("/books")
        .then()
        .statusCode(200);

        book.setIsbn("879");

        given()
        .contentType(ContentType.JSON)
        .body(book)
        .when()
        .put("/books/878")
        .then()
        .statusCode(200);

        given()
        .when()
        .get("books/879")
        .then()
        .contentType(ContentType.JSON)
        .and()
        .body("isbn",equalTo("879"))
        .body("title",equalTo("Book 123"))
        .body("price",equalTo(18.95f))
        .body("author",equalTo("Joe Smith"));

        given()
        .when()
        .delete("books/879");
    }

    @Test
    public void testSearchBooks(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");

        given()
        .contentType(ContentType.JSON)
        .body(book)
        .when()
        .post("/books")
        .then()
        .statusCode(200);

        given()
        .when()
        .get("/books?author='Joe Smith'")
        .then()
        .contentType(ContentType.JSON)
        .and()
        .body("books[0].isbn", equalTo("878"))
        .body("books[0].title", equalTo("Book 123"))
        .body("books[0].price", equalTo(18.95f))
        .body("books[0].author", equalTo("Joe Smith"));

        given()
        .when()
        .delete("books/878");

    }

}

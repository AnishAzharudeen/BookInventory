package models;

import java.util.Map;

public class AddBookModel {

    private String author;
    private String title;
    private String isbn;
    private String published;
    private String price;
    private String genre;

    // Default Constructor
    public AddBookModel() {}

    // Getters and Setters for each field
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Static Factory Method
    public static AddBookModel createAddBookModel(Map<String, String> entry) {
        AddBookModel addBookModel = new AddBookModel();

        // Fetch values from the map and set them
        addBookModel.setAuthor(entry.get("author"));
        addBookModel.setTitle(entry.get("title"));
        addBookModel.setIsbn(entry.get("isbn"));
        addBookModel.setPublished(entry.get("published"));
        addBookModel.setPrice(entry.get("price"));


        addBookModel.setGenre(entry.get("genre"));

        return addBookModel;
    }
}
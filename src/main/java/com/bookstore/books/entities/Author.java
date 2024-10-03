package com.bookstore.books.entities;
import jakarta	.persistence.*;
import java.util.List;

@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuthorID")
    private int authorID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Biography")
    private String biography;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

	public Author(int authorID, String name, String biography, List<Book> books) {
		super();
		this.authorID = authorID;
		this.name = name;
		this.biography = biography;
		this.books = books;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [authorID=" + authorID + ", name=" + name + ", biography=" + biography + ", books=" + books
				+ "]";
	}

    
    // Getters and Setters
}

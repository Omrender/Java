package com.epam.rd.autotasks.collections.map;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BooksCatalog {
    private static final String EOL = System.lineSeparator();
    private Map<Author, List<Book>> catalog;

    public BooksCatalog() {
        this.catalog = new TreeMap<>();
    }

    public BooksCatalog(Map<Author, List<Book>> catalog) {
        this.catalog = new TreeMap<>(catalog);
    }

    /**
     * Returns a List of books of the specified author.
     *
     * @param author the author of books to search for.
     * @return a list of books or {@code null}
     * if there is no such author in the catalog.
     */
    public List<Book> findByAuthor(Author author) {
        if (author == null) {
            throw new NullPointerException("Author cannot be null");
        }
        return catalog.get(author);
    }

    /**
     * @return the string representation of all authors
     * separated by the current operating system {@code lineSeparator}.
     */
    public String getAllAuthors() {
        StringBuilder sb = new StringBuilder();
        for (Author author : catalog.keySet()) {
            sb.append(author).append(EOL);
        }
        return sb.toString().trim();
    }

    /**
     * Searches for pairs of (author, book) by the book title.
     * The pair must be included in the resulting map if the
     * book title contains the specified string matched ignore case.
     * All authors of the book must be specified in the
     * book authors list.
     *
     * @param pattern the string to search for in the book title.
     * @return the map which contains all found books and their authors.
     * It must be sorted by titles of books, if the titles match,
     * by increasing cost.
     */
    public Map<Book, List<Author>> findAuthorsByBookTitle(String pattern) {
        if (pattern == null) {
            throw new NullPointerException("Pattern cannot be null");
        }
        
        Map<Book, List<Author>> result = new TreeMap<>();
        Pattern searchPattern = Pattern.compile(Pattern.quote(pattern), Pattern.CASE_INSENSITIVE);

        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();
            
            for (Book book : books) {
                Matcher matcher = searchPattern.matcher(book.getTitle());
                if (matcher.find()) {
                    result.put(book, findAuthorsByBook(book));
                }
            }
        }
        return result;
    }

    /**
     * Searches for all books whose genre list contains the specified string.
     * The book must be included in the resulting list if at least
     * one genre of the book contains the specified pattern ignoring case.
     *
     * @param pattern the string to search for in the book genre list.
     * @return a set of books sorted using natural ordering.
     * @see Book class.
     */
    public Set<Book> findBooksByGenre(String pattern) {
        if (pattern == null) {
            throw new NullPointerException("Pattern cannot be null");
        }

        Set<Book> result = new TreeSet<>();
        Pattern searchPattern = Pattern.compile(Pattern.quote(pattern), Pattern.CASE_INSENSITIVE);

        for (List<Book> books : catalog.values()) {
            for (Book book : books) {
                for (String genre : book.getGenres()) {
                    Matcher matcher = searchPattern.matcher(genre);
                    if (matcher.find()) {
                        result.add(book);
                        break; // Move to the next book if this genre matches
                    }
                }
            }
        }
        return result;
    }

    /**
     * Searches for authors of the specified book.
     *
     * @param book the book.
     * @return a list of authors of the specified book.
     * @throws NullPointerException if the parameter is {@code null}
     */
    public List<Author> findAuthorsByBook(Book book) {
        if (book == null) {
            throw new NullPointerException("Book cannot be null");
        }
        
        List<Author> authors = new ArrayList<>();
        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();
            
            if (books.contains(book)) {
                authors.add(author);
            }
        }
        return authors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();
            sb.append(author).append(":\n");
            for (Book book : books) {
                sb.append("  ").append(book).append(EOL);
            }
        }
        return sb.toString().trim();
    }
}

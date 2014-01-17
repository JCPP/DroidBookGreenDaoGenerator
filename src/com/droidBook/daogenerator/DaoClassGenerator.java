package com.droidBook.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DaoClassGenerator {

	public static void main(String[] args) throws Exception {
		Schema schema = new Schema(1, "com.jcpp.droidBook.dao");

		addSchema(schema);

		new DaoGenerator().generateAll(schema, "../DroidBook/src-gen");
	}

	private static void addSchema(Schema schema){
		// Entità book
		Entity book = schema.addEntity("Book");
		book.addIdProperty();
		book.addStringProperty("isbn");
		book.addStringProperty("isbn13");
		book.addStringProperty("asin");
		book.addStringProperty("title").notNull();
		book.addStringProperty("editor");
		book.addDateProperty("date");
		book.addStringProperty("description");
		book.addIntProperty("nop");
		book.addIntProperty("vote");
		book.addDoubleProperty("vote_avg");
		book.addStringProperty("my_comment");
		book.addStringProperty("cover_path");
		

		//Entità author
		Entity author = schema.addEntity("Author");
		author.addIdProperty();
		author.addStringProperty("name").notNull();
		author.addStringProperty("surname");
		author.addStringProperty("gender");
		author.addDateProperty("born_date");
		author.addDateProperty("died_date");
		author.addStringProperty("hometown");
		author.addStringProperty("nation");
		author.addStringProperty("description");
		author.addStringProperty("thumbnail_path");

		//Entità Book_Author e relazioni M-N
		Entity bookAuthor = schema.addEntity("BookAuthor");
		bookAuthor.addIdProperty();

		Property fk_bookId_BA = bookAuthor.addLongProperty("bookId").notNull().getProperty();
		book.addToMany(bookAuthor, fk_bookId_BA);
		bookAuthor.addToOne(book, fk_bookId_BA);

		Property authorId = bookAuthor.addLongProperty("AuthorId").notNull().getProperty();
		author.addToMany(bookAuthor, authorId);
		bookAuthor.addToOne(author, authorId);
		
		
		//Entità shelve o scaffale e relazioni M-N
		Entity shelve = schema.addEntity("Shelve");
		shelve.addIdProperty();
		shelve.addStringProperty("name").notNull();
		shelve.addStringProperty("description");
		
		Entity bookShelve = schema.addEntity("BookShelve");
		bookShelve.addIdProperty();
		Property fk_bookId_BS = bookShelve.addLongProperty("bookId").notNull().getProperty();
		
		book.addToMany(bookShelve, fk_bookId_BS);
		bookShelve.addToOne(book, fk_bookId_BS);
		
		//Entità category e relazioni M-N
		Entity category = schema.addEntity("Category");
		category.addIdProperty();
		category.addStringProperty("name").notNull();
		category.addStringProperty("description");
		
		Entity bookCategory = schema.addEntity("BookCategory");
		bookCategory.addIdProperty();
		Property fk_bookId_BC = bookCategory.addLongProperty("bookId").notNull().getProperty();
		
		book.addToMany(bookCategory, fk_bookId_BC);
		bookCategory.addToOne(book, fk_bookId_BC);
		
		//Entità note e relazioni N-1
		Entity note = schema.addEntity("Note");
		note.addIdProperty();
		note.addStringProperty("title");
		note.addStringProperty("text").notNull();
		Property fk_bookId_BN = note.addLongProperty("bookId").notNull().getProperty();
		
		book.addToMany(note, fk_bookId_BN);
		note.addToOne(book, fk_bookId_BN);
		
		//Entità quote o citazione e relazioni N-1
		Entity quote = schema.addEntity("Quote");
		quote.addIdProperty();
		quote.addStringProperty("title");
		quote.addStringProperty("text").notNull();
		quote.addStringProperty("nop");
		quote.addStringProperty("row");
		Property fk_bookId_BQ = quote.addLongProperty("bookId").notNull().getProperty();
		
		book.addToMany(quote, fk_bookId_BQ);
		quote.addToOne(book, fk_bookId_BQ);
	}
}


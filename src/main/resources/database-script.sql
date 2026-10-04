CREATE TABLE Publisher (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

GO

CREATE TABLE Book
(
    id INt IDENTITY PRIMARY KEY,
    title        VARCHAR(50)    NOT NULL,
    isbn        VARCHAR(50)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    pages       INT NOT NULL,
    publisher_id INT NOT NULL FOREIGN KEY REFERENCES Publisher(id),
);

GO

INSERT INTO Publisher (name, country)
VALUES
    ('Penguin Books', 'United Kingdom'),
    ('HarperCollins', 'United States'),
    ('O''Reilly Media', 'United States'),
    ('Školska knjiga', 'Croatia'),
    ('Profil', 'Croatia');



INSERT INTO Book (title, isbn, price, pages, publisher_id)
VALUES
    ('Clean Code', '9780132350884', 35.99, 464, 1),
    ('The Pragmatic Programmer', '9780135957059', 42.50, 352, 1),
    ('Design Patterns', '9780201633610', 49.99, 395, 2),
    ('Java: The Complete Reference', '9781260463415', 55.00, 1248, 2),
    ('Learning SQL', '9781492057611', 39.99, 384, 3),
    ('Head First Java', '9781491910771', 44.99, 754, 3),
    ('Uvod u programiranje', '9789530612345', 25.00, 320, 4),
    ('Algoritmi i strukture podataka', '9789530615678', 30.00, 410, 4),
    ('Python za početnike', '9789531201234', 28.50, 280, 5),
    ('Web programiranje', '9789531205678', 32.00, 350, 5);


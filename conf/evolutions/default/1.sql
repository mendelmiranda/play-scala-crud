# --- !Ups
    CREATE TABLE Client (
      id INT NOT NULL AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      birth DATE NOT NULL,
      email VARCHAR(100) NULL,
      PRIMARY KEY (id)
    );

# --- !Downs
    DROP TABLE Client;
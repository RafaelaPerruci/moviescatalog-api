DROP TABLE IF EXISTS movies_catalog;

CREATE TABLE movies (
                        id VARCHAR(36),
                        title VARCHAR(255),
                        overview_resume VARCHAR(3000),
                        release_date VARCHAR(10),
                        rating DECIMAL(2,1)
);

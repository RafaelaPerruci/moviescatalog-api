CREATE TABLE movies (
                        id VARCHAR(36) PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        overview_resume VARCHAR(400),
                        release_date VARCHAR(10),
                        rating DECIMAL(1,1)
);
DROP DATABASE IF EXISTS gameTest;
CREATE DATABASE gameTest;

USE gameTest;

CREATE TABLE Game (
	gameId INT NOT NULL AUTO_INCREMENT,
    answer VARCHAR(4) NOT NULL,
    isFinished TINYINT(1) NOT NULL,
    CONSTRAINT pk_Game PRIMARY KEY (gameId)
);

CREATE TABLE Round (
	roundId INT NOT NULL AUTO_INCREMENT,
    gameId INT NOT NULL,
    guess VARCHAR(4) NOT NULL,
    exactMatch INT NOT NULL,
    partialMatch INT NOT NULL,
    `time` DATETIME NOT NULL,
    CONSTRAINT pk_Round PRIMARY KEY (roundId),
    CONSTRAINT fk_Round_gameId
		FOREIGN KEY (gameId)
        REFERENCES Game(gameId)
);
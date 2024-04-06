-- inserting a movie
    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, '28 Weeks Later', '11/may/2007', 'After a rage virus outbreak in the British Ilses, two children try to escape the deadly outcomes.',
    'R', 99, 'Horror', 15, 'Showing');

    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, 'Jaws', '20/jun/1975', 'A police chief, marine biologist, and fisherman hunt a han-eating shark.',
    'PG', 124, 'Horror', 9, 'Showing');

    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, 'The Dark Knight', '18/jul/08', 'Batman tries to save Gotham City from the Joker.',
    'PG-13', 152, 'Action', 185, 'Showing');

    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, 'WALL-E', '10/jul/08', 'Compost robot tries to make Earth inhabitable for humans again after lots of pollution.',
    'G', 98, 'Family', 180, 'Coming Soon');

    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, 'The Big Short', '11/dec/2015', 'A fun explaination of the 2008 housing crisis in the US.',
    'R', 130, 'Drama', 50, 'Showing');

    INSERT INTO MOVIE (MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType)
    VALUES (seq_mov_id.nextval, 'Barbie', '21/jul/23', 'Something weird happens in Barie Land. All the Barbies have to fix it. Ken also there.',
    'PG-13', 114, 'Family', 145, 'Coming Soon');



-- inserting a person
    -- used for cast
        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Carlyle', 'Robert', 100, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Byrne', 'Rose', 150, 'Actress');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Scheider', 'Roy', 150, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Gary', 'Lorraine', 60, 'Actress');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Bale', 'Christian', 200, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Murphy', 'Cillian', 180, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Burtt', 'Ben', 180, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Knight', 'Elissa', 100, 'Actress');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Gosling', 'Ryan', 150, 'Actor');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Robbie', 'Margot', 200, 'Actress');
    
    -- Used for crew
        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Rowan', 'Joffe', 50, 'Writer');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Fresnadillo', 'Jan', 100, 'Director');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Lavigne', 'Enrique', 100, 'Producer');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Spielberg', 'Steven', 70, 'Director');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Brown', 'David', 50, 'Producer');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Zanuck', 'Richard', 30, 'Writer');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Nolan', 'Christopher', 100, 'Director');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Stanton', 'Andrew', 70, 'Director');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'McKay', 'Adam', 80, 'Director');

        INSERT INTO PERSON(PersonID, LastName, FirstName, PayK, PersonType)
        VALUES(seq_per_id.nextval, 'Gerwig', 'Greda', 80, 'Director');





-- inserting cast (key to movie and person table)
    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Don', 1, 1);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Scarlet', 1, 2);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Martin Brody', 2, 3);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Ellen Brody', 2, 4);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Batman', 3, 5);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Scarecrow', 3, 6);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'WALL-E', 4, 7);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'EVE', 4, 8);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Michael Burry', 5, 5);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Jared Vennett', 5, 9);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Se Stessa', 5, 10);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Ken', 6, 9);

    INSERT INTO CAST(CastID, CharName, MovieID, PersonID)
    VALUES(seq_cast_id.nextval, 'Barbie', 6, 10);


-- inserting crew (key to movie and person table)

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 1, 11);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 1, 12);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 1, 13);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 2, 14);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 2, 15);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 2, 16);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 3, 17);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 4, 18);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 4, 15);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 4, 16);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 5, 19);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Primary', 6, 20);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 6, 11);

    INSERT INTO CREW(CrewID, Contribution, MovieID, PersonID)
    VALUES(seq_crew_id.nextval, 'Secondary', 5, 13);



-- inserting theater
    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('AMC Forum 25', 1);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('AMC Forum 25', 2);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('AMC Forum 25', 3);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('AMC Forum 30', 1);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('AMC Forum 30', 2);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('Emagine', 1);

    INSERT INTO THEATER(TheaterName, ScreenID)
    VALUES('Emagine', 2);


-- inserting showtimes
INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'Emagine', 1, 2, TO_DATE('2024/04/23 12:30:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'Emagine', 1, 2, TO_DATE('2024/04/14 17:30:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'AMC Forum 25', 2, 1, TO_DATE('2024/04/11 15:30:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'AMC Forum 30', 2, 2, TO_DATE('2024/04/11 15:30:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'AMC Forum 25', 3, 3, TO_DATE('2024/04/20 23:00:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'AMC Forum 30', 3, 1, TO_DATE('2024/04/19 19:00:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'AMC Forum 25', 5, 3, TO_DATE('2024/04/29 11:00:00', 'yyyy/mm/dd hh24:mi:ss'));

INSERT INTO SHOWTIMES (ShowtimeID, TheaterName, MovieID, ScreenID, Showtime)
VALUES (seq_show_id.nextval, 'Emagine', 5, 1, TO_DATE('2024/04/30 15:00:00', 'yyyy/mm/dd hh24:mi:ss'));

		
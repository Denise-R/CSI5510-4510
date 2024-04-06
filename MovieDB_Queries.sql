
-- 1. List all the movies produced by a given producer
/*Button name = movies by a producer
  param 1 = personType
  param 2 = person id (int)*/
  SELECT m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil, p.FirstName, p.LastName, p.PayK, c.Contribution
  FROM MOVIE m, PERSON p, CREW c
  WHERE m.MovieID = c.MovieID
  AND p.personID = c.personID
  AND p.PersonID LIKE '13'
  AND p.PersonType LIKE 'Producer';

-- 2. List all the movies that were directed by a given director
/*Button name = movies by a director
  param 1 = personType
  param 2 = person id (int)*/
SELECT m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil, p.FirstName, p.LastName, p.PayK, c.Contribution
  FROM MOVIE m, PERSON p, CREW c
  WHERE m.MovieID = c.MovieID
  AND p.personID = c.personID
  AND p.PersonID LIKE '20'
  AND p.PersonType LIKE 'Director';


-- 3. Find the most expensive movie to produce.
/*Button name = Producers most expensive movie
  param 1 = person id (int)*/
SELECT m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil, m.ScreeningType
	FROM MOVIE m, (SELECT distinct m.MovieID
                  FROM MOVIE m, PERSON p, CREW c
                  WHERE m.MovieID = c.MovieID 
                    AND p.personID = c.personID
                    AND p.PersonID LIKE '13'
                    AND p.PersonType LIKE 'Producer') MOVIESOFINTEREST
	WHERE m.MovieID = MOVIESOFINTEREST.MovieID
	and m.CostMil = (SELECT MAX(m.CostMil)
                  FROM MOVIE m, PERSON p, CREW c
                  WHERE m.MovieID = c.MovieID 
                    AND p.personID = c.personID
                    AND p.PersonID LIKE '13'
                    AND p.PersonType LIKE 'Producer');


-- 4. Find all the movies that were produced in the same year
/*Button name = Movies/ year
  param 1 = last 2 digits in year; need the % */
SELECT DISTINCT m.MovieID, Title, ReleaseDate, Rating, LengthMin, Category, CostMil
FROM MOVIE m
WHERE  m.ReleaseDate LIKE '%08';



-- 5. Find an actress who does not join a movie produced by a producer. 
/*Button name = Actor/Actress does not work Producer
  param 1 = producer personID
  param 2 = actor/actress drop down (will return all actors/actress not just 1)*/
SELECT PERSON.FirstName, PERSON.LastName 
FROM PERSON
WHERE PERSON.PersonID NOT IN (SELECT  p.PersonID 
  FROM MOVIE m, PERSON p, CAST c
  WHERE m.MovieID = c.MovieID
    AND p.personID = c.personID
    And m.MovieID in (SELECT m.movieID
                      FROM MOVIE m, PERSON p, CREW c
                      WHERE m.MovieID = c.MovieID
                      AND p.personID = c.personID
                      AND p.PersonID LIKE '13'
                      AND p.PersonType LIKE 'Producer')
    AND p.PersonType LIKE 'Actress')
AND PERSON.PersonType LIKE 'Actress';



-- 6. Find the highest amount of money earned by an actress in a movie.
/*Button name = Max pay Movie
  param 1 = Movie ID
  param 2 = actor/actress drop down*/
SELECT p.FirstName, p.LastName, p.PayK 
FROM MOVIE m, PERSON p, CAST c
WHERE m.MovieID = c.MovieID
AND p.personID = c.personID
AND p.PersonType LIKE 'Actor'
AND m.movieID = 3
And p.payK = (SELECT max(p.payK)
              FROM MOVIE m, PERSON p, CAST c
              WHERE m.MovieID = c.MovieID
              AND p.personID = c.personID
              AND p.PersonType LIKE 'Actor'
              AND m.movieID = 3);




-- 7. Find actors and actresses who joined a movie
/*Button name = Cast of a Movie
  param 1 = Movie ID*/
select m.title, p.FirstName, p.LastName, p.personType
FROM MOVIE m, PERSON p, CAST c
WHERE m.MovieID = c.MovieID
AND p.personID = c.personID
AND m.movieID = 3


-- 8. List all the movies below a price directed by a director.
/*Button name = Movies filtered by budget
  param 1 = personID (only directors)
  param 2 = int amount below value. (cost in mil)*/
SELECT m.Title, m.ReleaseDate, m.Rating, m.LengthMin, m.Category, m.CostMil
  FROM MOVIE m, PERSON p, CREW c
  WHERE m.MovieID = c.MovieID
  AND p.personID = c.personID
  AND p.PersonID LIKE '20'
  AND p.PersonType LIKE 'Director'
  AND m.CostMil < 100;


  -- 9. List producers who produced all the most expensive movies in a given year.
  /*Button name = Most expensive movie per year
  param 1 = last 2 digits in year; need the %*/
select m.title, p.FirstName, p.LastName, p.personType
FROM MOVIE m, PERSON p, Crew c
WHERE m.MovieID = c.MovieID
AND p.personID = c.personID
AND m.movieID = (select m.MovieID
                    FROM MOVIE m
                    WHERE m.ReleaseDate like '%08'
                    AND m.CostMil = (SELECT MAX(m.CostMil)
                                    FROM MOVIE m
                                    WHERE m.MovieID in (select m.MovieID
                                                        FROM MOVIE m
                                                        WHERE m.ReleaseDate like '%08')));



-- 10. Find movies that people are more watching for an actor or an actress.
  /*Button name = Movies by Cast
  param 1 = actor/actress dropdown
  param2 = person id %*/
SELECT m.Title, p.FirstName, p.LastName, p.PersonType
  FROM MOVIE m, PERSON p, CAST c
  WHERE m.MovieID = c.MovieID
  AND p.personID = c.personID
  AND p.PersonID LIKE '10'
  AND (p.PersonType = 'Actor' OR p.PersonType = 'Actress');


-- Movie Page View Table
SELECT MovieID, Title, ReleaseDate, Synopsis, Rating, LengthMin, Category, CostMil, ScreeningType FROM movie;

-- Cast Page View Query
select m.title, p.PersonID, p.LastName, p.FirstName, c.CharName, p.PayK, p.PersonType FROM MOVIE m, PERSON p, CAST c WHERE m.MovieID = c.MovieID AND p.personID = c.personID;

-- Crew Page View Query
select m.title, p.PersonID, p.LastName, p.FirstName, c. Contribution, p.PayK, p.PersonType FROM MOVIE m, PERSON p, CREW c WHERE m.MovieID = c.MovieID AND p.personID = c.personID;

-- Showtime Page View Query
select s.ShowtimeID, s.TheaterName, s.MovieID, m.title, s.ScreenID, s.Showtime FROM MOVIE m, SHOWTIMES s where m.movieID = s.MovieID;
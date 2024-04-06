--Movie
	-- Used to auto increment movie ID
	create sequence seq_mov_id start with 1 increment by 1;

	-- Creating Movie table
	CREATE TABLE MOVIE
	(
		MovieID int,
		Title VARCHAR(50),
		ReleaseDate date,
			CONSTRAINT chk_movie_release CHECK( ReleaseDate > '01/JAN/1900'),
		Synopsis VARCHAR(500),
			CONSTRAINT unq_mov_synopsis unique(Synopsis),
		Rating VARCHAR(10),
			CONSTRAINT chk_mov_rating CHECK(Rating IN ('R', 'G', 'PG', 'PG-13')),
		LengthMin int,
		Category VARCHAR(100),
			CONSTRAINT chk_mov_cat CHECK(Category IN ('Action', 'Animation', 'Comedy', 'Drama', 'Family', 'Horror', 'Romance', 'Fantasy')),
		CostMil int,
		ScreeningType VARCHAR(15),
			CONSTRAINT chk_mov_screentype CHECK(ScreeningType IN ('Showing', 'Coming Soon')),
		PRIMARY KEY(MovieID)
	);
	
	-- crating an index on person first name for quicker lookup
	create INDEX IDX_mtitle on movie(Title);





-- Person
	-- Used to auto increment PersonID
	create sequence seq_per_id start with 1 increment by 1;

	-- creating person table
	CREATE TABLE PERSON
	(
		PersonID int,
		LastName VARCHAR(50) not null,
		FirstName VARCHAR(50) not null,
		PayK int not null,
		PersonType VARCHAR(50),
			CONSTRAINT chk_person_ptype CHECK(PersonType IN ('Actor', 'Actress', 'Director', 'Producer','Writer')),
		PRIMARY KEY(PersonID)
	);

	-- crating an index on person first name for quicker lookup
	create INDEX IDX_pname on person(FirstName);




-- Cast (Actor, Actress)

	-- Used to auto increment cast ID
	create sequence seq_cast_id start with 1 increment by 1;

	-- Creating Cast table
	CREATE TABLE CAST
	(
		CastID int,
		CharName VARCHAR(50) not null,
		MovieID int,
		PersonID int,
		PRIMARY KEY(CastID, PersonID),
		CONSTRAINT cast_has_movieID FOREIGN KEY(MovieID) REFERENCES MOVIE(MovieID),
		CONSTRAINT cast_has_personID FOREIGN KEY(PersonID) REFERENCES PERSON(PersonID)
	);

	-- crating an index on Character name for quicker lookup
	create INDEX IDX_charname on cast(CharName);





-- Crew (Writer, director, producer)

	-- Used to auto increment crew ID
	create sequence seq_crew_id start with 1 increment by 1;

	-- Creating Crew table
	CREATE TABLE CREW
	(
		CrewID int,
		Contribution VARCHAR(50) not null,
		MovieID int,
		PersonID int,
		PRIMARY KEY(CrewID, PersonID),
		CONSTRAINT crew_has_movieID FOREIGN KEY(MovieID) REFERENCES MOVIE(MovieID),
		CONSTRAINT crew_has_personID FOREIGN KEY(PersonID) REFERENCES PERSON(PersonID)
	);

	-- crating an index on Contribution for quicker lookup
	create INDEX IDX_contr on crew(Contribution);




-- Theater
	-- Used to auto increment TheaterID
	create sequence seq_thr_id start with 1 increment by 1;

	-- Creating Theater table
	CREATE TABLE THEATER
	(
		TheaterName VARCHAR(20),
		ScreenID int,
		PRIMARY KEY(TheaterName, ScreenID)
	);






-- Showtimes
	-- Used to auto increment TheaterID
	create sequence seq_show_id start with 1 increment by 1;

	-- Creating Showtimes table
	CREATE TABLE SHOWTIMES
	(
		ShowtimeID int,
		TheaterName VARCHAR(20),
		MovieID int,
		ScreenID int,
		Showtime date,
			CONSTRAINT chk_st_showtime CHECK( Showtime > '01/JAN/1900'),
		PRIMARY KEY(ShowtimeID, TheaterName, MovieID, ScreenID, Showtime),
			CONSTRAINT showtime_has_theater FOREIGN KEY(TheaterName, ScreenID) REFERENCES THEATER(TheaterName, ScreenID),
			CONSTRAINT showtime_has_movie FOREIGN KEY(MovieID) REFERENCES MOVIE(MovieID)
	);

	create INDEX IDX_showtime on SHOWTIMES(Showtime);



-- dropping tables, index, seq
drop INDEX IDX_showtime;
drop TABLE SHOWTIMES;
drop sequence seq_show_id;
drop TABLE THEATER;
drop sequence seq_thr_id;
drop INDEX IDX_contr;
drop table crew;
drop sequence seq_crew_id;
drop INDEX IDX_charname;
drop table cast;
drop sequence seq_cast_id;
drop INDEX IDX_pname;
drop table person;
drop sequence seq_per_id;
drop INDEX IDX_mtitle;
drop table movie;
drop sequence seq_mov_id;
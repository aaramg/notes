INSERT INTO "user" ("id", "email", "password_hash", "created", "updated")
VALUES (-1, 'aaramg@gmail.com', '$2a$12$.lR3Zmi4ZLUInYyS9dsKSOMINGItl.5VI2gFoaQ2bGHCv5bWTrccG', current_timestamp,
        current_timestamp);

INSERT INTO "user" ("id", "email", "password_hash", "created", "updated")
VALUES (-2, 'aaramg1@gmail.com', '$2a$12$.lR3Zmi4ZLUInYyS9dsKSOMINGItl.5VI2gFoaQ2bGHCv5bWTrccG', current_timestamp,
        current_timestamp);

INSERT INTO "note" ("id", "title", "description", "user_id", "created", "updated")
VALUES (-1, 'Title -1', 'Description -1', -1, current_timestamp, current_timestamp);

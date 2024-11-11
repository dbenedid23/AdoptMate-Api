package com.adoptmate.tools;


public class SQLConfiguration {

    public static class QUERIES{

        public static class PET{
            public static final String FIND_BY_SHELTER = "SELECT p FROM Pet p JOIN p.shelterFinal s WHERE s.name = ?1",
                    FIND_BY_SPECIE = "SELECT p FROM Pet p JOIN p.breedFinal b WHERE b.specie = ?1",
                    FIND_BY_TYPE= "SELECT p FROM Pet p JOIN p.breedFinal b WHERE b.type = ?1";
        }

        public static class USER{
            public static final String STATEMENTS = "SELECT s FROM User u JOIN u.statements s WHERE u.id = ?1",
                    MATCHED_PETS = "SELECT p FROM User u JOIN u.matchedShelters ms JOIN ms.pets p WHERE u.id = ?1",
                    LIKED_SHELTERS = "SELECT s FROM User u JOIN u.matchedShelters s WHERE u.id = ?1",
                    LOGIN = "SELECT u FROM User u WHERE u.name = ?1 AND u.password = ?2",
                    FIND_BY_NAME= "SELECT u FROM User u WHERE u.name like ?1%";

        }

        public static class BREED{
            public static final String FIND_BY_NAME = "SELECT b FROM Breed b WHERE b.name like ?1%";
        }
    }

    public static class COLUMN_NAMES{

        public static final String REACHED_USERS = "reached_users",
                MATCHED_USERS = "matched_users",
                USERS_PETS = "users_pets";

        public static class PET{
            public static final String ID = "pet_id",
                    TABLE = "pets",
                    NAME = "name",
                    SEX = "sex",
                    AGE = "age",
                    DESCRIPTION = "description",
                    SHELTER = "shelter_tmp",
                    BREED = "breed_tmp",
                    MAPPED_BY = "pet";

        }

        public static class USER{
            public static final String ID = "user_id",
                    TABLE = "users",
                    NAME = "name",
                    PASSWORD = "password",
                    ZIP_CODE = "zip_code",
                    DESCRIPTION = "description",
                    PHONE = "phone",
                    HOME = "home",
                    PETS = "pets",
                    KIDS = "users_pets",
                    PROFILE_IMAGE = "profile_image",
                    MAPPED_BY = "user";
        }

        public static class SHELTER{
            public static final String ID = "shelter_id",
                    TABLE = "shelters",
                    CIF = "cif",
                    NAME = "name",
                    PASSWORD = "password",
                    LOCATION = "location",
                    PHONE = "phone",
                    DESCRIPTION = "description",
                    MAPPED_BY = "shelter";


        }

        public static class BREED{
            public static class DEFINITIONS{
                public static final String SPECIE = "INT DEFAULT 0";
            }
            public static final String TABLE = "breeds",
                    ID = "breed_id",
                    NAME = "name",
                    TYPE = "type",
                    SIZE = "size",
                    SPECIE = "specie",
                    MAPPED_BY = "breed";
        }

        public static class IMAGE{
            public static final String TABLE = "images", IMAGE = "image";

        }

        public static class INCOME_STATEMENT{
            public static final String TABLE = "income_statements", FILE = "file";
        }

        public static class MESSAGE{
            public static final String TABLE = "messages", TIME = "time",
                    TEXT = "text", ISSUER = "issuer";
        }
    }





}

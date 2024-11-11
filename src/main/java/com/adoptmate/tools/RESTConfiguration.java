package com.adoptmate.tools;


public class RESTConfiguration {

    public static class PARAMS{
        public static final String FIND_RANDOM_NAME = "n";
    }
    public static final String SAVE = "/save",
            UPDATE = "/update/{id}",
            DELETE = "/delete/{id}",
            GET = "/{id}",
            ALL = "/all",
            RANDOM = "/random",
            FIND_RANDOM = "/randoms";

    public static class PET{
        public static class PARAMS {
            public static final String FIND_BY_SHELTER_NAME = "shelter", BREEDS_NAME = "name";
        }
        public static final String REQUEST_MAPPING = "api/pet",
                FIND_BY_SHELTER = "/shelter",
                FIND_DOGS = "/dogs",
                FIND_DOGS_BREEDS = "/dogs/breeds",
                FIND_CATS = "/cats",
                FIND_CATS_BREEDS = "/cats/breeds",
                BREEDS = "/breeds",
                TYPE = "/type/{type}";
    }

    public static class USER{
        public static class PARAMS{
            public static final String USER_ID = "userId", USER_NAME = "userName",
                    SHELTER_NAME = "shelterName", PET_ID = "petId";

        }
        public static final String REQUEST_MAPPING = "api/user",
                GET_PHONE = "/{id}/phone",
                GET_NAME = "/{id}/name",
                GET_BY_NAME = "/name/{name}",
                GET_INCOMES = "/incomes/{id}",
                GET_PETS = "/pets/{id}",
                GET_SHELTERS = "/shelters/{id}",
                LOGIN = "/login",
                LIKE = "/like",
                SEND_MESSAGE = "/text";
    }

    public static class SHELTER{
        public static final String REQUEST_MAPPING = "api/shelter", FIND_BY_NAME = "/name/{name}",
                ADD_PET = "/add/{id}", REMOVE_PET = "/remove/{id}";
    }
}

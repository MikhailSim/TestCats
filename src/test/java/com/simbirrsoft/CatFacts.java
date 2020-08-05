package com.simbirrsoft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFacts {
    public User user;
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class User {
        public Name name;
        public class Name {
            public String first;
            public String last;
        }
    }
}
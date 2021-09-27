package com.globallogic.basecamp.model;

/**
 * Address is a representation of the {@link com.globallogic.basecamp.model.Student Student} home address.
 */
public class Address {

    private String country;

    private String city;

    private String street;

    private Integer houseNumber;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * TODO: implement getters and other methods if necessary
     */


    /**
     * Allows to get the builder for the Address
     *
     * @return builder
     */
    private Address(Builder builder) {
        this.country = builder.country;
        this.city = builder.city;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
    }

    /**
     * Builder class is a part of the builder pattern implementation
     * Needed to ease the Address object construction
     * <p>
     * TODO: implement the builder functionality
     */
    static class Builder {
        private String country;
        private String city;
        private String street;
        private Integer houseNumber;

        public Builder(String country, String city, String street, Integer houseNumber) {
            this.country = country;
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setHouseNumber(Integer houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }
        public Address build(){
           return new Address(this);
        }
    }
    }

package com.globallogic.basecamp.model;

/**
 * Address is a representation of the {@link com.globallogic.basecamp.model.Student Student} home address.
 */
public class Address {

    private final String country;

    private final String city;

    private final String street;

    private final Integer houseNumber;

    /**
     * TODO: implement getters and other methods if necessary
     */

    /**
     * Allows to get the builder for the Address
     *
     * @return builder
     */
    public static Builder builder() {
        
    }

    /**
     * Builder class is a part of the builder pattern implementation
     * Needed to ease the Address object construction
     * <p>
     * TODO: implement the builder functionality
     */
    public class Builder {
    }

}

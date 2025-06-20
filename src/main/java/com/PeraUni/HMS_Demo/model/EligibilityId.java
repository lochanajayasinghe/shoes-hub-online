package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EligibilityId implements Serializable {
    private String regNo;
    private String hostelId;

    // Default constructor
    public EligibilityId() {}

    public EligibilityId(String regNo, String hostelId) {
        this.regNo = regNo;
        this.hostelId = hostelId;
    }

    // Getters, setters, equals, and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityId that = (EligibilityId) o;
        return regNo.equals(that.regNo) && hostelId.equals(that.hostelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo, hostelId);
    }
}
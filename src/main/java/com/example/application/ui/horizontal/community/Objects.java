package com.example.application.ui.horizontal.community;

public class Objects implements Cloneable {
    private int id;
    private String scope;
    private Status status;
    private double price;
    private String email;

    public Objects() {

    }

    public Objects(int id, String scope, Status status, double price, String email) {
        super();
        this.id = id;
        this.scope = scope;
        this.status = status;
        this.price = price;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return scope;
    }

    public void setCategory(String scope) {
        this.scope = scope;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Objects)) {
            return false;
        }
        Objects other = (Objects) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return scope;
    }

    @Override
    public Objects clone() {
        try {
            return (Objects) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(
                    "The Person object could not be cloned.", e);
        }
    }
}

enum Status {
    BIETE, SUCHE
}
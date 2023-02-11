package com.example.apt_app;

public class Payment {

    private double utilities;
    private double rent;

    public Payment(double utilities, double rent)
    {
        this.utilities = utilities;
        this.rent = rent;
    }

    public double getUtilities()
    {
        return utilities;
    }

    public double getRent()
    {
        return rent;
    }

}

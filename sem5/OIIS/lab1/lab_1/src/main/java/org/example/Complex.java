package org.example;

public class Complex {
    double real;
    double imaginary;

    Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    Complex multiply(Complex other) {
        return new Complex(
                this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real
        );
    }

    public double magnitude() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public static Complex fromPolar(double magnitude, double angle) {
        return new Complex(
                magnitude * Math.cos(angle),
                magnitude * Math.sin(angle)
        );
    }
}
#include <iostream>
#include <cmath>

using namespace std;


double sqrt_recursive(double a, double x0 /*int iterations*/) {
    double xn = x0;
    for (int i = 0; i < 100 /*iterations*/; i++) {
        xn = 0.5 * (xn + a / xn);
    }
    return xn;
}

int main() {
    double a;
    cout << "Enter the number to find its square root: ";
    cin >> a;

    if (a < 0) {
        cout << "Error: Cannot compute square root of a negative number." << endl;
        return 1;
    }

    double x0 = 0.5 * (1 + a);


   /* int iterations;
    cout << "Enter the number of iterations: ";
    cin >> iterations;*/


    double result = sqrt_recursive(a, x0 /*iterations*/);
    double result1 = sqrt(a);

    cout << "Square root of " << a << " is approximately: " << result << endl;
    cout << "Square root of " << a << " is approximately1: " << result1 << endl;

    return 0;
}


#include <math.h>
#include <iostream>
using namespace std;


int main()
{
	double x, y, f, a, s;
	int k;
	cout << "Vvedite x "; cin >> x;
	cout << "Vvedite y "; cin >> y;
	cout << "Viberite f: 1 - sh(x), 2 - x^2, 3 - exp(x) "; cin >> k;
	switch (k)
	{
	case 1: f = sinh(x); break;
	case 2: f = pow(x, 2); break;
	case 3: f = exp(x); break;
	default: cout << "Ne vuibrana funkciya "; return 1;
	}
	//a = fabs(x * y);
	if (x> fabs(y)) {
		s = 2* pow(f,3) +3*pow(y,2);
	}
	else if (3<x<fabs(y)) {
		s = fabs(f - y);
	}
	else {
		s = pow( (fabs( f - y)), 1.0 / 3);
	}
	cout << "RESULT = " << s << endl;
	return 0;
}

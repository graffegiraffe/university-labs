#include <iostream>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    string input;
    cout << "Введите строку с числами, разделенными пробелами: ";
    getline( cin, input);

    istringstream iss(input); //читаем данные из потока
    vector<int> numbers;

    int number;
    while (iss >> number) {
        numbers.push_back(number);
    }

    sort(numbers.begin(), numbers.end());

    cout << "Числа в порядке возрастания: ";
    for (const int &num: numbers)
        cout << num << " ";


    cout << endl;

    return 0;
}
#include <iostream>

using namespace std;

int main() {
    setlocale(LC_ALL, "RU");
    int k;
    cout << "Введите количество элементов массива: ";
    cin >> k;

    char mas[100];

    cout << "Введите элементы массива: " << endl;
    for (int i = 0; i < k; i++){
        cin >> mas[i];
    }

    char numbers[110];
    char liters[110];

    int cnt_num = 0, cnt_lit = 0;
    for (int i = 0; i < k; i++) {
        if (isdigit(mas[i])) {
            numbers[cnt_num] = mas[i];
            cnt_num++;
        }
        else {
            liters[cnt_lit] = mas[i];
            cnt_lit++;
        }
    }

    cout << "Заданный массив: \n";
    for (int i = 0; i < k; i++) {
        cout << mas[i] << " ";
    }
    cout << "\n\nЧисла: \n";
    for (int i = 0; i < cnt_num; i++) {
        cout << numbers[i] << " ";
    }
    cout << "\n\nБуквочки: \n";
    for (int i = 0; i < cnt_lit; i++) {
        cout << liters[i] << " ";
    }
}



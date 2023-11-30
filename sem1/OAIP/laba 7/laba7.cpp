#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

struct tovar{
    int count;
    string name;
};

struct Ceh
{
    int ceh_size;
    tovar array[100];
};

int main()
{
    Ceh ceh_array[100];
    int count_ceh, count_goods, current_ceh = 1;
    cout << "Введите кол-во цехов: ";
    cin >> count_ceh;
    for(int i = 0; i < count_ceh; i++){
        cout << "Введите кол-во товаров в цехе " << i + 1 << ": ";
        cin >> ceh_array[i].ceh_size;
        for(int j = 0; j < ceh_array[i].ceh_size; j++){
            cout << "==============================================" << '\n';
            cout << "Введите название товара: ";
            cin >> ceh_array[i].array[j].name;
            cout << "Введите кол-во товара: ";
            cin >> ceh_array[i].array[j].count;
        }
    }
    cout << "Введите цех, который необходимо вывести: ";
    cin >> current_ceh;
    cout << "========!Результат запроса==========" << '\n';
    for(int i = 0; i < ceh_array[current_ceh - 1].ceh_size; i++){
        cout << "Название товара: " << ceh_array[current_ceh - 1].array[i].name << '\n';
        cout << "Кол-во товара: " <<  ceh_array[current_ceh - 1].array[i].count << '\n';
        cout << "==============================================" << '\n';
    }
    return 0;
}

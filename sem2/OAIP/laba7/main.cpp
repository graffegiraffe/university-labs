#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Detail {
    string name;
    int quantity;
    int workshopNumber;
};

struct HashStack {
    Detail info;
    HashStack* next;
};

class HashTable {
private:
    int table_size;
    HashStack** N;
    string NameOfFile;

public:
    HashTable(int table_size, string FN) {
        this->table_size = table_size;
        NameOfFile = FN;
        N = new HashStack * [table_size];
        for (int j = 0; j < table_size; j++)
            N[j] = nullptr;
    }

    ~HashTable() {
        SaveToFile();
        for (int j = 0; j < table_size; j++) {
            HashStack* t = N[j];
            while (t != nullptr) {
                HashStack* temp = t;
                t = t->next;
                delete temp;
            }
        }
        delete[] N;
    }

    void AddInfo(Detail inf) {
        int key = inf.quantity;
        int j = HashFunc(key);

        // Создаем новый элемент
        HashStack* newElement = new HashStack;
        newElement->info = inf;
        newElement->next = nullptr; // Важно установить next в nullptr

        // Если цепочка пуста, добавляем в начало
        if (N[j] == nullptr) {
            N[j] = newElement;
        } else {
            // Находим последний элемент в цепочке
            HashStack* current = N[j];
            while (current->next != nullptr) {
                current = current->next;
            }

            // Соединяем новый элемент с последним
            current->next = newElement;
        }

        // Обновляем файл
        SaveToFile();
    }

    void Del(int key) {
        int j = HashFunc(key);
        HashStack* t = N[j];
        HashStack* prev = nullptr;

        while (t != nullptr) {
            if (t->info.quantity == key) {
                // Удаляем элемент
                if (prev == nullptr) {
                    N[j] = t->next;
                } else {
                    prev->next = t->next;
                }
                delete t;
                cout << "Деталь с указанным количеством удалена." << endl;
                // Обновляем файл
                SaveToFile();
                return;
            }
            prev = t;
            t = t->next;
        }

        cout << "Деталь с указанным количеством не найдена." << endl;
    }
    void Read(int key) {
        int j = HashFunc(key);
        HashStack* t = N[j];
        while (t != nullptr && t->info.quantity != key)
            t = t->next;
        if (t != nullptr) {
            cout << "Название детали: " << t->info.name << endl;
            cout << "Количество деталей: " << t->info.quantity << endl;
            cout << "Номер цеха: " << t->info.workshopNumber << endl;
        }
        else {
            cout << "Деталь с указанным количеством не найдена." << endl;
        }
    }

    void Task(int workshopNumber) {
        vector<HashStack*> workshopDetails;
        for (int j = 0; j < table_size; j++) {
            HashStack* t = N[j];
            while (t != nullptr) {
                if (t->info.workshopNumber == workshopNumber) {
                    workshopDetails.push_back(t);
                }
                t = t->next;
            }
        }

        sort(workshopDetails.begin(), workshopDetails.end(), [](HashStack* a, HashStack* b) {
            return a->info.quantity > b->info.quantity;
        });

        for (const auto& detail : workshopDetails) {
            cout << "Название детали: " << detail->info.name << endl;
            cout << "Количество деталей: " << detail->info.quantity << endl;
            cout << "Номер цеха: " << detail->info.workshopNumber << endl;
        }
    }

private:
    int HashFunc(int key) {
        return key % table_size;  //  Хеш-функция, использующая остаток от деления
    }

    void SaveToFile() {
        ofstream file(NameOfFile);
        if (!file.is_open()) {
            cout << "Не удалось открыть файл для записи." << endl;
            return;
        }
        for (int j = 0; j < table_size; j++) {
            HashStack* t = N[j];
            while (t != nullptr) {
                file << t->info.name << ' ';
                file << t->info.quantity << ' ';
                file << t->info.workshopNumber << endl;
                t = t->next;
            }
        }
        file.close();
    }
};

int main() {
    string filename;
    cout << "Введите путь к файлу: ";
    cin >> filename;

    ifstream file(filename);
    if (!file.is_open()) {
        cout << "Не удалось открыть файл." << endl;
        return 0;
    }

    vector<Detail> details;
    string name;
    int quantity;
    int workshopNumber;
    while (file >> name >> quantity >> workshopNumber) {
        Detail detail;
        detail.name = name;
        detail.quantity = quantity;
        detail.workshopNumber = workshopNumber;
        details.push_back(detail);
    }
    file.close();

    // Выберите размер таблицы хеширования больше, чем  n_records
    int n_records = details.size();
    int table_size = n_records * 2; //  Умножение на 2 - просто пример
    HashTable table(table_size, filename);

    for (const auto& detail : details) {
        table.AddInfo(detail);
    }

    int choice;
    do {
        cout << "Выберите действие:\n";
        cout << "1. Добавить деталь в список\n";
        cout << "2. Удалить деталь из списка\n";
        cout << "3. Найти деталь в списке\n";
        cout << "4. Выполнить задание\n";
        cout << "0. Выход\n";
        cout << "Ваш выбор: ";
        cin >> choice;

        switch (choice) {
            case 1: {
                Detail detail;
                cout << "Введите название детали: ";
                cin >> detail.name;
                cout << "Введите количество деталей: ";
                cin >> detail.quantity;
                cout << "Введите номер цеха: ";
                cin >> detail.workshopNumber;
                table.AddInfo(detail);
                break;
            }
            case 2: {
                int key;
                cout << "Введите количество деталей для удаления: ";
                cin >> key;
                table.Del(key);
                break;
            }
            case 3: {
                int key;
                cout << "Введите количество деталей для поиска: ";
                cin >> key;
                table.Read(key);
                break;
            }
            case 4: {
                int workshopNumber;
                cout << "Введите номер цеха: ";
                cin >> workshopNumber;
                table.Task(workshopNumber);
                break;
            }
            case 0: {
                break;
            }
            default: {
                cout << "Некорректный ввод. Попробуйте еще раз.\n";
                break;
            }
        }
    } while (choice != 0);

    return 0;
}
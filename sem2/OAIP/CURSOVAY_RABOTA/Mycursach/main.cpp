#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <iomanip>

using namespace std;

// Структура для изделия
struct Product {
    string name;
    string type;
    int quantity;
    double price;
    string status;
};

// Функция для создания файла
void createFile(const string& filename) {
    ofstream file(filename);
    if (!file.is_open()) {
        cerr << "Ошибка создания файла!" << endl;
        return;
    }
    cout << "Файл создан успешно." << endl;
    file.close();
}

// Функция для просмотра содержимого файла
void viewFile(const string& filename) {
    ifstream file(filename);
    if (!file.is_open()) {
        cerr << "Ошибка открытия файла!" << endl;
        return;
    }

    string line;
    cout << "Содержимое файла:" << endl;
    while (getline(file, line)) {
        if (line.empty()) {
            continue; // Пропускаем пустые строки
        }

        stringstream ss(line);
        Product product;
        ss >> quoted(product.name) >> quoted(product.type) >> product.quantity >> product.price;
        getline(ss >> ws, product.status); // Получаем статус, пропуская ведущие пробелы
        cout << product.name << " | " << product.type << " | " << product.quantity << " | " << product.price << " | " << product.status << endl;
    }
    file.close();
}

// Функция для добавления данных в файл
void addData(const string& filename) {
    ofstream file(filename, ios::app); // Добавление в конец файла
    if (!file.is_open()) {
        cerr << "Ошибка открытия файла!" << endl;
        return;
    }

    Product product;
    cout << "Введите данные продукта:" << endl;
    cout << "Название: ";
    cin.ignore(); // Очищаем буфер, чтобы считать название с пробелами
    getline(cin, product.name); // Считываем название целиком, включая пробелы
    cout << "Тип: ";      getline(cin, product.type);
    cout << "Количество: "; cin >> product.quantity;
    cout << "Цена: ";     cin >> product.price;
    cin.ignore(); // Игнорируем оставшийся символ новой строки
    cout << "Статус: ";  getline(cin, product.status);

    // Запись в файл с новой строки, только если файл уже содержит данные
    if (file.tellp() > 0) {
        file << '\n';
    }

    file << quoted(product.name) << " "
         << quoted(product.type) << " "
         << product.quantity << " "
         << product.price << " "
         << product.status;

    file.close();
    cout << "Данные добавлены в файл." << endl;
}

// Функция для загрузки данных из файла в вектор
vector<Product> loadProductsFromFile(const string& filename) {
    vector<Product> products;
    ifstream file(filename);
    if (!file.is_open()) {
        cerr << "Ошибка открытия файла!" << endl;
        return products; // Возвращаем пустой вектор при ошибке
    }
    string line;
    while (getline(file, line)) {
        if (line.empty()) {
            continue; // Пропускаем пустые строки
        }

        stringstream ss(line);
        Product product;
        ss >> quoted(product.name) >> quoted(product.type) >> product.quantity >> product.price;
        getline(ss >> ws, product.status); // Получаем статус, пропуская ведущие пробелы
        products.push_back(product);
    }
    file.close();
    return products;
}

// Функция для сохранения данных в файл
void saveProductsToFile(const string& filename, const vector<Product>& products) {
    ofstream file(filename);
    if (!file.is_open()) {
        cerr << "Ошибка открытия файла!" << endl;
        return;
    }
    for (const Product& product : products) {
        file << quoted(product.name) << " "
             << quoted(product.type) << " "
             << product.quantity << " "
             << product.price << " "
             << product.status
             << endl;
    }
    file.close();
}

// Функция для линейного поиска
void linearSearch(const vector<Product>& products, double price) {
    bool found = false;
    for (int i = 0; i < products.size(); i++) {
        if (products[i].price == price) {
            cout << "Продукт найден: " << products[i].name << endl;
            found = true;
        }
    }
    if (!found) {
        cout << "Продукт не найден." << endl;
    }
}

// Функция для сортировки выбором
void selectionSort(vector<Product>& products) {
    int n = products.size();
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (products[j].price < products[minIndex].price) {
                minIndex = j;
            }
        }
        swap(products[i], products[minIndex]);
    }
}

// Функция для разбиения в QuickSort
int partition(vector<Product>& products, int low, int high) {
    double pivot = products[high].price;
    int i = (low - 1);
    for (int j = low; j < high; j++) {
        if (products[j].price <= pivot) {
            i++;
            swap(products[i], products[j]);
        }
    }
    swap(products[i + 1], products[high]);
    return (i + 1);
}

// Функция для QuickSort
void quickSort(vector<Product>& products, int low, int high) {
    if (low < high) {
        int pi = partition(products, low, high);
        quickSort(products, low, pi - 1);
        quickSort(products, pi + 1, high);
    }
}

// Функция для двоичного поиска
vector<int> binarySearch(const vector<Product>& products, double price, int low, int high) {
    vector<int> indices;
    while (high >= low) {
        int mid = low + (high - low) / 2;
        if (products[mid].price == price) {
            int left = mid;
            while (left >= low && products[left].price == price) {
                indices.push_back(left);
                left--;
            }
            int right = mid + 1;
            while (right <= high && products[right].price == price) {
                indices.push_back(right);
                right++;
            }
            return indices;
        }
        if (products[mid].price > price) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return indices;
}

// Функция для вывода информации о продуктах по типу и статусу
void printProductsByTypeAndStatus(const vector<Product>& products, const string& type, const string& status) {
    for (const Product& product : products) {
        if (product.type == type && product.status == status) {
            cout << product.name << " | " << product.type << " | "
                 << product.quantity << " | " << product.price << " | "
                 << product.status << endl;
        }
    }
}

// функция удаления
void deleteProductsByName(const string& filename, const string& productName) {
    vector<Product> products = loadProductsFromFile(filename);
    products.erase(remove_if(products.begin(), products.end(),
                             [&productName](const Product& product) {
                                 return product.name == productName;
                             }), products.end());
    saveProductsToFile(filename, products);
    cout << "Продукты с именем '" << productName << "' удалены (если найдены)." << endl;
}


int main() {
    string filename = "/Users/katusarublevsk/Desktop/BSUIR/ОАИП/Mycursach/cmake-build-debug/products.txt";
    int choice;

    while (true) {
        cout << "\nМеню:\n";
        cout << "1. Создать файл\n";
        cout << "2. Просмотр файла\n";
        cout << "3. Добавить данные\n";
        cout << "4. Удаление элементов\n";
        cout << "5. Сортировка выбором\n";
        cout << "6. Сортировка QuickSort\n";
        cout << "7. Двоичный поиск\n";
        cout << "8. Вывести продукты по типу и статусу\n";
        cout << "9. Линейный поиск\n";
        cout << "0. Выход\n";
        cout << "Ваш выбор: ";
        cin >> choice;
        if (choice == 0) {
            break;
        }

        switch (choice) {
            case 1: createFile(filename); break;
            case 2: viewFile(filename); break;
            case 3: addData(filename); break;
            case 4: {
                string productName;
                cin.ignore();
                cout << "Введите название продукта, который хотите удалить: ";
                getline(cin, productName);
                deleteProductsByName(filename, productName);
                break;
            }
            case 5: {
                vector<Product> products = loadProductsFromFile(filename);
                selectionSort(products);
                saveProductsToFile(filename, products);
                cout << "Данные отсортированы выбором." << endl;
                break;
            }
            case 6: {
                vector<Product> products = loadProductsFromFile(filename);
                quickSort(products, 0, products.size() - 1);
                saveProductsToFile(filename, products);
                cout << "Данные отсортированы QuickSort." << endl;
                break;
            }
            case 7: {
                vector<Product> products = loadProductsFromFile(filename);
                // Сначала сортируем для двоичного поиска
                quickSort(products, 0, products.size() - 1);
                double price;
                cout << "Введите цену для поиска: "; cin >> price;
                vector<int> indices = binarySearch(products, price, 0, products.size() - 1);
                if (!indices.empty()) {
                    cout << "Найдены продукты с ценой " << price << ":" << endl;
                    for (int index : indices) {
                        cout << "Продукт найден: " << products[index].name << endl;
                    }
                } else {
                    cout << "Продукт не найден." << endl;
                }
                break;
            }
            case 8: {
                string type, status;
                cin.ignore(); // Очищаем буфер ввода перед считыванием строк
                cout << "Введите тип продукта: ";
                getline(cin, type);
                cout << "Введите статус продукта: ";
                getline(cin, status);
                vector<Product> products = loadProductsFromFile(filename);
                printProductsByTypeAndStatus(products, type, status);
                break;
            }
            case 9: {
                double price;
                cout << "Введите цену для поиска: "; cin >> price;
                vector<Product> products = loadProductsFromFile(filename);
                linearSearch(products, price);
                break;
            }
            default: cout << "Неверный выбор!" << endl;
        }
    }

    return 0;
}

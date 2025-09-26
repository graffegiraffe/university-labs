#include <iostream>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <unordered_map>
#include <locale>

using namespace std;

// Функция генерации случайной строки
wstring generate_random_string(int length, const wstring& alphabet) {
    wstring result;
    result.reserve(length); // Резервируем память для строки
    int alphabet_size = alphabet.size();

    srand(time(0));
    for (int i = 0; i < length; ++i) {
        result += alphabet[rand() % alphabet_size];
    }

    return result;
}

// Функция для вычисления частотного распределения символов
unordered_map<wchar_t, int> calculate_frequency(const wstring& str) {
    unordered_map<wchar_t, int> frequency;
    for (wchar_t ch : str) {
        frequency[ch]++;
    }
    return frequency;
}

// Функция для визуализации частотного распределения
void visualize_frequency(const unordered_map<wchar_t, int>& frequency) {
    wcout << L"\nЧастотное распределение символов:\n";
    for (const auto& pair : frequency) {
        wcout << pair.first << L": " << wstring(pair.second, L'*')
              << L" (" << pair.second << L")\n";
    }
}

// Функция для подбора пароля
void generate_combinations(const wstring& alphabet, const wstring& password) {
    int length = password.size();
    int alphabet_size = alphabet.size();

    vector<int> indices(length, 0);
    long long total_combinations = 1;
    for (int i = 0; i < length; ++i) {
        total_combinations *= alphabet_size;
    }

    clock_t start_time = clock();

    for (long long i = 0; i < total_combinations; ++i) {
        wstring str;
        bool is_match = true;

        for (int j = 0; j < length; ++j) {
            str += alphabet[indices[j]];
            if (str[j] != password[j]) {
                is_match = false;
                break;
            }
        }

        if (is_match) {
            clock_t end_time = clock();
            double time_taken = double(end_time - start_time) / CLOCKS_PER_SEC;

            wcout << L"Пароль подобран: " << str << endl;
            wcout << L"Время, затраченное на подбор: " << time_taken << L" секунд\n";
            return;
        }

        for (int j = length - 1; j >= 0; --j) {
            if (++indices[j] < alphabet_size) break;
            indices[j] = 0;
        }
    }
}

int main() {
    wcin.imbue(locale());
    wcout.imbue(locale());

    int string_length;
    wstring alphabet = L"абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    wcout << L"Введите длину строки: ";
    wcin >> string_length;

    wstring random_string = generate_random_string(string_length, alphabet);
    wcout << L"\nСгенерированная строка: " << random_string << endl;

    auto frequency = calculate_frequency(random_string);
    visualize_frequency(frequency);

    wstring password;
    wcout << L"\nВведите пароль для вычисления времени подбора: ";
    wcin >> password;

    generate_combinations(alphabet, password);

    return 0;
}


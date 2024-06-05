#include <iostream>
#include <vector>
/*#include <stack>
using namespace std;
int main() {

    vector<int> List = {3, 8, 4, 12, 6, 15, 9, 11};


    stack<int> elementsAfterMax;

    int maxElement = List[0];
    size_t maxIndex = 0;
    for (size_t i = 1; i < List.size(); ++i) {
        if (List[i] > maxElement) {
            maxElement = List[i];
            maxIndex = i;
        }
    }

    for (size_t i = maxIndex + 1; i < List.size(); ++i) {
        elementsAfterMax.push(List[i]);
    }


    cout << "Элементы между максимальным элементом и вершиной: ";
    while (!elementsAfterMax.empty()) {
        cout << elementsAfterMax.top() << " ";
        elementsAfterMax.pop();
    }
        cout << endl;

    return 0;
}*/

#include <cstdlib> // для rand()
#include <ctime>

using namespace std;

struct Stack { //Stack: Это структура, представляющая узел стека. Она содержит целочисленное значение info,
    int info; //что хранится в узле
    Stack* next; //указывающий на следующий элемент стека.
} *begins; //Это указатель на вершину стека.

Stack* InStack(Stack* p, int in) { //функция добавления узлов которая принимает два параметра:
    // указатель p на вершину стека и целочисленное значение in, которое будет добавлено в новый узел стека.
    auto* t = new Stack;
    t->info = in; //присваивается значение in полю info в новом узле стека t.
    t->next = p;
    return t;
}

void ViewReverse(Stack* p) { //для вывода
    Stack* t = p;
    while (t != nullptr)
    {
        cout << t->info << " ";
        t = t->next;
    }
}

void Del_All(Stack** p) {
    Stack* t;
    while (*p != nullptr) {
        t = *p;
        *p = (*p)->next;
        delete t;
    }
}

int main() {
    setlocale(LC_ALL, "Russian");
    srand(static_cast<unsigned int>(time(nullptr)));
    int size = rand() % 9 + 2;
    for (int i = 0; i < size; i++) {
        begins = InStack(begins, -20 + rand() % 40 + 1);
    }
    cout << "Исходный стек: " << endl;
    ViewReverse(begins);

    // Находим максимальный элемент
    int max = begins->info; //Здесь устанавливается начальное значение переменной max равным значению информационного поля (info) вершины стека (begins).
    Stack* t = begins->next; //оздается указатель t, который инициализируется указателем на следующий элемент после вершины стека (begins).
    while (t != nullptr) {
        if (t->info > max) { //сли это значение больше, чем текущее максимальное значение max, то max обновляется на это значение.
            max = t->info;
        }
        t = t->next;
    }

    // Создаем новый стек для элементов между вершиной и максимальным элементом
    Stack* newStack = nullptr;
    t = begins;
    while (t->info != max) t = t->next;
    t = t->next;
    while (t->next != nullptr) {
        newStack = InStack(newStack, t->info);
        t = t->next;
    }

    cout << "\nНовый список между максимальным элементом вершиной и вершиной: " << endl;
    ViewReverse(newStack);

    // Освобождаем память
    Del_All(&begins);
    Del_All(&newStack);

    return 0;
}
#include <iostream>
#include <climits>
using namespace std;

struct My_List {
    int data;
    My_List* next, * prev;
} * l_begin = nullptr, * l_end = nullptr, * new_list_begin = nullptr, * new_list_end = nullptr;

void Create_List(My_List**, My_List**, int);
void Add_Element_begin(My_List**, My_List**, int);
void Add_Element_end(My_List**, My_List**, int);
void Print_List_begin(My_List**);
void Print_List_end(My_List**);
void Find_Max_And_Transfer(My_List**, My_List**);

void Create_List(My_List** b, My_List** e, int value) {
    My_List* t = new My_List;// Устанавливается значение поля data нового элемента списка равным значению, переданному в качестве аргумента функции.
    t->data = value;
    t->next = t->prev = nullptr;
    *b = *e = t; // Указатели на начало и конец списка (b и e) устанавливаются на новый элемент списка t.
    // Так как список содержит только один элемент, он одновременно является началом и концом списка.
}

void Add_Element_begin(My_List** b, My_List** e, int value) {
    My_List* t = new My_List;
    t->data = value;
    t->next = *b;
    t->prev = nullptr;
    if (*b != nullptr)
        (*b)->prev = t;
    *b = t; //Указатель на начало списка обновляется, чтобы указывать на новый элемент t, который теперь стал первым элементом списка.
    if (*e == nullptr)
        *e = t;
}

void Add_Element_end(My_List** b, My_List** e, int value) {
    My_List* t = new My_List;
    t->data = value;
    t->prev = *e;
    t->next = nullptr;
    if (*e != nullptr)
        (*e)->next = t;
    *e = t;
    if (*b == nullptr)
        *b = t;
}

void Print_List_begin(My_List** t) {
    if (*t == nullptr) {
        cout << "Очередь пуста!" << endl;
    }
    else {
        My_List* temp = *t;
        while (temp != nullptr) {
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << endl;
    }
}

void Print_List_end(My_List** t) {
    if (*t == nullptr) {
        cout << "Очередь пуста!" << endl;
    }
    else {
        My_List* temp = *t;
        while (temp != nullptr) {
            cout << temp->data << " ";
            temp = temp->prev;
        }
        cout << endl;
    }
}

void Find_Max_And_Transfer(My_List** b, My_List** e) {
    if (*b == nullptr) {
        cout << "Список пуст!" << endl;
        return;
    }
    My_List* temp = *b;
    int max_value = INT_MIN;
    while (temp != nullptr) {
        if (temp->data > max_value) {
            max_value = temp->data;
        }
        temp = temp->next;
    }

    // Переносим элементы между началом списка и максимальным элементом в новый список
    temp = *b;
    bool found_max = false;
    while (temp != nullptr) {
        if (!found_max) {
            if (temp->data == max_value) {
                found_max = true;
            }
        }
        else {
            Add_Element_end(&new_list_begin, &new_list_end, temp->data);
        }
        temp = temp->next;
    }

    cout << "Новый список (элементы между началом и максимальным): ";
    Print_List_begin(&new_list_begin);
}

int main() {

    int first_el;
    cout << "----------СОЗДАНИЕ СПИСКА---------- \nВведите первый элемент списка : ";
    cin >> first_el;
    Create_List(&l_begin, &l_end, first_el);
    Print_List_begin(&l_begin);
    int size_b;
    cout << "\n\n\n----------ЗАПОЛНЕНИЕ СПИСКА С НАЧАЛА---------- \nВведите количество новых элементов списка:";
    cin >> size_b;
    cout << "\nВведите элементы, которыми вы хотите заполнить список : \n";
    for (int i = 1; i <= size_b; i++) {
        int input;
        cin >> input;
        Add_Element_begin(&l_begin, &l_end, input);
    }
    cout << "\nПросмотр с начала: " << endl;
    Print_List_begin(&l_begin);
    cout << "Просмотр с конца: " << endl;
    Print_List_end(&l_end);

    int size_e;
    cout << "\n\n\n----------ЗАПОЛНЕНИЕ СПИСКА С КОНЦА---------- \n Введите количество новых элементов списка:";
    cin >> size_e;
    cout << "\nВведите элементы, которыми вы хотите заполнить список : \n";
    for (int i = 1; i <= size_e; i++) {
        int input;
        cin >> input;
        Add_Element_end(&l_begin, &l_end, input);
    }
    cout << "\nПросмотр с начала: " << endl;
    Print_List_begin(&l_begin);
    cout << "Просмотр с конца: " << endl;
    Print_List_end(&l_end);

    cout << "\n\n\n----------Перенос элементов между началом и максимальным---------- " << endl;
    Find_Max_And_Transfer(&l_begin, &l_end);

    return 0;
}


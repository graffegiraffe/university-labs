// создание
//добавление
// просмотр
//удаление всего дерева
// балансировка
// три обхода
// решение

#include <iostream>
#include <functional>
#include <string>
using namespace std;

struct TREE{
    string name;
    int info;
    TREE* left;
    TREE* right;
};

TREE* LIST(string na, int in) {
    TREE* t = new TREE;
    t->info = in;
    t->name = na;
    t->left = nullptr;
    t->right = nullptr;
    return t;
}

TREE* ADD(TREE* root, string na, int in) {
    if (root == nullptr) {
        root = LIST(na, in);
    }
    else if (in < root->info) {
        root->left = ADD(root->left, na, in);
    }
    else {
        root->right = ADD(root->right, na, in);
    }
    return root;
}

void printTree(TREE* root, int level) {
    if (root == nullptr) {
        return;
    }

    printTree(root->right, level + 1);

    for (int i = 0; i < level; ++i) {
       cout << "   ";
    }
 cout << root->name << " " << root->info << std::endl;

    printTree(root->left, level + 1);
}

void DEL(TREE* root) {
    if (root != nullptr) {
        DEL(root->right);
        DEL(root->left);
        delete root;
        root = nullptr;
    }
}

TREE* BALANCE(TREE* a[], int first, int last) {
    if (first > last) {
        return nullptr;
    }

    int m = (first + last) / 2;
    TREE* p = a[m];

    p->left = BALANCE(a, first, m - 1);
    p->right = BALANCE(a, m + 1, last);

    return p;
}

void UP_DOWN(TREE* root) {
    if (root != nullptr) {
        cout << root->name << " " << root->info << endl;
        UP_DOWN(root->left);
        UP_DOWN(root->right);
    }
}

void LEFT_RIGHT(TREE* root) {
    if (root != nullptr) {
        LEFT_RIGHT(root->left);
        cout << root->name << " " << root->info << endl;
        LEFT_RIGHT(root->right);
    }
}

void DOWN_UP(TREE* root) {
    if (root != nullptr) {
        DOWN_UP(root->left);
        DOWN_UP(root->right);
        cout << root->name << " " << root->info << endl;
    }
}

TREE* DEL_KEY(TREE* root, int in) {
    TREE* Del;
    TREE* Predok_Del;
    TREE* R;
    TREE* Predok_R;
    Del = root;
    Predok_Del = nullptr;
    while (Del != nullptr && Del->info != in) {
        Predok_Del = Del;
        if (Del->info > in) Del = Del->left;
        else Del = Del->right;
    }
    if (Del == nullptr) {
        cout << "Че с ключом?";
        return root;
    }
    if (Del->right == nullptr) R = Del->left;
    if (Del->left == nullptr) R = Del->right;
    else {
        Predok_R = Del;
        R = Del->left;
        while (R->right != NULL) {
            Predok_R = R;
            R = R->right;
        }
        if (Predok_R == Del) R->right = Del->right;
        else {
            R->right = Del->right;
            Predok_R->right = Del->left;
            R->left = Predok_R;
        }
    }
    if (Del == root) root = R;
    else
    if (Del->info < Predok_Del->info)
        Predok_Del->left = R;
    else Predok_Del->right = R;
    delete Del;
    return root;
}

void VIEW_IN(TREE* root, int in) {
    TREE* key = root;
    if (key == nullptr) {
        cout << "Че с ключом?";
    }
    while (key != NULL && key->info != in) {
        if (key->info > in) key = key->left;
        else key = key->right;
    }
    cout << key->name << " " << key->info;
}

int countNodesWithSingleChild(TREE* root) {
    if (root == nullptr) {
        return 0;
    }
    int count = 0;
    if ((root->left == nullptr && root->right != nullptr) || (root->left != nullptr && root->right == nullptr)) {
        count++;
    }
    count += countNodesWithSingleChild(root->left);
    count += countNodesWithSingleChild(root->right);
    return count;
}

int main() {
    setlocale(LC_ALL, "RU");

    TREE* root = nullptr;

    string data[][2] = { {"Smi", "8"}, {"Joh", "10"}, {"Wil", "14"}, {"Gig", "13"}, {"Smt", "3"}, {"Brt", "1"}, {"Kat", "6"}, {"Dsh", "4"}, {"Qwe", "7"} };

    for (auto& d : data) {
        root = ADD(root, d[0], stoi(d[1]));
    }

    cout << "Дерево: " << endl;
    printTree(root, 0);
    cout << endl;

    cout << "Добавление новой записи: Done!" << endl;
    root = ADD(root, "New", 11);
    root = ADD(root, "Yur", 9);
    cout << endl;

    cout << "Дерево: " << endl;
    printTree(root, 0);
    cout << endl;

    cout << "Поиск информации по заданному ключу:" << endl;
    VIEW_IN(root, 8);
    cout << endl;

    cout << "Удаление информации с заданным ключом: Done!" << endl;
    root = DEL_KEY(root, 10);
    cout << endl;

    cout << "Дерево в порядке возрастания:" << endl;
    LEFT_RIGHT(root);
    cout << endl;

    cout << "Просмотр дерева сверху вниз:" << endl;
    UP_DOWN(root);
    cout << endl;

    cout << "Просмотр дерева снизу вверх:" << endl;
    DOWN_UP(root);
    cout << endl;

    cout << "Решение: " << countNodesWithSingleChild(root);
    cout << endl;

    // Сохранение всех узлов в массиве
    TREE* nodes[100]; // Предполагаем максимальное количество узлов
    int index = 0;

    // Функция для сохранения всех узлов в массиве
    function<void(TREE*)> saveNodes = [&](TREE* node) {
        if (node == nullptr) return;

        saveNodes(node->left);
        nodes[index++] = node;
        saveNodes(node->right);
    };

    saveNodes(root);

    cout << "Сбалансированное дерево:" << endl;

    // Балансировка дерева
    root = BALANCE(nodes, 0, index - 1);

    cout << "Дерево: " << endl;
    printTree(root, 0);

    DEL(root);

    return 0;
}
//
// Created by Катюша Рублевская on 19.01.24.
//
#include <iostream>
#include "RBTree.h"
    //Инициализация пустой вершины с черным цветом
    //0 - Черный: 1 - Красный
    void RBTree::initializeNULLNode(NodePtr node, NodePtr parent) {
        node->data = 0;
        node->parent = parent;
        node->left = nullptr; //Устанавливают указатели на левого и правого потомков узла в nullptr, так как пустой узел не имеет потомков.
        node->right = nullptr;
        node->color = 0;
    }
    //Функция помогает найти нужную вершину по ключу (рекурсивно ищет узел с заданным ключом)
    NodePtr RBTree::searchTreeHelper(NodePtr node, int key) {
        if (node == TNULL ) {
            return NULL;
        }

        if (key == node->data) {
            return node;

        }

        if (key < node->data) {
            return searchTreeHelper(node->left, key);
        }
        return searchTreeHelper(node->right, key);
    }
    //Балансирует функцию после удаления вершины
    void RBTree::fixDelete(NodePtr x) {
        NodePtr s; //указатель s, который будет использоваться для представления соседнего узла (брата) узла x.
        while (x != root && x->color == 0) {
            //При левой части
            if (x == x->parent->left) {
                s = x->parent->right;
                if (s->color == 1) { // Проверяется цвет соседнего узла s. Если он красный, то это означает, что родительский узел x должен быть черным
                                      //  В этом случае происходит перекрашивание и вращение, чтобы восстановить баланс.
                    //  3.1
                    s->color = 0;
                    x->parent->color = 1;
                    leftRotate(x->parent);
                    s = x->parent->right;
                }

                if (s->left->color == 0 && s->right->color == 0) { //Это условие проверяет, являются ли оба потомка узла s черными.
                    // Если оба потомка черные, это означает, что у нас есть недостаток черных узлов на пути относительно узла x,
                    // и мы можем восстановить баланс, перекрасив s в красный и поднимаясь к родительскому узлу x.
                    // 3.2
                    s->color = 1;
                    x = x->parent;
                }
                else {
                    if (s->right->color == 0) { //Проверяем, является ли правый потомок s черным.
                        // Если да, это означает, что мы имеем красный левый потомок узла s.
                        //  3.3
                        s->left->color = 0;
                        s->color = 1;
                        rightRotate(s); //Выполняем правый поворот вокруг узла s, чтобы сбалансировать дерево.
                        s = x->parent->right; //обновляем указатель s, чтобы он указывал на новое правое поддерево родительского узла x.
                    }

                    //  3.4
                    s->color = x->parent->color; //Цвет узла s становится таким же, как у родительского узла x.
                    x->parent->color = 0;
                    s->right->color = 0;
                    leftRotate(x->parent);
                    x = root;
                }
            }
            else {
                //При правой части
                s = x->parent->left;
                if (s->color == 1) {
                    //  3.1
                    s->color = 0;
                    x->parent->color = 1;
                    rightRotate(x->parent);
                    s = x->parent->left;
                }

                if (s->right->color == 0 && s->right->color == 0) {
                    //  3.2
                    s->color = 1;
                    x = x->parent;
                }
                else {
                    if (s->left->color == 0) {
                        //  3.3
                        s->right->color = 0;
                        s->color = 1;
                        leftRotate(s);
                        s = x->parent->left;
                    }

                    //  3.4
                    s->color = x->parent->color;
                    x->parent->color = 0;
                    s->left->color = 0;
                    rightRotate(x->parent);
                    x = root;
                }
            }
        }
        x->color = 0;
    }
    //Меняет местами две вершины (выполняет замену поддерева, управляемого узлом u, поддеревом, управляемым узлом v)
    void RBTree::rbTransplant(NodePtr u, NodePtr v) {
        if (u->parent == nullptr) {
            root = v;
        }
        else if (u == u->parent->left) {
            u->parent->left = v;
        }
        else {
            u->parent->right = v;
        }
        v->parent = u->parent;
    }
    //Помогает удалить определенную вершину перед балансировкой
    void RBTree::deleteNodeHelper(NodePtr node, int key) {

        NodePtr z = TNULL;
        NodePtr x, y;
        while (node != TNULL) {
            if (node->data == key) {
                z = node;
            }

            if (node->data <= key) {
                node = node->right;
            }
            else {
                node = node->left;
            }
        }

        if (z == TNULL) {
            cout << "Couldn't find key in the tree" << endl;
            return;
        }

        y = z;
        int y_original_color = y->color;
        if (z->left == TNULL) { //Этот блок кода определяет, какой тип потомков есть у узла z.
            // Если у узла нет левого потомка, то x присваивается правый потомок z. Если у узла нет правого потомка, то x присваивается левый потомок z
            x = z->right;
            rbTransplant(z, z->right);
        }
        else if (z->right == TNULL) {
            x = z->left;
            rbTransplant(z, z->left);
        }
        else {
            y = minimum(z->right);// В этой строке переменной y присваивается минимальный узел из правого поддерева узла z.
            // Это делается для того, чтобы найти наименьший узел, который будет являться следующим после узла z в порядке возрастания ключей.
            y_original_color = y->color;
            x = y->right;
            if (y->parent == z) { //В этой части кода мы работаем с поддеревом, которое будет заменять удаляемый узел z.
                // Если у узла y нет левого потомка, то мы просто присваиваем правого потомка узла y переменной x.
                //Если у узла y есть левый потомок, то мы используем функцию rbTransplant для замены узла y его правым потомком.
                // Затем правый потомок узла y становится правым потомком узла z.
                x->parent = y;
            }
            else {
                rbTransplant(y, y->right);
                y->right = z->right;
                y->right->parent = y;
            }

            rbTransplant(z, y); //В этой части кода происходит замена узла z узлом y. Мы используем функцию rbTransplant для переноса
            // узла y в позицию узла z.Затем левый потомок узла z становится левым потомком узла y. При этом обновляются ссылки на родителей.
            //Цвет узла y устанавливается равным цвету узла z. Это важно для сохранения инвариантов красно-черного дерева после удаления узла
            y->left = z->left;
            y->left->parent = y;
            y->color = z->color;
        }
        delete z;
        if (y_original_color == 0) {
            fixDelete(x);
        }
    }
    //Балансирует дерево после добавления вершины
    void RBTree::fixInsert(NodePtr k) {
        NodePtr u;
        while (k->parent->color == 1) {
            if (k->parent == k->parent->parent->right) {
                //Для правой стороны
                u = k->parent->parent->left; //проверяется, находится ли узел k справа от своего родителя k->parent.
                // Это важно для определения, находится ли узел k в левом или правом поддереве своего дедушки k->parent->parent.
                //Переменная u инициализируется узлом, который является левым потомком дедушки k->parent->parent.
                //Если узел u (левый потомок дедушки) красный, мы выполняем операции 3.1, перекрашивая узлы и перемещая указатель k на дедушку.
                if (u->color == 1) {
                    // 3.1
                    u->color = 0;
                    k->parent->color = 0;
                    k->parent->parent->color = 1;
                    k = k->parent->parent;
                }
                else {
                    if (k == k->parent->left) {
                        // 3.2.2
                        k = k->parent;
                        rightRotate(k);
                    }
                    // 3.2.1
                    k->parent->color = 0;
                    k->parent->parent->color = 1;
                    leftRotate(k->parent->parent);
                }
            }
            else {
                //для левой стороны
                u = k->parent->parent->right;

                if (u->color == 1) {
                    // 3.1
                    u->color = 0;
                    k->parent->color = 0;
                    k->parent->parent->color = 1;
                    k = k->parent->parent;
                }
                else {
                    if (k == k->parent->right) {
                        // 3.2.2
                        k = k->parent;
                        leftRotate(k);
                    }
                    // 3.2.1
                    k->parent->color = 0;
                    k->parent->parent->color = 1;
                    rightRotate(k->parent->parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root->color = 0;
    }
    //Красиво оформляет дерево
    void RBTree::printHelper(NodePtr root, string indent, bool last) {
        if (root != TNULL) {
            cout << indent;
            if (last) {
                cout << "R----";
                indent += "     ";
            }
            else {
                cout << "L----";
                indent += "|    ";
            }

            string sColor = root->color ? "RED" : "BLACK";
            cout << root->data << "(" << sColor << ")" << endl;
            printHelper(root->left, indent, false); //что левое поддерево не является последним на текущем уровне
            printHelper(root->right, indent, true);
        }
    }
    //доступ к корню
    NodePtr RBTree::getRoot() {
        return root;
    }
    //конструктор
    RBTree::RBTree() {
        TNULL = new Node;
        TNULL->color = 0;
        TNULL->left = nullptr;
        TNULL->right = nullptr;
        root = TNULL;
    }
    //Начальная функция поиска вершины
    NodePtr RBTree::searchTree(int k) {
        return searchTreeHelper(this->root, k);
    }
    //Ищет минимальную вершину для данной
    NodePtr RBTree::minimum(NodePtr node) {
        while (node->left != TNULL) {
            node = node->left;
        }
        return node;
    }
    //ищет максимальную вершину для данной
    NodePtr RBTree::maximum(NodePtr node) {
        while (node->right != TNULL) {
            node = node->right;
        }
        return node;
    }
    // находит преемника для вершины
    // елси правое поддерево непустое, то преемник - самый левый узел в правом поддереве
    NodePtr RBTree::successor(NodePtr x) {

        if (x->right != TNULL) {
            return minimum(x->right);
        }

        NodePtr y = x->parent;
        while (y != TNULL && x == y->right) {
            x = y;
            y = y->parent;
        }
        return y;
    }
    //находит предшественника для вершины
    //если левое поддерево непустое, то предшественник - самый правый в левом поддереве
    NodePtr RBTree::predecessor(NodePtr x) {

        if (x->left != TNULL) {
            return maximum(x->left);
        }

        NodePtr y = x->parent;
        while (y != TNULL && x == y->left) {
            x = y;
            y = y->parent;
        }

        return y;
    }
    //Делает левый поворот от данной вершины
    void RBTree::leftRotate(NodePtr x) {
        NodePtr y = x->right; //останавливается указатель y на правого потомка узла x,
        // который будет поднят на уровень узла x в результате левого поворота.
        x->right = y->left; //Поддерево y становится новым правым поддеревом для узла x.
        if (y->left != TNULL) {
            y->left->parent = x; //Если у узла y есть левый потомок, то устанавливается связь обратного указателя на узел x
        }
        y->parent = x->parent;
        if (x->parent == nullptr) {
            this->root = y; //Если узел x был корнем дерева, то устанавливается новый корень y.
        }
        else if (x == x->parent->left) {
            x->parent->left = y;
        }
        else {
            x->parent->right = y;
        }
        y->left = x;
        x->parent = y;
    }
    //Делает правый поворот от данной вершины
    void RBTree::rightRotate(NodePtr x) {
        NodePtr y = x->left;
        x->left = y->right;
        if (y->right != TNULL) {
            y->right->parent = x;
        }
        y->parent = x->parent;
        if (x->parent == nullptr) {
            this->root = y;
        }
        else if (x == x->parent->right) {
            x->parent->right = y;
        }
        else {
            x->parent->left = y;
        }
        y->right = x;
        x->parent = y;
    }
    //Основная функция добавления вершины
    void RBTree::insert(int key) {
        NodePtr node = new Node;
        node->parent = nullptr;
        node->data = key;
        node->left = TNULL;
        node->right = TNULL;
        node->color = 1;

        NodePtr y = nullptr;
        NodePtr x = this->root;

        while (x != TNULL) {
            y = x;
            if (node->data < x->data) {
                x = x->left;
            }
            else {
                x = x->right;
            }
        }

        //y - родитель x
        node->parent = y;
        if (y == nullptr) {
            root = node;
        }
        else if (node->data < y->data) {
            y->left = node;
        }
        else {
            y->right = node;
        }

        //Если новая вершина - корень, то просто завершаем
        if (node->parent == nullptr) {
            node->color = 0;
            return;
        }

        //Если дед пустой, то просто завершаем
        if (node->parent->parent == nullptr) {
            return;
        }

        //Балансировка
        fixInsert(node);
    }
    //Основная функция удаления вершины
    void RBTree::deleteNode(int data) {
        deleteNodeHelper(this->root, data);
    }
    //Вывод дерева на экран
    void RBTree::prettyPrint() {
        if (root) {
            printHelper(this->root, "", true);
        }
    }
    NodePtr RBTree::findClosestGreater(int key) {
        NodePtr closestGreater = nullptr; //closestGreater, который изначально устанавливается в nullptr.
        // Этот указатель будет использоваться для отслеживания ближайшего узла, ключ которого больше, чем key
        NodePtr current = root;

        while (current != TNULL) {
            if (current->data > key) {
                closestGreater = current;
                current = current->left; //Если ключ текущего узла больше заданного key, это означает, что текущий узел
                // может быть потенциальным кандидатом на ближайший больший ключ. Поэтому closestGreater устанавливается
                // на текущий узел, и поиск продолжается в левом поддереве, потому что ключи в левом поддереве меньше текущего ключа.
            } else {
                current = current->right;
            }
        }

        return closestGreater;
    }

    NodePtr RBTree::findClosestSmaller(int key) {
        NodePtr closestSmaller = nullptr;
        NodePtr current = root;

        while (current != TNULL) {
            if (current->data < key) {
                closestSmaller = current;
                current = current->right;
            } else {
                current = current->left;
            }
        }

        return closestSmaller;
    }

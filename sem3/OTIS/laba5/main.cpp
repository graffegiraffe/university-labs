#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include "Graph.h"
using namespace std;

int main() {
    setlocale(LC_ALL, "Russian");
    int V, E;
    cout << "Введите количество вершин: ";
    cin >> V;
    cout << "Введите количество ребер: ";
    cin >> E;

    Graph graph(V);

    cout << "Введите ребра (формат: вершина1 вершина2):" << endl;
    for (int i = 0; i < E; ++i) {
        int src, dest;
        cin >> src >> dest;
        graph.addEdge(src, dest);
    }

    int choice;
    do {
        cout << "\nМеню:" << endl;
        cout << "1. Отобразить граф" << endl;
        cout << "2. Проверить, является ли граф деревом" << endl;
        cout << "3. Преобразовать в двоичное дерево" << endl;
        cout << "4. Преобразовать в обычное дерево" << endl;
        cout << "5. Найти гамильтоновы циклы" << endl;
        cout << "6. Вычислить диаметр графа" << endl;
        cout << "7. Вычислить радиус графа" << endl;
        cout << "8. Найти центр графа" << endl;
        cout << "9. Вычислить тензорное произведение" << endl;
        cout << "10. Вычислить декартово произведение" << endl;
        cout << "11. Добавить петлю" << endl;
        cout << "12. Объединение графов" << endl;
        cout << "13. Пересечение графов" << endl;
        cout << "14. Дополнение графа 1" << endl;
        cout << "0. Выход" << endl;
        cout << "Ваш выбор: ";
        cin >> choice;

        switch (choice) {
            case 1:
                graph.printGraph();
                break;
            case 2:
                cout << (graph.isTree() ? "Граф является деревом" : "Граф не является деревом") << endl;
                break;
            case 3: {
                Graph binaryTree = graph.toBinaryTree();
                cout << "Двоичное дерево:" << endl;
                binaryTree.printGraph();
                break;
            }
            case 4: {
                Graph tree = graph.toTree();
                cout << "Обычное дерево:" << endl;
                tree.printGraph();
                break;
            }
            case 5:
                graph.findHamiltonianCycles();
                break;
            case 6:
                cout << "Диаметр графа: " << graph.diameter() << endl;
                break;
            case 7:
                cout << "Радиус графа: " << graph.radius() << endl;
                break;
            case 8: {
                vector<int> center = graph.center();
                cout << "Центр графа: ";
                for (int c : center) {
                    cout << c << " ";
                }
                cout << endl;
                break;
            }
            case 9: {
                cout << "Введите количество вершин второго графа: ";
                int V2;
                cin >> V2;
                cout << "Введите количество ребер второго графа: ";
                int E2;
                cin >> E2;

                Graph otherGraph(V2);
                cout << "Введите ребра второго графа (формат: вершина1 вершина2):" << endl;
                for (int i = 0; i < E2; ++i) {
                    int src, dest;
                    cin >> src >> dest;
                    otherGraph.addEdge(src, dest);
                }

                Graph tensorProduct = graph.tensorProduct(otherGraph);
                cout << "Тензорное произведение:" << endl;
                tensorProduct.printGraph();
                break;
            }
            case 10: {
                cout << "Введите количество вершин второго графа: ";
                int V2;
                cin >> V2;
                cout << "Введите количество ребер второго графа: ";
                int E2;
                cin >> E2;

                Graph otherGraph(V2);
                cout << "Введите ребра второго графа (формат: вершина1 вершина2):" << endl;
                for (int i = 0; i < E2; ++i) {
                    int src, dest;
                    cin >> src >> dest;
                    otherGraph.addEdge(src, dest);
                }

                Graph cartesianProduct = graph.cartesianProduct(otherGraph);
                cout << "Декартово произведение:" << endl;
                cartesianProduct.printGraph();
                break;
            }
            case 11: {
                int loopVertex;
                cout << "Введите вершину для добавления петли: ";
                cin >> loopVertex;
                graph.addLoop(loopVertex);
                cout << "Петля добавлена." << endl;
                break;
            }// с этого момента не работает
            case 12: {
                // Ввод данных для второго графа
                cout << "Введите количество вершин второго графа: ";
                int V2;
                cin >> V2;
                cout << "Введите количество рёбер второго графа: ";
                int E2;
                cin >> E2;

                Graph otherGraph(V2);
                cout << "Введите рёбра второго графа (формат: вершина1 вершина2):" << endl;
                for (int i = 0; i < E2; ++i) {
                    int src, dest;
                    cin >> src >> dest;
                    otherGraph.addEdge(src, dest);
                }
                // Объединение графов
                Graph unionGraph = graph.unionWith(otherGraph); // Используем otherGraph
                cout << "Объединение графов:" << endl;
                unionGraph.printGraph();
                break;
            }
            case 13: {
                // Ввод данных для второго графа
                cout << "Введите количество вершин второго графа: ";
                int V2;
                cin >> V2;
                cout << "Введите количество рёбер второго графа: ";
                int E2;
                cin >> E2;

                Graph otherGraph(V2);
                cout << "Введите рёбра второго графа (формат: вершина1 вершина2):" << endl;
                for (int i = 0; i < E2; ++i) {
                    int src, dest;
                    cin >> src >> dest;
                    otherGraph.addEdge(src, dest);
                }
                Graph intersectionGraph = graph.intersectionWith(otherGraph);
                cout << "Пересечение графов:" << endl;
                intersectionGraph.printGraph();
                break;
            }
            case 14: {
                Graph complementGraph = graph.complement();
                cout << "Дополнение графа:" << endl;
                complementGraph.printGraph();
                break;
            }
            case 0:
                cout << "Выход..." << endl;
                break;
            default:
                cout << "Неверный выбор!" << endl;
        }
    } while (choice != 0);

    return 0;
}
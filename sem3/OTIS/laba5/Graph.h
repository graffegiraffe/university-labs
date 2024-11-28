//
// Created by Катюша Рублевская on 14.10.24.
//

#ifndef LABA5_GRAPH_H
#define LABA5_GRAPH_H

#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <limits>
#include <set>

using namespace std;
// Структура для представления ребра
//struct Edge {
  //  int src, dest;
//};

// Класс для представления графа
class Graph {
public:
    // Объединение графов
    Graph unionWith(const Graph &other) const;

    // Пересечение графов
    Graph intersectionWith(const Graph &other) const;

    // Дополнение графа
    Graph complement() const;
    void printGraph() const;
    int V; // Количество вершин
    vector<vector<int>> adj; // Список смежности

    // Конструктор
    Graph(int V) : V(V), adj(V) {}

    // Добавление ребра
    void addEdge(int src, int dest) {
        adj[src].push_back(dest);
        adj[dest].push_back(src); // Для неориентированного графа
    }

    // Добавление петли
    void addLoop(int v) {
        adj[v].push_back(v);
    }

    // Проверка, является ли граф деревом
    bool isTree();
    // Преобразование в двоичное дерево
    Graph toBinaryTree();

    // Преобразование в обычное дерево
    Graph toTree();

    // Поиск гамильтоновых циклов
    void findHamiltonianCycles();

    // Вычисление диаметра графа
    int diameter();

    // Вычисление радиуса графа
    int radius();

    // Поиск центра графа
    vector<int> center();

    // Тензорное произведение двух графов
    Graph tensorProduct(const Graph &other);

    // Декартово произведение двух графов
    Graph cartesianProduct(const Graph &other);
    // Вспомогательная функция для findHamiltonianCycles()
    bool isSafe(int v, int pos, vector<int> &path, vector<bool> &visited);

private:
    // Вспомогательные функции для isTree()
    bool isCyclicUtil(int v, vector<bool> &visited, int parent);

    // Вспомогательная функция для diameter()
    pair<int, int> bfs(int src);

};

#endif //LABA5_GRAPH_H

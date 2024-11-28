//
// Created by Катюша Рублевская on 14.10.24.
//


#include "Graph.h"
// Функция для вывода графа
void Graph::printGraph() const { // Удаляем параметр graph
    for (int v = 0; v < V; ++v) { // Обращаемся к V напрямую
        cout << "Вершина " << v << ": ";
        for (int neighbor : adj[v]) { // Обращаемся к adj напрямую
            cout << neighbor << " ";
        }
        cout << endl;
    }
}
// Объединение графов
Graph Graph::unionWith(const Graph &other) const {
    if (V != other.V) {
        cerr << "Ошибка: нельзя объединить графы с разным количеством вершин." << endl;
        return Graph(0);
    }

    Graph result(V);
    for (int u = 0; u < V; ++u) {
        // Используем set для избежания дубликатов ребер
        set<int> neighbors(adj[u].begin(), adj[u].end());
        neighbors.insert(other.adj[u].begin(), other.adj[u].end());

        for (int v : neighbors) {
            result.addEdge(u, v);
        }
    }
    return result;
}

// Пересечение графов
Graph Graph::intersectionWith(const Graph &other) const {
    if (V != other.V) {
        cerr << "Ошибка: нельзя найти пересечение графов с разным количеством вершин." << endl;
        return Graph(0);
    }

    Graph result(V);
    for (int u = 0; u < V; ++u) {
        for (int v : adj[u]) {
            // Ребро присутствует в обоих графах
            if (find(other.adj[u].begin(), other.adj[u].end(), v) != other.adj[u].end()) {
                result.addEdge(u, v);
            }
        }
    }
    return result;
}
// Дополнение графа
Graph Graph::complement() const {
    Graph result(V);
    for (int u = 0; u < V; ++u) {
        vector<bool> connected(V, false);
        for (int v : adj[u]) {
            connected[v] = true;
        }
        for (int v = 0; v < V; ++v) {
            if (u != v && !connected[v]) {
                result.addEdge(u, v);
            }
        }
    }
    return result;
}
// Проверка на цикличность с помощью DFS
bool Graph::isCyclicUtil(int v, vector<bool> &visited, int parent) {
    visited[v] = true;

    for (int i = 0; i < adj[v].size(); ++i) {
        int u = adj[v][i];

        if (!visited[u]) {
            if (isCyclicUtil(u, visited, v))
                return true;
        } else if (u != parent)
            return true;
    }
    return false;
}

// Проверка, является ли граф деревом
bool Graph::isTree() {
    vector<bool> visited(V, false);

    // Проверка на цикличность
    if (isCyclicUtil(0, visited, -1))
        return false;

    // Проверка на связность
    for (int u = 0; u < V; u++)
        if (!visited[u])
            return false;

    return true;
}

// Преобразование в двоичное дерево (приблизительное)
Graph Graph::toBinaryTree() {
    // Создаем копию графа
    Graph tree(V);
    for (int u = 0; u < V; u++) {
        for (int v : adj[u]) {
            tree.addEdge(u, v);
        }
    }

    // Удаляем ребра, чтобы получить структуру двоичного дерева
    for (int u = 0; u < V; u++) {
        if (tree.adj[u].size() > 2) {
            // Оставляем только два первых соседа
            tree.adj[u].erase(tree.adj[u].begin() + 2, tree.adj[u].end());
        }
    }

    return tree;
}

// Преобразование в обычное дерево (остовное дерево)
Graph Graph::toTree() {
    vector<bool> visited(V, false);
    vector<int> parent(V, -1);
    queue<int> q;

    q.push(0);
    visited[0] = true;

    while (!q.empty()) {
        int u = q.front();
        q.pop();

        for (int v : adj[u]) {
            if (!visited[v]) {
                visited[v] = true;
                parent[v] = u;
                q.push(v);
            }
        }
    }

    // Создаем дерево на основе родительских связей
    Graph tree(V);
    for (int i = 1; i < V; ++i) {
        if (parent[i] != -1) {
            tree.addEdge(parent[i], i);
        }
    }

    return tree;
}

// Проверка, можно ли добавить вершину v в гамильтонов цикл
bool Graph::isSafe(int v, int pos, vector<int> &path, vector<bool> &visited) {
    if (pos == 0) return true; // Первую вершину всегда можно добавить
    if (find(adj[path[pos - 1]].begin(), adj[path[pos - 1]].end(), v) ==
        adj[path[pos - 1]].end())
        return false; // Проверяем смежность
    if (visited[v]) return false; // Проверяем, посещена ли вершина
    return true;
}

// Рекурсивная функция для поиска гамильтоновых циклов
bool hamiltonianUtil(Graph &g, int pos, vector<int> &path, vector<bool> &visited) {
    if (pos == g.V) {
        // Если все вершины добавлены, проверяем, образуют ли они цикл
        if (find(g.adj[path[pos - 1]].begin(), g.adj[path[pos - 1]].end(), path[0]) !=
            g.adj[path[pos - 1]].end())
            return true;
        else
            return false;
    }

    for (int v = 1; v < g.V; v++) {
        if (g.isSafe(v, pos, path, visited)) {
            path[pos] = v;
            visited[v] = true;

            if (hamiltonianUtil(g, pos + 1, path, visited))
                return true;

            path[pos] = -1;
            visited[v] = false;
        }
    }
    return false;
}

// Поиск гамильтоновых циклов
void Graph::findHamiltonianCycles() {
    vector<int> path(V, -1);
    vector<bool> visited(V, false);

    path[0] = 0;
    visited[0] = true;

    cout << "Гамильтоновы циклы:" << endl;
    if (hamiltonianUtil(*this, 1, path, visited)) {
        for (int i = 0; i < V; i++)
            cout << path[i] << " ";
        cout << path[0] << endl;
    } else {
        cout << "Гамильтоновых циклов нет" << endl;
    }
}

// Вспомогательная функция для вычисления расстояния от заданной вершины до всех остальных
pair<int, int> Graph::bfs(int src) {
    vector<int> dist(V, INT_MAX);
    queue<int> q;

    dist[src] = 0;
    q.push(src);

    int lastNode = src; // Запоминаем последний посещенный узел
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        lastNode = u;

        for (int v : adj[u]) {
            if (dist[v] == INT_MAX) {
                dist[v] = dist[u] + 1;
                q.push(v);
            }
        }
    }

    // Возвращаем последний посещенный узел и его расстояние от исходного
    return make_pair(lastNode, dist[lastNode]);
}

// Вычисление диаметра графа
int Graph::diameter() {
    int farthestNode = 0;
    int maxDist = 0;

    // Запускаем BFS из каждой вершины, чтобы найти самый удаленный узел
    for (int i = 0; i < V; i++) {
        pair<int, int> result = bfs(i);
        if (result.second > maxDist) {
            farthestNode = result.first;
            maxDist = result.second;
        }
    }

    // Диаметр - это максимальное расстояние от самого удаленного узла
    return bfs(farthestNode).second;
}

// Вычисление радиуса графа
int Graph::radius() {
    int minEccentricity = INT_MAX;

    // Радиус - это минимальный эксцентриситет среди всех вершин
    for (int i = 0; i < V; i++) {
        minEccentricity = min(minEccentricity, bfs(i).second);
    }

    return minEccentricity;
}

// Поиск центра графа
vector<int> Graph::center() {
    vector<int> centerNodes;
    int minEccentricity = radius();

    // Центр графа состоит из вершин с минимальным эксцентриситетом
    for (int i = 0; i < V; i++) {
        if (bfs(i).second == minEccentricity) {
            centerNodes.push_back(i);
        }
    }

    return centerNodes;
}

// Тензорное произведение двух графов
Graph Graph::tensorProduct(const Graph &other) {
    int newV = V * other.V;
    Graph result(newV);

    for (int u1 = 0; u1 < V; u1++) {
        for (int u2 = 0; u2 < other.V; u2++) {
            for (int v1 : adj[u1]) {
                for (int v2 : other.adj[u2]) {
                    // Добавляем ребро между (u1, u2) and (v1, v2)
                    result.addEdge(u1 * other.V + u2, v1 * other.V + v2);
                }
            }
        }
    }

    return result;
}

// Декартово произведение двух графов
Graph Graph::cartesianProduct(const Graph &other) {
    int newV = V * other.V;
    Graph result(newV);

    for (int u1 = 0; u1 < V; u1++) {
        for (int u2 = 0; u2 < other.V; u2++) {
            // Добавляем ребра для вершин (u1, u2)
            // 1. Ребра из графа 1:
            for (int v1: adj[u1]) {
                result.addEdge(u1 * other.V + u2, v1 * other.V + u2);
            }

            // 2. Ребра из графа 2:
            for (int v2: other.adj[u2]) {
                result.addEdge(u1 * other.V + u2, u1 * other.V + v2);
            }
        }
    }

    return result;
}


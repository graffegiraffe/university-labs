#include <iostream>
#include <fstream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class GraphRidiculousness {
public:
    vector<vector<int>> katusha;

    explicit GraphRidiculousness(const string& path) {
        ifstream input_file(path);
        vector<int> actual;
        string line;

        katusha.resize(1, {-1});

        while(getline(input_file, line)) {
            for(int j = 0;j < line.size();j+=2) {
                actual.push_back(line[j] - '0');
            }
            katusha.push_back(actual);
            actual.clear();
        }

        input_file.close();
    }

    /*void addEdge(int node1, int node2) {
        katusha[node1].push_back(node2);
        katusha[node2].push_back(node1);
    } */

    int minimumDistancePeripheralNodes() {
        vector<int> vectorOfMinDistances = distancesPeripheralNodes();
        return *min_element(vectorOfMinDistances.begin(), vectorOfMinDistances.end());
    }

    float averageDistancePeripheralNodes() {
        vector<int> vectorOfMinDistances = distancesPeripheralNodes();
        float summary = 0;

        for (const auto& it : vectorOfMinDistances) {
            summary += (float)it;
        }

        return summary / static_cast<float>(vectorOfMinDistances.size());
    }

private:
    vector<int> findPeripheralNodes() {
        vector<int> peripheralNodes;
        int extrecity = 0, diameter = 0;

        for (int i = 1; i < katusha.size(); ++i) {
            for (int j = 1; j < katusha.size(); ++j) {
                extrecity = max(BFS(i, j), extrecity);
            }
            diameter = max(extrecity, diameter);
            extrecity = 0;
        }

        // Значение диаметра получено

        for (int i = 1; i < katusha.size(); ++i) {
            for (int j = 1; j < katusha.size(); ++j) {
                extrecity = max(BFS(i, j), extrecity);
            }
            if (extrecity == diameter) {
                peripheralNodes.push_back(i);
            }
            extrecity = 0;
        }

        return peripheralNodes;
    }

    vector<int> distancesPeripheralNodes() {
        vector<int> peripheralNodes = findPeripheralNodes();
        vector<int> vectorOfDistance;

        for (int i = 1; i < peripheralNodes.size(); ++i) {
            for (int j = i + 1; j < peripheralNodes.size(); ++j) {
                vectorOfDistance.push_back(BFS(peripheralNodes[i], peripheralNodes[j]));
            }
        }

        return vectorOfDistance;
    }

    int BFS(int start, int end) {
        int n = (int)katusha.size(); // Количество вершин в графе

        vector<bool> visited(n, false); // Массив для отслеживания посещенных вершин
        vector<int> distance(n, -1);    // Массив для хранения расстояний от стартовой вершины

        queue<int> q;
        q.push(start);
        visited[start] = true;
        distance[start] = 0;

        while (!q.empty()) {
            int current = q.front();
            q.pop();

            for (int neighbor : katusha[current]) {
                if (!visited[neighbor]) {
                    q.push(neighbor);
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;

                    // Если достигнута конечная вершина, возвращаем расстояние
                    if (neighbor == end) {
                        return distance[neighbor];
                    }
                }
            }
        }

        // Если конечная вершина не достигнута
        return -1;
    }
};

int main() {
    // Пример использования
    GraphRidiculousness g("../file.txt");

    // Основная часть
    cout << "Minimum distances of graph: " << g.minimumDistancePeripheralNodes() << endl;
    cout << "Average distances of graph: " << g.averageDistancePeripheralNodes() << endl;

    return 0;
}
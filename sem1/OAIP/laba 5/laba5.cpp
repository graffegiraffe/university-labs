#include <iostream>
#include <iomanip>
using namespace std;

void print(int** arr, const int n, const int m) {
    for(int x = 0;x < n;x++) {
        for(int y = 0;y < m;y++) {
            cout << setw (3) << arr[x][y];
        }
        cout << '\n';
    }
    cout << '\n';
}

int min_value_array(const int* mas, int size) {
    int min_v = mas[0];

    for(int i = 1;i  < size;i++) {
        min_v = mas[i] > min_v ? min_v : mas[i];
    }

    return min_v;
}

int** sort_for_min_in_row(int**& arr, const int N, const int M) {
    int** array_copy = new int*[M];
    for(int i = 0; i < M; i++) {
        array_copy[i] = new int[N];
    }

    for(int x = 0;x < M;x++) {
        for(int y = 0;y < N;y++) {
            array_copy[x][y] = arr[y][x];
        }
    }

    for (int i = 0; i < M; i++) {
        bool flag = true;
        for (int j = 0; j < M - (i + 1); j++) {
            if (min_value_array(array_copy[j], N) > min_value_array(array_copy[j+1], N)) {
                flag = false;
                swap (array_copy[j], array_copy[j+1]);
            }
        }
        if (flag) {
            break;
        }
    }


    for(int x = 0;x < N;x++) {
        for(int y = 0;y < M;y++) {
            arr[x][y] = array_copy[y][x];
        }
    }

    return arr;
}



int  main() {
    int rows;
    int cols;

    cout << "enter rows count:" << endl;
    cin >> rows;
    cout << "enter columns count:" << endl;
    cin >> cols;

    int **arr = new int* [rows];
    for ( int i=0; i<rows; i ++) {
        arr[i]=new int [cols];
    }

    for ( int i=0; i<rows; i ++) {
        for ( int j=0; j<cols; j ++) {
        arr[i][j] = rand() % 20;

        }
    }



    for ( int i=0; i<rows; i ++) {
        for ( int j=0; j<cols; j ++) {
            cout << arr[i][j] << "\t";
        }
        cout << endl;
    }

    cout << "result:" << endl;
    arr = sort_for_min_in_row(arr, rows, cols);
    print(arr, rows, cols);




    for(int i = 0; i<rows; i++){
        delete [] arr[i];
    }
delete[] arr;
    return 0;


}

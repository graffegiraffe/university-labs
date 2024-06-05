#include <iostream>
#include <cmath>
using namespace std;

int main() {
	system("chcp 1251");

	//СОЗАДЕМ СООТВЕТСТВИЕ А
	int sizeX;
	cout << "Введите мощность X: ";
	cin >> sizeX;
	int* X = new int[sizeX];

	int sizeY;
	cout << "Введите мощность Y: ";
	cin >> sizeY;
	int* Y = new int[sizeY];

	int sizeG;
	cout << "Введите мощность G: ";
	cin >> sizeG;
	int** G = new int* [sizeG];
	for (int i = 0; i < sizeG; i++) {
		G[i] = new int[2];
	}

	cout << "Вводите значения X:\n";
	for (int i = 0; i < sizeX; i++) {
		cout << "Введите " << i + 1 << "-e значение: ";
		cin >> X[i];
	}

	cout << "Вводите значения Y:\n";
	for (int i = 0; i < sizeY; i++) {
		cout << "Введите " << i + 1 << "-e значение: ";
		cin >> Y[i];
	}

	cout << "вводите значения G:\n";
	for (int i = 0; i < sizeG; i++) {
		cout << "Введите " << i + 1 << "-ю пару: ";
		cin >> G[i][0];
		cin >> G[i][1];
	}


	//СОЗАДЕМ СООТВЕТСТВИЕ B
	int sizeU;
	cout << "Введите мощность U: ";
	cin >> sizeU;
	int* U = new int[sizeU];

	int sizeV;
	cout << "Введите мощность V: ";
	cin >> sizeV;
	int* V = new int[sizeV];

	int sizeF;
	cout << "Введите мощность F: ";
	cin >> sizeF;
	int** F = new int* [sizeF];
	for (int i = 0; i < sizeF; i++) {
		F[i] = new int[2];
	}

	cout << "Вводите значения U:\n";
	for (int i = 0; i < sizeU; i++) {
		cout << "Введите " << i + 1 << "-e значение: ";
		cin >> U[i];
	}

	cout << "Вводите значения V:\n";
	for (int i = 0; i < sizeV; i++) {
		cout << "Введите " << i + 1 << "-e значение: ";
		cin >> V[i];
	}

	cout << "вводите значения F:\n";
	for (int i = 0; i < sizeF; i++) {
		cout << "Введите " << i + 1 << "-ю пару: ";
		cin >> F[i][0];
		cin >> F[i][1];
	}


	//СОЗДАДИМ ОБЪЕДИНЕНИЕ А и В

	//ОБЪЕДИНЕНИЕ X и U
	cout << "-------ОБЪЕДИНЕНИЕ A и В-------\n\       ОБЪЕДИНЕНИЕ X и U\n";
	bool flag = false;
	int* Ob1 = new int[sizeX+sizeU]; //Создаем множество объединения
	for (int i = 0; i < sizeX; i++) {
		cout << X[i] << " ";  //Записываем все элементы Х во множество
	}
	for (int i = 0; i < sizeU; i++) {
		for (int j = 0; j < sizeX; j++) {  //Проверяем элементы U и записываем их во множество
			if (X[j] == U[i]) {
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << U[i] << " ";
		}
	}

	//ОБЪЕДИНЕНИЕ Y и V
	cout << "\n       ОБЪЕДИНЕНИЕ Y и V\n";
	flag = false;
	int* Ob2 = new int[sizeY + sizeV];  //Создзаем множество объединения
	for (int i = 0; i < sizeY; i++) {
		cout << Y[i] << " ";   //Записываем все элементы Y
	}
	for (int i = 0; i < sizeV; i++) {
		for (int j = 0; j < sizeY; j++) {  //Проверяем элементы V и тоже записываем их во множество
			if (Y[j] == V[i]) {
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << V[i] << " ";
		}
	}

	//ОБЪЕДИНЕНИЕ G и F
	cout << "\n       ОБЪЕДИНЕНИЕ G и F\n";
	flag = false;
	int** Ob_Gr = new int* [sizeF + sizeG];
	for (int i = 0; i < sizeG; i++) {
		cout << "<" << G[i][0] << "," << G[i][1] << "> ";  //Записываем все пары G в объединение
	}
	for (int i = 0; i < sizeF; i++) {
		for (int j = 0; j < sizeG; j++) {
			if ((F[i][0] == G[j][0]) && (F[i][1] == G[j][1])) {  //Проверяем пары F и тоже записываем их
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << "<" << F[i][0] << "," << F[i][1] << "> ";
		}
	}


	//СОЗДАДИМ ПЕРЕСЕЧЕНИЕ А и В

	//ПЕРЕСЕЧЕНИЕ X и U
	cout << "\n\n-------ПЕРЕСЕЧЕНИЕ A и В-------\n       ПЕРЕСЕЧЕНИЕ X и U\n";
	int* Per1 = new int[sizeX + sizeU];
	for (int i = 0; i < sizeX; i++) {
		for (int j = 0; j < sizeU; j++) {  //Находим совпадающие элементы в двух множествах
			if (X[i] == U[j]) {            //И записываем их
				cout << X[i] << " ";
				break;
			}
		}
	}

	//ПЕРЕСЕЧЕНИЕ Y и V
	cout << "\n       ПЕРЕСЕЧЕНИЕ Y и V\n";
	int* Per2 = new int[sizeY + sizeV];        //Также находим совпадающие элементы и записываем их
	for (int i = 0; i < sizeY; i++) {
		for (int j = 0; j < sizeV; j++) {
			if (Y[i] == V[j]) {
				cout << Y[i] << " ";
				break;
			}
		}
	}

	//ПЕРЕСЕЧЕНИЕ G и F
	cout << "\n       ПЕРЕСЕЧЕНИЕ G и F\n";
	int** Per_Gr = new int* [sizeG + sizeF];
	for (int i = 0; i < sizeG; i++) {
		for (int j = 0; j < sizeF; j++) {
			if ((G[i][0] == F[j][0]) && (G[i][1] == F[j][1])) {  //Находим совпадающие пары и записываем их
				cout << "<" << G[i][0] << "," << G[i][1] << "> ";
			}
		}
	}


	//РАЗНОСТЬ А\В

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------РАЗНОСТЬ А и В-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	flag = false;
	int* FR1 = new int[sizeX];          //Создаем область отправления
	for (int i = 0; i < sizeX; i++) {   //Записываем все элементы Х
		cout << X[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	flag = false;
	int* SR1 = new int[sizeY];          //Создаем область прибытия
	for (int i = 0; i < sizeY; i++) {   //Записываем все элементы Y
		cout << Y[i] << " ";
	}

	//РАЗНОСТЬ G\F
	cout << "\n       РАЗНОСТЬ G\\F\n";
	flag = false;
	int** GR1 = new int* [sizeG];               //Создаем график разности
	for (int i = 0; i < sizeG; i++) {
		for (int j = 0; j < sizeF; j++) {
			if ((G[i][0] == F[j][0]) && (G[i][1] == F[j][1])) {      //Проверяем равны ли элементы
				flag = false;
				break;
			}
			flag = true;            //Если они равны, то мы не записываем пару
		}
		if (flag) {
			cout << "<" << G[i][0] << "," << G[i][1] << "> ";
		}
	}


	//РАЗНОСТЬ В\А

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------РАЗНОСТЬ B и A-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	flag = false;
	int* FR2 = new int[sizeU];
	for (int i = 0; i < sizeU; i++) {   //переписываем все элементы U
		cout << U[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	flag = false;
	int* SR2 = new int[sizeV];
	for (int i = 0; i < sizeV; i++) {    //переписываем все элементы V
		cout << V[i] << " ";
	}

	//РАЗНОСТЬ F\G
	cout << "\n       РАЗНОСТЬ F\\G\n";
	flag = false;
	int** GR2 = new int* [sizeF];
	for (int i = 0; i < sizeF; i++) {
		for (int j = 0; j < sizeG; j++) {
			if ((F[i][0] == G[j][0]) && (F[i][1] == G[j][1])) {        //Тоже проверяем на равенство элементов пар
				flag = false;
				break;               //Если равны, то не записываем пару
			}
			flag = true;
		}
		if (flag) {
			cout << "<" << F[i][0] << "," << F[i][1] << "> "; 
		}
	}


	//СИММЕТРИЧЕСКАЯ РАЗНОСТЬ А и В

	//СИММЕТРИЧЕСКАЯ РАЗНОСТЬ X и U
	cout << "\n\n-------СИММЕТРИЧЕСКАЯ РАЗНОСТЬ А и В-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	
	flag = false;
	int* FSim = new int[sizeX+sizeU];
	for (int i = 0; i < sizeX; i++) { //Находим объединение двух областей отправления соответствий
		cout << X[i] << " ";
	}
	for (int i = 0; i < sizeU; i++) {
		for (int j = 0; j < sizeX; j++) {
			if (U[i] == X[j]) {
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << U[i] << " ";
		}
	}

	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	flag = false;
	int* SSim = new int[sizeY+sizeV];  //Находим объединение двух областей прибытия соответствий
	for (int i = 0; i < sizeY; i++) {
		cout << Y[i] << " ";
	}
	for (int i = 0; i < sizeV; i++) {
		for (int j = 0; j < sizeY; j++) {
			if (V[i] == Y[j]) {
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << V[i] << " ";
		}
	}

	cout << "\n       СИММЕТРИЧЕСКАЯ РАЗНОСТЬ G и F\n";
	//G\F
	flag = false;
	int** GSim = new int* [sizeG+sizeF];  //Находим сначала разность для графика G
	for (int i = 0; i < sizeG; i++) {
		for (int j = 0; j < sizeF; j++) {
			if ((G[i][0] == F[j][0]) && (G[i][1] == F[j][1])) {
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << "<" << G[i][0] << "," << G[i][1] << "> ";
		}
	}

	//F\G
	flag = false;
	for (int i = 0; i < sizeF; i++) {
		for (int j = 0; j < sizeG; j++) {
			if ((F[i][0] == G[j][0]) && (F[i][1] == G[j][1])) {  //Затем находим разность для графика F
				flag = false;
				break;
			}
			flag = true;
		}
		if (flag) {
			cout << "<" << F[i][0] << "," << F[i][1] << "> "; //Выводим все найденные значения
		}
	}


	//ИНВЕРСИЯ А

	//ИНВЕРСИЯ ОБЛАСТИ ОТПРАВЛЕНИЯ
	cout << "\n\n-------ИНВЕРСИЯ А-------\n       ИНВЕРСИЯ ОБЛАСТИ ОТПРАВЛЕНИЯ\n";
	int* InvX = new int[sizeY];
	for (int i = 0; i < sizeY; i++) {  //записываем в область отправления множество Y
		cout << Y[i] << " ";
	}

	//ИНВЕРСИЯ ОБЛАСТИ ПРИБЫТИЯ
	cout << "\n       ИНВЕРСИЯ ОБЛАСТИ ПРИБЫТИЯ\n";
	int* InvY = new int[sizeX];                     //Записываем в область прибытия множество X
	for (int i = 0; i < sizeX; i++) {
		cout << X[i] << " ";
	}

	//ИНВЕРСИЯ ГРАФИКА
	cout << "\n       ИНВЕРСИЯ ГРАФИКА\n";
	int** InvG = new int* [sizeG];
	for (int i = 0; i < sizeG; i++) {
		cout << "<" << G[i][1] << "," << G[i][0] << "> "; //Меняем местами первый и второй элемент каждой пары и выводим
	}


	//ИНВЕРСИЯ В

	//ИНВЕРСИЯ ОБЛАСТИ ОТПРАВЛЕНИЯ
	cout << "\n\n-------ИНВЕРСИЯ В-------\n       ИНВЕРСИЯ ОБЛАСТИ ОТПРАВЛЕНИЯ\n";
	int* InvU = new int[sizeV];
	for (int i = 0; i < sizeV; i++) {  //Записываем в область отправления множество V
		cout << V[i] << " ";
	}

	//ИНВЕРСИЯ ОБЛАСТИ ПРИБЫТИЯ
	cout << "\n       ИНВЕРСИЯ ОБЛАСТИ ПРИБЫТИЯ\n";
	int* InvV = new int[sizeU];
	for (int i = 0; i < sizeU; i++) {  //Записываем в область прибытия множество U
		cout << U[i] << " ";
	}

	//ИНВЕРСИЯ ГРАФИКА
	cout << "\n       ИНВЕРСИЯ ГРАФИКА\n";
	int** InvF = new int* [sizeF];
	for (int i = 0; i < sizeF; i++) {
		cout << "<" << F[i][1] << "," << F[i][0] << "> ";  //Меняем местами первый и второй элемент местами и выводим
	}

	//КОМПОЗИЦИЯ А*В

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------КОМПОЗИЦИЯ А*В-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int *FK1 = new int[sizeX];
	for (int i = 0; i < sizeX; i++) { //Записываем в область отправления множество X
		cout << X[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* SK1 = new int[sizeV];
	for (int i = 0; i < sizeV; i++) {  //Записываем в область прибытия множество V
		cout << V[i] << " ";
	}

	//ГРАФИК КОМПОЗИЦИИ
	cout << "\n       ГРАФИК КОМПОЗИЦИИ\n";
	flag = false;
	int** GK1 = new int* [sizeG + sizeF];
	for (int i = 0; i < sizeG + sizeF; i++) { //Создаем график композиции
		GK1[i] = new int[2];
	}
	int num = 0;
	for (int i = 0; i < sizeG; i++) {
		for (int j = 0; j < sizeF; j++) {
			if (G[i][1] == F[j][0]) {
				if (num == 0) {
					GK1[num][0] = G[i][0]; //Проверяем, чтобы второй элемент G совпадал с первым элементом F
					GK1[num][1] = F[j][1]; //Если совпадает, проверяем на включение
					num++;
				}
				else {
					for (int k = 0; k < num; k++) {
						if ((G[i][0] == GK1[k][0]) && (F[j][1] == GK1[k][1])) {  //Если такой пары еще нет, то запишем ее во множество
							flag = false;
							break;
						}
						flag = true;
					}
					if (flag) {
						GK1[num][0] = G[i][0];
						GK1[num][1] = F[j][1];
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << "<" << GK1[i][0] << "," << GK1[i][1] << "> ";  //Выведем все пары
	}


	//КОМПОЗИЦИЯ B*A

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------КОМПОЗИЦИЯ B*A-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int* FK2 = new int[sizeU];
	for (int i = 0; i < sizeU; i++) {  //Запишем в область отправления множество U
		cout << U[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* SK2 = new int[sizeY];
	for (int i = 0; i < sizeY; i++) {  //Запишем в область прибытия множество Y
		cout << Y[i] << " ";
	}

	//ГРАФИК КОМПОЗИЦИИ
	cout << "\n       ГРАФИК КОМПОЗИЦИИ\n";
	flag = false;
	int** GK2 = new int* [sizeG + sizeF];
	for (int i = 0; i < sizeG + sizeF; i++) { //Создадим график композиции
		GK2[i] = new int[2];
	}
	num = 0;
	for (int i = 0; i < sizeF; i++) {
		for (int j = 0; j < sizeG; j++) {
			if (F[i][1] == G[j][0]) {
				if (num == 0) {
					GK2[num][0] = F[i][0];  //Првоерим, равен ли второй элемент G первому элементу F
					GK2[num][1] = G[j][1];
					num++;
				}
				else {
					for (int k = 0; k < num; k++) {
						if ((F[i][0] == GK2[k][0]) && (G[j][1] == GK2[k][1])) { //Если равен, то проверяем на повтор пары
							flag = false;
							break;
						}
						flag = true;
					}
					if (flag) {
						GK2[num][0] = F[i][0]; //Если такой пары еще нет, то записываем ее
						GK2[num][1] = G[j][1];
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << "<" << GK2[i][0] << "," << GK2[i][1] << "> "; //Выведем все пары композиции
	}


	//ОБРАЗ А
	cout << "\n\n-------ОБРАЗ А-------\n";
	num = 0;
	flag = false;
	cout << "Введите мощность множества Obr11: ";
	int sizeObr11;
	cin >> sizeObr11;
	int* Obr11 = new int[sizeObr11];
	int* ObrA = new int[sizeObr11];  //Создаем множество для нахождения образа
	for (int i = 0; i < sizeObr11; i++) {
		cout << "Введите " << i << "-e значение: ";
		cin >> Obr11[i];  //Введем значения для этого множества
	}
	for (int i = 0; i < sizeObr11; i++) {
		for (int j = 0; j < sizeG; j++) {  //проверяем, чтобы элемент множества совпадал с первым элементом пары
			if (Obr11[i] == G[j][0]) {
				if (num == 0) {
					ObrA[num] = G[j][1];
					num++;
				}
				else {
					for (int k = 0; k < num; k++) {//проверяем элементы на повтор
						if (G[j][1] == ObrA[k]) {
							flag = false;
							break;
						}
						flag = true; //Если такой элемент существует, то не записываем его
					}
					if (flag) {
						ObrA[num] = G[j][1]; //Иначе мы его записывваем
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << ObrA[i] << " ";
	}


	//ПРООБРАЗ А
	cout << "\n\n-------ПРООБРАЗ А-------\n";
	num = 0;
	flag = false;
	cout << "Введите мощность множества Obr12: ";
	int sizeObr12;
	cin >> sizeObr12;
	int* Obr12 = new int[sizeObr12];
	int* PrObrA = new int[sizeObr12]; //Создаем множество для прообраза
	for (int i = 0; i < sizeObr12; i++) {
		cout << "Введите " << i << "-e значение: ";
		cin >> Obr12[i];
	}
	for (int i = 0; i < sizeObr12; i++) {  //проверяем элементы на сравнение
		for (int j = 0; j < sizeG; j++) {
			if (Obr12[i] == G[j][1]) {
				if (num == 0) {
					PrObrA[num] = G[j][0];  //Если они равны, то проверяем элемент на повторение
					num++;
				}
				else {
					for (int k = 0; k < num; k++) {
						if (G[j][0] == PrObrA[k]) { //Если он повторяется, то не записываем его, иначе записываем
							flag = false;
							break;
						}
						flag = true;
					}
					if (flag) {
						PrObrA[num] = G[j][0];
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << PrObrA[i] << " ";
	}


	//ОБРАЗ В
	cout << "\n\n-------ОБРАЗ В-------\n";
	num = 0;
	flag = false;
	cout << "Введите мощность множества Obr21: ";
	int sizeObr21;
	cin >> sizeObr21;
	int* Obr21 = new int[sizeObr21]; //Создаем множество для образа
	int* ObrB = new int[sizeObr21];
	for (int i = 0; i < sizeObr21; i++) {
		cout << "Введите " << i << "-e значение: ";
		cin >> Obr21[i];
	}
	for (int i = 0; i < sizeObr21; i++) { //Сравниваем элементы. Если они равны, то проверяем на включение
		for (int j = 0; j < sizeF; j++) {
			if (Obr21[i] == F[j][0]) {
				if (num == 0) {
					ObrB[num] = F[j][1];
					num++;
				}
				else {
					for (int k = 0; k < num; k++) { //Если элемент повторяется, то не записываем его еще раз, иначе записываем
						if (F[j][1] == ObrB[k]) {
							flag = false;
							break;
						}
						flag = true;
					}
					if (flag) {
						ObrB[num] = F[j][1];
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << ObrB[i] << " ";
	}


	//ПРООБРАЗ B
	cout << "\n\n-------ПРООБРАЗ B-------\n";
	num = 0;
	flag = false;
	cout << "Введите мощность множества Obr22: ";
	int sizeObr22;
	cin >> sizeObr22;
	int* Obr22 = new int[sizeObr22];  //Создаем множество для прообраза
	int* PrObrB = new int[sizeObr22];
	for (int i = 0; i < sizeObr22; i++) {
		cout << "Введите " << i << "-e значение: ";
		cin >> Obr22[i];
	}
	for (int i = 0; i < sizeObr22; i++) { //Проверяем элементы на равенство. Если равен, то проверяем на повтор
		for (int j = 0; j < sizeF; j++) {
			if (Obr22[i] == F[j][1]) {
				if (num == 0) {
					PrObrB[num] = F[j][0];
					num++;
				}
				else {
					for (int k = 0; k < num; k++) {  //Если элемент повторяется, то не записываем заново, иначе записываем 
						if (F[j][0] == PrObrB[k]) {
							flag = false;
							break;
						}
						flag = true;
					}
					if (flag) {
						PrObrB[num] = F[j][0];
						num++;
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << PrObrB[i] << " ";
	}


	//СУЖЕНИЕ А

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	int sizeW;
	cout << "\nВведите мощность множества W: ";
	cin >> sizeW;
	int* W = new int[sizeW];
	for (int i = 0; i < sizeW; i++) {  //Задаем мощность множества сужения и задаем его элементы
		cout << "Введите " << i + 1 << "-е значение : ";
		cin >> W[i];
	}
	cout << "\n\n-------СУЖЕНИЕ А-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int* SygX = new int[sizeX];
	for (int i = 0; i < sizeX; i++) { //Записываем в область отправления множество X
		cout << X[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* sygY = new int[sizeY];
	for (int i = 0; i < sizeY; i++) { //Записываем в область прибытия множество Y
		cout << Y[i] << " ";
	}

	//ГРАФИК СУЖЕНИЯ
	cout << "\n       ГРАФИК СУЖЕНИЯ\n";
	num = 0;
	flag = false;
	int** SygG = new int* [sizeG];
	for (int i = 0; i < sizeG; i++) { //Создаем график сужения
		SygG[i] = new int[2];
	}
	for (int i = 0; i < sizeW; i++) {
		for (int j = 0; j < sizeY; j++) {
			for (int k = 0; k < sizeG; k++) {
				if ((W[i] == G[k][0]) && (Y[j] == G[k][1])) {  //Проверяем на равенство пары в графике
					if (num == 0) {
						SygG[num][0] = G[k][0];
						SygG[num][1] = G[k][1];  //Если она равна, о проверяем ее на повтор
						num++;
					}
					else {
						for (int l = 0; l < num; l++) {
							if ((SygG[l][0] == G[k][0]) && (SygG[l][1] == G[k][1])) { //Если пара повторяется, то не записываем еще раз, иначе записываем
								flag = false;
								break;
							}
							flag = true;
						}
						if (flag) {
							SygG[num][0] = G[k][0];
							SygG[num][1] = G[k][1];
							num++;
						}
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << "<" << SygG[i][0] << "," << SygG[i][1] << "> ";  //выводим все пары
	}


	//ПРОДОЛЖЕНИЕ А

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------ПРОДОЛЖЕНИЕ А-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int* ProdX = new int[sizeX];
	for (int i = 0; i < sizeX; i++) { //Записываем в область отправления множество X
		cout << X[i] << " ";
	}

	//ООБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* ProdY = new int[sizeY];
	for (int i = 0; i < sizeY; i++) { //Записываем в область прибытия множество Y
		cout << Y[i] << " ";
	}

	//ГРАФИК ПРОДОЛЖЕНИЯ
	cout << "\n       ГРАФИК ПРОДОЛЖЕНИЯ\n";
	int** ProdG = new int* [sizeX + sizeY];
	for (int i = 0; i < sizeX; i++) {
		for (int j = 0; j < sizeY; j++) {
			cout << "<" << X[i] << "," << Y[j] << "> "; //создаем пары декартовым произведением из областей продолжения и отправления
		}
	}


	//СУЖЕНИЕ В

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------СУЖЕНИЕ В-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int* SygU = new int[sizeU];
	for (int i = 0; i < sizeU; i++) {  //записываем в область отправления множество U
		cout << U[i] << " ";
	}

	//ОБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* sygV = new int[sizeV];
	for (int i = 0; i < sizeV; i++) {  //Записываем в область прибытия множество V
		cout << V[i] << " ";
	}

	//ГРАФИК СУЖЕНИЯ
	cout << "\n       ГРАФИК СУЖЕНИЯ\n";
	num = 0;
	flag = false;
	int** SygF = new int* [sizeF];
	for (int i = 0; i < sizeF; i++) {  //Создаем график сужения
		SygF[i] = new int[2];
	}
	for (int i = 0; i < sizeW; i++) {
		for (int j = 0; j < sizeV; j++) {
			for (int k = 0; k < sizeF; k++) {
				if ((W[i] == F[k][0]) && (V[j] == F[k][1])) {  //Проверяем пару на равенство. Если равна, то проверяем на повтор
					if (num == 0) {
						SygF[num][0] = F[k][0];
						SygF[num][1] = F[k][1];
						num++;
					}
					else {
						for (int l = 0; l < num; l++) {
							if ((SygF[l][0] == F[k][0]) && (SygF[l][1] == F[k][1])) { //Если пара уже существует, то не записываем ее
								flag = false;
								break;
							}
							flag = true;
						}
						if (flag) {
							SygF[num][0] = F[k][0]; //Иначе записываем его в график
							SygF[num][1] = F[k][1];
							num++;
						}
					}
				}
			}
		}
	}
	for (int i = 0; i < num; i++) {
		cout << "<" << SygF[i][0] << "," << SygF[i][1] << "> ";  //Выводим все па
	}


	//ПРОДОЛЖЕНИЕ В

	//ОБЛАСТЬ ОТПРАВЛЕНИЯ
	cout << "\n\n-------ПРОДОЛЖЕНИЕ B-------\n       ОБЛАСТЬ ОТПРАВЛЕНИЯ\n";
	int* ProdU = new int[sizeU];
	for (int i = 0; i < sizeU; i++) { //записываем в область отправления множество U
		cout << U[i] << " ";
	}

	//ООБЛАСТЬ ПРИБЫТИЯ
	cout << "\n       ОБЛАСТЬ ПРИБЫТИЯ\n";
	int* ProdV = new int[sizeV];
	for (int i = 0; i < sizeV; i++) { //записываем в область прибытия множество V
		cout << V[i] << " ";
	}

	//ГРАФИК ПРОДОЛЖЕНИЯ
	cout << "\n       ГРАФИК ПРОДОЛЖЕНИЯ\n";
	int** ProdF = new int* [sizeU + sizeV];  //Находим декартово произведение и записываем пары
	for (int i = 0; i < sizeU; i++) {
		for (int j = 0; j < sizeV; j++) {
			cout << "<" << U[i] << "," << V[j] << "> ";  //Выводим пары
		}
	}
}
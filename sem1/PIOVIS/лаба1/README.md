# BSUIR(cmd-bash)
 Данная лабораторная работа была предназначена для ознакомления с командной строкой операционных систем **Windows** и **Linux**.
 В рамках ЛР 1 необходимо создать исполняемый файл _.bat_ и _.sh_ в соответствии с заданным вариантом.

 ## Условие ЛР1, 54 вариант
 <img width="669" alt="Снимок экрана 2023-09-21 в 15 58 59" src="https://github.com/iis-32170x/RPIIS/assets/145375460/c622ba48-d1fd-41ef-a862-ceec99205dd9">

 ## Пример работы .bat файла (описание алгоритма, пример запуска и выполнения программы):

 ### Вот так выглядит весь код:

 <img width="669" alt="Снимок экрана 2023-09-21 в 22 02 28" src="https://github.com/iis-32170x/RPIIS/assets/145375460/ce901172-28fa-4af2-b7a6-26740d93d348">

#### Используемые перменные:

* *directory* - переменная, которая хранит пользовательский ввод пути к директории.
* *home* - переменная, которая хранит текущую директорию.
* *string* - переменная,которая используется для конкатенации имен файлов.
* *result* - переменная, которая используется для подсчета результата.
* *char* - переменная, которая хранит один символ из переменной string.
* *count* - переменная, которая используется для подсчета символов.
* *char_to_count* - переменная, которая хранит один символ из переменной string.

### Подробное описание кода:

На вход пакетному файлу приходит относительный путь к папке (как параметр пакетного файла). Далее идет проверка, существует ли такая папка. Если нет, пользователь видит сообщение "Данной папки нет".

 <img width="248" alt="Снимок экрана 2023-09-21 в 22 33 41" src="https://github.com/iis-32170x/RPIIS/assets/145375460/da0685f2-7ca6-48a6-8a94-42719ecb13dc"> 

  Далее запускается цикл, который обходит данную директорию и её поддиректории, добавляя имя файла (без расширения) к переменной 'string'.
 
 <img width="248" alt="Screenshot 2023-09-22 at 20 02 44" src="https://github.com/iis-32170x/RPIIS/assets/145375460/639c3b57-40db-41f5-8569-70175faa69b0">

 Затем запускается цикл, который выполняется 101 рз (от 0 до 100). И при каждой интерации извлекается один символ из 'string' и сохраняет его в переменной 'char'


<img width="248" alt="Screenshot 2023-09-22 at 20 13 51" src="https://github.com/iis-32170x/RPIIS/assets/145375460/9582cdc5-720d-47d3-8c8d-1b6d928e6826">

Вложенный цикл извлекает один символ из строки 'string' и сохраняет его в переменной 'char_to_count'. Потом сравнивает символы 'char' и 'char_to_count'. Если они совпадают, увеличивает переменную 'count' на 1.

<img width="430" alt="Screenshot 2023-09-22 at 20 22 52" src="https://github.com/iis-32170x/RPIIS/assets/145375460/b81af460-0c7e-41ed-bd9a-a3a87bb6ef74">

Cледующий цикл проверяет, равно ли значение 'count' 1. Если да, то символ 'char' встречается только один раз в строке 'string', и переменная 'result' увеличивается на 1.

<img width="214" alt="Screenshot 2023-09-22 at 20 39 27" src="https://github.com/iis-32170x/RPIIS/assets/145375460/0561c582-babe-44a7-9698-4a108316bca4">

Далее записывается значение переменной 'result' в файл result.txt. И программа завершается.


<img width="232" alt="Screenshot 2023-09-22 at 20 44 45" src="https://github.com/iis-32170x/RPIIS/assets/145375460/e21f3e43-4807-42eb-bfcf-7efa02a7fb9c">

## Пример запуска .bat файла:
1.1 Запускаем командную строку любым способом (Я открыла через комбинацию клавиш win+R).

<img width="1728" alt="Screenshot 2023-09-22 at 20 59 57" src="https://github.com/iis-32170x/RPIIS/assets/145375460/4b44d2a6-cbe0-4a9a-a167-7d23ad6e8a47">


1.2 Переходим в директорию, где лежит *.bat файл. Для этого вводим в командной строке **cd <путь файла>** и нажимаем **enter**.

<img width="1728" alt="Screenshot 2023-09-22 at 21 24 27" src="https://github.com/iis-32170x/RPIIS/assets/145375460/501d61f5-ae6e-4b93-b9e3-91ab7546399f">


1.3 Далее нужно открыть файл, с помощью команды **start <имя файла>** и нажимаем **enter**.


<img width="1728" alt="Screenshot 2023-09-22 at 21 21 55" src="https://github.com/iis-32170x/RPIIS/assets/145375460/b1f0a43b-cc5d-45de-9fde-afed0187a40e"> 


1.4 В открывшемся окне нужно ввести путь к директории (по вашему выбору) и нажимаем **enter**.

<img width="1728" alt="Screenshot 2023-09-22 at 21 22 12" src="https://github.com/iis-32170x/RPIIS/assets/145375460/8e650843-fea0-4ffa-ad49-58732ad003d6">

1.5 После, программа завершится, нужно зайти в "домашнюю" директорию и открыть файл **result**.

<img width="1728" alt="Screenshot 2023-09-22 at 21 04 55" src="https://github.com/iis-32170x/RPIIS/assets/145375460/1a2f5f11-2879-4af3-afc6-792ffe9f9767">

<img width="1728" alt="Screenshot 2023-09-22 at 21 05 05" src="https://github.com/iis-32170x/RPIIS/assets/145375460/fabe38d6-6748-42b2-ab89-b95bca932e0a">

**УРА,ЭТО КОНЕЦ БАТНИКА!**


 ## Пример работы .sh файла (описание алгоритма, пример запуска и выполнения программы):

 ### Вот так выглядит весь код:

<img width="456" alt="Screenshot 2023-09-22 at 21 47 40" src="https://github.com/iis-32170x/RPIIS/assets/145375460/fefdf6eb-d7f3-4772-9c22-d42393132491">


#### Используемые перменные:

* *directory* - переменная, которая хранит пользовательский ввод пути к директории.
* *lenght_string* - переменная, которая хранит длину строки 'string'.
* *string* - переменная, которая используется для конкатенации имен файлов.
* *result* - переменная, которая используется для подсчета результата.
* *char* - переменная, которая хранит один символ из переменной string.
* *count* - переменная, которая используется для подсчета символов.
* *filename* - перменная, которая хранит имя файла без расширения.
* *local input_string* - локальная переменная (аргумент), которая хранит входную строку.
* *local target_char* - локальная переменная (аргумент), которая хранит cимволы, которые нужно подсчитать.
* *local count* - переменная, которая отслеживает количество вхождений символа.
  
   

### Подробное описание кода:

На вход пакетному файлу приходит относительный путь к папке (как параметр пакетного файла). Далее идет проверка, существует ли такая папка. Если нет, пользователь видит сообщение "Данной папки нет" и программа завершается с ошибкой. Если есть - осуществляется переход к данной папке

<img width="411" alt="Снимок экрана 2023-09-23 в 15 20 48" src="https://github.com/iis-32170x/RPIIS/assets/145375460/8cd5d163-761d-408d-ac9d-fc7f44585cf0">

Далее запускается цикл, который обходит данную директорию и её поддиректории, добавляя имя файла (без расширения) к переменной 'string'. После измеряется длинна строки 'string'.
 
<img width="411" alt="Снимок экрана 2023-09-23 в 15 24 13" src="https://github.com/iis-32170x/RPIIS/assets/145375460/f0071476-b8aa-47ee-8b36-41f2a2467d6b">

 Далее запускается цикл for, который будет итерироваться через каждый символ в строке 'string'. Переменная i используется для отслеживания текущей позиции в строке, начиная с 0. Внутри цикла для каждой итерации извлекается символ из строки 'string' и сохраняется в переменной 'char'. Для каждого символа вызывается функция count_char_in_string(). Эта функция подсчитывает, сколько раз 'char' встречается в строке 'string', и возвращает результат, который сохраняется в переменной 'count'.

Далее, с помощью условия if  программа проверяет, равно ли 'count' 1. Если это условие выполняется (т.е., символ встречается в строке только один раз), то счетчик 'result' увеличивается на 1 с помощью.

После завершения цикла, программа выводит значение переменной 'result' в файл result.txt.

<img width="428" alt="Снимок экрана 2023-09-23 в 15 34 55" src="https://github.com/iis-32170x/RPIIS/assets/145375460/321f835f-6e92-4c80-82d8-f3f23da999ab">


Функция,которая выполняет подсчет количества вхождений указанного символа 'target_char' в заданной строке 'input_string'. 
Она использует цикл for, чтобы пройти по каждому символу в строке 'input_string'. Цикл начинается с i = 0 и продолжает до достижения конца строки.
Внутри цикла символы извлекаются и сохраняются в переменной 'char'. Далее, с помощью оператора if, проверяется, равен ли текущий символ 'char' целевому символу 'target_char'. Если да, то счетчик count увеличивается на 1. После завершения цикла, функция выводит значение счетчика 'count'. Это значение представляет собой количество вхождений символа 'target_char' в строку 'input_string'.

<img width="393" alt="Снимок экрана 2023-09-23 в 16 06 42" src="https://github.com/iis-32170x/RPIIS/assets/145375460/ffc020fa-bd82-4dd0-9414-c67257a7a0fa">



## Пример запуска .sh файла:
1.1 Запускаем git bash любым удобным способом.

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 14 31" src="https://github.com/iis-32170x/RPIIS/assets/145375460/77d9ee30-dba9-49ea-8fc4-94a7b7259288">



1.2 Переходим в директорию, где лежит *.sh файл. Для этого вводим в командной строке **cd <путь файла>** и нажимаем **enter**.

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 15 07" src="https://github.com/iis-32170x/RPIIS/assets/145375460/d233fcbe-1866-4fd1-b1f7-c5a12c07a015">

1.3 Далее нужно открыть файл, с помощью команды **./ <имя файла>** и нажимаем **enter**.

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 15 46" src="https://github.com/iis-32170x/RPIIS/assets/145375460/efa53e50-b947-4adb-92f9-3ffa318d377b">

1.4 Далее нужно ввести путь к директории (по вашему выбору) и нажимаем **enter**.

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 15 56" src="https://github.com/iis-32170x/RPIIS/assets/145375460/bcc2e6c9-69b6-4345-a711-97d655ee65da">

1.5 После, программа завершится, нужно зайти в директорию, где лежит файл **result** и открыть его.

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 16 22" src="https://github.com/iis-32170x/RPIIS/assets/145375460/38583dcd-f0d3-4ecd-8a61-d93c82244109">

<img width="1728" alt="Снимок экрана 2023-09-23 в 16 16 31" src="https://github.com/iis-32170x/RPIIS/assets/145375460/e51cd9cb-868b-4840-bd70-f166a9dd0f85">

**УРА,ЭТО КОНЕЦ ШЕЛЛ ФАЙЛА!**


  

count_char_in_string() { # Функция которая считает количесто определенного символа в строке
    local input_string="$1"
    local target_char="$2"
    local count=0
    
    for ((i = 0;i < ${#input_string};i++)); do
        char="${input_string:i:1}"
        if [ "$char" == "$target_char" ]; then
            ((count++))
        fi
    done
    
    echo "$count"
}

################################################# Разделение между функцией и главной программой 

read -p "Введите путь к директории: " directory

# Проверяем, что пользователь ввел директорию
if [ ! -d "$directory" ]; then
    echo "Данной папки нет"
    exit 1
fi

# Переходим в указанную директорию или выходим если невозможно перейти 
cd "$directory" || exit 1

string="" # Строка для записи всех названий файлов
result=0 # Количество неповторяющихся симвалов

# Ищем файлы с разрешением .doc в текущей директории и ее подкаталогах
while IFS= read -r -d $'\0' file; do
    # Извлекаем название файла без расширения и добавляем его к строке без пробелов
    filename=$(basename "$file" .doc)
    string="${string}${filename}"
done < <(find . -type f -name "*.doc" -print0)

length_string=${#string} # Длина строки string

for ((i = 0;i < length_string;i++)); do #Цикл для перебора всех символов в строке string
    # Интилизация объектов char - parse string, conut - количество char в string
    char="${string:i:1}" 
    count=$(count_char_in_string "$string" "$char")
    
    if [[ $count == 1 ]]; then 
		# result = result + 1, при условии, что количесвто char == 1 и в строке result такого символа не было
        ((result++))
    fi
done

echo "$result" > result.txt

#Путь указывается так
#/c/... 

def glue_terms(term1, term2, variables="abc"):
    # Преобразуем термы в бинарный вид
    bin1 = ""
    bin2 = ""
    for var in variables:
        if f"¬{var}" in term1:
            bin1 += "0"
        elif var in term1:
            bin1 += "1"
        else:
            bin1 += "X"
        if f"¬{var}" in term2:
            bin2 += "0"
        elif var in term2:
            bin2 += "1"
        else:
            bin2 += "X"

    # Сравниваем бинарные строки
    diff = 0
    result_bin = ""
    for b1, b2 in zip(bin1, bin2):
        if b1 == b2:
            result_bin += b1
        else:
            diff += 1
            result_bin += "X"
        if diff > 1:
            return None

    if diff != 1:
        return None

    # Преобразуем обратно в строковый вид
    result = ""
    for i, val in enumerate(result_bin):
        if val == "1":
            result += variables[i]
        elif val == "0":
            result += f"¬{variables[i]}"
    return result


def print_table(headers=None, rows=None, table=None, orig_terms=None, imp_terms=None):
    """
    Универсальная функция для вывода таблиц с динамическим выравниванием:
    - Если переданы headers и rows, выводит карту Карно.
    - Если переданы table, orig_terms и imp_terms, выводит таблицу покрытия.
    """

    def get_column_widths(data):
        """Вычисляет максимальную длину текста в каждом столбце."""
        widths = []
        for col in range(len(data[0])):
            widths.append(max(len(str(row[col])) for row in data))
        return widths

    if headers is not None and rows is not None:
        # Вывод карты Карно
        print("Карта Карно:")
        all_data = [headers] + rows
        widths = get_column_widths(all_data)

        # Формируем заголовок
        header = " " * widths[0] + " | " + " | ".join(str(h).rjust(widths[i + 1]) for i, h in enumerate(headers[1:]))
        print(header)
        print("-" * len(header))

        # Выводим строки
        for row in rows:
            row_str = f"{str(row[0]):<{widths[0]}} | " + " | ".join(
                str(cell).rjust(widths[i + 1]) for i, cell in enumerate(row[1:]))
            print(row_str)

    elif table is not None and orig_terms is not None and imp_terms is not None:
        # Вывод таблицы покрытия
        print("Таблица покрытия:")
        # Подготовка данных: заголовки + строки
        all_data = [["Импликанты"] + orig_terms] + [[imp] + ["X" if t in table[imp] else " " for t in orig_terms] for
                                                    imp in imp_terms]
        widths = get_column_widths(all_data)

        # Формируем заголовок
        header = " | ".join(str(col).ljust(widths[i]) for i, col in enumerate(all_data[0]))
        print(header)
        print("-" * len(header))

        # Выводим строки
        for row in all_data[1:]:
            row_str = " | ".join(str(cell).ljust(widths[i]) for i, cell in enumerate(row))
            print(row_str)

    else:
        raise ValueError(
            "Неверные аргументы для print_table: укажите либо headers и rows, либо table, orig_terms и imp_terms")
from src.boolean_function import BooleanFunction
from src.calculation_minimizer import CalculationMinimizer
# from src.utils import glue_terms


# Функция для вывода таблицы истинности
def print_truth_table(variables, table, output_name):
    print(f"\nТаблица истинности для {output_name}:")
    header = ' | '.join(variables) + f' | {output_name}'
    print(header)
    print('-' * len(header))
    for values, result in table:
        row = ' | '.join(map(str, values)) + ' | ' + str(result)
        print(row)


# Переменные
variables = ['a', 'b', 'c']  # a=Q2, b=Q1, c=Q0

# Таблицы истинности (вручную)
truth_tables = {
    'T2': [
        ((0, 0, 0), 0),
        ((0, 0, 1), 0),
        ((0, 1, 0), 0),
        ((0, 1, 1), 1),
        ((1, 0, 0), 0),
        ((1, 0, 1), 0),
        ((1, 1, 0), 0),
        ((1, 1, 1), 1)
    ],
    'T1': [
        ((0, 0, 0), 0),
        ((0, 0, 1), 1),
        ((0, 1, 0), 0),
        ((0, 1, 1), 1),
        ((1, 0, 0), 0),
        ((1, 0, 1), 1),
        ((1, 1, 0), 0),
        ((1, 1, 1), 1)
    ],
    'T0': [
        ((0, 0, 0), 1),
        ((0, 0, 1), 1),
        ((0, 1, 0), 1),
        ((0, 1, 1), 1),
        ((1, 0, 0), 1),
        ((1, 0, 1), 1),
        ((1, 1, 0), 1),
        ((1, 1, 1), 1)
    ]
}

# Вывод таблиц истинности
for output in ['T2', 'T1', 'T0']:
    print_truth_table(variables, truth_tables[output], output)


# Функция для получения СДНФ
def get_sdnf(table, variables):
    terms = []
    for values, result in table:
        if result == 1:
            term = []
            for var, val in zip(variables, values):
                if val == 0:
                    term.append(f"¬{var}")
                else:
                    term.append(var)
            terms.append(''.join(term))
    return terms


# Функция для получения СКНФ
def get_sknf(table, variables):
    terms = []
    for values, result in table:
        if result == 0:
            term = []
            for var, val in zip(variables, values):
                if val == 1:
                    term.append(f"¬{var}")
                else:
                    term.append(var)
            terms.append(f"({'∨'.join(term)})")
    return terms


# Получение и минимизация логических функций
results = {}
for output in ['T2', 'T1', 'T0']:
    # СДНФ
    sdnf_terms = get_sdnf(truth_tables[output], variables)
    formatted_sdnf = ' ∨ '.join(f"({'∧'.join(t)})" for t in sdnf_terms) if sdnf_terms else '0'
    print(f"\nСДНФ для {output}: {formatted_sdnf}")

    # Минимизация СДНФ
    if sdnf_terms:
        bf = BooleanFunction(sdnf_terms, is_dnf=True)
        minimizer = CalculationMinimizer(bf)
        result = minimizer.minimize()
        results[output] = result["result"]
        print(f"Минимизированная СДНФ для {output}: {result['result']}")
    else:
        results[output] = "0"
        print(f"Минимизированная СДНФ для {output}: 0")

    # СКНФ
    sknf_terms = get_sknf(truth_tables[output], variables)
    formatted_sknf = ' ∧ '.join(sknf_terms) if sknf_terms else '1'
    print(f"СКНФ для {output}: {formatted_sknf}")

# Реализация в базисе НЕ, И-ИЛИ
print("\nРеализация в базисе НЕ, И-ИЛИ:")
for output, expr in results.items():
    if expr == "0":
        print(f"{output} = 0")
    elif expr == "1":
        print(f"{output} = 1")
    else:
        # Заменяем ∧ на AND, ∨ на OR, ¬ на NOT
        # expr = expr.replace('∧', ' AND ').replace('∨', ' OR ').replace('¬', 'NOT ')
        print(f"{output} = {expr}")
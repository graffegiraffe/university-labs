import networkx as nx
import matplotlib.pyplot as plt

# Создаем пустой граф
print("Выберите тип графа:")
print("1. Неориентированный граф")
print("2. Ориентированный граф")
graph_type = int(input('Ваш выбор (1 или 2): '))
graph = nx.DiGraph() if graph_type == 2 else nx.Graph()


def show_graph(g):
    pos = nx.spring_layout(g)
    nx.draw(g, pos, with_labels=True, node_color='lightblue', edge_color='gray')
    plt.show()


def add_node(g):
    node_name = input('Введите название нового узла: ')
    g.add_node(node_name)
    print(f"Узел {node_name} добавлен.")


def add_edge(g):
    node1 = input('Введите первый узел: ')
    node2 = input('Введите второй узел: ')
    if node1 == node2 and isinstance(g, nx.Graph):
        g.add_edge(node1, node2)
        print(f"Петля для узла {node1} добавлена.")
    else:
        g.add_edge(node1, node2)
        print(f"Ребро между {node1} и {node2} добавлено.")


def connect_components(g):
    if nx.is_connected(g):
        print("Граф уже связный.")
    else:
        components = list(nx.connected_components(g))
        for i in range(len(components) - 1):
            g.add_edge(list(components[i])[0], list(components[i + 1])[0])
        print("Граф приведен к связному.")


def hamiltonian_cycles(g):
    if not nx.is_directed(g):
        g = g.to_directed()
    cycles = list(nx.simple_cycles(g))
    hamiltonian_cycles = [cycle for cycle in cycles if len(cycle) == len(g.nodes())]
    print("Гамильтоновы циклы:", hamiltonian_cycles if hamiltonian_cycles else "Циклов нет.")


def calculate_graph_properties(g):
    if nx.is_connected(g):
        print(f"Диаметр графа: {nx.diameter(g)}")
        print(f"Радиус графа: {nx.radius(g)}")
        print(f"Центр графа: {nx.center(g)}")
    else:
        print("Граф не связный, невозможно вычислить диаметр, радиус и центр.")


def graph_products():
    G1 = nx.Graph()
    G1.add_edges_from([(1, 2), (2, 3)])
    G2 = nx.Graph()
    G2.add_edges_from([(4, 5), (5, 6)])
    tensor_prod = nx.tensor_product(G1, G2)
    cartesian_prod = nx.cartesian_product(G1, G2)
    print("Тензорное произведение:", list(tensor_prod.edges()))
    print("Декартово произведение:", list(cartesian_prod.edges()))

def is_tree(g):
    is_tree = nx.is_tree(g)
    print("Граф является деревом:", is_tree)


def to_binary_tree(g):
        if nx.is_tree(g):
            print("Граф уже является деревом.")
            return g

        if not nx.is_connected(g):
            connect_components(g)

        # Преобразование в дерево (построение остовного дерева)
        tree = nx.minimum_spanning_tree(g)

        # Преобразование в бинарное дерево
        binary_tree = nx.DiGraph()
        root = list(tree.nodes())[0]  # Выбираем корень дерева
        binary_tree.add_node(root)

        def _to_binary_tree_recursive(node):
            children = list(tree.neighbors(node))
            if len(children) > 0:
                left_child = children[0]
                binary_tree.add_edge(node, left_child)
                _to_binary_tree_recursive(left_child)

            if len(children) > 1:
                right_child = children[1]
                binary_tree.add_edge(node, right_child)
                _to_binary_tree_recursive(right_child)

            # Удаляем "лишние" ребра, если детей больше двух
            for child in children[2:]:
                tree.remove_edge(node, child)

        _to_binary_tree_recursive(root)

        print("Граф преобразован в бинарное дерево.")
        return binary_tree


# Основное меню
while True:
    if plt.fignum_exists(1):
        plt.close(1)

    print("\n--- Меню ---")
    print("1. Отобразить граф")
    print("2. Добавить узел")
    print("3. Добавить ребро/петлю")
    print("4. Нахождение гамильтоновых циклов")
    print("5. Диаметр, радиус, центр графа")
    print("6. Тензорное и декартово произведение графов")
    print("7. Является ли граф деревом?")
    print("8. Преобразование в дерево")
    print("9. Преобразование в бинароное дерево")
    print("10. Выход")

    choice = input("Ваш выбор: ")

    if choice == '1':
        show_graph(graph)
    elif choice == '2':
        add_node(graph)
    elif choice == '3':
        add_edge(graph)
    elif choice == '4':
        hamiltonian_cycles(graph)
    elif choice == '5':
        calculate_graph_properties(graph)
    elif choice == '6':
        graph_products()
    elif choice == '7':
        is_tree(graph)
    elif choice == '8':
        graph = nx.minimum_spanning_tree(graph.copy())  # Преобразование в дерево
        print("Граф преобразован в дерево.")
    elif choice == '9':
        graph = to_binary_tree(graph.copy())
    elif choice == '10':
        print("Выход из программы.")
        break
    else:
        print("Неверный ввод, попробуйте снова.")

#include <iostream>
#include <stack>
#include <string>

using namespace std;

int prioritet(char op) { //Определяет приоритет операторов
    if (op == '+' || op == '-')
        return 1;
    if (op == '*' || op == '/')
        return 2;
    return 0;
}

bool isOperator(char c) { //Проверяет, является ли символ оператором
    return c == '+' || c == '-' || c == '*' || c == '/';
}

bool isOperand(char c) { //Проверяет, является ли символ операндом (буквой от 'a' до 'z')
    return (c >= 'a' && c <= 'z');
}

double applyOp(double a, double b, char op) {
    switch (op) {
        case '+': return a + b;
        case '-': return a - b;
        case '*': return a * b;
        case '/': return a / b;
        default: return 0;
    }
}

float evaluatePostfix(const string& expression, const float variables[]) {
    stack<double> operands; //Создает стек

    for (char c : expression) {
        if (isOperand(c)) {
            operands.push(variables[c - 'a']);
        }
        else if (isOperator(c)) {
            double operand2 = operands.top();
            operands.pop();
            double operand1 = operands.top();
            operands.pop();
            operands.push(applyOp(operand1, operand2, c));
        }
    }
    return operands.top(); //Возвращает верхний элемент стека operands после обработки всей строки, что представляет собой результат вычислений.
}

string infixToPostfix(const string& expression) {
    string postfix = "";
    stack<char> operators;

    for (char c : expression) {
        if (c == ' ')
            continue;
        if (isOperand(c)) {
            postfix += c;
        }
        else if (c == '(') {
            operators.push(c);
        }
        else if (c == ')') {
            while (!operators.empty() && operators.top() != '(') {
                postfix += operators.top();
                operators.pop();
            }
            operators.pop();
        }
        else {
            while (!operators.empty() && prioritet(operators.top()) >= prioritet(c)) {
                postfix += operators.top();
                operators.pop();
            }
            operators.push(c);
        }
    }

    while (!operators.empty()) {
        postfix += operators.top();
        operators.pop();
    }

    return postfix; //результат перевода в постфиксную нотацию.
}


int main() {
    string infix_expression = "(a*(b-c)/(d+e))";
    float variables[5] = {1.6, 4.9, 5.7, 0.8, 2.3};

    string postfix_expression = infixToPostfix(infix_expression);
    cout << "Postfix expression: " << postfix_expression << endl;

    float result = evaluatePostfix(postfix_expression, variables);
    cout << "Result: " << result << endl;

    return 0;
}


//
// Created by Катюша Рублевская on 19.01.24.
//
#include <string>

#ifndef LABA1_SEM2_RBTREE_H
#define LABA1_SEM2_RBTREE_H
using namespace std;
struct Node {
    int data;
    Node* parent; //Это указатель на родительский узел в дереве.
    // Если узел является корнем дерева, то значение этого поля будет равно nullptr.
    Node* left;
    Node* right;
    int color;
};

typedef Node* NodePtr;


    class RBTree {
    private:
        NodePtr root;
        NodePtr TNULL;

        void initializeNULLNode(NodePtr, NodePtr);

        NodePtr searchTreeHelper(NodePtr, int);

        void fixDelete(NodePtr);

        void rbTransplant(NodePtr, NodePtr);

        void deleteNodeHelper(NodePtr, int);

        void fixInsert(NodePtr);

        void printHelper(NodePtr, string, bool);

    public:
        NodePtr getRoot();

        RBTree();

        NodePtr searchTree(int);

        NodePtr minimum(NodePtr);

        NodePtr maximum(NodePtr);

        NodePtr successor(NodePtr);

        NodePtr predecessor(NodePtr);

        void leftRotate(NodePtr);

        void rightRotate(NodePtr);

        void insert(int);

        void deleteNode(int);

        void prettyPrint();

        NodePtr findClosestGreater(int);

        NodePtr findClosestSmaller(int);

    };
#endif //LABA1_SEM2_RBTREE_H

#include <iostream>
#include "RBTree.h"
using namespace std;



int main() {
    RBTree bst;
     bst.insert(8);
     bst.prettyPrint();
     cout << endl;
     bst.insert(18);
     bst.prettyPrint();
     cout << endl;
     bst.insert(5);
     bst.prettyPrint();
     cout << endl;
     bst.insert(15);
     bst.prettyPrint();
     cout << endl;
     bst.insert(17);
     bst.prettyPrint();
     cout << endl;
     bst.insert(25);
     bst.prettyPrint();
     cout << endl;
     bst.insert(40);
     bst.prettyPrint();
     cout << endl;
     bst.insert(80);
     bst.prettyPrint();
     cout << endl;
     bst.deleteNode(25);
     bst.prettyPrint();
     cout << endl;
     bst.deleteNode(17);
     bst.insert(25);
     bst.insert(17);
     bst.deleteNode(15);
     bst.insert(15);
     bst.insert(20);
     bst.insert(10);
     bst.insert(6);
    /* bst.insert(13);
    bst.insert(8);
    bst.insert(1);
    bst.insert(6);
    bst.insert(11);
    bst.insert(17);
    bst.insert(25);
    bst.insert(22);
    bst.insert(27);
    bst.insert(15);*/

     bst.prettyPrint();
      cout << endl;
     (bst.searchTree(22)) ? cout << "success" : cout << "not found";
     cout << endl;
     cout << "minimum:" << bst.minimum(bst.getRoot())->data << endl;
     cout << "maxsimum:" <<bst.maximum(bst.getRoot())->data << endl;
     cout <<"nearest larger:" << bst.findClosestGreater(8)->data << endl;
     cout <<"nearest smaller:" << bst.findClosestSmaller(8)->data << endl;
     return 0;
 }
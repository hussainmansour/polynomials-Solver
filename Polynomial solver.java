import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

interface IPolynomialSolver {

    void setPolynomial(char poly, int[][] terms);

    String print(char poly);

    void clearPolynomial(char poly);

    float evaluatePolynomial(char poly, float value);

    int[][] add(char poly1, char poly2);

    int[][] subtract(char poly1, char poly2);

    int[][] multiply(char poly1, char poly2);
}

interface ILinkedList1 {

    public void add(int index, Object element);

    public void add(Object element);

    public Object get(int index);

    public void set(int index, Object element);

    public void clear();

    public boolean isEmpty();

    public void remove(int index);

    public int size();

    public ILinkedList1 sublist(int fromIndex, int toIndex);

    public boolean contains(Object o);
}

class SingleLinkedList1 implements ILinkedList1 {

    class Node {
        Object data;
        Node next;

    }

    Node head = null;
    static int size = 0;

    public void add(Object element) {
        size++;
        Node current = new Node();
        current.data = element;
        current.next = null;
        Node temp = head;
        if (head == null) {
            head = current;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = current;
        }
    }

    public void add(int index, Object element) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        size++;
        Node current = new Node();
        current.data = element;
        Node temp = head;
        Node prev = head;

        if (index == 0) {
            current.next = head;
            head = current;
        } else {
            for (int i = 0; i < index; i++) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = current;
            current.next = temp;
        }
    }

    public Object get(int index) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        return temp.data;
    }

    public void set(int index, Object element) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        temp.data = element;

    }

    public int size() {
        int size = 0;

        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;

    }

    public void clear() {
        size = 0;
        head = null;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }

    public void remove(int index) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }
        size--;
        Node current = head;
        Node prev = head;
        if (index == 0) {
            head = head.next;

        } else {
            int i = 0;
            while (i != index) {
                prev = current;
                current = current.next;
                i++;
            }
            prev.next = current.next;
        }

    }

    public boolean contains(Object o) {

        if (size == 0) {
            System.out.println("Error");
            System.exit(0);
        }

        boolean flag = false;
        String c1 = o.toString();
        Node current = head;
        while (current != null) {
            if ((((current.data).toString()).equals(c1))) {
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    public ILinkedList1 sublist(int fromIndex, int toIndex) {

        if ((fromIndex < 0) || (fromIndex >= size) || (toIndex < 0) || (toIndex >= size) || (toIndex < fromIndex)) {
            System.out.println("Error");
            System.exit(0);
        }

        SingleLinkedList1 sub = new SingleLinkedList1();
        Node last = head;
        Node first = head;
        for (int i = 0; i < fromIndex; i++) {
            first = first.next;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            sub.add(first.data);
            first = first.next;
        }
        return sub;
    }

    public void display() {
        Node current = head;
        System.out.print("[");
        for (int i = 0; current != null; ++i) {
            System.out.print(current.data);
            if (current.next != null)
                System.out.print(", ");
            current = current.next;
        }
        System.out.print("]");
    }
}

public class PolynomialSolver implements IPolynomialSolver {

    SingleLinkedList1 list = new SingleLinkedList1();

    public void setPolynomial(char poly, int[][] terms) {

        SingleLinkedList1 innerlist = new SingleLinkedList1();
        for (int i = 0; i < terms[0].length; i++) {
            innerlist.add(terms[0][i]);
        }
        list.add(innerlist);
    }

    public String print(char poly) {
        String eq = " ";
        if (poly == 'A') {
            SingleLinkedList1 ans = new SingleLinkedList1();
            ans = (SingleLinkedList1) list.get(0);
            int size = (int) ans.size();
            String k = "";
            for (int i = 0; i < size; i++) {
                // coeff
                if ((int) ans.get(i) > 0 && i != 0)
                    k = "+" + String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) < 0)
                    k = String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) > 0 && (int) ans.get(i) != 1 && i == 0)
                    k = String.valueOf((int) ans.get(i));
                // exponent
                if (size - i - 1 == 1)
                    eq = eq + k + "x";
                else if (size - i - 1 == 0)
                    eq = eq + k;
                else
                    eq = eq + k + "x^" + String.valueOf(size - i - 1);
            }
        } else if (poly == 'B') {
            SingleLinkedList1 ans = new SingleLinkedList1();
            ans = (SingleLinkedList1) list.get(1);
            int size = (int) ans.size();
            String k = "";
            for (int i = 0; i < size; i++) {
                // coeff
                if ((int) ans.get(i) > 0 && i != 0)
                    k = "+" + String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) < 0)
                    k = String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) > 0 && (int) ans.get(i) != 1 && i == 0)
                    k = String.valueOf((int) ans.get(i));
                // exponent
                if (size - i - 1 == 1)
                    eq = eq + k + "x";
                else if (size - i - 1 == 0)
                    eq = eq + k;
                else
                    eq = eq + k + "x^" + String.valueOf(size - i - 1);
            }
        } else if (poly == 'C') {
            SingleLinkedList1 ans = new SingleLinkedList1();
            ans = (SingleLinkedList1) list.get(2);
            int size = (int) ans.size();
            String k = "";
            for (int i = 0; i < size; i++) {
                // coeff
                if ((int) ans.get(i) > 0 && i != 0)
                    k = "+" + String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) < 0)
                    k = String.valueOf((int) ans.get(i));
                else if ((int) ans.get(i) > 0 && (int) ans.get(i) != 1 && i == 0)
                    k = String.valueOf((int) ans.get(i));
                // exponent
                if (size - i - 1 == 1)
                    eq = eq + k + "x";
                else if (size - i - 1 == 0)
                    eq = eq + k;
                else
                    eq = eq + k + "x^" + String.valueOf(size - i - 1);
            }
        }
        return eq.substring(1);
    }

    public void clearPolynomial(char poly) {
        if (poly == 'A')
            list.remove(0);
        else if (poly == 'B')
            list.remove(1);
        else
            list.remove(2);
    }

    public int[][] add(char poly1, char poly2) {
        int index1;
        int index2;
        if (poly1 == 'A')
            index1 = 0;
        else if (poly1 == 'B')
            index1 = 1;
        else
            index1 = 2;
        if (poly2 == 'A')
            index2 = 0;
        else if (poly2 == 'B')
            index2 = 1;
        else
            index2 = 2;
        SingleLinkedList1 list1 = new SingleLinkedList1();
        SingleLinkedList1 list2 = new SingleLinkedList1();
        list1 = (SingleLinkedList1) list.get(index1);
        list2 = (SingleLinkedList1) list.get(index2);
        int size = (int) list1.size();
        int[][] sum = new int[2][size];
        for (int i = 0; i < size; i++) {
            sum[0][i] = (int) list1.get(i) + (int) list2.get(i);
            sum[1][i] = (sum.length) - 1 - i;
        }
        return sum;
    }

    public int[][] subtract(char poly1, char poly2) {

        int index1;
        int index2;
        if (poly1 == 'A')
            index1 = 0;
        else if (poly1 == 'B')
            index1 = 1;
        else
            index1 = 2;
        if (poly2 == 'A')
            index2 = 0;
        else if (poly2 == 'B')
            index2 = 1;
        else
            index2 = 2;
        SingleLinkedList1 list1 = new SingleLinkedList1();
        SingleLinkedList1 list2 = new SingleLinkedList1();
        list1 = (SingleLinkedList1) list.get(index1);
        list2 = (SingleLinkedList1) list.get(index2);
        int size = (int) list1.size();
        int[][] diff = new int[2][size];
        for (int i = 0; i < size; i++) {
            diff[0][i] = (int) list1.get(i) - (int) list2.get(i);
            diff[1][i] = (diff.length) - 1 - i;
        }
        return diff;
    }

    void print(int[][] arr) {
        SingleLinkedList1 ans = new SingleLinkedList1();
        for (int i = 0; i < arr[0].length; i++) {
            ans.add(arr[0][i]);
        }
        String eq = " ";
        String k = "";
        for (int i = 0; i < arr[0].length; i++) {
            // coeff
            if ((int) ans.get(i) == 0)
                continue;
            else if ((int) ans.get(i) > 0 && i != 0)
                k = "+" + String.valueOf((int) ans.get(i));
            else if ((int) ans.get(i) < 0)
                k = String.valueOf((int) ans.get(i));
            else if ((int) ans.get(i) > 0 && i == 0)
                k = String.valueOf((int) ans.get(i));
            // exponent
            if (arr[0].length - i - 1 == 1)
                eq = eq + k + "x";
            else if (arr[0].length - i - 1 == 0)
                eq = eq + k;
            else if ((int) ans.get(i) == 1 && arr[0].length - i - 1 > 0) {
                if (i == 0)
                    eq = eq + "x^" + String.valueOf(arr[0].length - i - 1);
                else if ((int) ans.get(i) != 0 && (int) ans.get(i - 1) == 0)
                    eq = eq + "x^" + String.valueOf(arr[0].length - i - 1);
                else
                    eq = eq + "+x^" + String.valueOf(arr[0].length - i - 1);
            } else
                eq = eq + k + "x^" + String.valueOf(arr[0].length - i - 1);
        }
        eq = eq.substring(1);
        char a = eq.charAt(0);
        if (a == '+') {
            eq = eq.substring(1);
            System.out.println(eq);
        } else
            System.out.println(eq);
    }

    public int[][] multiply(char poly1, char poly2) {
        int index1;
        int index2;
        if (poly1 == 'A')
            index1 = 0;
        else if (poly1 == 'B')
            index1 = 1;
        else
            index1 = 2;
        if (poly2 == 'A')
            index2 = 0;
        else if (poly2 == 'B')
            index2 = 1;
        else
            index2 = 2;
        SingleLinkedList1 list1 = new SingleLinkedList1();
        SingleLinkedList1 list2 = new SingleLinkedList1();
        SingleLinkedList1 coeff = new SingleLinkedList1();
        SingleLinkedList1 exp = new SingleLinkedList1();
        list1 = (SingleLinkedList1) list.get(index1);
        list2 = (SingleLinkedList1) list.get(index2);
        int size = (int) list1.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int coefficient = (int) list1.get(i) * (int) list2.get(j);
                int exponent = (size - i - 1) + (size - j - 1);
                coeff.add(coefficient);
                exp.add(exponent);
            }
        }
        int max = ((size - 1) * 2);
        int length = coeff.size();
        int[][] eq = new int[2][((size - 1) * 2) + 1];
        int a = 0;
        for (int i = max; i >= 0; i--) {
            eq[1][a] = i;
            for (int j = 0; j < length; j++) {
                if ((int) exp.get(j) == i) {
                    eq[0][a] = eq[0][a] + (int) coeff.get(j);
                }
            }
            a++;
        }
        return eq;
    }

    public float evaluatePolynomial(char poly, float value) {
        int index;
        if (poly == 'A')
            index = 0;
        else if (poly == 'B')
            index = 1;
        else
            index = 2;

        SingleLinkedList1 list1 = new SingleLinkedList1();
        list1 = (SingleLinkedList1) list.get(index);
        int size = list1.size();
        float ans = 0;
        for (int i = 0; i < size; i++) {
            ans += (int) list1.get(i) * Math.pow(value, size - i - 1);
        }
        return ans;
    }

    public static void main(String[] args) {

        SingleLinkedList1 list = new SingleLinkedList1();
        Scanner read = new Scanner(System.in);
        String temp;
        temp = read.nextLine();
        PolynomialSolver equation = new PolynomialSolver();

        try {
            while (temp != "") {

                if (temp.equals("set")) {

                    String s1 = read.nextLine();
                    String s2 = read.nextLine();

                    if ((!(s1.equals("A") || s1.equals("B") || s1.equals("C"))) || s2.equals("[]")) {
                        System.out.println("Error");
                        return;
                    }

                    char poly = s1.charAt(0);

                    String sin = s2.replaceAll("\\[|\\]", "");
                    String[] s = sin.split(",");
                    int[][] terms = new int[2][s.length];

                    for (int i = 0; i < s.length; ++i) {
                        terms[0][i] = Integer.parseInt(s[i]);
                        terms[1][i] = (s.length) - 1 - i;
                    }
                    equation.setPolynomial(poly, terms);
                }
                if (temp.equals("print")) {
                    String s1 = read.nextLine();
                    if (!(s1.equals("A") || s1.equals("B") || s1.equals("C"))) {
                        System.out.println("Error");
                        return;
                    }
                    char poly = s1.charAt(0);
                    String ans = equation.print(poly);
                    System.out.println(ans);
                } else if (temp.equals("add")) {
                    String s1 = read.nextLine();
                    String s2 = read.nextLine();

                    if (!(s1.equals("A") || s1.equals("B") || s1.equals("C") || s2.equals("A") || s2.equals("B")
                            || s2.equals("C"))) {
                        System.out.println("Error");
                        return;
                    }
                    char poly1 = s1.charAt(0);
                    char poly2 = s2.charAt(0);
                    int[][] sum = equation.add(poly1, poly2);
                    equation.print(sum);
                } else if (temp.equals("sub")) {
                    String s1 = read.nextLine();
                    String s2 = read.nextLine();

                    if (!(s1.equals("A") || s1.equals("B") || s1.equals("C") || s2.equals("A") || s2.equals("B")
                            || s2.equals("C"))) {
                        System.out.println("Error");
                        return;
                    }
                    char poly1 = s1.charAt(0);
                    char poly2 = s2.charAt(0);
                    int[][] diff = equation.subtract(poly1, poly2);
                    equation.print(diff);
                } else if (temp.equals("eval")) {
                    String s1 = read.nextLine();
                    if (!(s1.equals("A") || s1.equals("B") || s1.equals("C"))) {
                        System.out.println("Error");
                        return;
                    }
                    char poly = s1.charAt(0);

                    float value = read.nextFloat();
                    System.out.println((int) equation.evaluatePolynomial(poly, value));
                } else if (temp.equals("mult")) {
                    char poly1 = read.next().charAt(0);
                    char poly2 = read.next().charAt(0);

                    if (!(poly1 == 'A' || poly1 == 'B' || poly1 == 'C' || poly2 == 'A' || poly2 == 'B'
                            || poly2 == 'C')) {
                        System.out.println("Error");
                        return;
                    }

                    int[][] mult = equation.multiply(poly1, poly2);
                    equation.print(mult);
                } else if (temp.equals("clear")) {
                    String s1 = read.nextLine();

                    if (!(s1.equals("A") || s1.equals("B") || s1.equals("C"))) {
                        System.out.println("Error");
                        return;
                    }
                    char poly = s1.charAt(0);
                    equation.clearPolynomial(poly);
                    System.out.println("[]");
                } else if (!(temp.equals("set") || temp.equals("print") || temp.equals("add") || temp.equals("sub")
                        || temp.equals("mult") || temp.equals("eval") || temp.equals("clear"))) {
                    System.out.println("Error");
                    System.exit(0);
                }
                temp = read.nextLine();
            }
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
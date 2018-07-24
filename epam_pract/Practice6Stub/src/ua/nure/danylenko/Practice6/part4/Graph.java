package ua.nure.danylenko.Practice6.part4;

import java.util.*;

public class Graph {
    private static int countName = 0;
    private static final String NAME = "Graph-";
    //private String name;
    private int[][] vertices;
    private int[] names;
    private List<Integer> namesList;
    private Set<Integer> set;
    private int dimension;

    public Graph(int n) {
        vertices = new int[n][n];
        names =  new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = i + 1;
        }
        dimension = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i + 1 || j == i - 1) {
                    vertices[i][j] = 1;
                }
            }
        }
    }

    public Graph(List<Integer> list) {
        set = new TreeSet<>(list);
        namesList = new ArrayList<>(set);
        int n = namesList.size();
        names = new int[n];
        vertices = new int[n][n];
        dimension = n;
        for (int i = 0; i < list.size() - 1; i++) {
            vertices[namesList.indexOf(list.get(i))][namesList.indexOf(list.get(i + 1))] = 1;
            vertices[namesList.indexOf(list.get(i + 1))][namesList.indexOf(list.get(i))] = 1;
        }
        copyFromListToArray();
    }

    private void copyFromListToArray(){
        for (int i = 0; i < namesList.size(); i++) {
            names[i] = namesList.get(i);
        }
    }

    private void copyTwoDimArr(int[][] src, int[][] tar){
        int len = src.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                tar[i][j] = src[i][j];
            }
        }
    }

    private void fillTwoDimencionalArray(int n, List<Integer> list){
        vertices = new int[n][n];
        dimension = n;
        for (int i = 0; i < list.size() - 1; i++) {
            vertices[namesList.indexOf(list.get(i))][namesList.indexOf(list.get(i + 1))] = 1;
            vertices[namesList.indexOf(list.get(i + 1))][namesList.indexOf(list.get(i))] = 1;
        }
    }

    public void add(int a, int b) {
        int indexA = namesList.indexOf(a);
        int indexB = namesList.indexOf(b);
        if (indexA != -1 && indexB != -1) {
            vertices[indexA][indexB] = 1;
            vertices[indexA][indexB] = 1;
        } else if (indexA != -1 && indexB == -1) {
            set.add(b);
            namesList = new ArrayList<>(set);
            copyFromListToArray();
            indexA = namesList.indexOf(a);
            indexB = namesList.indexOf(b);
            int[][] temp = new int[++dimension][dimension];
            vertices = new int[dimension][dimension];
            copyTwoDimArr(temp, vertices);
            vertices[indexA][indexB] = 1;
            vertices[indexA][indexB] = 1;
        } else if (indexA == -1 && indexB != -1) {
            set.add(a);
            namesList = new ArrayList<>(set);
            names = new int[namesList.size()];
            copyFromListToArray();
            indexA = namesList.indexOf(a);
            indexB = namesList.indexOf(b);
            int[][] temp = new int[++dimension][dimension];
            vertices = new int[dimension][dimension];
            copyTwoDimArr(temp, vertices);
            vertices[indexA][indexB] = 1;
            vertices[indexA][indexB] = 1;
        } else {
            set.add(a);
            set.add(b);
            namesList = new ArrayList<>(set);
            names = new int[namesList.size()];
            copyFromListToArray();
            indexA = namesList.indexOf(a);
            indexB = namesList.indexOf(b);
            int[][] temp = new int[++dimension + 1][++dimension];
            vertices = new int[dimension][dimension];
            copyTwoDimArr(temp, vertices);
            vertices[indexA][indexB] = 1;
            vertices[indexA][indexB] = 1;
        }
    }

    void print() {
        System.out.print("  ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(names[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(names[i] + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(vertices[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        /*Graph g = new Graph(5);
        g.print();*/
        //System.out.println("====================");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        //list.add(2);
        list.add(3);
        //list.add(2);
        //list.add(4);
        list.add(5);
        Graph gr = new Graph(list);
        gr.print();
    }
}


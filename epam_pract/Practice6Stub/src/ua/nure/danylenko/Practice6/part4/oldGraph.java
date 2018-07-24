package ua.nure.danylenko.Practice6.part4;

import java.util.*;

class oldGraph {

    private static int countName = 0;
    private static final String NAME = "Graph-";

    private String name;
    private Set<Vertex> vertices;
    private List<Integer> namesList;
    private int[] names;
    private int[][] matrix;
    private int dimension;

    public oldGraph(int n) {
        name = NAME + countName++;
        vertices = new TreeSet<>() {
            @Override
            public boolean contains(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return false;
                }
                for (Vertex ob : this) {
                    if (ob.name == ((int) o) ){
                        return true;
                    }
                }
                return false;
            }
        };
        for (int i = 1; i < n; i++) {
            if (i == 1 && n > 2) {
                vertices.add(new Vertex(i, new int[]{i + 1}));
            } else if (i == n - 1) {
                vertices.add(new Vertex(i, new int[]{i - 1}));
            } else {
                vertices.add(new Vertex(i, new int[]{i - 1, i + 1}));
            }
        }
    }

    public oldGraph(List<Integer> list) {
        name = NAME + countName++;
        int n = list.size();
        vertices = new TreeSet<>(){
            @Override
            public boolean contains(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return false;
                }
                for (Vertex ob : this) {
                    if (ob.name == ((int) o) ){
                        return true;
                    }
                }
                return false;
            }
        };
        for (int i = 0; i < n; i++) {
            if (i == 0 && n > 1) {
                vertices.add(new Vertex(list.get(i), new int[]{list.get(i + 1)}));
            } else if (i == n - 1) {
                vertices.add(new Vertex(list.get(i), new int[]{list.get(i - 1)}));
            } else {
                vertices.add(new Vertex(list.get(i), new int[]{list.get(i - 1), list.get(i + 1)}));
            }
        }
    }

    public void fillMatrix(){
        int len = vertices.size();
        matrix = new int[len][len];
        namesList = new ArrayList<>(len);
        names = new int[len];
        dimension = len;
        //int pos = 0;
        for (Vertex v : vertices) {
            namesList.add(v.name);
        }
        for (Vertex v : vertices) {
            int[] arr = new int[v.links.size()];
            for (int i = 0; i < v.links.size(); i++) {
                arr[i] = v.links.get(i);
            }

            for (int i = 0; i < arr.length; i++) {
                matrix[namesList.indexOf(v.name)][namesList.indexOf(arr[i])] = 1;
                matrix[namesList.indexOf(arr[i])][namesList.indexOf(v.name)] = 1;
            }
            //pos++;
        }
        for (int i = 0; i < namesList.size(); i++) {
            names[i] = namesList.get(i);
        }
    }

    public void print() {
        fillMatrix();
        System.out.print("  ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(names[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(names[i] + " ");
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void add(int a, int b) {
        boolean containsA = vertices.contains(a);
        boolean containsB = vertices.contains(b);
        List<Vertex> list = new ArrayList<>(vertices){
            @Override
            public int indexOf(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return -1;
                }
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).name == ((int) o)){
                        return i;
                    }
                }
                return -1;
            }
        };

        if (containsA || containsB) {
            if (containsA) {
                Vertex v = list.get(list.indexOf(a));
                v.addPoint(b);
            }
            if (containsB) {
                Vertex v = list.get(list.indexOf(b));
                v.addPoint(a);
            }
        } else {
            list.add(new Vertex(a, new int[]{b}));
            list.add(new Vertex(b, new int[]{a}));
        }
        vertices = new TreeSet<Vertex>(list){
            @Override
            public boolean contains(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return false;
                }
                for (Vertex ob : this) {
                    if (ob.name == ((int) o) ){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public void remove(int a, int b) {
        boolean containsA = vertices.contains(a);
        boolean containsB = vertices.contains(b);
        List<Vertex> list = new ArrayList<>(vertices){
            @Override
            public int indexOf(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return -1;
                }
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).name == ((int) o)){
                        return i;
                    }
                }
                return -1;
            }
        };

        if (containsA && containsB) {
            if (containsA) {
                Vertex v = list.get(list.indexOf(a));
                v.removePoint(b);
            }
            if (containsB) {
                Vertex v = list.get(list.indexOf(b));
                v.removePoint(a);
            }
        }
        vertices = new TreeSet<Vertex>(list){
            @Override
            public boolean contains(Object o) {
                if (o == null || !(o instanceof Integer)) {
                    return false;
                }
                for (Vertex ob : this) {
                    if (ob.name == ((int) o) ){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private class Vertex implements Comparable<Vertex>{
        int name;
        List<Integer> links;

        private Vertex(int n, int[] arr){
            links = new ArrayList<>(arr.length);
            name = n;
            for (int i : arr){
                links.add(i);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return name == vertex.name;
        }

        private void addPoint(int point) {
           int index = links.indexOf(point);
           if (index == -1) {
               links.add(point);
           }
        }

        private void removePoint(int point) {
            int index = links.indexOf(point);
            if (index != -1) {
                links.remove(index);
            }
        }

        @Override
        public int compareTo(Vertex o) {
            return name - o.name;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        //oldGraph g = new oldGraph(5);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(9);
        oldGraph g = new oldGraph(list);
        g.print();
        System.out.println("~~~~~~~~~");
        g.add(1, 9);
        g.add(1, 1);
        g.print();
        g.remove(1, 9);
        g.remove(9, 9);
        g.remove(2, 1);
        System.out.println("~~~~~~~~~");
        g.print();
    }

}

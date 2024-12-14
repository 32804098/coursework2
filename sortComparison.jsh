int cardCompare(String card1, String card2) {
        String[] suits = {"H", "C", "D", "S"};
        int suit1 = Arrays.asList(suits).indexOf(card1.substring(1));
        int suit2 = Arrays.asList(suits).indexOf(card2.substring(1));
        if (suit1 != suit2) {
                return suit1 - suit2;
        }
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int value1 = Arrays.asList(values).indexOf(card1.substring(0, 1));
        int value2 = Arrays.asList(values).indexOf(card2.substring(0, 1));
        return value1 - value2;
}
void bubbleSort(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size() - i - 1; j++) {
                        if (cardCompare(list.get(j), list.get(j + 1)) > 0) {
                                String temp = list.get(j);
                                list.set(j, list.get(j + 1));
                                list.set(j + 1, temp);
                        }
                }
        }
}
void mergeSort(ArrayList<String> list) {
        if (list.size() > 1) {
                int middle = list.size() / 2;
                ArrayList<String> left = new ArrayList<>(list.subList(0, middle));
                ArrayList<String> right = new ArrayList<>(list.subList(middle, list.size()));
                mergeSort(left);
                mergeSort(right);
                merge(list, left, right);
        }
}
void merge(ArrayList<String> list, ArrayList<String> left, ArrayList<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
                if (cardCompare(left.get(i), right.get(j)) <= 0) {
                        list.set(k, left.get(i++));
                        k++;
                } else {
                        list.set(k, right.get(j++));
                        k++;
                }
        }
        while (i < left.size()) {
                list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
                list.set(k++, right.get(j++));
        }
}
long measureBubbleSort(String filename) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
                reader = Files.newBufferedReader(Paths.get(filename));
                reader.lines().forEach(list::add);
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (reader != null) {
                        try {
                                reader.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
        long startTime = System.nanoTime();
        bubbleSort(list);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
}
long measureMergeSort(String filename) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
                reader = Files.newBufferedReader(Paths.get(filename));
                reader.lines().forEach(list::add);
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (reader != null) {
                        try {
                                reader.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
        long startTime = System.nanoTime();
        mergeSort(list);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
}
void sortComparison(String[] filenames) {
        BufferedWriter writer = null;
        try {
                writer = Files.newBufferedWriter(Paths.get("sortComparison.csv"));
                writer.write(", 10, 100, 10000");
                writer.newLine();
                writer.write("bubbleSort, " + measureBubbleSort(filenames[0]) + ", " + measureBubbleSort(filenames[1]) + ", " + measureBubbleSort(filenames[2]));
                writer.newLine();
                writer.write("mergeSort, " + measureMergeSort(filenames[0]) + ", " + measureMergeSort(filenames[1]) + ", " + measureMergeSort(filenames[2]));
                writer.newLine();
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                if (writer != null) {
                        try {
                                writer.close();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
}
sortComparison(new String[]{"sort10.txt", "sort100.txt", "sort10000.txt"});
sortComparison(new String[]{"sort10.txt", "sort100.txt", "sort10000.txt"});
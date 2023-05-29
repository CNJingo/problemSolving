import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    Map<String, Cell> table = new HashMap<>();

    public String[] solution(String[] commands) {
        String[] answer = {};

        List<String> result = new ArrayList<>();

        for (int col = 1; col <= 50; col++) {
            for (int row = 1; row <= 50; row++) {
                String position = row + "," + col;
                table.put(position, new Cell(row, col));
            }
        }

        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");

            String instruction = command[0];

            switch (instruction) {
                case "UPDATE":
                    if (command.length == 4) {
                        String position = command[1] + "," + command[2];
                        Cell cell = table.get(position);
                        find(cell).updateValue(command[3]);
                    } else {
                        for (var entry : table.entrySet()) {
                            if (entry.getValue().getValue() == null) {
                                continue;
                            }

                            if (entry.getValue().getValue().equals(command[1])) {
                                entry.getValue().updateValue(command[2]);
                            }
                        }
                    }
                    break;

                case "MERGE":
                    String position1 = command[1] + "," + command[2];
                    String position2 = command[3] + "," + command[4];
                    Cell firstCell = table.get(position1);
                    Cell secondCell = table.get(position2);

                    if (find(firstCell).equals(find(secondCell))) {
                        break;
                    } else {
                        unionCell(firstCell, secondCell);
                    }
                    break;
                case "UNMERGE":
                    String position = command[1] + "," + command[2];
                    Cell cell = table.get(position);
                    unmerge(cell);
                    break;

                case "PRINT":
                    Cell printCell = table.get(command[1] + "," + command[2]);
                    String value = printFinalValue(printCell);
                    if (value == null) {
                        result.add("EMPTY");
                    } else {
                        result.add(value);
                    }
                    break;
            }
        }


        return result.toArray(new String[0]);
    }

    private String printFinalValue(Cell cell) {
        if (!cell.getParent().equals(cell)) {
            return printFinalValue(cell.getParent());
        }
        return cell.getValue();
    }

    private void unmerge(Cell cell) {
        cell.updateValue(find(cell).getValue());
        List<Cell> updateList = new ArrayList<>();
        for (var entry : table.entrySet()) {
            if (find(entry.getValue()).equals(find(cell)) && !entry.getValue().equals(cell)) {
                updateList.add(entry.getValue());
            }
        }
        for (Cell updateCell : updateList) {
            updateCell.setParent(updateCell);
            updateCell.updateValue(null);
        }

        cell.setParent(cell);
    }

    private void unionCell(Cell firstCell, Cell secondCell) {
        Cell cell1 = find(firstCell);
        Cell cell2 = find(secondCell);

        if (cell1.getValue() != null) {
            cell2.setParent(cell1);
        } else if ( cell2.getValue() != null) {
            cell1.setParent(cell2);
        } else {
            cell2.setParent(cell1);
        }
    }

    private Cell find(Cell cell) {
        if (cell.getParent() == cell) {
            return cell;
        }
        cell.setParent(find(cell.getParent()));

        return cell.getParent();
    }

    private void printTable(int size) {
        for (int i = 1 ; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                String position = i + "," + j;
                if (table.get(position).getValue() == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(table.get(position).getValue() + " ");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] result = solution.solution(new String[]{"UPDATE 1 1 A", "UPDATE 2 2 B", "UPDATE 3 3 C", "UPDATE 4 4 D", "MERGE 1 1 2 2", "MERGE 3 3 4 4", "MERGE 1 1 4 4", "UNMERGE 3 3", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3", "PRINT 4 4"});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }



    class Cell {
        private Cell parent;

        private int row;
        private int col;

        private String value;

        public Cell(int row, int col) {
            this.parent = this;
            this.row = row;
            this.col = col;
            this.value = null;
        }

        public Cell getParent() {
            return parent;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public String getValue() {
            return value;
        }

        public void updateValue(String newValue) {
            this.value = newValue;
        }

        public void setParent(Cell parent) {
            this.parent = parent;
        }
    }
}

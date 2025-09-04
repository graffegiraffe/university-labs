package org.example;

import java.util.*;
public class KarnaughMapProcessor {
    private final boolean isConjunctive;
    private final List<String> variables;
    private final List<int[]> truthTableRows;
    private List<List<Map<String, Object>>> mapData;
    private boolean validProcessing;

    public KarnaughMapProcessor(List<String> variables, List<int[]> truthTableRows, boolean isConjunctive) {
        this.isConjunctive = isConjunctive;
        this.variables = variables;
        this.truthTableRows = truthTableRows;
        this.validProcessing = true;
        this.mapData = new ArrayList<>();

        initializeMap();
    }

    private void initializeMap() {
        if (truthTableRows == null || truthTableRows.isEmpty()) {
            validProcessing = false;
            return;
        }

        int varCount = variables.size();
        if (varCount == 2) {
            initialize2VarMap();
        } else if (varCount == 3) {
            initialize3VarMap();
        } else if (varCount == 4) {
            initialize4VarMap();
        } else {
            validProcessing = false;
        }
    }

    private void initialize2VarMap() {
        mapData = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            List<Map<String, Object>> row = new ArrayList<>(2);
            for (int j = 0; j < 2; j++) {
                row.add(createEmptyCell());
            }
            mapData.add(row);
        }

        for (int[] row : truthTableRows) {
            int y = row[0];
            int x = row[1];
            int cellValue = row[2];

            Map<String, Object> cell = mapData.get(y).get(x);
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(0), row[0]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(1), row[1]});
            cell.put("result", cellValue);
        }
    }

    private void initialize3VarMap() {
        mapData = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            List<Map<String, Object>> row = new ArrayList<>(4);
            for (int j = 0; j < 4; j++) {
                row.add(createEmptyCell());
            }
            mapData.add(row);
        }

        for (int[] row : truthTableRows) {
            int rowIndex = row[0];
            int colIndex = calculatePositionIndex(row[1], row[2]);
            int cellValue = row[3];

            Map<String, Object> cell = mapData.get(rowIndex).get(colIndex);
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(0), row[0]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(1), row[1]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(2), row[2]});
            cell.put("result", cellValue);
        }
    }

    private void initialize4VarMap() {
        mapData = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            List<Map<String, Object>> row = new ArrayList<>(4);
            for (int j = 0; j < 4; j++) {
                row.add(createEmptyCell());
            }
            mapData.add(row);
        }

        for (int[] row : truthTableRows) {
            int rowIndex = calculatePositionIndex(row[0], row[1]);
            int colIndex = calculatePositionIndex(row[2], row[3]);
            int cellValue = row[4];

            Map<String, Object> cell = mapData.get(rowIndex).get(colIndex);
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(0), row[0]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(1), row[1]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(2), row[2]});
            ((List<Object[]>) cell.get("vars")).add(new Object[]{variables.get(3), row[3]});
            cell.put("result", cellValue);
        }
    }

    private int calculatePositionIndex(int primary, int secondary) {
        if (primary == 0) {
            return secondary;
        } else {
            return secondary == 0 ? 3 : 2;
        }
    }

    private Map<String, Object> createEmptyCell() {
        Map<String, Object> cell = new HashMap<>();
        cell.put("vars", new ArrayList<Object[]>());
        cell.put("result", null);
        cell.put("processed", false);
        return cell;
    }

    public BooleanExpressionMinimizer computeMinimizedForm() {
        if (!validProcessing) {
            return new BooleanExpressionMinimizer(isConjunctive);
        }

        List<int[]> possibleRegions = Arrays.asList(
                new int[]{4, 4}, new int[]{2, 4}, new int[]{4, 2},
                new int[]{2, 2}, new int[]{1, 4}, new int[]{4, 1},
                new int[]{1, 2}, new int[]{2, 1}, new int[]{1, 1}
        );

        List<int[]> identifiedRegions = new ArrayList<>();
        for (int[] region : possibleRegions) {
            int width = region[0];
            int height = region[1];

            if (width > mapData.getFirst().size() || height > mapData.size()) {
                continue;
            }

            for (int y = 0; y < mapData.size(); y++) {
                for (int x = 0; x < mapData.getFirst().size(); x++) {
                    if (validateRegion(x, y, width, height)) {
                        identifiedRegions.add(new int[]{x, y, width, height});
                        markRegion(x, y, width, height);
                    }
                }
            }
        }

        BooleanExpressionMinimizer result = new BooleanExpressionMinimizer(isConjunctive);
        for (int[] region : identifiedRegions) {
            int x = region[0];
            int y = region[1];
            int w = region[2];
            int h = region[3];

            List<Object[]> commonVars = extractCommonVars(x, y, w, h);
            if (!commonVars.isEmpty()) {
                result.appendComponent(commonVars);
            }
        }

        return result;
    }

    private boolean validateRegion(int startCol, int startRow, int regionWidth, int regionHeight) {
        boolean hasUnprocessed = false;
        for (int dy = 0; dy < regionHeight; dy++) {
            for (int dx = 0; dx < regionWidth; dx++) {
                int y = (startRow + dy) % mapData.size();
                int x = (startCol + dx) % mapData.getFirst().size();

                Map<String, Object> cell = mapData.get(y).get(x);
                int result = (int) cell.get("result");
                boolean processed = (boolean) cell.get("processed");

                if (result == (isConjunctive ? 1 : 0)) {
                    return false;
                }
                if (!processed) {
                    hasUnprocessed = true;
                }
            }
        }
        return hasUnprocessed;
    }

    private void markRegion(int startCol, int startRow, int regionWidth, int regionHeight) {
        for (int dy = 0; dy < regionHeight; dy++) {
            for (int dx = 0; dx < regionWidth; dx++) {
                int y = (startRow + dy) % mapData.size();
                int x = (startCol + dx) % mapData.getFirst().size();
                mapData.get(y).get(x).put("processed", true);
            }
        }
    }

    private List<Object[]> extractCommonVars(int startCol, int startRow, int regionWidth, int regionHeight) {
        List<Object[]> sharedVariables = new ArrayList<>();
        Map<String, Object> referenceCell = mapData.get(startRow).get(startCol);
        List<Object[]> referenceVars = (List<Object[]>) referenceCell.get("vars");

        for (int varIndex = 0; varIndex < referenceVars.size(); varIndex++) {
            String varName = (String) referenceVars.get(varIndex)[0];
            int varValue = (int) referenceVars.get(varIndex)[1];
            boolean isConsistent = true;

            for (int dy = 0; dy < regionHeight && isConsistent; dy++) {
                for (int dx = 0; dx < regionWidth && isConsistent; dx++) {
                    int y = (startRow + dy) % mapData.size();
                    int x = (startCol + dx) % mapData.getFirst().size();

                    List<Object[]> currentVars = (List<Object[]>) mapData.get(y).get(x).get("vars");
                    Object[] currentVar = currentVars.get(varIndex);

                    if (!currentVar[0].equals(varName) || (int) currentVar[1] != varValue) {
                        isConsistent = false;
                    }
                }
            }

            if (isConsistent) {
                sharedVariables.add(new Object[]{
                        varName,
                        isConjunctive ? varValue == 0 : varValue == 1
                });
            }
        }

        return sharedVariables;
    }

    public void displayMap() {
        for (List<Map<String, Object>> row : mapData) {
            List<Integer> results = new ArrayList<>();
            for (Map<String, Object> cell : row) {
                results.add((Integer) cell.get("result"));
            }
            System.out.println(results);
        }
    }
    public void displayKarnaughMap() {
        System.out.println("Карта Карно:");
        for (List<Map<String, Object>> row : mapData) {
            for (Map<String, Object> cell : row) {
                System.out.print(cell.get("result") + " ");
            }
            System.out.println();
        }
    }
    public void printKarnaughMap() {
        System.out.println("Карта Карно для переменных: " + variables);
        System.out.print("AB\\CD ");
        for (int cd = 0; cd < 4; cd++) {
            System.out.printf("%02d ", cd);
        }
        System.out.println();

        for (int ab = 0; ab < 4; ab++) {
            System.out.printf("%02d   ", ab);
            for (int cd = 0; cd < 4; cd++) {
                int row = calculatePositionIndex(ab >> 1, ab & 1);
                int col = calculatePositionIndex(cd >> 1, cd & 1);
                System.out.print(mapData.get(row).get(col).get("result") + "  ");
            }
            System.out.println();
        }
    }
}

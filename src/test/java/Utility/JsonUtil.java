package Utility;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static Object[][] getData(String nodeName) throws IOException {
        return getData(nodeName, (String[]) null);
    }

    public static Object[][] getData(String nodeName, String... keys) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(
                new File(System.getProperty("user.dir")+"\\testdata\\testdata.json"));

        JsonNode dataNode = rootNode.get(nodeName);

        if (dataNode == null) {
            throw new RuntimeException("Node not found: " + nodeName);
        }

        int rows = dataNode.size();
        if (rows == 0) {
            return new Object[0][0];
        }

        JsonNode firstRow = dataNode.get(0);
        String[] orderedKeys = keys;
        if (orderedKeys == null || orderedKeys.length == 0) {
            orderedKeys = new String[firstRow.size()];
            int index = 0;
            Iterator<String> it = firstRow.fieldNames();
            while (it.hasNext()) {
                orderedKeys[index++] = it.next();
            }
        }
        int cols = orderedKeys.length;

        Object[][] data = new Object[rows][cols];

        for (int i = 0; i < rows; i++) {
            JsonNode rowNode = dataNode.get(i);
            for (int j = 0; j < cols; j++) {
                JsonNode valueNode = rowNode.get(orderedKeys[j]);
                if (valueNode == null) {
                    throw new RuntimeException(
                            "Key '" + orderedKeys[j] + "' not found in node '" + nodeName + "' for row index " + i);
                }
                data[i][j] = valueNode.asText();
            }
        }

        return data;
    }
}

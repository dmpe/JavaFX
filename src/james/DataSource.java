package james;

import java.util.*;

public class DataSource {
	Map<String, String> dataMap = new HashMap<>();

	public DataSource() {
		dataMap.put("key1", "value1");
		dataMap.put("key2", "value2");
		dataMap.put("key3", "value3");
	}

	public Map<String, String> getDataMap() {
		Random generator = new Random();
		int randInt = generator.nextInt();
		dataMap.put("key1", "value" + randInt);
		return dataMap;
	}
}

package com.sapience.admin.automation.dataProvider;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.sapience.admin.automation.factory.Log4JFactory;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestDataProvider {

    @DataProvider(name = "testData")
    public static Object[][] testDataSupplier(Method method) throws IOException {
        try
        {
            Logger logger = Log4JFactory.getLogger("TestDataProvider");

            logger.info("=====================================================================================");
            logger.info("====== Fetch Test Data : START");
            //Reading through yaml
            String envName = System.getProperty("envName");

            Yaml yaml = new Yaml();
            String path="src\\test\\resources\\test-data\\" + envName + "TestData.yaml";
            if(!System.getProperty("os.name").toLowerCase().contains("windows")){
                path =path.replace("\\","/");
            }
            YamlReader reader = new YamlReader(new FileReader(path));
            Object object = reader.read();
            Map map = (Map) object;
//        logger.info("====== Test Method Names : " + map.keySet());

            ArrayList<Map> listOfMaps = new ArrayList<Map>();
            int totalRecords = 0;
            ArrayList<Object> listOfKeys = new ArrayList<Object>(map.keySet());
            Map map1 = null;
            Map map2 = null;

            for (Object i : listOfKeys) {
                if (i.toString().equalsIgnoreCase(method.getName())) {
                    logger.info("=====================================================================================");
                    map1 = (Map) map.get(i);

                    logger.info("====== Test Data availbale for current test method : " + i.toString() + " ===> " + map1.keySet());
                    logger.info("====== Test Data availbale for current test SIZE : " + i.toString() + " ===> " + map1.keySet().size());

                    for (Object itr : map1.keySet()) {
                        map2 = (Map) map1.get(itr);

                        Map<Object, Object> requestMap = new HashMap<>();
                        logger.info("====== " + itr.toString() + " ===> " + map2);
                        for (Object itr2 : map2.keySet()) {
                            requestMap.put(itr2, map2.get(itr2));
                        }
                        listOfMaps.add(requestMap);
                    }
                    logger.info("====== Complete TestData for method : " + i.toString() + " ===> " + listOfMaps);
                    logger.info("====== Fetch Test Data : END");
                    logger.info("=====================================================================================");

                } else {
                    map1 = new HashMap();
                    map2 = new HashMap();
                }
            }

            Object[][] obj = new Object[listOfMaps.size()][1];
            for (int i = 0; i < listOfMaps.size(); i++) {
                obj[i][0] = listOfMaps.get(i);
            }

            return obj;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}

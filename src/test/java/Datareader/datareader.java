package Datareader;

import Components.BaseTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class datareader extends BaseTest {


    @DataProvider(name = "jsonData",
            parallel = true) // Set parallel to true for parallel execution)

    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data= getjsondata();
        Object[][] result = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i);// pass entire HashMap as one object
            //System.out.println(result[i][0]);
        }

        return result;

    }
}

package atmdatabase;

import java.io.IOException;
import java.util.Map;

public interface ReadData {
    public Map<Integer, Customer> readCustomers() throws IOException;
}

package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "GetShopCheckListResult", strict = false)
public class Survey {
    @Element(name = "TaskOperationID", required = false)
    private String TaskOperationID;
    @Element(name = "TaskOperationName", required = false)
    private String TaskOperationName;

    public String getTaskOperationID() {
        return TaskOperationID;
    }

    public void setTaskOperationID(String taskOperationID) {
        TaskOperationID = taskOperationID;
    }

    public String getTaskOperationName() {
        return TaskOperationName;
    }

    public void setTaskOperationName(String taskOperationName) {
        TaskOperationName = taskOperationName;
    }
}

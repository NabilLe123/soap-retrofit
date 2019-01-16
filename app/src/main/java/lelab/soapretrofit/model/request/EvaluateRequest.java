package lelab.soapretrofit.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TaskOperationEvaluation")
public class EvaluateRequest {
    @Element(name = "TaskOperationEvaluationID", required = false)
    private String taskOperationEvaluationID;
    @Element(name = "TaskEvaluationValue", required = false)
    private String taskEvaluationValue;

    public EvaluateRequest(String taskOperationEvaluationID, String taskEvaluationValue) {
        this.taskOperationEvaluationID = taskOperationEvaluationID;
        this.taskEvaluationValue = taskEvaluationValue;
    }

    public String getTaskOperationEvaluationID() {
        return taskOperationEvaluationID;
    }

    public void setTaskOperationEvaluationID(String taskOperationEvaluationID) {
        this.taskOperationEvaluationID = taskOperationEvaluationID;
    }

    public String getTaskEvaluationValue() {
        return taskEvaluationValue;
    }

    public void setTaskEvaluationValue(String taskEvaluationValue) {
        this.taskEvaluationValue = taskEvaluationValue;
    }
}

package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "GetShopCheckListResult", strict = false)
public class Evaluate {
    @Element(name = "EvaluationPercentage", required = false)
    private String evaluationPercentage;
    @Element(name = "EvaluationRating", required = false)
    private String evaluationRating;
    @Element(name = "MinPenaltyAmount", required = false)
    private String minPenaltyAmount;
    @ElementList(name = "Penalties", required = false)
    public List<Penalty> penalties;

    public String getEvaluationPercentage() {
        return evaluationPercentage;
    }

    public void setEvaluationPercentage(String evaluationPercentage) {
        this.evaluationPercentage = evaluationPercentage;
    }

    public String getEvaluationRating() {
        return evaluationRating;
    }

    public void setEvaluationRating(String evaluationRating) {
        this.evaluationRating = evaluationRating;
    }

    public String getMinPenaltyAmount() {
        return minPenaltyAmount;
    }

    public void setMinPenaltyAmount(String minPenaltyAmount) {
        this.minPenaltyAmount = minPenaltyAmount;
    }

    public List<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Penalty> penalties) {
        this.penalties = penalties;
    }
}

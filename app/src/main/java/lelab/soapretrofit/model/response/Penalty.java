package lelab.soapretrofit.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Penalties", strict = false)
public class Penalty {
    @Element(name = "PenaltyID", required = false)
    private String penaltyID;
    @Element(name = "PenaltyCode", required = false)
    private String penaltyCode;
    @Element(name = "PenaltyName", required = false)
    private String penaltyName;
    @Element(name = "MinAmount", required = false)
    private String minAmount;
    @Element(name = "MaxAmount", required = false)
    private String maxAmount;

    public String getPenaltyID() {
        return penaltyID;
    }

    public void setPenaltyID(String penaltyID) {
        this.penaltyID = penaltyID;
    }

    public String getPenaltyCode() {
        return penaltyCode;
    }

    public void setPenaltyCode(String penaltyCode) {
        this.penaltyCode = penaltyCode;
    }

    public String getPenaltyName() {
        return penaltyName;
    }

    public void setPenaltyName(String penaltyName) {
        this.penaltyName = penaltyName;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }
}

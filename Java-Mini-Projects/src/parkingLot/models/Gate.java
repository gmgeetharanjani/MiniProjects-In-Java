package parkingLot.models;

public class Gate {
    private Long id;
    private GateStatus gateStatus;
    private GateType gateType;
    private Operator operator;
    private int gateNumber;

    public Gate(Long id, GateStatus gateStatus, GateType gateType, Operator operator, int gateNumber) {
        this.gateStatus = gateStatus;
        this.gateType = gateType;
        this.operator = operator;
        this.gateNumber = gateNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }
}

package parkingLot.models;

public class Operator {
    private int employee_id;
    private String name;
    private Gate gate;

    public Operator(int employee_id, String name) {
        this.employee_id = employee_id;
        this.name = name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}

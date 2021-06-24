package com.redhat.training;


@ApplicationScoped
public class OperationRegistry {

    @Inject
    Add add;

    @Inject
    Substract substract;

    @Inject
    Multiply multiply;

    @Inject
    Identity identity;

    private List<Operation> operations;

    @PostConstruct
    protected void buildOperationList() {
        setOperations(List.of(substract, add, multiply, identity));
    }

    public List<Operation> getOperations() {
        return operations;
    }

    private void setOperations(final List<Operation> operationsParam) {
        this.operations = operationsParam;
    }

}

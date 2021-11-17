package co.eltrut.differentiate.core.registrator;

public abstract class AbstractHelper implements IHelper {
    protected final Registrator parent;

    public AbstractHelper(Registrator parent) {
        this.parent = parent;
    }

    @Override
    public Registrator getParent() {
        return this.parent;
    }
}
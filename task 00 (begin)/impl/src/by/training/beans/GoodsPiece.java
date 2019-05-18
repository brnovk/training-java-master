package by.training.beans;

/**
 * Description piece goods
 * @author Victor Baranov
 */
public class GoodsPiece {

    private final double costBatch;
    private final long numberInBatch;

    public GoodsPiece(double costBatch, long numberInBatch) {
        this.costBatch = costBatch;
        this.numberInBatch = numberInBatch;
    }

    public double getCostBatch() {
        return costBatch;
    }

    public long getNumberInBatch() {
        return numberInBatch;
    }
}

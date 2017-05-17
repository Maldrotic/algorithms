import java.util.ArrayList;
import java.util.List;

public class Context {

    private Strategy strategy;
    private List<Double> runTimes;

    public Context(Strategy strategy) {
        this.strategy = strategy;
        this.runTimes = new ArrayList<>();
    }

    public void executeAlgorithm(int[] array) throws Exception {
        runTimes.add(strategy.run(array));
    }

    public List<Double> getRunTimes() {
        return runTimes;
    }

    public double getAverageRunTime() {
        double sum = 0.0;
        for (double runTime : runTimes) {
            sum += runTime;
        }
        return runTimes.size() != 0 ? sum / (double) runTimes.size() : 1;
    }

    public boolean hasFailedSort() {
        return strategy.hasFailed();
    }

    public void clearTimes() {
        runTimes.clear();
        strategy.resetHasFailed();
    }

    @Override
    public String toString() {
        return strategy.getName();
    }
}

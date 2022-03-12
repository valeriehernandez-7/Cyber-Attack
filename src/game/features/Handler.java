/**
 * Game object classes and dependencies implementation.
 */
package game.features;

/**
 * Game event handler using play time as reference.
 */
public class Handler {
    private long sample;

    public Handler() {
        sample = System.currentTimeMillis();
    }

    public long getSample() {
        return sample;
    }

    public void setSample(long sample) {
        this.sample = sample;
    }

    public void newSample() {
        sample = System.currentTimeMillis();
    }

    public boolean sampleStatus(int status) {
        if (status < (System.currentTimeMillis() - getSample())) {
            newSample();
            return true;
        }
        return false;
    }
}
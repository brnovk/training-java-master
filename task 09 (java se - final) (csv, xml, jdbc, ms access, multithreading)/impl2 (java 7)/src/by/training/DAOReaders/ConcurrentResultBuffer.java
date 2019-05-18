package by.training.DAOReaders;

import static by.training.Constants.*;
import by.training.beans.Result;

/**
 * Buffer management class in synchronization problem "producer-consumer".
 * @author BaranauViktar
 */
public class ConcurrentResultBuffer implements IConcurentResultDAO {

	/**
	 * Flag checking / setting conditions for access threads.
	 */
    private volatile boolean empty = true;

    /**
     * Link to the producer thread is required to start a thread 
     * in the "threadStart()" and check the conditions of the thread 
     * in the method "hasResult()".
     */
    private Thread threadRead;

    /**
     * Buffer entity.
     */
    private Result result;

    public ConcurrentResultBuffer() {
    }

    @Override
    public synchronized boolean hasResult() {
        while (true) {
            if (empty == false) {
                return true;
            } else if (threadRead.isAlive()) {
                try {
                    wait(DELAY_WAITING_THREAD);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            } else {
                return false;
            }
        }
    }

    /**
     * The consumer thread synchronization method.
     */
    @Override
    public synchronized Result nextResult() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
        empty = true;
        notify();
        debugPrint(LABEL_DEBUG_GET, result);
        return result;
    }

    /**
     * The producer thread synchronization method.
     */
    @Override
    public synchronized void setResult(Result result) {
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
        empty = false;
        this.result = result;
        debugPrint(LABEL_DEBUG_SET, result);
        notify();
    }

    @Override
    public void closeReader() {
        // The implementation of this method does not require
    }

    /**
     * Method start thread-producer.
     */
    @Override
    public void threadStart(Runnable producer) {
        threadRead = new Thread(producer);
        threadRead.start();
    }

    private void debugPrint(String label, Result result) {
    	System.out.println(label + LABEL_DEBUG_DELIMETER + result);
    }
}

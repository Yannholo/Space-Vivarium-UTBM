package spaceVivarium.utils.thread;

//Imports
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class execute a thread
 * 
 * @author Baudouin Dafflon
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class ThreadUtil {

    // Defined the Executor Service
    private final static ExecutorService exec = Executors.newCachedThreadPool();

    /**
     * Run the specific thread
     * 
     * @param r
     */
    public static void execute(Runnable r) {
        synchronized (ThreadUtil.class) {
            exec.execute(r);
        }
    }

}
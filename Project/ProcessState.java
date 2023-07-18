package Project;

import java.util.Map;
import static java.util.Map.entry;

/**
 * Representa o estado de um processo.
 */
public enum ProcessState {
    INTERRUPTIBLE_SLEEP,
    UNINTERRUPTIBLE_SLEEP,
    DEAD,
    ZOMBIE,
    RUNNING,
    STOPPED,
    UNKNOWN;

    private static Map<String, ProcessState> states = Map.ofEntries(
        entry("D", ProcessState.UNINTERRUPTIBLE_SLEEP),
        entry("R", ProcessState.RUNNING),
        entry("S", ProcessState.INTERRUPTIBLE_SLEEP),
        entry("T", ProcessState.STOPPED),
        entry("X", ProcessState.DEAD),
        entry("Z", ProcessState.ZOMBIE)
    );

    private static Map<ProcessState, String> statesString = Map.ofEntries(
        entry(ProcessState.UNKNOWN, "Unknown"),
        entry(ProcessState.UNINTERRUPTIBLE_SLEEP, "Uninterruptible sleep"),
        entry(ProcessState.RUNNING, "Running"),
        entry(ProcessState.INTERRUPTIBLE_SLEEP, "Interruptible sleep"),
        entry(ProcessState.STOPPED, "Stopped"),
        entry(ProcessState.DEAD, "Dead"),
        entry(ProcessState.ZOMBIE, "Zombie")
    );

    public static ProcessState getProcessStateFromString(String string){
        ProcessState state = states.get(string);

        if(state != null){
            return state;
        }

        if(Settings.DEBUG_MODE){
            System.out.println("[ProcessState] There is no process state for the string: " + string + ". Returning UNKNOWN...");
        }

        return ProcessState.UNKNOWN;
    }

    public static String getStringFromProcessState(ProcessState state){
        return statesString.get(state);
    }

}






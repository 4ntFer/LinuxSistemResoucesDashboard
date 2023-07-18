package Project;

public class ProcessInfo {

    private int PID;
    private String user;
    private String command;
    private float cpuUsage;
    private float memUsage;
    private int heapSize;
    private int stackSize;
    private int codeSize;
    private int dataSize;
    private int numberOfThreads;

    private int virtualMemorySize;//Em kb
    private int currentPhysicalMemoryUsage;
    private ProcessState state;

    public ProcessInfo(int PID, String user, String command) {
        this.PID = PID;
        this.user = user;
        this.command = command;
    }

    public String getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public float getMemUsage() {
        return memUsage;
    }

    public ProcessState getState() {
        return state;
    }

    public int getPID() {
        return PID;
    }

    public int getVirtualMemorySize() {
        return virtualMemorySize;
    }

    public int getCurrentPhysicalMemoryUsage() {
        return currentPhysicalMemoryUsage;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getCodeSize() {
        return codeSize;
    }

    public int getDataSize() {
        return dataSize;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void updateMemoryInfo(float memUsage, int virtualMemorySize, int currentPhysicalMemoryUsage){
        this.memUsage = memUsage;
        this.virtualMemorySize = virtualMemorySize;
        this.currentPhysicalMemoryUsage = currentPhysicalMemoryUsage;
    }

    public void updateMemoryPageInfo(int heapSize,int stackSize, int codeSize, int dataSize){
        this.heapSize = heapSize;
        this.stackSize = stackSize;
        this.codeSize = codeSize;
        this.dataSize = dataSize;
    }

    @Override
    public String toString(){
        return "PID:" + PID + ", User:" + user + ", Command:" + command + ", Cpu Usage: " + cpuUsage + ", DataSize:"
                + dataSize + ", HeapSize:" + heapSize + ", Threads: " + numberOfThreads;
    }


}

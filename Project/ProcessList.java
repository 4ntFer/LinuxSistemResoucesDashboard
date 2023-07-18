package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProcessList{
    private Pattern mapsPattern = Pattern.compile("(\\w+)-(\\w+).+\\[heap\\]");
    private Pattern statusPattern = Pattern.compile("VmData:\\s+(\\d+).+VmStk:\\s+(\\d+).+VmExe:\\s+(\\d+).+Threads:\\s+(\\d+)", Pattern.DOTALL);

    private ArrayList<ProcessInfo> processes;
    private final String psCommand = "ps aux";

    public ProcessList(){
        processes = new ArrayList<ProcessInfo>();

        update();
    }

    public void update(){
        try {
            String consoleReturn = Console.exec(psCommand);
            ArrayList<String> lines = new ArrayList<String>();
            ArrayList<ProcessInfo> newProcesses = new ArrayList<ProcessInfo>();

            lines.addAll(Arrays.asList(consoleReturn.split("\\n")));

            /*
             * Removemos a primeira da linha do comando que contem os nomes de cada coluna.
            */
            lines.remove(0);

            /*
             * Removemos o último processo que é o processo iniciado pelo comando ps, que após sua execução,
             * deixa de existir, não sendo possível pegar informações sobre ele.
             */
            lines.remove(lines.size()-1);

            for(String line: lines){
                ArrayList<String> processInfo = new ArrayList<String>();

                processInfo.addAll(Arrays.asList(line.split("\\s+")));

                System.out.println(processInfo.get(7).substring(0,1));

                String user = processInfo.get(0);
                int PID = Integer.parseInt(processInfo.get(1));
                float cpuUsage = Float.parseFloat(processInfo.get(2));
                float memUsage = Float.parseFloat(processInfo.get(3));
                int virtualMemorySize = Integer.parseInt(processInfo.get(4));
                int currentPhysicalMemoryUsage = Integer.parseInt(processInfo.get(5));
                ProcessState processState = ProcessState.getProcessStateFromString(processInfo.get(7).substring(0,1));
                String command = processInfo.get(10);
                
                ProcessInfo info = processAlreadyExists(PID);

                if(info == null){
                    info = new ProcessInfo(PID, user, command);
                }

                if(!user.equals(Settings.ROOT_USER)){
                    
                    String maps = Console.exec(getMapsCommandFromPID(PID));
                    String status = Console.exec(getStatusCommandFromPID(PID));

                    Matcher mapsMatcher = mapsPattern.matcher(maps);
                    Matcher statusMatcher = statusPattern.matcher(status);

                    mapsMatcher.find();
                    statusMatcher.find();

                    try{
                        int dataSize = Integer.parseInt(statusMatcher.group(1));
                        int stackSize = Integer.parseInt(statusMatcher.group(2));
                        int codeSize = Integer.parseInt(statusMatcher.group(3));
                        int numberOfThreads = Integer.parseInt(statusMatcher.group(4));

                        int heapSize = calcHeapSize(mapsMatcher.group(1), mapsMatcher.group(2));

                        info.updateMemoryPageInfo(heapSize, stackSize, codeSize, dataSize);
                        info.setNumberOfThreads(numberOfThreads);

                    }catch(Exception e){
                        System.out.println(PID);
                        System.out.println(e);
                    }

                }

                info.updateMemoryInfo(memUsage,
                    virtualMemorySize,
                    currentPhysicalMemoryUsage);

                info.setState(processState);
                info.setCpuUsage(cpuUsage);

                newProcesses.add(info);
            }

            processes = newProcesses;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private int calcHeapSize(String heapStartAddressHex, String heapEndAddressHex) {
        //Converte o endereço do inicio do heap de hexadecimal para decimal
        int heapStartAddress = (int) Long.parseLong(heapStartAddressHex, 16);

        //Converte o endereço do fim do heap de hexadecimal para decimal
        int heapEndAddress = (int) Long.parseLong(heapEndAddressHex, 16);

        //Calcula o tamanho do heap a partir dos endereço de inicio e fim e converte para kilobyte
        return (heapEndAddress - heapStartAddress) / 1024;
    }

    private String getMapsCommandFromPID(int PID){
        return "cat /proc/"+PID+"/maps";
    }

    private String getStatusCommandFromPID(int PID){
        return "cat /proc/"+PID+"/status";
    }

    private ProcessInfo processAlreadyExists(int PID){
        /*
         * TODO: Implementar uma busca binária no lugar
         */
        return processes.stream()
            .filter(p -> p.getPID() == PID)
            .findFirst()
            .orElse(null);
    }

    public ArrayList<ProcessInfo> getProcesses() {
        return processes;
    }
}
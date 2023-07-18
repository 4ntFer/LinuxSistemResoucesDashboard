package Project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe resposavel por coletar e retornar informações sobre
 * a CPU do sistema.
 */
public class CpuInfo{
    private final String COMMAND_LINE = "top -b -n 1";
    private final Pattern idlePattern = Pattern.compile("(\\d*\\.\\d*)\\s*id", 0);
    private final Pattern nTasksPattern = Pattern.compile("Tasks:\\s*(\\d*)", 0);

    private float usage;
    private float idleTime;
    private int processNum;
    private int threadsNum;
    

    public CpuInfo(){

    }

    /**
     * Atualiza as informações da CPU
     */
    public void update(){
        String commandReturn;
        String aux;
        

        Matcher matcher;
    
        try {
            commandReturn = Console.exec(COMMAND_LINE);

            matcher = idlePattern.matcher(commandReturn);
            matcher.find();

            aux = matcher.group(1);
            idleTime = Float.parseFloat(aux);
            
            usage = 100 - idleTime;

            matcher = nTasksPattern.matcher(commandReturn);
            matcher.find();

            aux = matcher.group(1);

            processNum = Integer.parseInt(aux);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    /**
     * Retorna o uso da CPU.
     * @return
     */
    public float getUsage(){
        return usage;
    }

    /**
     * Retorna o tempo ocioso da CPU.
     * @return
     */
    public float getIdleTime(){
        return idleTime;
    }

    /**
     * Retorna o número de processos.
     * @return
     */
    public int getNumberOfProcess(){
        return processNum;
    }

    /**
     * Retorna o número de threads.
     * @return
     */
    public int getNumberOfThreads(){
        return threadsNum;
    }
}
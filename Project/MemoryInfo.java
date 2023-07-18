package Project;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Coleta guarda e retorna as informações referentes ao uso de memória.
 */
public class MemoryInfo {
    private final String COMMAND_LINE = "top -b -n 1";
    private final Pattern pattern = Pattern.compile("(\\d+\\.\\d+) total,\\s+(\\d+\\.\\d+) free,\\s+(\\d+\\.\\d+) used,\\s+(\\d+\\.\\d+) buff\\/cache");
    private float totalMem = 0.f;
    private float freeMem = 0.f;
    private float usedMem = 0.f;
    private float cacheMem = 0.f;

    public MemoryInfo() {}

    public void update() {
        String commandReturn;
        String aux;

        Matcher matcher;

        try {
            commandReturn = Console.exec(COMMAND_LINE);

            matcher = pattern.matcher(commandReturn);
            matcher.find();

            totalMem = Float.parseFloat(matcher.group(1));
            freeMem = Float.parseFloat(matcher.group(2));
            cacheMem = Float.parseFloat(matcher.group(4));

            /*O top separa a memória utilizada por processos e por cache, então somamos os dois para descobrir o quanto de memória está sendo utilizada. */
            usedMem = Float.parseFloat(matcher.group(3)) + cacheMem;

        } catch (IOException e) {
            // TODO: Lançar uma exceção;
            e.printStackTrace();
        }
    }

    public float getTotalMem() {
        return totalMem;
    }

    public float getFreeMem() {
        return freeMem;
    }

    public float getUsedMem() {
        return usedMem;
    }

    public float getCacheMem() {
        return cacheMem;
    }

    public  float getUsage(){
        return usedMem*100/totalMem;
    }

}
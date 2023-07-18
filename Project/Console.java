package Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe reponsavel por executar comandos no console.
 */
public class Console {
    /**
     * Método que excuta um comando no console e
     * retorna uma string com a saída dada.
     */

    public static String exec(String commandLine) throws IOException {
        Process process;
        BufferedReader input;    
        StringBuffer cmdOut = new StringBuffer();
        String lineOut;
        int numberOfOutline = 0;

        String result;

        try{
            process = Runtime.getRuntime().exec(commandLine);

            input = new BufferedReader(new InputStreamReader (process.getInputStream()));

            //trata o retorno do cmd pra input
            while((lineOut = input.readLine()) != null){
                if (numberOfOutline > 0) {
                    cmdOut.append("\n");
                }
                cmdOut.append(lineOut);
                numberOfOutline++;
            }

            result = cmdOut.toString();
            input.close();

            process.destroyForcibly();

            return result;

        }catch(IOException e){
            System.out.println(e);

            return "";
        }

    }
}
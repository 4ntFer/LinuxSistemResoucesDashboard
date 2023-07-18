package Project;

public class Settings {

    public static class MemoryChart{
        public static final String CHART_NAME = "Uso de mem贸ria";
        public static final String FREE_MEMORY_LABEL = "Mem贸ria livre";
        public static final String USED_MEMORY_LABEL = "Mem贸ria usada";
        public static final String CACHE_MEMORY_LABEL = "Mem贸ria em cache";
        public static final String FREE_MEMORY_COLOR = "#7B6DB3";
        public static final String USED_MEMORY_COLOR = "#392880";
        public static final String CACHE_MEMORY_COLOR = "#463E66";
        public static final int WIDTH = 240;
        public static final int HEIGHT = (int)(WIDTH*0.75);
    }

    public static class MemoryUsageChart{
        public static final int WIDTH = 600;
        public static final int HEIGHT = 150;
    }

    public static class Colors{
        public static final String HARDWARE_PANEL = "#1A1726"; 
    }

    public static final int UPDATE_DELAY = 0;
    public static final int UPDATE_PERIOD = 1;
    public static final boolean DEBUG_MODE = false;
    public static final String ROOT_USER = "root";

}
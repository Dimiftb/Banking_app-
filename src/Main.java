import GUI.MonitorGUI;
import GUI.SampleTestProgram;

public class Main {
    public static void main(String[] args) {


        Thread bankThread = new Thread("Bank Test") {
            public void run() {
                try {
                    BankTester test = new BankTester();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        bankThread.start();

        Thread GUIThread = new Thread("Bank Test") {
            public void run() {
                MonitorGUI g = new MonitorGUI();
            }
        };
        GUIThread.start();

        Thread GUI_TEST = new Thread("GUI Test") {
            public void run() {
                SampleTestProgram GUITest = new SampleTestProgram();
            }
        };
        GUI_TEST.start();
    }
}

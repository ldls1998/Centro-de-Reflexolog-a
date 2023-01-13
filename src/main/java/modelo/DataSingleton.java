package modelo;

/**
 *
 * @author Vlik35
 */
public class DataSingleton {

    private static DataSingleton instance;
    private Cita_Paciente cita_paciente;

    private DataSingleton() {
    }

    public static DataSingleton getInstance() {
        if (instance == null) {
            instance = new DataSingleton();
        }
        return instance;
    }

    public void setData(Cita_Paciente cita_paciente) {
        this.cita_paciente = cita_paciente;
    }

    public Cita_Paciente getData() {
        return cita_paciente;
    }
    
}

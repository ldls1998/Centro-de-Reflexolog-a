package modelo;

/**
 *
 * @author Vlik35
 */
public class PacienteSingleton {

    private static PacienteSingleton instance;
    private Paciente paciente;

    private PacienteSingleton() {
    }

    public static PacienteSingleton getInstance() {
        if (instance == null) {
            instance = new PacienteSingleton();
        }
        return instance;
    }

    public void setData(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getData() {
        return paciente;
    }
    
}


package Parcial2;

   
  // Clase Garaje
import java.util.ArrayList;

public class Garaje implements iGarage {
    private ArrayList<Vehiculo> espacios;
    private static final int NUM_ESPACIOS = 50;

    public Garaje() {
        this.espacios = new ArrayList<>();
    }

    public boolean alquilarEspacio(Vehiculo vehiculo) {
        if (espacios.size() >= NUM_ESPACIOS || vehiculo.getPlaca() == null) {
            return false;
        }

        long numMotos = espacios.stream().filter(v -> v instanceof Moto).count();
        if (vehiculo instanceof Moto && (numMotos + 1) > (0.8 * NUM_ESPACIOS)) {
            return false;
        }

        espacios.add(vehiculo);
        return true;
    }

    public boolean retirarVehiculo(String placa) {
        return espacios.removeIf(v -> v.getPlaca() != null && v.getPlaca().equals(placa));
    }

    @Override
    public double calcularIngresos() {
        return espacios.stream().mapToDouble(Vehiculo::getCuotaMesGaraje).sum();
    }

    @Override
    public int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipo) {
        return (int) espacios.stream().filter(tipo::isInstance).count();
    }

    public double calcularProporcionAutosMotos() {
        long numAutos = calcularOcupacionPorTipoVehiculo(Auto.class);
        long numMotos = calcularOcupacionPorTipoVehiculo(Moto.class);
        if (numMotos == 0) {
            return numAutos; // Si no hay motos, la proporción es el número de autos
        }
        return (double) numAutos / numMotos;
    }

    public void listarVehiculos() {
        if (espacios.isEmpty()) {
            System.out.println("No hay vehículos en el garaje.");
        } else {
            for (Vehiculo v : espacios) {
                System.out.println("Placa: " + v.getPlaca() + ", Cuota: " + v.getCuotaMesGaraje() + ", Tipo: " + v.getClass().getSimpleName());
            }
        }
    }
}


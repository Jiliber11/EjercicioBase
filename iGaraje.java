
package Parcial2;
// Interfaz iGarage
interface iGarage {
    double calcularIngresos();
    int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipo);
}

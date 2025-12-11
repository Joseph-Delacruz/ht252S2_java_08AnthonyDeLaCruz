package Anthony.De_La_Cruz.service;

import Anthony.De_La_Cruz.controller.ComputerController;
import Anthony.De_La_Cruz.model.Computer;

import java.util.List;

public class ComputerService {

    private final ComputerController controller;

    public ComputerService() {
        this.controller = new ComputerController();
    }

    // ---------------- CREATE ----------------
    public boolean registrar(Computer computer) {
        try {
            controller.create(computer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------------- UPDATE ----------------
    public boolean actualizar(Computer computer) {
        try {
            controller.update(computer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---------------- READ ----------------
    public List<Computer> listar() {
        try {
            return controller.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // lista vacía segura
        }
    }

    // ---------------- DELETE LÓGICO ----------------
    public boolean eliminar(int id) {
        try {
            controller.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

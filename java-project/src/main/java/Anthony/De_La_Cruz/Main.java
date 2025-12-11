package Anthony.De_La_Cruz;

import Anthony.De_La_Cruz.view.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        // Estilo del sistema (Windows / Linux / Mac)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Lanzar la UI en el hilo correcto
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setLocationRelativeTo(null); // centrar ventana
            frame.setVisible(true);
        });
    }
}

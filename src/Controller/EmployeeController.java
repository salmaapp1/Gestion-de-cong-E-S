package Controller;
import View.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Model.Employee;
import Model.EmployeeModel;
import Model.Poste;
import Model.Role;
import Utilities.Utils;
import View.EmployeeView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import DAO.EmployeeDAOImpl;
import java.io.IOException;

public class EmployeeController {

    protected EmployeeModel employeeModel;
    protected static EmployeeView employeeView;

    public EmployeeController(EmployeeModel employeeModel, EmployeeView employeeView) {
        this.employeeModel = employeeModel;
        EmployeeController.employeeView = employeeView;
        EmployeeController.employeeView.getAjouterButton().addActionListener(e -> this.ajouterEmployee());
        EmployeeController.employeeView.getAfficherButton().addActionListener(e -> {
            if (employeeView.getNomField().getText().isEmpty() && employeeView.getPrenomField().getText().isEmpty() && employeeView.getSalaireField().getText().isEmpty() && employeeView.getEmailField().getText().isEmpty() && employeeView.getPhoneField().getText().isEmpty()) {
                this.afficherEmployee();
            }
            if (!employeeView.getNomField().getText().isEmpty() && !employeeView.getPrenomField().getText().isEmpty()){
                String firstname = employeeView.getNomField().getText();
                String lastname = employeeView.getPrenomField().getText();
                this.findByFullName(firstname,lastname);
            }
            if (employeeView.getNomField().getText().isEmpty() || employeeView.getPrenomField().getText().isEmpty() ){
                if (!employeeView.getNomField().getText().isEmpty()) {
                    String lastname = employeeView.getNomField().getText();
                    this.findByLastName(lastname);
                }
                if (!employeeView.getPrenomField().getText().isEmpty()) {
                    String firstname = employeeView.getPrenomField().getText();
                    this.findByFirstName(firstname);
                }
            }
            if (!employeeView.getPhoneField().getText().isEmpty()) {
                String phone = employeeView.getPhoneField().getText();
                this.findByPhone(phone);
            }
            if (!employeeView.getEmailField().getText().isEmpty()) {
                String email = employeeView.getEmailField().getText();
                this.findByEmail(email);
            }
            if (!employeeView.getSalaireField().getText().isEmpty()) {
                String salaireString = employeeView.getSalaireField().getText();
                double salaire = Double.parseDouble(salaireString);
                this.findBySalaire(salaire);
            }
        });
        EmployeeController.employeeView.getSupprimerButton().addActionListener(e -> this.supprimerEmployee());
        EmployeeController.employeeView.getModifierButton().addActionListener(e -> this.updateEmployee());
        EmployeeController.employeeView.getImportButton().addActionListener(e -> this.handleImport());
        EmployeeController.employeeView.getexportButton().addActionListener(e -> this.handleExport());
        this.afficherEmployee();
    }

    public void ajouterEmployee() {
        String nom  = employeeView.getNomField().getText();
        String prenom = employeeView.getPrenomField().getText();
        String salaire = employeeView.getSalaireField().getText();
        String email = employeeView.getEmailField().getText();
        String phone = employeeView.getPhoneField().getText();
        Role role = (Role) employeeView.getRoleComboBox().getSelectedItem();
        Poste poste = (Poste) employeeView.getPosteComboBox().getSelectedItem();
        employeeModel.ajouterEmployee(nom, prenom, salaire, email, phone, role , poste);
    }

    public void afficherEmployee() {
        List<Employee> employees = employeeModel.afficherEmployee();
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findByEmail(String email) {
        List<Employee> employees = employeeModel.findByEmail(email);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findByFullName(String firstname, String lastname) {
        List<Employee> employees = employeeModel.findByFullName(firstname, lastname);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findByFirstName(String firstname) {
        List<Employee> employees = employeeModel.findByFirstName(firstname);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findByLastName(String lastname) {
        List<Employee> employees = employeeModel.findByLastName(lastname);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findByPhone(String phone) {
        List<Employee> employees = employeeModel.findByPhone(phone);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void findBySalaire(double salaire) {
        List<Employee> employees = employeeModel.findBySalaire(salaire);
        DefaultTableModel tableModel = (DefaultTableModel) employeeView.getTable().getModel();
        tableModel.setRowCount(0);
        for(Employee e : employees) {
            tableModel.addRow(new Object[]{e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getSalaire(), e.getPhone(), e.getRole(), e.getPoste(), e.getHolidayBalance()});
        }
    }

    public void supprimerEmployee() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
                employeeModel.supprimerEmployee(id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        } else {
            employeeView.SupprimerFail("Veuillez choisir un employé.");
        }
        this.afficherEmployee();
    }

    public void updateEmployee() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                int id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
                String nom = employeeView.getNomField().getText();
                String prenom = employeeView.getPrenomField().getText();
                String email = employeeView.getEmailField().getText();
                double salaire = Utils.parseDouble(employeeView.getSalaireField().getText());
                String phone = employeeView.getPhoneField().getText();
                Role role = (Role) (employeeView.getRoleComboBox().getSelectedItem());
                Poste poste = (Poste) employeeView.getPosteComboBox().getSelectedItem();
                Employee employeeToUpdate = employeeModel.findById(id);
                if (employeeToUpdate != null) {
                    employeeModel.updateEmployee(employeeToUpdate, id, nom, prenom, email, salaire, phone, role, poste);
                } else {
                    employeeView.ModifierFail("L'employé avec l'ID spécifié n'existe pas.");
                }
            } catch (NumberFormatException e) {
                employeeView.ModifierFail("Erreur lors de la mise à jour de l'employé.");
            }
        } else {
            employeeView.ModifierFail("Veuillez choisir un employé.");
        }
    }

    public static int getId() {
        int selectedRow = employeeView.getTable().getSelectedRow();
        int id = -1;
        if (selectedRow != -1) {
            try {
                id = Integer.parseInt(employeeView.getTable().getModel().getValueAt(selectedRow, 0).toString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        }
        return id;
    }

    public static void viderLesChamps() {
        employeeView.getNomField().setText("");
        employeeView.getPrenomField().setText("");
        employeeView.getSalaireField().setText("");
        employeeView.getEmailField().setText("");
        employeeView.getPhoneField().setText("");
        employeeView.getRoleComboBox().setSelectedIndex(-1);
        employeeView.getPosteComboBox().setSelectedIndex(-1);
    }

    public void handleImport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "csv", "txt"));

        try {
            if (fileChooser.showOpenDialog(employeeView) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                employeeModel.importData(filePath); // Ensure employeeModel is an instance, not static
                employeeView.afficherMessageSucces("Importation réussie !");
            }
        } catch (IOException ex) {
            // Catch and handle the IOException
            employeeView.afficherMessageErreur("Une erreur d'importation est survenue : " + ex.getMessage());
        } catch (Exception ex) {
            // Catch any other unexpected exceptions
            employeeView.afficherMessageErreur("Une erreur inattendue est survenue : " + ex.getMessage());
        }
    }


    public void handleExport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "csv"));

        if (fileChooser.showSaveDialog(employeeView) == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath += ".txt";
                }
                List<Employee> employes = new EmployeeDAOImpl().afficher();
                employeeModel.exportData(filePath, employes);  // Ensure employeeModel is an instance, not static
                employeeView.afficherMessageSucces("Exportation réussie !");
            } catch (Exception ex) {
                employeeView.afficherMessageErreur("Une erreur inattendue est survenue : " + ex.getMessage());
            }
        }
    }
}

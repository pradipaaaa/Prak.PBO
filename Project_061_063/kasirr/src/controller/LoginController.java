package controller;

import model.User;
import model.UserDAO;
import view.AdminDashboard;
import view.KasirDashboard;
import view.LoginFrame;

public class LoginController {
    private LoginFrame loginFrame;

    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void handleLogin() {
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();

        User user = UserDAO.login(username, password);
        if (user != null) {
            loginFrame.showMessage("Login berhasil sebagai " + user.getRole());

            loginFrame.dispose();
            if ("admin".equals(user.getRole())) {
                new AdminDashboard(user).setVisible(true);
            } else {
                new KasirDashboard(user).setVisible(true);
            }
        } else {
            loginFrame.showMessage("Username atau password salah!");
        }
    }
}


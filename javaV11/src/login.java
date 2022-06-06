import com.mysql.jdbc.PreparedStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login {
    private JButton btn_giris;
    private JTextField txt_kadi;

    private JPanel pnl_login;
    private JPasswordField txt_sifre;

    public login() {
        btn_giris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement ps;
                ResultSet rs;
                String kadi = txt_kadi.getText();
                String sifre = String.valueOf(txt_sifre.getPassword());
                String query = "SELECT * FROM `tbl_yetkililer` WHERE `yetkili_kadi` =? AND `yetkili_sifre` =?";

                try {
                    ps = (PreparedStatement) MyCon.getCon().prepareStatement(query);

                    ps.setString(1, kadi);
                    ps.setString(2, sifre);

                    rs = ps.executeQuery();

                    if(rs.next())
                    {
                        JOptionPane.showMessageDialog(null,"Başarıyla Giriş Yapıldı");
                        Anasayfa anasayfa = new Anasayfa();
                        Anasayfa.main();
                        login lgn = new login();
                        lgn.pnl_login.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Kullanıcı Adı veya Şifre Yanlış");
                    }

                } catch (SQLException ex) {

                }






                /*
                try {
                    String kadi = txt_kadi.getText();
                    String sifre = String.valueOf(txt_sifre.getPassword());

                    MyCon ConnectionProvider = new MyCon();
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    String queryselect = "SELECT * FROM java.tbl_yetkililer where tbl_yetkililer.yetkili_kadi = '"+kadi+"' and tbl_yetkililer.yetkili_sifre = '"+sifre+"'";
                    //  st.executeUpdate(queryselect);
                    st.executeQuery(queryselect);
                    JOptionPane.showMessageDialog(null,"Başarıyla Giriş Yapıldı");
                    Anasayfa anasayfa = new Anasayfa();
                    Anasayfa.main();
                    login lg = new login();
                    lg.pnl_login.setVisible(false);
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"HATA : "+exception.getMessage());
                    // System.out.println(exception.getMessage());
                }*/
            }
        });
    }
    public static void main() {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().pnl_login);
        frame.pack();
        frame.setVisible(true);
    }
}

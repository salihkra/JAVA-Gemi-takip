import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Anasayfa {
    public Anasayfa() {
        btn_ara.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gemi_kod =  txt_gemi_kod.getText();
                Gemi gemi = new Gemi();
                gemi.gemi_Ara(gemi_kod);
            }
        });



        //VERİ GÜNCELLEME BUTONU
        btn_gncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gemi_kod_update = txt_gemi_kod2.getText();
                String gemi_kaptan_update = txt_kaptan_isim2.getText();
                String gemi_yuk_update = txt_gemi_yuk2.getText();
                String gemi_yakit_update = txt_yakit2.getText();
                String gemi_bakim_update = txt_bakim2.getText();
                String gemi_konum_update = txt_konum2.getText();
                Gemi gemi = new Gemi();
                gemi.gemi_update(gemi_kod_update,gemi_kaptan_update,gemi_yuk_update,gemi_yakit_update,gemi_bakim_update,gemi_konum_update);


            }
        });
        //VERİ SİLME BUTONU
        btn_sil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String gemi_kod_sil = txt_gemikod_sil.getText();
                Gemi gemi = new Gemi();
                gemi.gemi_delete(gemi_kod_sil);

            }
        });
        //VERİ EKLEME BUTONU
        btn_ekle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gemi_kod =  txt_gemi_kod.getText();
                String gemi_kaptan = txt_kaptan_isim.getText();
                String gemi_yuk = txt_yukdurum.getText();
                String gemi_yakit = txt_yakit.getText();
                String gemi_bakim = txt_bakim.getText();
                String gemi_konum = txt_konum.getText();

                Gemi gemi = new Gemi();
                gemi.gemi_add(gemi_kod, gemi_kaptan,gemi_yuk, gemi_yakit, gemi_bakim, gemi_konum);




            }
        });
        btn_sifirla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_gemi_kod.setText("");
                txt_kaptan_isim.setText("");
                txt_konum.setText("");
                txt_yakit.setText("");
                txt_yukdurum.setText("");
                txt_bakim.setText("");
            }
        });
        //TÜM GEMİLERİ LİSTELER
        btn_goster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String query ="SELECT  * FROM java.gemi_bilgileri";
                    MyCon ConnectionProvider = new MyCon();
                    Connection con = ConnectionProvider.getCon();
                    Statement stm = con.createStatement();
                    ResultSet res = stm.executeQuery(query);
                    PreparedStatement ps;
                    String columns[] = { "gemi_kodu","gemi_kaptani", "gemi_yuk_durum","gemi_yakit_durumu","gemi_bakim_tarihi","gemi_konum"};
                    String data[][] = new String[10][6];
                    int i = 0;
                    while (res.next()) {

                        String gemi_kodu = res.getString("gemi_kodu");
                        String gemi_kaptani = res.getString("gemi_kaptani");
                        String yuk_durum = res.getString("gemi_yuk_durum");
                        String gemi_yakit_durumu = res.getString("gemi_yakit_durumu");
                        String gemi_bakim_tarihi = res.getString("gemi_bakim_tarihi");
                        String gemi_konum = res.getString("gemi_konum");

                        data[i][0] = gemi_kodu+"";
                        data[i][1] = gemi_kaptani;
                        data[i][2] = yuk_durum+"";
                        data[i][3] = gemi_yakit_durumu+"";
                        data[i][4] = gemi_bakim_tarihi+"";
                        data[i][5] = gemi_konum+"";

                        i++;
                    }
                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    JTable table = new JTable(model);
                    table.setShowGrid(true);
                    table.setShowVerticalLines(true);
                    JScrollPane pane = new JScrollPane(table);
                    JFrame f = new JFrame("Populate JTable from Database");
                    JPanel panel = new JPanel();
                    panel.add(pane);
                    f.add(panel);
                    f.setSize(500, 250);

                    f.setVisible(true);
                }catch (SQLException exception){
                    exception.printStackTrace();


                }


            }
        });
        btn_cikis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        txt_gemikod_sil.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_gemi_kod.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }


                super.keyTyped(e);
            }
        });
        txt_kaptan_isim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE )) {
                    e.consume();  // ignore the event if it's not an alphabet
                }
                super.keyTyped(e);
            }
        });
        txt_yukdurum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_yakit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_bakim.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9') || caracter == '-') && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_konum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE )) {
                    e.consume();  // ignore the event if it's not an alphabet
                }
                super.keyTyped(e);
            }
        });


        txt_gemikod_sil.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_gemi_kod2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_gemi_yuk2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_yakit2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz

                    e.consume();
                }
                super.keyTyped(e);
            }
        });
        txt_konum2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE )) {
                    e.consume();  // ignore the event if it's not an alphabet
                }
                super.keyTyped(e);
            }
        });
        txt_kaptan_isim2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isAlphabetic(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE )) {
                    e.consume();  // ignore the event if it's not an alphabet
                }
                super.keyTyped(e);
            }
        });
    }





    public static void main() {
        JFrame frame = new JFrame("Anasayfa");
        frame.setContentPane(new Anasayfa().pnl_gemi_ekle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel pnl_anasayfa;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_gemi_ekle;
    private JTextField txt_gemi_kod;
    private JTextField txt_kaptan_isim;
    private JTextField txt_yukdurum;
    private JTextField txt_yakit;
    private JTextField txt_bakim;
    private JTextField txt_konum;
    private JButton btn_ekle;
    private JTextField txt_gemi_kod2;
    private JTextField txt_kaptan_isim2;
    private JButton btn_sifirla;
    private JButton btn_cikis;
    private JCheckBox chx_silmek_onay;
    private JTextField txt_gemikod_sil;
    private JButton btn_sil;
    private JButton btn_goster;
    private JButton btn_gncelle;
    private JTextField txt_gemi_yuk2;
    private JTextField txt_yakit2;
    private JTextField txt_bakim2;
    private JTextField txt_konum2;
    private JButton btn_ara;


}

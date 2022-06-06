import com.mysql.jdbc.Messages;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class Gemi {
    //GEMİ ARATMAK İÇİN OLUŞTURDUĞUM METHOD...
    public  boolean  gemi_Ara (String gemi_kod){
        PreparedStatement ps;
        ResultSet rs;
        boolean gemiArA = false;
        String query = "SELECT * FROM `gemi_bilgileri` WHERE `gemi_kodu` =?";
        try {
            ps = MyCon.getCon().prepareStatement(query);
            ps.setString(1, gemi_kod);

            rs = ps.executeQuery();

            if(rs.next())
            {
                gemiArA = true;
                JOptionPane.showMessageDialog(null,"GEMİ MEVCUT");
            }
            if (gemiArA == false) {
                JOptionPane.showMessageDialog(null,"GEMİ SİSTEMDE YOK");

            }
        } catch (Exception e) {

        }
        return gemiArA;






/*
        try {
            MyCon ConnectionProvider = new MyCon();
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String queryara = "SELECT * FROM java.gemi_bilgileri WHERE gemi_kodu = '"+gemi_kod+"' ";
            st.executeQuery(queryara);

            JOptionPane.showMessageDialog(null,"Gemi MEVCUT");
        }catch (Exception exception){

            JOptionPane.showMessageDialog(null,"Gemi YOK");
            System.out.println(exception);
        }*/
    }
    //GEMİ GÜNCELLEMEK İÇİN OLUŞTURDUĞUM METHODUM...
    public  boolean gemi_update(String gemi_kod_update, String gemi_kaptan_update, String gemi_yuk_update,
                             String gemi_yakit_update, String gemi_bakim_update, String gemi_konum_update ){

        PreparedStatement ps;
        ResultSet rs;

        boolean gemiArA = false;
        String query = "SELECT * FROM `gemi_bilgileri` WHERE `gemi_kodu` =?";
        String query_gemi_update= "UPDATE java.gemi_bilgileri  set gemi_kaptani = '"+gemi_kaptan_update+"' , gemi_yuk_durum = '"+gemi_yuk_update+"' ,  gemi_yakit_durumu = '"+gemi_yakit_update+"' ,  gemi_bakim_tarihi  = '"+gemi_bakim_update+"', gemi_konum  = '"+gemi_konum_update+"'  WHERE  gemi_kodu = '"+gemi_kod_update+"'";

        try {
            ps = MyCon.getCon().prepareStatement(query);
            ps.setString(1, gemi_kod_update);
            rs = ps.executeQuery();
            if(rs.next())
            {
                gemiArA = true;
                if (gemi_kod_update.isEmpty() ||gemi_kaptan_update.isEmpty() ||gemi_yuk_update.isEmpty() ||
                        gemi_bakim_update.isEmpty() ||gemi_konum_update.isEmpty() ||gemi_yakit_update.isEmpty() ) {
                    JOptionPane.showMessageDialog(null,"BOŞ ALANLARI DOLDURUNUZ");
                }else {
                    ps.executeUpdate(query_gemi_update);

                    JOptionPane.showMessageDialog(null,"GEMİ BİLGİLERİ DEĞİŞTİRİLDİ");
                }

            }
            if (gemiArA == false) {
                JOptionPane.showMessageDialog(null,"GEMİ SİSTEMDE YOK");
            }

        }catch (Exception e){
            System.out.println(e);
        }return gemiArA;



    }
    //GEMİ SİLMEK İÇİN OLUŞTURDUĞUM METHODUM...
    public boolean gemi_delete(String gemi_kod_sil){

        PreparedStatement ps;
        ResultSet rs;

        boolean gemiArA = false;
        String query = "SELECT * FROM `gemi_bilgileri` WHERE `gemi_kodu` =?";
        String querydelete = "DELETE FROM java.gemi_bilgileri WHERE gemi_kodu = '"+gemi_kod_sil +"' ";

        try {
            ps = MyCon.getCon().prepareStatement(query);
            ps.setString(1, gemi_kod_sil);
            rs = ps.executeQuery();
            if(rs.next())
            {
                gemiArA = true;
                ps.executeUpdate(querydelete);

                JOptionPane.showMessageDialog(null,"GEMİ SİLİNDİ");
            }
            if (gemiArA == false) {
                JOptionPane.showMessageDialog(null,"GEMİ SİSTEMDE YOK");
            }

        }catch (Exception e){
            System.out.println(e);
        }return gemiArA;
        /*try {
            MyCon ConnectionProvider = new MyCon();
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String querydelete = "DELETE FROM java.gemi_bilgileri WHERE gemi_kodu = '"+gemi_kod_sil +"' ";
            st.executeUpdate(querydelete);
            JOptionPane.showMessageDialog(null,"Başarıyla Silindi");
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"BAŞARISIZ");
            System.out.println(exception);
        }*/
    }
    //GEMİ EKLEMEK İÇİN OLUŞTURULAN METHODUM...
    public void gemi_add(String gemi_kod, String gemi_kaptan, String gemi_yuk, String gemi_yakit, String gemi_bakim, String gemi_konum ){
        try {
            MyCon ConnectionProvider = new MyCon();
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            if (gemi_kod.isEmpty() ||gemi_kaptan.isEmpty() ||gemi_yuk.isEmpty() ||
                    gemi_bakim.isEmpty() ||gemi_konum.isEmpty() ||gemi_yakit.isEmpty() ) {
                JOptionPane.showMessageDialog(null,"BOŞ ALANLARI DOLDURUNUZ");
            }else {
            st.executeUpdate("insert into java.gemi_bilgileri(gemi_kodu,gemi_kaptani,gemi_yuk_durum,gemi_yakit_durumu,gemi_bakim_tarihi,gemi_konum) values('"+gemi_kod+"','"+gemi_kaptan+"', '"+gemi_yuk+"','"+gemi_yakit+"','"+gemi_bakim+"','"+gemi_konum+"')");
            JOptionPane.showMessageDialog(null,"Başarıyla Eklendi");}
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,"EKLENEMEDİ");
            System.out.println(exception);
        }
    }

    public String getGemi_kod() {
        return gemi_kod;
    }

    public void setGemi_kod(String gemi_kod) {
        this.gemi_kod = gemi_kod;
    }

    public String getGemi_kaptan() {
        return gemi_kaptan;
    }

    public void setGemi_kaptan(String gemi_kaptan) {
        this.gemi_kaptan = gemi_kaptan;
    }

    public String getGemi_yuk() {
        return gemi_yuk;
    }

    public void setGemi_yuk(String gemi_yuk) {
        this.gemi_yuk = gemi_yuk;
    }

    public String getGemi_yakit() {
        return gemi_yakit;
    }

    public void setGemi_yakit(String gemi_yakit) {
        this.gemi_yakit = gemi_yakit;
    }

    public String getGemi_bakim() {
        return gemi_bakim;
    }

    public void setGemi_bakim(String gemi_bakim) {
        this.gemi_bakim = gemi_bakim;
    }

    public String getGemi_konum() {
        return gemi_konum;
    }

    public void setGemi_konum(String gemi_konum) {
        this.gemi_konum = gemi_konum;
    }



    private String gemi_kod;
    private String gemi_kaptan ;
    private String gemi_yuk ;
    private String gemi_yakit ;
    private String gemi_bakim;
    private String gemi_konum ;
}

import models.Kitap;
import models.Uye;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Şeyma Yılmaz on 7.6.2017.
 */
public class DatabaseHelper {


    public static List<Kitap> kitapListesiGetir(){

        /* kitap nesnelerini tutan liste olusturalim */
        List<Kitap> kitapListesi = new ArrayList<Kitap>();

        /* connection yardimiyla baglatiyi kuraririz */
        Connection connection = null;
        /* statement yardimiyla da sorgularimizi calistiririz */
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(
                    Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD
            );
            statement = connection.createStatement();

            String sql = "SELECT * FROM kutuphane.kitap";
            /* sonuclar ResultSet'te saklanir */
            ResultSet resultSet = statement.executeQuery(sql);

            /* bir sonraki kayit var oldugu surece don */
            while(resultSet.next()){
                Kitap kitap = new Kitap();

                int kitap_id = resultSet.getInt("kitap_id");
                kitap.setId(kitap_id);

                String isim = resultSet.getString("isim");
                kitap.setIsim(isim);

                String tur = resultSet.getString("turu");
                kitap.setTur(tur);

                int adet = resultSet.getInt("adet");
                kitap.setAdet(adet);

                String yazar = resultSet.getString("yazar");
                kitap.setYazar(yazar);

                kitapListesi.add(kitap);
            }
            resultSet.close();


        } catch (SQLException e) {
            System.out.println("Bağlantıda hatası!");
        }
        finally {

            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return kitapListesi;
    }

    public static void kitapListesiYazdir(List<Kitap> kitapListesi){
        System.out.println("kütüphanedeki kitap sayısı : " + kitapListesi.size());

        for(Kitap kitap : kitapListesi){
            System.out.println("Kitap Id : " + kitap.getId());
            System.out.println("Kitap ismi: " + kitap.getIsim());
            System.out.println("Kitap türü : " + kitap.getTur());
            System.out.println("Kitap adeti : " + kitap.getAdet());
            System.out.println("Kitap yazarı : " + kitap.getYazar());
            System.out.println("===================================");
        }

    }

    public static List<Uye> uyeListesiGetir(){
        List<Uye> uyeListesi =new ArrayList<Uye>();

        Connection connection=null;
        Statement statement=null;


        try {
            connection=DriverManager.getConnection(
                    Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD
            );

            statement=connection.createStatement();
            String sql="SELECT * FROM kutuphane.uye";
            ResultSet resultSet=statement.executeQuery(sql);

            while(resultSet.next()){
                 Uye uye=new Uye();
                 int uyeId=resultSet.getInt("uye_id");
                 uye.setId(uyeId);
                 String isim=resultSet.getString("isim");
                 uye.setIsim(isim);
                 String soyisim=resultSet.getString("soyisim");
                 uye.setSoyisim(soyisim);
                 String tcNo=resultSet.getString("tcno");
                 uye.setTcNo(tcNo);
                 String adres=resultSet.getString("adres");
                 uye.setAdres(adres);
                 Date tarih=resultSet.getDate("tarih");
                 uye.setTarih(tarih);

                 uyeListesi.add(uye);

            }
            resultSet.close();


        } catch (SQLException e) {
            System.out.println("Bağlantı hatası!");
        }
        finally {
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



        return uyeListesi;
    }

    public static void uyeListesiniYazdir(List<Uye> uyeListesi){
        System.out.println("listedeki üye sayısı : " +  uyeListesi.size());
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );


        for (Uye uye : uyeListesi) {
            System.out.println("Uye id :  " + uye.getId());
            System.out.println("Uye ismi :  " + uye.getIsim() + " " + uye.getSoyisim());
            System.out.println("Uye adres :  " + uye.getAdres());
            System.out.println("Uye tcno :  " + uye.getTcNo());
            System.out.println("uye tarih:  " + dateFormat.format(uye.getTarih()));
            System.out.println("======================================");
        }
    }

    public static void kitapEkle(Kitap kitap){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(
                    Config.DB_URL,
                    Config.DB_USER,
                    Config.DB_PASSWORD
            );

            String sql = "INSERT INTO kutuphane.kitap (isim, turu, adet, yazar) " +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, kitap.getIsim());
            preparedStatement.setString(2, kitap.getTur());
            preparedStatement.setInt(3, kitap.getAdet());
            preparedStatement.setString(4, kitap.getYazar());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void uyeEkle(Uye uye){
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            connection=DriverManager.getConnection(Config.DB_URL,
                    Config.DB_USER,Config.DB_PASSWORD );


            String sql="INSERT INTO kutuphane.uye (isim, soyisim, tcno, adres, tarih)" +
                    " VALUES (?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setString(1,uye.getIsim());
            preparedStatement.setString(2,uye.getSoyisim());
            preparedStatement.setString(3,uye.getTcNo());
            preparedStatement.setString(4,uye.getAdres());
            Date currentDate = new Date(System.currentTimeMillis());
            preparedStatement.setDate(5, currentDate);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void kitapSil(int kitapId){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(
                    Config.DB_URL,
                    Config.DB_USER,
                    Config.DB_PASSWORD
            );

            String sql = "DELETE FROM kutuphane.kitap WHERE kitap_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kitapId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static boolean kitapVarMi(int kitapId){

        boolean kitapDurum = false; /* kitap yok */

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(
                    Config.DB_URL,
                    Config.DB_USER,
                    Config.DB_PASSWORD
            );

            String sql = "SELECT * FROM kutuphane.kitap WHERE kitap_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, kitapId);

            ResultSet resultSet = preparedStatement.executeQuery();

            int row = 0;
            while (resultSet.next()){
                row++;
            }

            if(row == 0){
                kitapDurum = false; /* kitap yok */
            }
            else{
                kitapDurum = true; /* kitap var */
            }

            resultSet.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return kitapDurum;

    }

    public static void uyeSil(int uyeId){
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        try {
            connection=DriverManager.getConnection(Config.DB_URL,
                    Config.DB_USER, Config.DB_PASSWORD);


            String sql="DELETE FROM kutuphane.uye WHERE uye_id=?";
            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,uyeId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public  static  boolean uyeVarMi (int uyeId){
        boolean uyeDurum=false;

        Connection connection=null;
        PreparedStatement preparedStatement=null;


        try {
            connection=DriverManager.getConnection(Config.DB_URL ,Config.DB_USER,
                    Config.DB_PASSWORD);


            String sql="SELECT *FROM kutuphane.uye WHERE uye_id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,uyeId);
            ResultSet resultSet=preparedStatement.executeQuery();

            int row=0;
            while(resultSet.next()){
                row++;
            }
            if(row==0){
                uyeDurum=false;
            }
            else {
                uyeDurum=true;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(preparedStatement != null ){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return uyeDurum;

    }


}

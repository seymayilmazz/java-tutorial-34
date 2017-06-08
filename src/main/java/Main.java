import models.Kitap;
import models.Uye;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Şeyma Yılmaz on 7.6.2017.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("com.mysql.jdbc.Driver class'ı bulunamadı!");
            return;
        }

        Scanner scanner=new Scanner(System.in);

        while (true){

            System.out.println("----MENU----");
            System.out.println("1-KİTAPLARI LİSTELE");
            System.out.println("2-KİTAP EKLE");
            System.out.println("3-KİTAP SİL");
            System.out.println("4-KİTAP DÜZENLE");
            System.out.println("5-ÜYELERİ LİSTELE");
            System.out.println("6-ÜYE EKLE");
            System.out.println("7-ÜYE SİL");
            System.out.println("8-ÜYE DÜZENLE");
            System.out.println("9-ÇIKIŞ");
            System.out.println("Lütfen seçiminizi giriniz: ");

            int secim = 0;
            try {
                secim = scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Lütfen menüdeki seçeneklerden birisini seçin!");
                scanner.next();
                continue;
            }

            if(secim ==Menu.KITAPLARI_LISTELE){
                List<Kitap> kitapListesi = DatabaseHelper.kitapListesiGetir();
                DatabaseHelper.kitapListesiYazdir(kitapListesi);

            }
            else if(secim==Menu.KITAP_EKLE){
                Kitap kitap = new Kitap();

                System.out.print("Kitap ismini girin : ");
                scanner.nextLine();
                String kitapIsmi = scanner.nextLine();
                kitap.setIsim(kitapIsmi);

                System.out.print("Kitap türünü girin :  ");
                String kitapTur = scanner.nextLine();
                kitap.setTur(kitapTur);

                System.out.println("Kitap adetini girin : ");
                int adet = scanner.nextInt();
                kitap.setAdet(adet);

                System.out.println("Kitap yazarını girin : ");
                scanner.nextLine();
                String yazar = scanner.nextLine();
                kitap.setYazar(yazar);

                /* degerleri al */
                DatabaseHelper.kitapEkle(kitap);
            }
            else if(secim==Menu.KITAP_SIL){
                List<Kitap> kitapListesi = DatabaseHelper.kitapListesiGetir();
                DatabaseHelper.kitapListesiYazdir(kitapListesi);

                System.out.println("Lütfen silinecek kitap id'sini girin : ");
                scanner.nextLine();
                int kitapId = scanner.nextInt();
                boolean kitapVarMi = DatabaseHelper.kitapVarMi(kitapId);

                if(kitapVarMi == false){
                    System.out.println("Bu id'ye sahip bir kitap yoktur");
                }
                else{
                    DatabaseHelper.kitapSil(kitapId);
                    System.out.println("kitap basariyla silindi");
                }


            }
            else if(secim==Menu.KITAP_DUZENLE){

            }
            else if(secim == Menu.UYELERİ_LISTELE){
                List<Uye> uyeListesi = DatabaseHelper.uyeListesiGetir();
                DatabaseHelper.uyeListesiniYazdir(uyeListesi);

            }
            else if(secim == Menu.UYE_EKLE){
                Uye uye=new Uye();
                System.out.println("üye isim: ");
                scanner.nextLine();
                String uyeIsmi=scanner.nextLine();
                uye.setIsim(uyeIsmi);
                System.out.println("üye soyisim: ");
                String uyeSoyisim=scanner.nextLine();
                uye.setSoyisim(uyeSoyisim);
                System.out.println("tc no giriniz:  ");
                String tcNo=scanner.nextLine();
                uye.setTcNo(tcNo);
                System.out.println("adresi giriniz:  ");
                String adres=scanner.nextLine();
                uye.setAdres(adres);

                DatabaseHelper.uyeEkle(uye);
            }
            else if(secim == Menu.UYE_SİL){
                List<Uye> uyeListesi= DatabaseHelper.uyeListesiGetir();
                DatabaseHelper.uyeListesiniYazdir(uyeListesi);

                System.out.println("Lütfen silinecek üye id'si giriniz:  ");
                scanner.nextLine();
                int uyeId=scanner.nextInt();
                boolean uyeVarMi=DatabaseHelper.uyeVarMi(uyeId);

                if(!uyeVarMi){
                    System.out.println("böyle bir üye yok ");
                }
                else{
                   DatabaseHelper.uyeSil(uyeId);
                    System.out.println("üye başarıyla silindi! ");
                }
            }
            else if(secim == Menu.UYE_DUZENLE){

            }
            else if(secim == Menu.CIKIS){
                break;
            }
            else{
                System.out.println("Yanlış seçim tekrar deneyiniz!");
            }



        }
    }
}

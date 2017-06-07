import models.Kitap;
import models.Uye;

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
                /* degerleri al */
                DatabaseHelper.kitapEkle(kitap);
            }
            else if(secim==Menu.KITAP_SIL){

            }
            else if(secim==Menu.KITAP_DUZENLE){

            }
            else if(secim == Menu.UYELERİ_LISTELE){
                List<Uye> uyeListesi = DatabaseHelper.uyeListesiGetir();
                DatabaseHelper.uyeListesiniYazdir(uyeListesi);

            }
            else if(secim == Menu.UYE_EKLE){

            }
            else if(secim == Menu.UYE_SİL){

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

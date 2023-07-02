import java.awt.desktop.SystemEventListener;
import java.util.*;

public class PhoneManagementApp {
    private Map<String, Phone> phones; // veritabanı
    Map<String, List<App>> appList ;

    public PhoneManagementApp() {
        phones = new HashMap<>();
       appList=new HashMap<>();
    }

    public void add(Phone phone) {
        phones.put(phone.getSerialNumber(), phone);
        System.out.println("Phone eklendi.");
    }

    public boolean search(String serialNumber) {
        phones.forEach((s, phone) -> System.out.println(phone.getSerialNumber()));
        Optional<Phone> foundPhone = phones.values().stream().
                filter(phone -> phone.getSerialNumber().equalsIgnoreCase(serialNumber)).findFirst();
        if (foundPhone.isPresent()) {
            System.out.println("Telefon bulundu: " + foundPhone.get());
            foundPhone.get().getApps();
        } else {
            System.out.println("Telefon bulunamadı.");
        }
        return foundPhone.isPresent();
    }

    public Map<String,Phone> list() {
        var listString="";


        for (Phone s : phones.values()){
            System.out.println("Telefon Modeli: "+ s.getModel());
            for(List<App> s1:appList.values()){
                for( App s2:s1){
                    System.out.println(s2.getAppName());
                }
            }
        }
        System.out.println("-----------------------------");
        return  phones;

    }
    public void geriYuklemeList(Map<String, Phone> phonesV2) {
        phones =phonesV2;
    }
    public void addApp(String serialNumber, Map<String, App> apps) {


        Optional<Phone> foundPhone = phones.values().stream().
                filter(phone -> phone.getSerialNumber().equalsIgnoreCase(serialNumber)).findFirst();

        //foundPhone.get().setApps(apps);

        appList.put(serialNumber,apps.values().stream().toList());

            String serialNumber1 = foundPhone.get().getSerialNumber();
            if (appList.containsKey(serialNumber1)) {
                List<App> uygulamalar = appList.get(serialNumber);
                for (App app : uygulamalar) {
                    foundPhone.get().uygulamaEkle(serialNumber1,app);
                    foundPhone.get().getApps().values().forEach( app1 -> System.out.println("ADD APP : "+app1.getAppName()));
                }

                System.out.println("LİSTE");

                for(App app:foundPhone.get().getApps().values()){
                    System.out.println("Uygulama:" + app.getAppName());
                }
        }


        System.out.println(" App Phone eklendi.");

    }

    public void deleteApp(String serialNumber, App app) {
        Optional<Phone> foundPhone = phones.values().stream().
                filter(phone -> phone.getSerialNumber().equalsIgnoreCase(serialNumber)).findFirst();

        foundPhone.get().getApps().remove(app);
        System.out.println(" App Phone silindi.");

    }



}

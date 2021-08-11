package by.yurachel.springapp.initDb.initPhones;

import by.yurachel.springapp.model.phone.OperatingSystem;
import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.service.IService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InitPhones {
    private final IService<Phone> service;

    public InitPhones(IService<Phone> service) {
        this.service = service;
    }

    public void initPhones() {
        List<Phone> list = new ArrayList<>();
        Phone phone1 = new Phone();
        phone1.setName("Samsung Galaxy S21");
        phone1.setImg("https://img.router-switch.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/s/a/samsung-galaxy-s21-5g-sm-g9910.jpg");
        phone1.setImages(Arrays.asList("https://www.ixbt.com/img/n1/news/2021/5/3/samsung-galaxy-s21_large_large_large.jpeg"));
        phone1.setOs(OperatingSystem.ANDROID);
        phone1.setScreenSize(6.2);
        phone1.setProcessor("Qualcomm Snapdragon 888");
        phone1.setRandomAccessMemory(8);
        phone1.setNumberOfMatrixPoints(64);
        phone1.setAccumulatorCapacity(4000);
        phone1.setNumberOfSimCards(2);
        phone1.setPrice(850);
        phone1.setNumberOfMainCameras(3);
        phone1.setCpuClockSpeed(2840);
        phone1.setGraphicsAccelerator("Adreno 660");
        phone1.setBackCoverMaterial("plastic");
        phone1.setBodyColor("purple");
        phone1.setBodyMaterial("metal");
        phone1.setAccumulatorType("Li-ion");
        phone1.setConnectionConnector("USB Type-C");
        phone1.setLength(151.7);
        phone1.setScreenRefreshRate(120);
        phone1.setScreenTechnology("AMOLED");
        phone1.setThickness(7.9);
        phone1.setWeight(169);
        phone1.setWidth(71.2);
        phone1.setWifi("5.0");
        list.add(phone1);

        Phone phone2 = new Phone();
        phone2.setName("Apple iPhone 11");
        phone2.setImg("https://med.csmobiles.com/192086-large_default/apple-iphone-11-128gb-rojo.jpg");
        phone2.setImages(Arrays.asList("https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-yellow-select-2019?wid=834&hei=1000&fmt=jpeg&qlt=95&.v=1568141245782",
                "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone11-select-2019-family?wid=882&hei=1058&fmt=jpeg&qlt=80&.v=1567022175704"));
        phone2.setOs(OperatingSystem.IOS);
        phone2.setScreenSize(6.1);
        phone2.setProcessor("Apple A13 Bionic");
        phone2.setRandomAccessMemory(4);
        phone2.setNumberOfMatrixPoints(12);
        phone2.setAccumulatorCapacity(3046);
        phone2.setNumberOfSimCards(1);
        phone2.setPrice(650);
        phone2.setNumberOfMainCameras(2);
        phone2.setCpuClockSpeed(2650);
        phone2.setGraphicsAccelerator("Apple A13 GPU");
        phone2.setBackCoverMaterial("glass");
        phone2.setBodyColor("black");
        phone2.setBodyMaterial("metal");
        phone2.setAccumulatorType("Li-ion");
        phone2.setConnectionConnector("lightning");
        phone2.setLength(150.9);
        phone2.setScreenRefreshRate(60);
        phone2.setScreenTechnology("IPS");
        phone2.setThickness(8.3);
        phone2.setWeight(194);
        phone2.setWidth(75.7);
        phone2.setWifi("5.0");

        list.add(phone2);

        Phone phone3 = new Phone();
        phone3.setName("Samsung Galaxy A52");
        phone3.setImg("https://alldeviceprice.com/wp-content/uploads/2021/05/Samsung-Galaxy-A52-5G.jpg");
        phone3.setImages(Arrays.asList("https://images.laurentwillen.be/sites/21/2021/04/samsung-galaxy-a52-test-avis-review-recensione-bewertung-analisis-top-scaled.jpg",
                "https://www.ixbt.com/img/n1/news/2021/1/5/gsmarena_002_large.jpg"));
        phone3.setOs(OperatingSystem.ANDROID);
        phone3.setScreenSize(6.5);
        phone3.setProcessor("Qualcomm Snapdragon 720G");
        phone3.setRandomAccessMemory(4);
        phone3.setNumberOfMatrixPoints(64);
        phone3.setAccumulatorCapacity(4500);
        phone3.setNumberOfSimCards(2);
        phone3.setPrice(370);
        phone3.setNumberOfMainCameras(4);
        phone3.setCpuClockSpeed(2300);
        phone3.setGraphicsAccelerator("Adreno 618");
        phone3.setBackCoverMaterial("plastic");
        phone3.setBodyColor("black");
        phone3.setBodyMaterial("plastic");
        phone3.setAccumulatorType("Li-ion");
        phone3.setConnectionConnector("USB Type-C");
        phone3.setLength(159.9);
        phone3.setScreenRefreshRate(90);
        phone3.setScreenTechnology("AMOLED");
        phone3.setThickness(8.4);
        phone3.setWeight(189);
        phone3.setWidth(75.1);
        phone3.setWifi("5.0");

        list.add(phone3);

        Phone phone4 = new Phone();
        phone4.setName("POCO X3 Pro");
        phone4.setImg("https://i01.appmifile.com/webfile/globalimg/Anna/x3-pro-gold.png");
        phone4.setImages(Arrays.asList("https://i.ytimg.com/vi/sB0suKK3vXQ/maxresdefault.jpg",
                "https://www.techadvisor.com/cmsdata/reviews/3804862/poco_x3_pro_review_thumb800.jpg"));
        phone4.setOs(OperatingSystem.ANDROID);
        phone4.setScreenSize(6.67);
        phone4.setProcessor("Qualcomm Snapdragon 860");
        phone4.setRandomAccessMemory(8);
        phone4.setNumberOfMatrixPoints(48);
        phone4.setAccumulatorCapacity(5160);
        phone4.setNumberOfSimCards(2);
        phone4.setPrice(350);
        phone4.setNumberOfMainCameras(4);
        phone4.setCpuClockSpeed(2960);
        phone4.setGraphicsAccelerator("Adreno 640");
        phone4.setBackCoverMaterial("plastic");
        phone4.setBodyColor("black");
        phone4.setBodyMaterial("plastic");
        phone4.setAccumulatorType("Li-ion");
        phone4.setConnectionConnector("USB Type-C");
        phone4.setLength(165.3);
        phone4.setScreenRefreshRate(120);
        phone4.setScreenTechnology("IPS");
        phone4.setThickness(9.4);
        phone4.setWeight(215);
        phone4.setWidth(76.8);
        phone4.setWifi("5.0");

        list.add(phone4);

        Phone phone5 = new Phone();
        phone5.setName("Huawei P40 lite");
        phone5.setImg("https://www.gizmochina.com/wp-content/uploads/2019/12/Huawei-nova-6-SE-500x500.jpg");
        phone5.setImages(Arrays.asList("https://www.mytrendyphone.eu/images/Huawei-P40-lite-128GB-Black-6901443375769-28042020-01-p.jpg",
                "https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=HU3145b4"));
        phone5.setOs(OperatingSystem.ANDROID);
        phone5.setScreenSize(6.4);
        phone5.setProcessor("HiSilicon Kirin 810");
        phone5.setRandomAccessMemory(6);
        phone5.setNumberOfMatrixPoints(48);
        phone5.setAccumulatorCapacity(4200);
        phone5.setNumberOfSimCards(2);
        phone5.setPrice(245);
        phone5.setNumberOfMainCameras(4);
        phone5.setCpuClockSpeed(2270);
        phone5.setGraphicsAccelerator("Mali-G52 MP6");
        phone5.setBackCoverMaterial("plastic");
        phone5.setBodyColor("pink");
        phone5.setBodyMaterial("plastic");
        phone5.setAccumulatorType("Li-ion");
        phone5.setConnectionConnector("USB Type-C");
        phone5.setLength(159.2);
        phone5.setScreenRefreshRate(60);
        phone5.setScreenTechnology("IPS");
        phone5.setThickness(8.7);
        phone5.setWeight(183);
        phone5.setWidth(76.3);
        phone5.setWifi("5.0");

        list.add(phone5);

        Phone phone6 = new Phone();
        phone6.setName("Xiaomi Mi 10T");
        phone6.setImg("https://sc04.alicdn.com/kf/H802e1e5773024501863f9baedaf11d65X.jpg");
        phone6.setImages(Arrays.asList("https://www.ixbt.com/img/n1/news/2021/6/6/DSCF7380.0_large_large_large_0_large.jpg",
                "https://mobile-review.com/review/image/xiaomi/mi-10t-pro/pic/31.jpg"));
        phone6.setOs(OperatingSystem.ANDROID);
        phone6.setScreenSize(6.67);
        phone6.setProcessor("Qualcomm Snapdragon 865");
        phone6.setRandomAccessMemory(8);
        phone6.setNumberOfMatrixPoints(64);
        phone6.setAccumulatorCapacity(5000);
        phone6.setNumberOfSimCards(2);
        phone6.setPrice(460);
        phone6.setNumberOfMainCameras(3);
        phone6.setCpuClockSpeed(2840);
        phone6.setGraphicsAccelerator("Adreno 650");
        phone6.setBackCoverMaterial("glass");
        phone6.setBodyColor("white");
        phone6.setBodyMaterial("metal");
        phone6.setAccumulatorType("Li-ion");
        phone6.setConnectionConnector("USB Type-C");
        phone6.setLength(165.1);
        phone6.setScreenRefreshRate(144);
        phone6.setScreenTechnology("IPS");
        phone6.setThickness(9.33);
        phone6.setWeight(216);
        phone6.setWidth(76.4);
        phone6.setWifi("5.0");

        list.add(phone6);

        Phone phone7 = new Phone();
        phone7.setName("Google Pixel 4a");
        phone7.setImg("https://cdn.alzashop.com/ImgW.ashx?fd=f16&cd=GPX1065b1");
        phone7.setImages(Arrays.asList("https://www.ixbt.com/img/n1/news/2020/4/5/Google_Pixel_4a_XL_cover_2048x2048_large.jpg",
                "https://www.notebookcheck-ru.com/uploads/tx_nbc2/4_zu_3_Teaser_Google_Pixel_4a.jpg"));
        phone7.setOs(OperatingSystem.ANDROID);
        phone7.setScreenSize(5.8);
        phone7.setProcessor("Qualcomm Snapdragon 730G");
        phone7.setRandomAccessMemory(6);
        phone7.setNumberOfMatrixPoints(12);
        phone7.setAccumulatorCapacity(3140);
        phone7.setNumberOfSimCards(1);
        phone7.setPrice(500);
        phone7.setNumberOfMainCameras(1);
        phone7.setCpuClockSpeed(2200);
        phone7.setGraphicsAccelerator("Adreno 618");
        phone7.setBackCoverMaterial("plastic");
        phone7.setBodyColor("black");
        phone7.setBodyMaterial("plastic");
        phone7.setAccumulatorType("Li-ion");
        phone7.setConnectionConnector("USB Type-C");
        phone7.setLength(144);
        phone7.setScreenRefreshRate(60);
        phone7.setScreenTechnology("OLED");
        phone7.setThickness(8.2);
        phone7.setWeight(143);
        phone7.setWidth(69.4);
        phone7.setWifi("5.0");

        list.add(phone7);

        Phone phone8 = new Phone();
        phone8.setName("Apple iPhone 12 Pro Max");
        phone8.setImg("https://www.mytrendyphone.eu/images/iPhone-12-Pro-Max-128GB-Graphite-0194252021200-25102020-1-p.jpg");
        phone8.setImages(Arrays.asList("https://cdn57.androidauthority.net/wp-content/uploads/2020/12/Apple-iPhone-12-Pro-vs-iPhone-12-Max-camera-2.jpg",
                "https://s.yimg.com/os/creatr-uploaded-images/2020-10/75b8c040-0d7c-11eb-bcb6-329ef15230da"));
        phone8.setOs(OperatingSystem.IOS);
        phone8.setScreenSize(6.7);
        phone8.setProcessor("Apple A14 Bionic");
        phone8.setRandomAccessMemory(6);
        phone8.setNumberOfMatrixPoints(12);
        phone8.setAccumulatorCapacity(3687);
        phone8.setNumberOfSimCards(1);
        phone8.setPrice(1300);
        phone8.setNumberOfMainCameras(3);
        phone8.setCpuClockSpeed(2900);
        phone8.setGraphicsAccelerator("Apple A14 GPU");
        phone8.setBackCoverMaterial("glass");
        phone8.setBodyColor("black");
        phone8.setBodyMaterial("metal");
        phone8.setAccumulatorType("Li-ion");
        phone8.setConnectionConnector("lightning");
        phone8.setLength(160.8);
        phone8.setScreenRefreshRate(60);
        phone8.setScreenTechnology("OLED");
        phone8.setThickness(7.4);
        phone8.setWeight(228);
        phone8.setWidth(78.1);
        phone8.setWifi("5.0");

        list.add(phone8);

        savePhones(list);

    }

    private void savePhones(List<Phone> phones) {
        phones.forEach(service::save);
    }
}

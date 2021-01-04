package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TienDien {
    private String maphong;
    private int Sokw;
    private int Sokw2;

    public TienDien(String maphong, int sokw, int sokw2) {
        this.maphong = maphong;
        Sokw = sokw;
        Sokw2=sokw2;
    }
    public TienDien(){}
    public  String toString(){
        return  maphong+"#"+Sokw+"#"+Sokw2+"\n";
    }

    public String getMaphong() {
        return maphong;
    }

    public void setMaphong(String maphong) {
        this.maphong = maphong;
    }

    public int getSokw() {
        return Sokw;
    }

    public void setSokw(int sokw) {
        Sokw = sokw;
    }

    public int getSokw2() {
        return Sokw2;
    }
    public void setSokw2(int sokw2) {
        Sokw2 = sokw2;
    }
}
class nhapTiendien{
    Scanner  scanner=new Scanner(System.in);
    String maphong;
    int Sokw,Sokw2;
    public String maphong() {
        do {
            System.out.println("Nhập mã lớp: ");
            maphong = scanner.nextLine();
        } while (maphong.equals(""));
        return maphong;
    }
    public int Sokw() {
        do {
            System.out.println("Nhập chỉ số công tơ tháng trước: ");
            Sokw = Integer.parseInt(scanner.nextLine());
        } while (Sokw <= 0);
        return Sokw;
    }
    public int sokw2() {
        do {
            System.out.println("Nhập chỉ số công tơ tháng này:");
            Sokw2 = Integer.parseInt(scanner.nextLine());
        } while (Sokw2 <= Sokw);

        return Sokw2;
    }
    public void nhaptiendien(ArrayList<TienDien> tienDienArrayList){
        while (true) {
            tienDienArrayList.add(new TienDien(maphong(),Sokw(),sokw2()));
            System.out.println("Bạn có muốn nhập tiếp không : Có/Không");
            String chon =scanner.nextLine();
            if(chon.equalsIgnoreCase("Không")) break;
        }

    }
}
class xulytiendien{
    public static Scanner sc = new Scanner(System.in);

    public void sua(ArrayList<TienDien> tienDienArrayList) {
        boolean kt = false;
        String keyword = sc.nextLine();
        for (int i = 0; i < tienDienArrayList.size(); i++) {
            if (keyword.equalsIgnoreCase(tienDienArrayList.get(i).getMaphong())) {
                kt = true;
                System.out.println(" Tìm thấy thông tin");
                System.out.println("Nhập mã lớp mới :");
                String maphong = sc.nextLine();
                System.out.println("Nhập lại chỉ số công tơ tháng trước mới :");
                int sokw = Integer.parseInt(sc.nextLine());
                System.out.println("Nhập lại chỉ số công tơ tháng này mới :");
                int sokw2 = Integer.parseInt(sc.nextLine());
                tienDienArrayList.set(i, new TienDien(maphong, sokw,sokw2));
            }

        }
        if (kt == false) System.out.println("Không tìm thấy mã lớp muốn sửa !");

    }

    public void xoa(ArrayList<TienDien> tienDienArrayList ) {
        String keyword = sc.nextLine();
        boolean kt = false;
        for (int i = 0; i < tienDienArrayList.size(); i++) {
            if (keyword.equalsIgnoreCase(tienDienArrayList.get(i).getMaphong())) {
                kt = true;
                System.out.println("Xóa thành công lớp có mã :" + tienDienArrayList.get(i).getMaphong());
                tienDienArrayList.remove(i);
            }
        }
        if (kt == false) System.out.println("Không tìm thấy mã lớp cần xóa !");
    }

    public void timkiem(ArrayList<TienDien> tienDienArrayList) {
        String keyword = sc.nextLine();
        boolean kt = false;

        for (TienDien tienDien : tienDienArrayList
        ) {
            if (keyword.equalsIgnoreCase(tienDien.getMaphong())) {
                System.out.println("Tìm kiếm thành công .");
                hientiendien hientiendien= new hientiendien();
               hientiendien.hientiendien(tienDien);
               kt = true;
            }
        }
        if (kt == false) System.out.println("Không tìm thấy mã lớp cần tìm !");
    }

    public void sapxepsotientulondenbe(ArrayList<TienDien> tienDienArrayList) {
        Collections.sort(tienDienArrayList, new Comparator<TienDien>() {
            @Override
            public int compare(TienDien o1, TienDien o2) {
                int t1=o1.getSokw2()-o1.getSokw();
                int t2= o2.getSokw2()- o2.getSokw();
                if (t1>t2) return -1;
                return 1;
            }
        });
    }
public int tiendien(int Sokw,int Sokw2){
        int t=0;
        return (Sokw2-Sokw)*1526;
}


}
class hientiendien{
    public void hientiendien(TienDien tienDien){
        xulytiendien xuly=new xulytiendien();
        System.out.printf("|%20s|%25s|%25s|%20s đ|\n",tienDien.getMaphong(),tienDien.getSokw(),tienDien.getSokw2()
                ,xuly.tiendien(tienDien.getSokw(),tienDien.getSokw2()));
        System.out.println("-------------------------------------------------------------------------------------------------");
    }
    public void hienalltiendien(ArrayList<TienDien>tienDienArrayList){
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("|%20s|%25s|%25s|%20s  |\n","Mã lớp","Số công tơ tháng trước","Số công tơ tháng này","Tiền điện");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (TienDien tienDien:tienDienArrayList
             ) {hientiendien(tienDien);
            
        }
    }
}
class xulyfiletiendien {

    static File file = new File("tiendien.txt");

    public void ghifile(ArrayList<TienDien> tienDienArrayList) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (TienDien tienDien : tienDienArrayList
        ) {
            fileWriter.write(tienDien.toString());
        }
        fileWriter.close();
    }

    public void docfile(ArrayList<TienDien> tienDienArrayList) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String maphong,int siso,String csvc,String vitri,int sokw
        String line ="";
        while ((line = bufferedReader.readLine()) != null) {
//            tienDienArrayList.add(new tiendien(line.split("#")[0],Integer.parseInt(line.split("#")[1]),
//                    line.split("#")[2],line.split("#")[3]));
//            //    line.split("#")[0],Integer.parseInt(line.split("#")[1],Integer.parseInt(line.split("#")[2])
            tienDienArrayList.add(new TienDien(line.split("#")[0],Integer.parseInt(line.split("#")[1]),Integer.parseInt(line.split("#")[2])));
        }

    }
}

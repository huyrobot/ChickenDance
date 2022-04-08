/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TuanLE
 */
public class KhungHinh {

    private ArrayList<BufferedImage> list;
    private double thoiGianThayDoi;
    private int khungHinhHienTai = 0;
    private double thoiGianTruoc;

    public KhungHinh(int thoiGianThayDoi) {
        this.thoiGianThayDoi = thoiGianThayDoi;
        list = new ArrayList<BufferedImage>();
        thoiGianTruoc = 0;
    }

    public void themKhungHinh(BufferedImage hinh) {
        list.add(hinh);
    }

    public BufferedImage getKhungHinh() {
        return list.get(khungHinhHienTai);
    }

    public void thayDoiKhungHinh() {
        if (System.currentTimeMillis() - thoiGianTruoc >= thoiGianThayDoi) {
            khungHinhHienTai++;
            if (khungHinhHienTai >= list.size()) {
                khungHinhHienTai = 0;
            }
            thoiGianTruoc = System.currentTimeMillis();
        }
    }

}

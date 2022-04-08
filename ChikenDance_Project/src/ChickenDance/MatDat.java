/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChickenDance;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TuanLE
 */
public class MatDat {

    private class HinhMatDat {
        double diChuyen_X;
        BufferedImage hinh;
    }
    private TinhToanGame mainTinhToanGame;

    public static final int toaDoMatDat_Y = 153;

    private ArrayList<HinhMatDat> danhSachMatDat;
    private BufferedImage matDat1;
    private BufferedImage matDat2;
    private BufferedImage matDat3;

    public MatDat(int chieuDaiDat, TinhToanGame mainTinhToanGame) {
        this.mainTinhToanGame = mainTinhToanGame;
        matDat1 = layHinhAnh("data/matDat1.png");
        matDat2 = layHinhAnh("data/matDat2.png");
        matDat3 = layHinhAnh("data/matDat3.png");

        int chieuDaiTongMatDat = chieuDaiDat / matDat1.getWidth() + 2;
        danhSachMatDat = new ArrayList<HinhMatDat>();
        for (int i = 0; i < chieuDaiTongMatDat; i++) {
            HinhMatDat hinhDat = new HinhMatDat();
            hinhDat.diChuyen_X = i * matDat1.getWidth();
            setHinhChoMatDat(hinhDat);
            danhSachMatDat.add(hinhDat);
        }
    }

    public void diChuyenMatDat() {
        Iterator<HinhMatDat> iterator = danhSachMatDat.iterator();
        HinhMatDat doiTuongDauTien = iterator.next();
        doiTuongDauTien.diChuyen_X -= mainTinhToanGame.getTocDoX();
        double diChuyenVeTruoc_X = doiTuongDauTien.diChuyen_X;
        while (iterator.hasNext()) {
            HinhMatDat doiTuong = iterator.next();
            doiTuong.diChuyen_X = diChuyenVeTruoc_X + matDat1.getWidth();
            diChuyenVeTruoc_X = doiTuong.diChuyen_X;
        }
        if (doiTuongDauTien.diChuyen_X < -matDat1.getWidth()) {
            danhSachMatDat.remove(doiTuongDauTien);
            doiTuongDauTien.diChuyen_X = diChuyenVeTruoc_X + matDat1.getWidth();
            setHinhChoMatDat(doiTuongDauTien);
            danhSachMatDat.add(doiTuongDauTien);
        }
    }

    private void setHinhChoMatDat(HinhMatDat hmd) {
        int loaiMatDat = getLoaiMatDat();
        if (loaiMatDat == 1) {
            hmd.hinh = matDat1;
        } else if (loaiMatDat == 3) {
            hmd.hinh = matDat3;
        } else {
            hmd.hinh = matDat2;
        }
    }

    public void drawMatDat(Graphics g) {
        for (HinhMatDat hinhDat : danhSachMatDat) {
            g.drawImage(hinhDat.hinh, (int) hinhDat.diChuyen_X, toaDoMatDat_Y, null);
        }
    }

    private int getLoaiMatDat() {
        Random rand = new Random();
        int loaiDat = rand.nextInt(10);
        if (loaiDat == 1) {
            return 1;
        } else if (loaiDat == 9) {
            return 3;
        } else {
            return 2;
        }
    }
public static BufferedImage layHinhAnh(String duongDan) {
        BufferedImage hinh = null;
        try {
            hinh = ImageIO.read(new File(duongDan));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hinh;
    }
}

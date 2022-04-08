/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChickenDance;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import UI.KhungHinh;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TuanLE
 */
public class TinhToanGame {

    private static final int trangThaiDangChay = 0;
    private static final int trangThaiDangNhay = 1;
    private static final int trangThaiChet = 2;
    public static final int viTriChickenDung = 162;
    public static final float trongLucRoi = 0.45f;

    private float diChuyen_Y;
    private float diChuyen_X;
    private float tocDoX;
    private float tocDoY;
    private Rectangle phamViRectangle;

    public int diem = 0;

    private int trangThai = trangThaiDangChay;

    private KhungHinh runBinhThuong;
    private BufferedImage dangNhay;
    private BufferedImage anhGaChet;

    private AudioClip tiengGaKhiNhay;
    private AudioClip tiengGaKhiChet;
    private AudioClip phanThuongVuiTai;

    public TinhToanGame() {
        diChuyen_X = 50;
        diChuyen_Y = viTriChickenDung;
        phamViRectangle = new Rectangle();
        runBinhThuong = new KhungHinh(90);
        runBinhThuong.themKhungHinh(layHinhAnh("data/gaChay1.png"));
        runBinhThuong.themKhungHinh(layHinhAnh("data/gaChay2.png"));
        dangNhay = layHinhAnh("data/gaNhay.png");
        anhGaChet = layHinhAnh("data/gaChet.png");

        try {
            tiengGaKhiNhay = Applet.newAudioClip(new URL("file", "", "data/gaKeuKhiNhay.wav"));
            tiengGaKhiChet = Applet.newAudioClip(new URL("file", "", "data/gaKeuKhiChet.wav"));
            phanThuongVuiTai = Applet.newAudioClip(new URL("file", "", "data/amThanhPhanThuong.wav"));
        } catch (MalformedURLException e) {
            System.out.println("Lỗi đường dẫn âm thanh không hợp lệ");
            e.printStackTrace();
        }
    }

    public float getTocDoX() {
        return tocDoX;
    }

    public void setTocDoX(int tocDoX) {
        this.tocDoX = tocDoX;
    }

    public void drawTrangThaiConGa(Graphics g) {
        switch (trangThai) {
            case trangThaiDangChay:
                g.drawImage(runBinhThuong.getKhungHinh(), (int) diChuyen_X, (int) diChuyen_Y, null);
                break;
            case trangThaiDangNhay:
                g.drawImage(dangNhay, (int) diChuyen_X, (int) diChuyen_Y, null);
                break;
            case trangThaiChet:
                g.drawImage(anhGaChet, (int) diChuyen_X, (int) diChuyen_Y, null);
                break;
        }
    }

    public void capNhatViTriGa() {
        runBinhThuong.thayDoiKhungHinh();
        if (diChuyen_Y >= viTriChickenDung) {
            diChuyen_Y = viTriChickenDung;
            trangThai = trangThaiDangChay;

        } else {
            tocDoY += trongLucRoi;
            diChuyen_Y += tocDoY;
        }
    }

    public void gaNhay() {
        if (diChuyen_Y >= viTriChickenDung) {
            if (tiengGaKhiNhay != null) {
                tiengGaKhiNhay.play();
            }
            tocDoY = -7.5f;
            diChuyen_Y += tocDoY;
            trangThai = trangThaiDangNhay;
        }
    }

    public void khiDuoc5Diem() {
        diem = diem + 1;
        if (diem % 5 == 0) {
            phanThuongVuiTai.play();
        }
    }

    public Rectangle getGioiHan() {
        phamViRectangle = new Rectangle();
        phamViRectangle.x = (int) diChuyen_X + 5;
        phamViRectangle.y = (int) diChuyen_Y;
        phamViRectangle.width = runBinhThuong.getKhungHinh().getWidth() - 10;
        phamViRectangle.height = runBinhThuong.getKhungHinh().getHeight();
        return phamViRectangle;
    }

    public void gaChet(boolean gaDaChet) {
        if (gaDaChet == true) {
            trangThai = trangThaiChet;
            //diem=0;
        } else {
            trangThai = trangThaiDangChay;
        }
    }

    public void reset() {
        diChuyen_Y = viTriChickenDung;
    }

    public void phatAmThanhGaChet() {
        tiengGaKhiChet.play();
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

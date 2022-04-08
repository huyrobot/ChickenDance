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
import UI.RunGame;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TuanLE
 */
public class DamMay {

    private class HinhDamMay {
        double x;
        int y;
    }

    private ArrayList<HinhDamMay> cacDamMay;
    private BufferedImage damMay;

    private TinhToanGame mainTinhToan;

    public DamMay(int chieuRong, TinhToanGame mainTinhToan) {
        this.mainTinhToan = mainTinhToan;
        damMay = layHinhAnh("data/may.png");
        cacDamMay = new ArrayList<HinhDamMay>();

        //add các dám mây
        HinhDamMay hinhMay = new HinhDamMay();
        hinhMay.x = 0;
        hinhMay.y = 30;
        cacDamMay.add(hinhMay);

        hinhMay = new HinhDamMay();
        hinhMay.x = 150;
        hinhMay.y = 40;
        cacDamMay.add(hinhMay);

        hinhMay = new HinhDamMay();
        hinhMay.x = 300;
        hinhMay.y = 50;
        cacDamMay.add(hinhMay);

        hinhMay = new HinhDamMay();
        hinhMay.x = 450;
        hinhMay.y = 20;
        cacDamMay.add(hinhMay);

        hinhMay = new HinhDamMay();
        hinhMay.x = 600;
        hinhMay.y = 80;
        cacDamMay.add(hinhMay);

        hinhMay = new HinhDamMay();
        hinhMay.x = 800;
        hinhMay.y = 100;
        cacDamMay.add(hinhMay);
    }

    public void diChuyenDamMay() {
        Iterator<HinhDamMay> iterator = cacDamMay.iterator();
        HinhDamMay doiTuongDauTien = iterator.next();
        doiTuongDauTien.x -= mainTinhToan.getTocDoX() / 8;

        while (iterator.hasNext()) {
            HinhDamMay doiTuong = iterator.next();
            doiTuong.x -= mainTinhToan.getTocDoX() / 8;
        }

        if (doiTuongDauTien.x < -damMay.getWidth()) {
            cacDamMay.remove(doiTuongDauTien);
            doiTuongDauTien.x = RunGame.chieuNgangManHinh;
            cacDamMay.add(doiTuongDauTien);
        }
    }

    public void drawDamMay(Graphics g) {
        //vẽ các dám mây
        for (HinhDamMay imgLand : cacDamMay) {
            g.drawImage(damMay, (int) imgLand.x, imgLand.y, null);
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

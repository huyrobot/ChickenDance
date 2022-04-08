/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChickenDance;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author TuanLE
 */
public class DoiTuongManagement {
    private BufferedImage cotGai1;
    private BufferedImage cotGai2;
    private Random randomNumber;

    private ArrayList<DoiTuong> cacDoiTuong;
    private TinhToanGame mainTinhToanGame;

    public DoiTuongManagement(TinhToanGame mainTinhToanGame) {
        randomNumber = new Random();
        cotGai1 = layHinhAnh("data/cotGai1.png");
        cotGai2 = layHinhAnh("data/cotGai2.png");
        cacDoiTuong = new ArrayList<DoiTuong>();
        this.mainTinhToanGame = mainTinhToanGame;
        cacDoiTuong.add(taoDoiTuong());
    }

    public void capNhatViTriDoiTuong() {
        for (DoiTuong e : cacDoiTuong) {
            e.capNhatViTriDoiTuong();
        }
        DoiTuong enemy = cacDoiTuong.get(0);
        if (enemy.outKhoiManHinh()) {
            mainTinhToanGame.khiDuoc5Diem();
            cacDoiTuong.clear();
            cacDoiTuong.add(taoDoiTuong());
        }
    }

    public void drawDoiTuong(Graphics g) {
        for (DoiTuong e : cacDoiTuong) {
            e.drawDoiTuong(g);
        }
    }

    private DoiTuong taoDoiTuong() {
        int temp = randomNumber.nextInt(2);
        if (temp == 0) {
            return new CotGai(mainTinhToanGame, 700, cotGai1.getWidth() - 10, cotGai1.getHeight() - 10, cotGai1);
        } else {
            return new CotGai(mainTinhToanGame, 700, cotGai2.getWidth() - 10, cotGai2.getHeight() - 10, cotGai2);
        }
    }

    public boolean checkVaCham() {
        for (DoiTuong e : cacDoiTuong) {
            if (mainTinhToanGame.getGioiHan().intersects(e.getGioiHan())) {
                return true;
            }
        }
        return false;
    }

    public void resetDoiTuong() {
        cacDoiTuong.clear();
        cacDoiTuong.add(taoDoiTuong());
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

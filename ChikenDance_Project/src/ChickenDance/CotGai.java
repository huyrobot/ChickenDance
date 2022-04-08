/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChickenDance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author TuanLE
 */
public class CotGai extends DoiTuong {

    public static final int viTriCotGai_Y = 205;

    private int diChuyenCotGai_X;
    private int chieuRong;
    private int chieuCao;

    private BufferedImage hinhAnhCotGai;
    private TinhToanGame mainTinhToan;

    private Rectangle gioiHanRectangle;

    public CotGai(TinhToanGame mainTinhToan, int diChuyenCotGai_X, int chieuRong, int chieuCao, BufferedImage hinhAnhCotGai) {
        this.mainTinhToan = mainTinhToan;
        this.diChuyenCotGai_X = diChuyenCotGai_X;
        this.chieuRong = chieuRong;
        this.chieuCao = chieuCao;
        this.hinhAnhCotGai = hinhAnhCotGai;
        gioiHanRectangle = new Rectangle();
    }

    @Override
    public boolean outKhoiManHinh() {
        if (diChuyenCotGai_X < -hinhAnhCotGai.getWidth()) {
            return true;
        }
        return false;
    }

    public Rectangle getGioiHan() {
        gioiHanRectangle = new Rectangle();
        gioiHanRectangle.x = (int) diChuyenCotGai_X + (hinhAnhCotGai.getWidth() - chieuRong) / 2;
        gioiHanRectangle.y = viTriCotGai_Y - hinhAnhCotGai.getHeight() + (hinhAnhCotGai.getHeight() - chieuCao) / 2;
        gioiHanRectangle.width = chieuRong;
        gioiHanRectangle.height = chieuCao;

        return gioiHanRectangle;
    }

    public void drawDoiTuong(Graphics g) {
        g.drawImage(hinhAnhCotGai, diChuyenCotGai_X, viTriCotGai_Y - hinhAnhCotGai.getHeight(), null);
        //g.setColor(Color.red);

    }

    public void capNhatViTriDoiTuong() {
        diChuyenCotGai_X -= mainTinhToan.getTocDoX();
    }

}

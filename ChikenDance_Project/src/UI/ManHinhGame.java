/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ChickenDance.DamMay;
import ChickenDance.DoiTuongManagement;
import ChickenDance.MatDat;
import ChickenDance.TinhToanGame;
import ChickenDance.TinhToanGame;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author TuanLE
 */
public class ManHinhGame extends JPanel implements Runnable, KeyListener {

    private static final int trangThaiGameBatDau = 0;
    private static final int trangThaiGameDangChoi = 1;
    private static final int trangThaiGameOver = 2;

    private MatDat matDat;
    private TinhToanGame mainTinhToanGame;
    private DoiTuongManagement quanLyDoiTuong;
    private DamMay damMay;
    private Thread thread;

    private boolean checkNhanNut;

    private BufferedImage hinhNutReplay;
    private BufferedImage hinhChuGameOver;
    private BufferedImage hinhBatDauGame;
    private BufferedImage hinhThanhVien;

    private int trangThaiGame = trangThaiGameBatDau;

    public ManHinhGame() {
        mainTinhToanGame = new TinhToanGame();
        matDat = new MatDat(RunGame.chieuNgangManHinh, mainTinhToanGame);
        mainTinhToanGame.setTocDoX(4);
        hinhNutReplay = layHinhAnh("data/nutReplay.png");
        hinhChuGameOver = layHinhAnh("data/gameOver.png");

        hinhBatDauGame = layHinhAnh("data/startGame.png");
        hinhThanhVien = layHinhAnh("data/thanhVien.png");

        quanLyDoiTuong = new DoiTuongManagement(mainTinhToanGame);
        damMay = new DamMay(RunGame.chieuNgangManHinh, mainTinhToanGame);
    }

    public void startGame() {
        thread = new Thread(this);
        thread.start();
    }

    public void thayDoiTrangThaiGame() {
        if (trangThaiGame == trangThaiGameDangChoi) {
            damMay.diChuyenDamMay();
            matDat.diChuyenMatDat();
            mainTinhToanGame.capNhatViTriGa();
            quanLyDoiTuong.capNhatViTriDoiTuong();
            if (quanLyDoiTuong.checkVaCham()) {
                mainTinhToanGame.phatAmThanhGaChet();
                trangThaiGame = trangThaiGameOver;
                mainTinhToanGame.gaChet(true);
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.decode("#94cbff"));
        g.fillRect(0, 0, getWidth(), getHeight());

        switch (trangThaiGame) {
            case trangThaiGameBatDau:
                g.drawImage(hinhBatDauGame, 200, 30, null);
                g.drawImage(hinhThanhVien, 10, 200, null);

                mainTinhToanGame.drawTrangThaiConGa(g);
                break;
            case trangThaiGameDangChoi:
            case trangThaiGameOver:
                damMay.drawDamMay(g);
                matDat.drawMatDat(g);
                quanLyDoiTuong.drawDoiTuong(g);
                mainTinhToanGame.drawTrangThaiConGa(g);
                g.setColor(Color.red);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

                //Điểm 
                g.drawString("Điểm : " + mainTinhToanGame.diem, 10, 30);

                if (trangThaiGame == trangThaiGameOver) {
                    g.drawImage(hinhChuGameOver, 200, 30, null);
                    g.drawImage(hinhNutReplay, 283, 50, null);
                    g.setColor(Color.orange);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                    g.drawString("ĐIỂM ĐẠT ĐƯỢC : " + mainTinhToanGame.diem, 150, 130);
                }
                break;
        }
    }

    @Override
    public void run() {
        int khungHinhTrenGiay = 100;
        double miliGiayTren1KhungHinh = 1000 * 1000000 / khungHinhTrenGiay;
        double thoiDiemCuoiCung = 0;
        double thoiDiemTroiQua;

        int miliGiayThoiGianNguChoThread;
        int themKhoangNano;

        double ketThuc;
        double doTre = 0;

        while (true) {
            thayDoiTrangThaiGame();
            repaint();
            ketThuc = System.nanoTime();
            thoiDiemTroiQua = (thoiDiemCuoiCung + miliGiayTren1KhungHinh - System.nanoTime());
            miliGiayThoiGianNguChoThread = (int) (thoiDiemTroiQua / 1000000);
            themKhoangNano = (int) (thoiDiemTroiQua % 1000000);
            if (miliGiayThoiGianNguChoThread <= 0) {
                thoiDiemCuoiCung = System.nanoTime();
                continue;
            }
            try {
                Thread.sleep(miliGiayThoiGianNguChoThread, themKhoangNano);
            } catch (InterruptedException e) {
                System.out.println("khung hình bị gián đoạn");
                e.printStackTrace();
            }
            thoiDiemCuoiCung = System.nanoTime();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        checkNhanNut = false;
        if (trangThaiGame == trangThaiGameDangChoi) {
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!checkNhanNut) {
            checkNhanNut = true;
            switch (trangThaiGame) {
                case trangThaiGameBatDau:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        trangThaiGame = trangThaiGameDangChoi;
                    }
                    break;
                case trangThaiGameDangChoi:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        mainTinhToanGame.gaNhay();
                    }
                    break;
                case trangThaiGameOver:
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        trangThaiGame = trangThaiGameDangChoi;
                        resetGame();
                    }
                    break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void resetGame() {
        mainTinhToanGame.diem = 0;
        quanLyDoiTuong.resetDoiTuong();
        mainTinhToanGame.gaChet(false);
        mainTinhToanGame.reset();
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

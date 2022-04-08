/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChickenDance;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author TuanLE
 */
public abstract class DoiTuong {

    public abstract void capNhatViTriDoiTuong();

    public abstract void drawDoiTuong(Graphics g);

    public abstract Rectangle getGioiHan();

    public abstract boolean outKhoiManHinh();
}
